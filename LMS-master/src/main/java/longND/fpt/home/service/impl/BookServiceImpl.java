package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.BookDto;
import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.SearchDto;
import longND.fpt.home.data.dto.ViewBookAuthorDto;
import longND.fpt.home.data.dto.ViewBookDto;
import longND.fpt.home.data.modal.Author;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Department;
import longND.fpt.home.data.modal.Favorite;
import longND.fpt.home.data.modal.Publisher;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.AuthorRepository;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.DepartmentRepository;
import longND.fpt.home.data.repository.FavoriteRepository;
import longND.fpt.home.data.repository.PublisherRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.exception.SaveDataException;
import longND.fpt.home.presentation.payload.request.BookRequest;
import longND.fpt.home.presentation.payload.request.EditBookRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.BookService;
import longND.fpt.home.service.VoucherService;
import longND.fpt.home.util.MapperObject;
import longND.fpt.home.util.SecurityUtils;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ApiResponse> addBook(BookRequest bookRequest) {


		if (bookRepository.existsByTitle(bookRequest.getTitle())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên sách đã tồn tại");
		} else {
			if (bookRequest.getTitle().isBlank() || bookRequest.getTitle().isEmpty()) {
				throw new SaveDataException("Hãy nhập tên sách");
			}

			if (bookRequest.getDescription().isBlank() || bookRequest.getDescription().isEmpty()) {
				throw new SaveDataException("Hãy nhập mô tả sách");
			}

			if (bookRequest.getPrice() < 1000) {
				throw new SaveDataException("Hãy nhập giá sách lớn hơn 1000");
			}

			if (bookRequest.getImageUrl().isBlank() || bookRequest.getImageUrl().isEmpty()) {
				throw new SaveDataException("Hãy chọn ảnh sách");
			}

			if (bookRequest.getCopies_available() <= 0) {
				throw new SaveDataException("Hãy nhập tổng số sách lớn hơn 0");
			}

			if (bookRequest.getPage() <= 0) {
				throw new SaveDataException("Hãy nhập tổng trang sách lớn hơn 0");
			}

			if (bookRequest.getAuthors().isEmpty()) {
				throw new SaveDataException("Hãy chọn tác giả");
			}

			if (bookRequest.getDepartments().isEmpty()) {
				throw new SaveDataException("Hãy chọn danh mục");
			}

			if (bookRequest.getPublisher() <= 0) {
				throw new SaveDataException("Hãy chọn nhà xuất bản");
			}

			Book book = new Book();
			book.setTitle(bookRequest.getTitle().trim());
			book.setDescription(bookRequest.getDescription());
			book.setPrice(bookRequest.getPrice());

			book.setImageUrl(bookRequest.getImageUrl());

			book.setCreateAt(LocalDateTime.now());
			book.setCopies(0);
			book.setCopies_available(bookRequest.getCopies_available());
			book.setLanguage(bookRequest.getLanguage());
			book.setForUser(bookRequest.isForUser());
			book.setPage(bookRequest.getPage());

			List<Author> authors = new ArrayList<>();
			if (!Objects.isNull(bookRequest.getAuthors())) {
				bookRequest.getAuthors().forEach(a -> {

					Author author = authorRepository.getById(a);
					if (!Objects.isNull(author)) {
						authors.add(author);
					}
				});
				book.setAuthors(authors);
			}

			List<Department> departments = new ArrayList<>();
			if (!Objects.isNull(bookRequest.getDepartments())) {
				bookRequest.getDepartments().forEach(d -> {

					Department department = departmentRepository.getById(d);
					if (!Objects.isNull(department)) {
						departments.add(department);
					}
				});
				book.setDepartments(departments);
				;
			}

			if (!Objects.isNull(bookRequest.getPublisher())) {

				Publisher publisher = publisherRepository.getById(bookRequest.getPublisher());

				book.setPublisher(publisher);
			}
			bookRepository.save(book);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thêm mới sách thành công", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editBook(EditBookRequest editBookRequest, Long bookId) {
		if (Objects.isNull(bookId)) {
			throw new NotFoundException("Book_id null");
		} else {
			Book book = bookRepository.getBookById(bookId);

			if (Objects.isNull(book)) {
				throw new NotFoundException("Book null");
			} else {

				if (editBookRequest.getTitle().isBlank() || editBookRequest.getTitle().isEmpty()) {
					throw new SaveDataException("Hãy nhập tên sách");
				}
				
				if (bookRepository.existsByTitle(editBookRequest.getTitle()) && !book.getTitle().equals(editBookRequest.getTitle())) {
					throw new APIException(HttpStatus.BAD_REQUEST, "Tên sách đã tồn tại");
				}

				if (editBookRequest.getDescription().isBlank() || editBookRequest.getDescription().isEmpty()) {
					throw new SaveDataException("Hãy nhập mô tả sách");
				}

				if (editBookRequest.getPrice() < 1000) {
					throw new SaveDataException("Hãy nhập giá sách lớn hơn 1000");
				}

				if (editBookRequest.getImageUrl().isBlank() || editBookRequest.getImageUrl().isEmpty()) {
					throw new SaveDataException("Hãy chọn ảnh sách");
				}

				if (editBookRequest.getCopies() < 0) {
					throw new SaveDataException("Hãy nhập tổng số sách đã được mượn lớn hơn 0");
				}

				if (editBookRequest.getCopies_available() <= 0) {
					throw new SaveDataException("Hãy nhập tổng số sách có trong thư viện lớn hơn 0");
				}

				if (editBookRequest.getPage() <= 0) {
					throw new SaveDataException("Hãy nhập tổng trang sách lớn hơn 0");
				}

				if (editBookRequest.getAuthors().isEmpty()) {
					throw new SaveDataException("Hãy chọn tác giả");
				}

				if (editBookRequest.getDepartments().isEmpty()) {
					throw new SaveDataException("Hãy chọn danh mục");
				}

				if (editBookRequest.getPublisher() <= 0) {
					throw new SaveDataException("Hãy chọn nhà xuất bản");
				}

				book.setTitle(editBookRequest.getTitle().trim());
				book.setDescription(editBookRequest.getDescription());
				book.setPrice(editBookRequest.getPrice());

				book.setImageUrl(editBookRequest.getImageUrl());

				book.setCopies(editBookRequest.getCopies());
				book.setCopies_available(editBookRequest.getCopies_available());
				book.setLanguage(editBookRequest.getLanguage());
				book.setForUser(editBookRequest.isForUser());
				book.setPage(editBookRequest.getPage());

				List<Author> authors = new ArrayList<>();
				if (!Objects.isNull(editBookRequest.getAuthors())) {
					editBookRequest.getAuthors().forEach(a -> {

						Author author = authorRepository.getById(a);
						if (!Objects.isNull(author)) {
							authors.add(author);
						}
					});
					book.setAuthors(authors);
				}

				List<Department> departments = new ArrayList<>();
				if (!Objects.isNull(editBookRequest.getDepartments())) {
					editBookRequest.getDepartments().forEach(d -> {

						Department department = departmentRepository.getById(d);
						if (!Objects.isNull(department)) {
							departments.add(department);
						}
					});
					book.setDepartments(departments);

				}
				if (!Objects.isNull(editBookRequest.getPublisher())) {
					Publisher publisher = publisherRepository.getById(editBookRequest.getPublisher());

					book.setPublisher(publisher);
				}
				bookRepository.save(book);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Cập nhật sách thành công", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllBook(int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Book> list = bookRepository.getAllBook(pageable);

		List<BookDto> bookDtos = new ArrayList<>();
		for (Book item : list.getContent()) {
			MapperObject mapper = new MapperObject(modelMapper);
			BookDto bookDto = mapper.convertToBookDto(item, null);
			bookDtos.add(bookDto);
		}

		CustomPage<BookDto> pageResponse = new CustomPage<>(bookDtos, indexPage, size, list.getTotalElements(),
				list.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Danh sách sách", new HashMap<>() {
			{
				put("books", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getDetailBook(Long bookId) {
		if (Objects.isNull(bookId)) {
			throw new NotFoundException("Book_id null");
		} else {

			MapperObject mapper = new MapperObject(modelMapper);
			if (SecurityUtils.checkAuth().equals("anonymousUser")) {
				Book book = bookRepository.getBookById(bookId);
				BookDto bookDto = mapper.convertToBookDto(book, 0L);

				List<Book> books = bookRepository.relatedBook(book.getDepartments().get(0).getId());
				books.remove(book);

				List<ViewBookAuthorDto> bookRelate = new ArrayList<>();
				for (Book item : books) {
					ViewBookAuthorDto itemDto = ViewBookAuthorDto.convertToVBADtoV2(item);
					bookRelate.add(itemDto);
				}

				return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Book", new HashMap<>() {
					{
						put("book", bookDto);
						put("relatedBook", bookRelate);
					}
				}));
			} else {
				User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
				Favorite favorite = favoriteRepository.findFavoriteByUserIdAndBookId(user.getId(), bookId);
				Book book = bookRepository.getBookById(bookId);
				BookDto bookDto = mapper.convertToBookDto(book, user.getId());

				if (!Objects.isNull(favorite)) {
					bookDto.setLiked(favorite.isFavorite());
				} else {
					bookDto.setLiked(false);
				}

				List<Book> books = bookRepository.relatedBook(book.getDepartments().get(0).getId());
				books.remove(book);

				List<ViewBookAuthorDto> bookRelate = new ArrayList<>();
				for (Book item : books) {
					ViewBookAuthorDto itemDto = ViewBookAuthorDto.convertToVBADtoV2(item);
					bookRelate.add(itemDto);
				}

					voucherService.addVoucherByUserID(user.getId());
				

				return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Book", new HashMap<>() {
					{
						put("book", bookDto);
						put("relatedBook", bookRelate);
					}
				}));
			}
		}
	}

	@Override
	public ResponseEntity<ApiResponse> deleteBook(Long bookId) {

		Book book = bookRepository.getBookById(bookId);

		if (Objects.isNull(book)) {
			throw new NotFoundException("Book_id không tồn tại trong book");
		} else {

			bookRepository.delete(book);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Xóa sách thành công", 200));

		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getBookForHome() {
		List<Book> bookNew = bookRepository.getTop10BookNew();
		List<ViewBookDto> bookNewDto = new ArrayList<>();
		bookNew.stream().forEach((item) -> {
			ViewBookDto bookDto = ViewBookDto.convertToViewBookDto(item);
			bookNewDto.add(bookDto);
		});

		List<Book> bookBorrowed = bookRepository.getTop10BookBorrowed();
		List<ViewBookDto> bookBorrowedDto = new ArrayList<>();
		bookBorrowed.stream().forEach((item) -> {
			ViewBookDto bookDto = ViewBookDto.convertToViewBookDto(item);
			bookBorrowedDto.add(bookDto);
		});

		List<Book> listBook = bookRepository.findAll();

		// Creating a Random object
		Random random = new Random();

		List<ViewBookDto> bookRecommendDto = new ArrayList<>();
		int totalBook = bookRepository.totalBook();
		int count = 0;

		while (count <= 10) {
			// Generating a random integer
			int randomNumber = random.nextInt(totalBook) + 1;

			Book item = listBook.get(randomNumber - 1);
			ViewBookDto bookDto = ViewBookDto.convertToViewBookDto(item);
			if (!bookRecommendDto.contains(bookDto)) {
				count++;
				bookRecommendDto.add(bookDto);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("List Books", new HashMap<>() {
			{
				put("bookNew", bookNewDto);
				put("bookBorrowed", bookBorrowedDto);
				put("bookRecommend", bookRecommendDto);
			}
		}));
	}

}
