package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import longND.fpt.home.data.dto.BookDto;
import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.OrderDto;
import longND.fpt.home.data.dto.OrderItemBorrowDto;
import longND.fpt.home.data.dto.OrderItemDto;
import longND.fpt.home.data.dto.UserDto;
import longND.fpt.home.data.dto.ViewBookBorrowDto;
import longND.fpt.home.data.dto.ViewRankingBookDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Favorite;
import longND.fpt.home.data.modal.Order;
import longND.fpt.home.data.modal.OrderItem;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.FavoriteRepository;
import longND.fpt.home.data.repository.OrderItemRepository;
import longND.fpt.home.data.repository.OrderRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.ExtendABookRequest;
import longND.fpt.home.presentation.payload.request.FilterDateRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.OrderItemService;
import longND.fpt.home.util.JavaMail;
import longND.fpt.home.util.MapperObject;
import longND.fpt.home.util.SecurityUtils;
import longND.fpt.home.util.Util;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
	private JavaMail javaMail;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> createOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thêm mới order thành công", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> getHistory(int indexPage) {

		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new AuthException("Quý khách chưa đăng nhập để lấy thông tin danh sách!");
		}

		int size = 8;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		if (!Objects.isNull(user)) {
			Page<OrderItem> listOrderItem = null;
			List<OrderItemBorrowDto> listDto = new ArrayList<>();
			if (user.getRoles().getName().equals("ROLE_USER")) {
				listOrderItem = orderItemRepository.getAllOrderItemByUserId(user.getId(), pageable);
			} else {
				listOrderItem = orderItemRepository.getAllOrderItemByEmployee(pageable);
			}

			for (OrderItem item : listOrderItem) {

				OrderItemBorrowDto oIDto = new OrderItemBorrowDto();
				oIDto.setOrderItemId(item.getId());
				ViewBookBorrowDto mapper = new ViewBookBorrowDto();
				oIDto.setBookdto(mapper.convertToBookDto(item.getBook()));
				oIDto.setCheckoutDate(item.getCheckoutDate());
				oIDto.setReturnDate(item.getReturnDate());
				oIDto.setDiscountedPrice(item.getDiscountedPrice());
				oIDto.setQuantity(item.getQuantity());
				oIDto.setPrice(item.getPrice());
				oIDto.setReturnEd(item.isReturn());
				oIDto.setCodeOrder(item.getCodeOrder());
				oIDto.setPayed(item.isPayed());
				listDto.add(oIDto);
			}

			CustomPage<OrderItemBorrowDto> pageResponse = new CustomPage<>(listDto, indexPage, size,
					listOrderItem.getTotalElements(), listOrderItem.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ObjectResponse("Lịch sử sách đã mượn", new HashMap<>() {
						{
							put("borrowedList", pageResponse);
						}
					}));
		}
		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> extendOneBook(ExtendABookRequest extendABookRequest) {
		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new AuthException("Quý khách chưa đăng nhập để lấy thông tin danh sách!");
		}

		if (extendABookRequest.getOrderItemId() == null) {
			throw new NotFoundException("Không tìm thấy orderItemId");
		}
		if (extendABookRequest.getReturnDate().isBlank() || extendABookRequest.getReturnDate().isEmpty()) {
			throw new NotFoundException("Hãy chọn ngày trả sách");
		}
		Long orderItemId = extendABookRequest.getOrderItemId();

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		if (!Objects.isNull(user)) {
			OrderItem orderItem = orderItemRepository.findOrderItemByUserIdAndOrderItemId(user.getId(), orderItemId);
			if (Objects.isNull(orderItem)) {
				throw new NotFoundException("Không tìm thấy id của sách đã mượn");
			}
			int extendBook = orderItem.getIsExtend();
			if (extendBook == 3) {
				throw new BadRequestAlertException("Bạn đã đạt giới hạn gia hạn thêm");
			}

			boolean isCheckDate = Util.checkReturnDate(
					Util.convertStringToLocalDateTime(extendABookRequest.getReturnDate()), orderItem.getReturnDate());

			if (isCheckDate == false) {
				throw new BadRequestAlertException("Bạn chỉ được gia hạn một cuốn sách trong vòng 7 ngày trở lại");
			}

			LocalDateTime newReturnDate = Util.convertStringToLocalDateTime(extendABookRequest.getReturnDate());
			orderItem.setReturnDate(newReturnDate);
			orderItem.setIsExtend(extendBook + 1);

			LocalDateTime oldDateReturn = orderItem.getOldReturnDate();
			long daysBetween = ChronoUnit.DAYS.between(oldDateReturn, newReturnDate);
			double priceOfBook = orderItem.getBook().getPrice();
			double newPricePay = priceOfBook * daysBetween;
			orderItem.setPrice(newPricePay);
			orderItem.setPayed(false);

			Order orderFind = orderRepository.getById(orderItem.getOrder().getId());
			double oldTotalPrice = orderFind.getTotalPrice();
			orderFind.setTotalPrice(oldTotalPrice + newPricePay);
			orderRepository.save(orderFind);

			orderItemRepository.save(orderItem);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Đã gia hạn mượn sách thành công", 200));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiResponse("Gia hạn mượn sách không thành công", 404));
	}

	@Override
	public ResponseEntity<ObjectResponse> Top10BookFilter(FilterDateRequest filterDateRequest) {
		if (filterDateRequest.getCheckoutDate().isBlank() || filterDateRequest.getCheckoutDate().isEmpty()) {
			throw new BadRequestAlertException("Hãy chọn ngày trong tháng");
		}
		if (filterDateRequest.getReturnDate().isBlank() || filterDateRequest.getReturnDate().isEmpty()) {
			throw new BadRequestAlertException("Hãy chọn ngày trong tháng");
		}
		long daysBetween = ChronoUnit.DAYS.between(Util.convertStringToLocalDate(filterDateRequest.getCheckoutDate()),
				Util.convertStringToLocalDate(filterDateRequest.getReturnDate()));

		if (daysBetween <= 0) {
			throw new BadRequestAlertException("Hãy chọn lại ngày trong tháng");
		}

		List<OrderItem> list = orderItemRepository.getTop10BookFilterDate(filterDateRequest.getCheckoutDate(),
				filterDateRequest.getReturnDate());
		List<ViewRankingBookDto> listDto = new ArrayList<>();

		Long temp = (long) 0;
		int count = 0;
		for (OrderItem item : list) {
			if (temp != item.getBook().getId() && count <= 10) {
				temp = item.getBook().getId();
				ViewRankingBookDto dto = new ViewRankingBookDto();
				int totalQuantity = 0;
				double totalPrice = 0;
				for (OrderItem item1 : list) {
					if (temp == item1.getBook().getId()) {
						totalQuantity += item1.getQuantity();
						totalPrice += item1.getPrice();
					}

				}
				dto.setId(item.getBook().getId());
				dto.setTitle(item.getBook().getTitle());
				dto.setImageUrl(item.getBook().getImageUrl());
				dto.setQuantity(totalQuantity);
				dto.setTotalPrice(totalPrice);
				listDto.add(dto);
				count++;
			}
		}

		Collections.sort(listDto, new ObjectCompare().reversed());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Ranking book", new HashMap<>() {
			{
				put("bookRankingList", listDto);
			}
		}));
	}

	@Override
	public ResponseEntity<ApiResponse> returnBook(Long orderItemId) {
		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new NotFoundException("Người dùng không tồn tại");
		}
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy id của orderItem"));

		Book book = bookRepository.findById(orderItem.getBook().getId())
				.orElseThrow(() -> new NotFoundException("Không tìm thấy id của sách"));

		Favorite favorite = favoriteRepository.findFavoriteByUserId(user.getId(), book.getId());
		if (!Objects.isNull(favorite) && book.getCopies_available() == 0) {
			String text = "Xin chào " + user.getFirstName() + " " + user.getLastName() + "! \n\n"
					+ "Lms gửi mail này để báo cho bạn là cuốn sách: \n" + book.getTitle()
					+ " đã có trong Lms. Bạn hãy nhanh tay đặt mượn kẻo lỡ mất cuốn sách yêu thích của bạn.\n"
					+ "Lms cảm ơn bạn đã dùng dịch vụ của hệ thống. Mọi thắc mắc bạn có thể liên hệ đến : longndhe140970@fpt.edu.vn. \n";
			javaMail.sentEmail(user.getEmail(), "LMS - Thông báo sách yêu thích!!!!!", text);

		}

		book.setCopies(book.getCopies() - orderItem.getQuantity());
		book.setCopies_available(book.getCopies_available() + orderItem.getQuantity());

		orderItem.setReturn(true);
		orderItem.setPayed(true);
		orderItem.setEmployee(user);
		orderItemRepository.save(orderItem);
		bookRepository.save(book);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Chấp nhận trả sách thành công!", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> findOrderItemByCode(SearchRequest searchRequest, int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<OrderItem> listOrderItem = orderItemRepository.searchByCode(searchRequest.getSearchText(), pageable);

		List<OrderItemDto> listOrderIDto = new ArrayList<>();

		for (OrderItem item : listOrderItem) {

			OrderItemDto orderIDto = new OrderItemDto();
			orderIDto.setOrderItemId(item.getId());
			orderIDto.setOrderId(item.getOrder().getId());
			MapperObject mapper = new MapperObject(modelMapper);
			orderIDto.setBookdto(mapper.convertToBookDto(item.getBook()));
			orderIDto.setCheckoutDate(item.getCheckoutDate());
			orderIDto.setReturnDate(item.getReturnDate());
			orderIDto.setQuantity(item.getQuantity());
			orderIDto.setPrice(item.getPrice());
			orderIDto.setDiscountedPrice(item.getDiscountedPrice());
			orderIDto.setReturnEd(item.isReturn());
			orderIDto.setUserDto(UserDto.convertToUserDto(item.getUser()));
			if (!Objects.isNull(item.getEmployee())) {
				orderIDto.setEmployeeDto(UserDto.convertToUserDto(item.getEmployee()));
			}

			orderIDto.setCodeOrder(item.getCodeOrder());
			orderIDto.setPayed(item.isPayed());
			listOrderIDto.add(orderIDto);
		}

		CustomPage<OrderItemDto> pageResponse = new CustomPage<>(listOrderIDto, indexPage, size,
				listOrderItem.getTotalElements(), listOrderItem.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

}

class ObjectCompare implements Comparator<ViewRankingBookDto> {

	@Override
	public int compare(ViewRankingBookDto o1, ViewRankingBookDto o2) {

		return o1.getQuantity() - o2.getQuantity();
	}

}