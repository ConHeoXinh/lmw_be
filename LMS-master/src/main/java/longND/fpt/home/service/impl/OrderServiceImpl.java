package longND.fpt.home.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import org.springframework.util.ObjectUtils;

import longND.fpt.home.data.dto.BookBorrowDto;
import longND.fpt.home.data.dto.CartDto;
import longND.fpt.home.data.dto.CartItemDto;
import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.OrderDto;
import longND.fpt.home.data.dto.OrderItemDto;
import longND.fpt.home.data.dto.UserDto;
import longND.fpt.home.data.dto.ViewDashboardDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Cart;
import longND.fpt.home.data.modal.CartItem;
import longND.fpt.home.data.modal.Order;
import longND.fpt.home.data.modal.OrderItem;
import longND.fpt.home.data.modal.Role;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.modal.Voucher;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.CartRepository;
import longND.fpt.home.data.repository.OrderItemRepository;
import longND.fpt.home.data.repository.OrderRepository;
import longND.fpt.home.data.repository.RoleRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.data.repository.VoucherRepository;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.exception.SaveDataException;
import longND.fpt.home.presentation.payload.request.CreateOrderRequest;
import longND.fpt.home.presentation.payload.request.ExtendBookRequest;
import longND.fpt.home.presentation.payload.request.FilterDateRequest;
import longND.fpt.home.presentation.payload.request.OrderItemRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.CartItemService;
import longND.fpt.home.service.CartService;
import longND.fpt.home.service.OrderService;
import longND.fpt.home.util.JavaMail;
import longND.fpt.home.util.MapperObject;
import longND.fpt.home.util.SecurityUtils;
import longND.fpt.home.util.Util;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JavaMail javaMail;

	@Override
	public ResponseEntity<ApiResponse> createOrder(CreateOrderRequest orderRequest) {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		// create order by employee

		Role role = roleRepository.findRoleByUserId(SecurityUtils.getPrincipal().getId());
		// case: employee create order for guest
		if (role.getName().equals("ROLE_EMPLOYEE")) {
			// create new user with role guest
			User guest = new User();
			guest.setFirstName(orderRequest.getFirstName());

			if (orderRequest.getLastName().isBlank() || orderRequest.getLastName().isEmpty()) {
				throw new SaveDataException("Hãy nhập tên khách hàng");
			}
			guest.setLastName(orderRequest.getLastName());

			if (orderRequest.getEmail().isBlank() || orderRequest.getEmail().isEmpty()) {
				throw new SaveDataException("Hãy nhập Email khách hàng");
			}
			guest.setEmail(orderRequest.getEmail());

			if (orderRequest.getPhoneNumber().isBlank() || orderRequest.getPhoneNumber().isEmpty()) {
				throw new SaveDataException("Hãy nhập số điện thoại khách hàng");
			}

			guest.setPhoneNumber(orderRequest.getPhoneNumber());
			Role guestRole = roleRepository.findByName("ROLE_GUEST")
					.orElseThrow(() -> new NotFoundException("Không tìm thấy role là guest"));
//			user.setUserStatus(false);
			guest.setRoles(guestRole);
			Long idGuest = (long) userRepository.totalAccount() + 1;
			String usernameForGuest = "guest-" + idGuest;
			guest.setUsername(usernameForGuest);
			guest.setPassword("123@123");
			guest.setCreateAt(LocalDateTime.now());

			// check guest existed
			User userFind = userRepository.getUserByEmail(orderRequest.getEmail());

			User savedGuest = new User();
			if (Objects.isNull(userFind)) {
				savedGuest = userRepository.save(guest);
			}

			// resolve choose book
			if (orderRequest.getListOrderItem().size() == 0) {
				throw new SaveDataException("Hãy chọn sách cho khách hàng");
			}

			// code
			Order order = new Order();

			order.setCreatedAt(LocalDateTime.now());
			order.setOrderStatus(true);
			order.setEmployee(user);
			order.setReturnOrder(false);
			order.setUser(savedGuest);

			if (!Objects.isNull(userFind)) {
				order.setUser(userFind);
			}
			Order savedOrder = orderRepository.save(order);

			List<OrderItemRequest> listOIRequest = orderRequest.getListOrderItem();
			double totalPrice = 0;
			int totalItem = 0;
			List<OrderItem> listOI = new ArrayList<>();

			String code = Util.genCode(12);
			if (orderRepository.existsByCode(code)) {
				code = Util.genCode(12);
			}

			// resolve ever item in request
			for (OrderItemRequest itemRequest : listOIRequest) {
				Book book = bookRepository.findById(itemRequest.getBookId())
						.orElseThrow(() -> new NotFoundException("Không tìm thấy id của sách"));

				OrderItem orderItem = new OrderItem();

				LocalDateTime checkoutDate = null;
				LocalDateTime returnDate = null;
				if (itemRequest.getCheckoutDate().isBlank() || itemRequest.getCheckoutDate().isEmpty()) {
					throw new SaveDataException("Hãy chọn ngày mượn sách");
				}
				checkoutDate = Util.convertStringToLocalDateTime(itemRequest.getCheckoutDate());

				if (itemRequest.getReturnDate().isBlank() || itemRequest.getReturnDate().isEmpty()) {
					throw new SaveDataException("Hãy chọn ngày trả sách");
				}
				returnDate = Util.convertStringToLocalDateTime(itemRequest.getReturnDate());

				Double price = book.getPrice() * itemRequest.getQuantity();

				// To charge when have 2 date
				if (returnDate != null && checkoutDate != null) {
					long daysBetween = ChronoUnit.DAYS.between(checkoutDate, returnDate);
					LocalDateTime createNow = LocalDateTime.now();
					long dayWithNow = ChronoUnit.DAYS.between(createNow, checkoutDate);
					if (daysBetween <= 0 || daysBetween > 14) {
						throw new SaveDataException("Hãy chọn lại ngày");
					} else if (dayWithNow < 0 || dayWithNow >= 1) {
						throw new SaveDataException("Hãy chọn lại ngày mượn");
					}
					price = price * (int) daysBetween;
				}

				totalPrice += price;

				orderItem.setPrice(price);
				orderItem.setDiscountedPrice(0);
				orderItem.setQuantity(itemRequest.getQuantity());

				totalItem += itemRequest.getQuantity();

				orderItem.setBook(book);
				orderItem.setEmployee(user);
				orderItem.setOrder(savedOrder);
				orderItem.setUser(savedGuest);
				orderItem.setVoucher(null);
				orderItem.setCheckoutDate(checkoutDate);
				orderItem.setReturnDate(returnDate);
				orderItem.setOldReturnDate(returnDate);
				orderItem.setSendDeadline(false);
				orderItem.setIsExtend(0);
				orderItem.setPayed(true);
				orderItem.setCodeOrder(code);

				if (!Objects.isNull(userFind)) {
					orderItem.setUser(userFind);
				}

				orderItemRepository.save(orderItem);

				listOI.add(orderItem);

				// update copies in library
				book.setCopies(book.getCopies() + itemRequest.getQuantity());
				book.setCopies_available(book.getCopies_available() - itemRequest.getQuantity());

				bookRepository.save(book);
			}

			Order findOrderSaved = orderRepository.findById(savedOrder.getId())
					.orElseThrow(() -> new NotFoundException("Không tìm thấy order đã lưu"));

			// update order
			findOrderSaved.setDiscounte(0);
			findOrderSaved.setTotalDiscountedPrice(totalPrice);
			findOrderSaved.setTotalItem(totalItem);
			findOrderSaved.setTotalPrice(totalPrice);

			findOrderSaved.setCode(code);
			orderRepository.save(findOrderSaved);

			String fullName = guest.getFirstName() + " " + guest.getLastName();
			String subject = "Thông báo gửi đơn mượn sách thành công!";
			String text = "Chào " + fullName + ",\n" + "Cảm ơn bạn đã mượn sách tại thư viện online. \n"
					+ "Dưới đây là mã đơn mượn của bạn:" + "\n" + code + "\n"
					+ "Mã sẽ được dùng để xác nhận cho việc đến trả sách."
					+ "\nXin vui lòng quý khách không cung cấp cho người lạ!";

			javaMail.sentEmail(orderRequest.getEmail(), subject, text);

			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Tạo đơn mượn thành công", 200));
		}

		if (role.getName().equals("ROLE_USER")) {

			// create order with user
			Cart cart = transferDataCart(cartService.findUserCart(user.getId()));

			// check total order this today, total order in on day is 3
			List<Order> checkOrder = orderRepository.findByUserId(user.getId());
			String dateNow = Util.formatLocalDateTime(LocalDateTime.now());
			int count = 0;
			for (Order item : checkOrder) {
				String dateCreate = Util.formatLocalDateTime(item.getCreatedAt());
				if (dateNow.equals(dateCreate)) {
					count++;
				}
				if (count == 4) {
					throw new BadRequestAlertException("Số hạn đơn của bạn đã đạt tối đa");
				}
			}

			List<OrderItem> orderItems = new ArrayList<>();

			List<CartItem> cartTemp = cart.getCartItems();
			List<OrderItemRequest> listOrderRequestTemp = orderRequest.getListOrderItem();
			Voucher voucher = new Voucher();
			for (int i = 0; i < cartTemp.size(); i++) {
				OrderItem orderItem = new OrderItem();

				orderItem.setBook(cartTemp.get(i).getBook());
				orderItem.setQuantity(cartTemp.get(i).getQuantity());
				orderItem.setDiscountedPrice(cartTemp.get(i).getDiscountedPrice());
				orderItem.setUser(cartTemp.get(i).getUser());
				orderItem.setVoucher(cartTemp.get(i).getVoucher());

				LocalDateTime checkoutDate = null;
				LocalDateTime returnDate = null;
				if (listOrderRequestTemp.get(i).getCheckoutDate().isBlank()
						|| listOrderRequestTemp.get(i).getCheckoutDate().isEmpty()) {
					throw new SaveDataException("Hãy chọn ngày mượn sách");
				}
				checkoutDate = Util.convertStringToLocalDateTime(listOrderRequestTemp.get(i).getCheckoutDate());

				if (listOrderRequestTemp.get(i).getCheckoutDate().isBlank()
						|| listOrderRequestTemp.get(i).getCheckoutDate().isEmpty()) {
					throw new SaveDataException("Hãy chọn ngày trả sách");
				}
				returnDate = Util.convertStringToLocalDateTime(listOrderRequestTemp.get(i).getReturnDate());

				long daysDifference = ChronoUnit.DAYS.between(checkoutDate, returnDate);

				if (daysDifference > 14 || daysDifference <= 0) {
					throw new BadRequestAlertException("Bạn hãy chọn lại ngày");
				}

				LocalDateTime createNow = LocalDateTime.now();
				long dayWithNow = ChronoUnit.DAYS.between(createNow, checkoutDate);
				if (dayWithNow < 0 || dayWithNow >= 1) {
					throw new SaveDataException("Hãy chọn lại ngày mượn");
				}

				orderItem.setCheckoutDate(checkoutDate);
				orderItem.setReturnDate(returnDate);
				orderItem.setOldReturnDate(returnDate);

				Double price = cartTemp.get(i).getPrice();
				Double resultPrice = (double) 0;

//				//System.err.println("price no voucher"+ price);
//				// To charge when have 2 date
//				if (returnDate != null && checkoutDate != null) {
//					long daysBetween = ChronoUnit.DAYS.between(checkoutDate, returnDate);
//					System.err.println("day" + (int) daysBetween);
//					price = price * (int) daysBetween;
//				}

				// check add voucher
				if (!listOrderRequestTemp.get(i).getCodeVoucher().isBlank()
						|| !listOrderRequestTemp.get(i).getCodeVoucher().isEmpty()) {
					String codeVoucher = listOrderRequestTemp.get(i).getCodeVoucher();
					voucher = voucherRepository.getVoucherByCode(codeVoucher);
					// check expire voucher
					long daysBetween = ChronoUnit.DAYS.between(voucher.getStartDay(),
							Util.convertStringToLocalDate(listOrderRequestTemp.get(i).getCheckoutDate()));
					long dueday = (long) voucher.getDueDay();
					if ((daysBetween < dueday) && (daysBetween >= 0)) {
						resultPrice = price - price * (((double) voucher.getPercent()) / 100);
					}
				} else {
					resultPrice = price;
				}

				orderItem.setPrice(resultPrice);
				orderItem.setIsExtend(0);
				orderItem.setSendDeadline(false);

				OrderItem createdOrderItem = orderItemRepository.save(orderItem);

				orderItems.add(createdOrderItem);
			}

			Order createdOrder = new Order();
			createdOrder.setUser(user);
			createdOrder.setOrderItems(orderItems);

			Double totalPrice = (double) 0;
			Double totalDiscountePrice = (double) 0;
			for (OrderItem item : orderItems) {
				totalPrice += item.getPrice();
				totalDiscountePrice += item.getDiscountedPrice();
			}

			createdOrder.setTotalPrice(totalPrice);
			createdOrder.setDiscounte(totalDiscountePrice);

			createdOrder.setOrderStatus(false);
			createdOrder.setTotalItem(cart.getTotalItem());
			createdOrder.setCreatedAt(LocalDateTime.now());
			createdOrder.setReturnOrder(false);

			String code = Util.genCode(12);
			if (orderRepository.existsByCode(code)) {
				code = Util.genCode(12);
			}
			createdOrder.setCode(code);

			cart.setOrdered(!cart.isOrdered());
			cartRepository.save(cart);
			Order savedOrder = orderRepository.save(createdOrder);

			for (OrderItem item : orderItems) {
				item.setOrder(savedOrder);
				OrderItem savedOrderItem = orderItemRepository.findById(item.getId())
						.orElseThrow(() -> new NotFoundException("Không tìm thấy sách trong giỏ hàng"));
				if (!Objects.isNull(savedOrderItem)) {
					savedOrderItem.setCheckoutDate(item.getCheckoutDate());
					savedOrderItem.setReturnDate(item.getReturnDate());
					savedOrderItem.setCodeOrder(code);
					savedOrderItem.setPayed(false);
					orderItemRepository.save(savedOrderItem);
				}
			}

			voucher.setStatus(0);
			voucherRepository.save(voucher);

			String fullName = user.getFirstName() + " " + user.getLastName();
			String subject = "Thông báo mượn sách thành công!";
			String text = "Chào " + fullName + ",\n" + "Cảm ơn bạn đã mượn sách tại thư viện online. \n"
					+ "Dưới đây là mã đơn mượn của bạn: \n" + code
					+ "\nMã sẽ được dùng để xác nhận cho việc đến nhận sách mượn."
					+ " Xin vui lòng quý khách không cung cấp cho người lạ!";

			javaMail.sentEmail(user.getEmail(), subject, text);

			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Tạo đơn mượn thành công", 200));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Tạo đơn mượn lỗi", 403));
	}

	@Override
	public ResponseEntity<ObjectResponse> findOrderByCode(SearchRequest searchRequest, int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Order> listOrder = orderRepository.searchByCode(searchRequest.getSearchText(), pageable);

		List<OrderDto> listOrderDto = new ArrayList<>();

		for (Order item : listOrder) {

			OrderDto orderDto = new OrderDto();
			orderDto.setOrderId(item.getId());
			orderDto.setTotalPrice(item.getTotalPrice());
			orderDto.setTotalDiscountedPrice(item.getTotalDiscountedPrice());
			orderDto.setDiscounte(item.getDiscounte());
			orderDto.setOrderStatus(item.isOrderStatus());
			orderDto.setTotalItem(item.getTotalItem());
			orderDto.setUserDto(UserDto.convertToUserDto(item.getUser()));
			if (!Objects.isNull(item.getEmployee())) {
				orderDto.setEmployeeDto(UserDto.convertToUserDto(item.getEmployee()));
			}
			orderDto.setCreateAt(item.getCreatedAt());
			orderDto.setCode(item.getCode());

			listOrderDto.add(orderDto);
		}

		CustomPage<OrderDto> pageResponse = new CustomPage<>(listOrderDto, indexPage, size,
				listOrder.getTotalElements(), listOrder.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrdersByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> usersOrderHistory(int indexPage) {

		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrderExpire(int indexPage) {

		return null;
	}

	@Override
	public ResponseEntity<ApiResponse> confirmedOrder(Long orderId) {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		Order order = orderRepository.getById(orderId);

		if (Objects.isNull(order)) {
			throw new NotFoundException("không tìm thấy order id");
		} else {
			order.setEmployee(user);

			List<OrderItem> orderItems = orderItemRepository.findAllOrderItemsByOrderId(orderId);

			for (OrderItem item : orderItems) {
				item.setEmployee(user);
				item.setPayed(true);
				orderItemRepository.save(item);
				Book book = bookRepository.findById(item.getBook().getId())
						.orElseThrow(() -> new NotFoundException("Không tìm thấy sách"));
				int quantity = item.getQuantity();
				book.setCopies(book.getCopies() + quantity);
				book.setCopies_available(book.getCopies_available() - quantity);

				bookRepository.save(book);
			}
			order.setOrderStatus(true);

			orderRepository.save(order);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Chấp nhận order thành công", 200));
	}

	@Override
	public ResponseEntity<ApiResponse> cancledOrder(Long orderId) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		Order order = orderRepository.getById(orderId);

		if (Objects.isNull(order)) {
			throw new NotFoundException("Không tìm thấy order");
		} else {
			order.setEmployee(user);
			List<OrderItem> orderItems = orderItemRepository.findAllOrderItemsByOrderId(orderId);

			for (OrderItem item : orderItems) {
				item.setEmployee(user);
				item.setPayed(false);
				orderItemRepository.save(item);
			}
			order.setReturnOrder(true);
			order.setOrderStatus(false);
		}
		orderRepository.save(order);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Từ chối order thành công", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllOrders(int indexPage) {
		int sizeItemOfPage = 4;
		int page = indexPage - 1;
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (!user.getRoles().getName().equals("ROLE_EMPLOYEE")) {
			throw new AuthException("Truy cập bị từ chối");
		} else {

			Pageable pageable = PageRequest.of(page, sizeItemOfPage);

			Page<Order> orders = orderRepository.findAllOrders(pageable);

			List<OrderDto> orderDtoList = new ArrayList<>();

			if (orders.getTotalElements() == 0) {
				throw new NotFoundException("Không tìm thấy order");
			} else {
				for (Order order : orders.getContent()) {
					OrderDto orderDto = new OrderDto();
					orderDto.setOrderId(order.getId());
					orderDto.setCode(order.getCode());
					orderDto.setTotalPrice(order.getTotalPrice());
					orderDto.setTotalDiscountedPrice(order.getTotalDiscountedPrice());
					orderDto.setDiscounte(order.getDiscounte());
					orderDto.setOrderStatus(order.isOrderStatus());
					orderDto.setTotalItem(order.getTotalItem());

					UserDto userDto = UserDto.convertToUserDto(order.getUser());
					orderDto.setUserDto(userDto);

					orderDtoList.add(orderDto);
				}
			}

			CustomPage<OrderDto> pageResponse = new CustomPage<>(orderDtoList, indexPage, sizeItemOfPage,
					orders.getTotalElements(), orders.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Danh sách order", new HashMap<>() {
				{
					put("searchList", pageResponse);
				}
			}));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getOrderDetail(Long orderId) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		if (!user.getRoles().getName().equals("ROLE_EMPLOYEE")) {
			throw new AuthException("Truy cập bị từ chối");
		} else {
			Order order = orderRepository.getById(orderId);
			if (Objects.isNull(order)) {
				throw new NotFoundException("Không tìm thấy order");
			} else {
				OrderDto orderDto = new OrderDto();
				orderDto.setOrderId(order.getId());

				orderDto.setCode(order.getCode());
				orderDto.setTotalPrice(order.getTotalPrice());
				orderDto.setTotalDiscountedPrice(order.getTotalDiscountedPrice());
				orderDto.setDiscounte(order.getDiscounte());
				orderDto.setOrderStatus(order.isOrderStatus());
				orderDto.setTotalItem(order.getTotalItem());

				UserDto userDto = UserDto.convertToUserDto(order.getUser());
				orderDto.setUserDto(userDto);
				orderDto.setCreateAt(order.getCreatedAt());
				List<OrderItem> list = orderItemRepository.findAllOrderItemsByOrderId(orderId);
				MapperObject mapper = new MapperObject(modelMapper);
				List<OrderItemDto> listDto = mapper.convertToOrderItemDto(list);

				return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Order", new HashMap<>() {
					{
						put("orderDetials", orderDto);
						put("orderItems", listDto);
					}
				}));
			}
		}
	}

	@Override
	public ResponseEntity<ApiResponse> deleteOrder(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<CartItem> transferDataCartItem(List<CartItemDto> cartItemDtos) {
		List<CartItem> cartItems = new ArrayList<>();
		for (CartItemDto cartItemDto : cartItemDtos) {
			CartItem cartItem = new CartItem();

			cartItem.setId(cartItemDto.getId());

			Cart cart = cartRepository.findById(cartItemDto.getCartId())
					.orElseThrow(() -> new NotFoundException("Không tìm thấy giỏ hàng"));

			cartItem.setCart(cart);
			Book book = bookRepository.getBookById(cartItemDto.getBookId());

			cartItem.setBook(book);

			cartItem.setCheckoutDate(cartItemDto.getCheckoutDate());
			cartItem.setReturnDate(cartItemDto.getReturnDate());

			cartItem.setUser(cart.getUser());

			if (!Objects.isNull(cartItemDto.getVoucherId())) {
				Voucher voucher = voucherRepository.findById(cartItemDto.getVoucherId())
						.orElseThrow(() -> new NotFoundException("Không tìm thấy voucher"));
				cartItem.setVoucher(voucher);
			}
			cartItem.setPrice(cartItemDto.getPrice());
			cartItem.setDiscountedPrice(cartItemDto.getDiscountedPrice());
			cartItem.setQuantity(cartItemDto.getQuantity());

			cartItems.add(cartItem);
		}
		return cartItems;
	}

	private Cart transferDataCart(CartDto cartDto) {
		Cart cart = new Cart();
		cart.setId(cartDto.getId());
		User user = userRepository.findUserById(cartDto.getUserId());
		cart.setUser(user);

		cart.setCartItems(transferDataCartItem(cartDto.getCartItemDtos()));
		cart.setTotalPrice(cartDto.getTotalPrice());
		cart.setTotalItem(cartDto.getTotalItem());
		cart.setTotalDiscountedPrice(cartDto.getTotalDiscountedPrice());
		cart.setDiscounte(cartDto.getDiscounte());

		return cart;
	}

	@Override
	public ResponseEntity<ApiResponse> extendOrder(ExtendBookRequest extendBookRequest) {
//		Order order = orderRepository.findById(extendBookRequest.getOrderId())
//				.orElseThrow(() -> new NotFoundException("không có order"));
//		order.setReturnDate(extendBookRequest.getReturnDate());
//		int currentExtend = order.getExtendOrder();
//		if (currentExtend >= 2) {
//			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("sách đã hết lượt gia hạn", 500));
//		}
//
//		order.setExtendOrder(currentExtend + 1);
//
//		orderRepository.save(order);
//
//		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("gia hạn sách thành công", 200));

		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllBookByUser(int indexPage) {
		Long userId = SecurityUtils.getPrincipal().getId();
		User user = userRepository.findUserById(userId);
		Page<OrderItem> orderList = null;

		int page = indexPage - 1;

		Pageable pageable = null;
		int size = 8;
		if (user.getRoles().getName().equals("ROLE_EMPLOYEE")) {
			size = 5;
			PageRequest.of(page, size);
			orderList = orderItemRepository.findAllByEmployee(user, pageable);
		} else {
			pageable = PageRequest.of(page, size);
			orderList = orderItemRepository.findAllByUser(user, pageable);
		}

		List<BookBorrowDto> listBookBorrowDto = new ArrayList<>();

		if (!ObjectUtils.isEmpty(orderList.getContent())) {
			for (OrderItem item : orderList) {
				BookBorrowDto bookBorrowDto = new BookBorrowDto();
				bookBorrowDto.setOrderId(item.getId());
				bookBorrowDto.setNameBook(item.getBook().getTitle());
				bookBorrowDto.setQuantity(item.getQuantity());
				bookBorrowDto.setUsername(user.getUsername());
				bookBorrowDto.setCheckoutDate(item.getCheckoutDate());
				bookBorrowDto.setReturnDate(item.getReturnDate());
				bookBorrowDto.setCodeOrder(item.getCodeOrder());
				bookBorrowDto.setPayed(item.isPayed());

				listBookBorrowDto.add(bookBorrowDto);
			}
		}

		CustomPage<BookBorrowDto> pageResponse = new CustomPage<>(listBookBorrowDto, indexPage, size,
				orderList.getTotalElements(), orderList.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Sách mượn", new HashMap<>() {
			{
				put("bookBorrow", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getDashboard(FilterDateRequest filterDateRequest) {
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

		List<Order> list = orderRepository.getAllOrderDashboard(filterDateRequest.getCheckoutDate(),
				filterDateRequest.getReturnDate());

		LocalDate oldDate = Util.convertStringToLocalDate(filterDateRequest.getCheckoutDate());
		LocalDate newDate = Util.convertStringToLocalDate(filterDateRequest.getCheckoutDate());
		long daysDifference = ChronoUnit.DAYS.between(oldDate, newDate);

		List<ViewDashboardDto> listDto = new ArrayList<>();
		if (daysDifference <= 365) {
			int tempMonth = 0;
			for (Order item : list) {
				int dateCreateAt = item.getCreatedAt().getMonthValue();
				if (tempMonth != dateCreateAt) {
					tempMonth = dateCreateAt;
					ViewDashboardDto dto = new ViewDashboardDto();
					int totalOrder = 0;
					double totalPrice = 0;
					for (Order item1 : list) {
						if (tempMonth == item1.getCreatedAt().getMonthValue()) {
							totalOrder += 1;
							// System.err.println(totalOrder);
							totalPrice += item1.getTotalPrice();
						}
					}
					dto.setMonth(tempMonth);
					dto.setYear(item.getCreatedAt().getYear());
					dto.setTotalOrder(totalOrder);
					dto.setTotalPrice(totalPrice);
					listDto.add(dto);
				}
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Doashboard", new HashMap<>() {
			{
				put("dashboardList", listDto);
			}
		}));
	}
}
