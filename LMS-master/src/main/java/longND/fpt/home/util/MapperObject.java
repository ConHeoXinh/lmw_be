package longND.fpt.home.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;

import longND.fpt.home.data.dto.AuthorDto;
import longND.fpt.home.data.dto.BookDto;
import longND.fpt.home.data.dto.DepartmentDto;
import longND.fpt.home.data.dto.OrderItemDto;
import longND.fpt.home.data.dto.PublisherDto;
import longND.fpt.home.data.dto.SearchDto;
import longND.fpt.home.data.dto.UserDto;
import longND.fpt.home.data.dto.VoucherDto;
import longND.fpt.home.data.modal.Author;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Department;
import longND.fpt.home.data.modal.OrderItem;
import longND.fpt.home.data.modal.Rating;


public class MapperObject {

	private ModelMapper modelMapper;

	public MapperObject(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	public SearchDto convertToSearchDto(Book book) {
		SearchDto searchDto = new SearchDto();

		List<AuthorDto> authorDtolist = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			authorDtolist.add(authorDto);
		}
		searchDto.setAuthorDtoList(authorDtolist);

		List<DepartmentDto> departmentDtolist = new ArrayList<>();
		for (Department department : book.getDepartments()) {
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			departmentDtolist.add(departmentDto);
		}
		searchDto.setDepartmentDtoList(departmentDtolist);

		PublisherDto publisherDto = modelMapper.map(book.getPublisher(), PublisherDto.class);

		searchDto.setPublisheDto(publisherDto);

		searchDto.setBookId(book.getId());
		searchDto.setTitle(book.getTitle());
		searchDto.setDesciption(book.getDescription());
		searchDto.setPrice(book.getPrice());
		searchDto.setImageUrl(book.getImageUrl());
		searchDto.setCopies(book.getCopies());
		searchDto.setCopies_available(book.getCopies_available());
		searchDto.setLanguage(book.getLanguage());

		return searchDto;
	}

	public BookDto convertToBookDto(Book book, Long userId) {
		BookDto bookDto = new BookDto();

		List<AuthorDto> authorDtolist = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			authorDtolist.add(authorDto);
		}
		bookDto.setAuthors(authorDtolist);

		List<DepartmentDto> departmentDtolist = new ArrayList<>();
		for (Department department : book.getDepartments()) {
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			departmentDtolist.add(departmentDto);
		}
		bookDto.setDepartments(departmentDtolist);

		PublisherDto publisherDto = modelMapper.map(book.getPublisher(), PublisherDto.class);

		bookDto.setPublisher(publisherDto);

		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		bookDto.setPrice(book.getPrice());
		bookDto.setImageUrl(book.getImageUrl());
		bookDto.setCreateAt(book.getCreateAt());
		bookDto.setCopies(book.getCopies());
		bookDto.setCopies_available(book.getCopies_available());
		bookDto.setLanguage(book.getLanguage());
		bookDto.setForUser(book.isForUser());
		bookDto.setPage(book.getPage());
		bookDto.setAuthors(authorDtolist);
		bookDto.setDepartments(departmentDtolist);
		bookDto.setPublisher(publisherDto);

		List<Rating> rate = book.getRatings();
		double totalRate = 0;
		for (Rating rating : rate) {
			totalRate += rating.getStars();
		}
		int totalSize = 0;
		double totalrate = 0;
		if (rate.size() > 0) {
			totalSize = rate.size();
			totalrate = totalRate / totalSize;
		}

		bookDto.setRate(totalrate);
		bookDto.setTotalRate(totalSize);

		return bookDto;
	}

	public BookDto convertToBookDto(Book book) {
		BookDto bookDto = new BookDto();

		List<AuthorDto> authorDtolist = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			authorDtolist.add(authorDto);
		}
		bookDto.setAuthors(authorDtolist);

		List<DepartmentDto> departmentDtolist = new ArrayList<>();
		for (Department department : book.getDepartments()) {
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			departmentDtolist.add(departmentDto);
		}
		bookDto.setDepartments(departmentDtolist);

		PublisherDto publisherDto = modelMapper.map(book.getPublisher(), PublisherDto.class);

		bookDto.setPublisher(publisherDto);

		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		bookDto.setPrice(book.getPrice());
		bookDto.setImageUrl(book.getImageUrl());
		bookDto.setCreateAt(book.getCreateAt());
		bookDto.setCopies(book.getCopies());
		bookDto.setCopies_available(book.getCopies_available());
		bookDto.setLanguage(book.getLanguage());
		bookDto.setForUser(book.isForUser());
		bookDto.setPage(book.getPage());
		bookDto.setAuthors(authorDtolist);
		bookDto.setDepartments(departmentDtolist);
		bookDto.setPublisher(publisherDto);
		return bookDto;
	}

	public SearchDto convertToSearchDtoV2(Book book) {
		SearchDto searchDto = new SearchDto();

		List<AuthorDto> authorDtolist = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
			authorDtolist.add(authorDto);
		}
		searchDto.setAuthorDtoList(authorDtolist);

		List<DepartmentDto> departmentDtolist = new ArrayList<>();
		for (Department department : book.getDepartments()) {
			DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
			departmentDtolist.add(departmentDto);
		}
		searchDto.setDepartmentDtoList(departmentDtolist);

		PublisherDto publisherDto = modelMapper.map(book.getPublisher(), PublisherDto.class);

		searchDto.setPublisheDto(publisherDto);

		searchDto.setBookId(book.getId());
		searchDto.setTitle(book.getTitle());
		searchDto.setDesciption(book.getDescription());
		searchDto.setPrice(book.getPrice());
		searchDto.setImageUrl(book.getImageUrl());
		searchDto.setCopies(book.getCopies());
		searchDto.setCopies_available(book.getCopies_available());
		searchDto.setLanguage(book.getLanguage());

		List<Rating> rate = book.getRatings();
		double totalRate = 0;
		for (Rating rating : rate) {
			totalRate += rating.getStars();
		}
		int totalSize = 0;
		double totalrate = 0;
		if (rate.size() > 0) {
			totalSize = rate.size();
			totalrate = totalRate / totalSize;
		}

		searchDto.setRate(totalrate);

		return searchDto;
	}
	
	public List<OrderItemDto> convertToOrderItemDto(List<OrderItem> orderItems) {
		List<OrderItemDto> list = new ArrayList<>();
		for (OrderItem item : orderItems) {
			OrderItemDto orderItemDto = new OrderItemDto();

			orderItemDto.setOrderId(item.getOrder().getId());
			orderItemDto.setOrderItemId(item.getId());

			BookDto bookDto = new BookDto();
			MapperObject mapper = new MapperObject(modelMapper);
			bookDto = mapper.convertToBookDto(item.getBook());

			orderItemDto.setCheckoutDate(item.getCheckoutDate());
			orderItemDto.setReturnDate(item.getReturnDate());
			orderItemDto.setCodeOrder(item.getCodeOrder());
			orderItemDto.setPayed(item.isPayed());
			orderItemDto.setBookdto(bookDto);
			orderItemDto.setQuantity(item.getQuantity());
			orderItemDto.setPrice(item.getPrice());
			orderItemDto.setDiscountedPrice(item.getDiscountedPrice());

//			UserDto employeeDto = convertToUserDto(item.getEmployee());
//			orderItemDto.setEmployeeDto(employeeDto);

			UserDto userDto = UserDto.convertToUserDto(item.getUser());
			orderItemDto.setUserDto(userDto);

			VoucherDto voucherDto = new VoucherDto();

			if (!Objects.isNull(item.getVoucher())) {

				voucherDto.setIdVoucher(item.getVoucher().getId());
				voucherDto.setNameVoucher(item.getVoucher().getCode());
				voucherDto.setDescription(item.getVoucher().getDescription());
				voucherDto.setPercent(item.getVoucher().getPercent());
				voucherDto.setDueDate(item.getVoucher().getDueDay());
				voucherDto.setStatus(item.getVoucher().getStatus());
				voucherDto.setUserId(item.getVoucher().getUser().getId());

				orderItemDto.setVoucherDto(voucherDto);
			}

			list.add(orderItemDto);
		}
		return list;
	}

}
