package longND.fpt.home.service.impl;

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

import longND.fpt.home.data.dto.AuthorDto;
import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.SearchDto;
import longND.fpt.home.data.dto.ViewBookAuthorDto;
import longND.fpt.home.data.modal.Author;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.repository.AuthorRepository;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.CreateAuthorRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.AuthorService;
import longND.fpt.home.util.MapperObject;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public ResponseEntity<ApiResponse> createAuthor(CreateAuthorRequest creatRequest) {

		if (creatRequest.getName().isBlank() || creatRequest.getName().isEmpty()) {
			throw new BadRequestAlertException("Hãy nhập tên tác giả");
		}
		if (creatRequest.getImageUrl().isBlank() || creatRequest.getImageUrl().isEmpty()) {
			throw new BadRequestAlertException("Hãy thêm ảnh tác giả");
		}
		if (creatRequest.getDescription().isBlank() || creatRequest.getDescription().isEmpty()) {
			throw new BadRequestAlertException("Hãy mô tả tác giả");
		}

		String nameAuthor = creatRequest.getName().trim();
		if (authorRepository.existsByName(nameAuthor)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên tác giả đã tồn tại");
		} else {
			Author author = new Author();
			author.setName(nameAuthor);
			author.setImageUrl(creatRequest.getImageUrl());
			author.setDescription(creatRequest.getDescription());
			authorRepository.save(author);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thêm mới tác giả thành công", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editAuthor(CreateAuthorRequest creatRequest, Long AuthorId) {
		Author author = authorRepository.getById(AuthorId);
		if (creatRequest.getName().isBlank() || creatRequest.getName().isEmpty()) {
			throw new BadRequestAlertException("Hãy nhập tên tác giả");
		}
		if (creatRequest.getImageUrl().isBlank() || creatRequest.getImageUrl().isEmpty()) {
			throw new BadRequestAlertException("Hãy thêm ảnh tác giả");
		}
		if (creatRequest.getDescription().isBlank() || creatRequest.getDescription().isEmpty()) {
			throw new BadRequestAlertException("Hãy mô tả tác giả");
		}
		if (Objects.isNull(author)) {
			throw new NotFoundException("Không tìm thấy Author");
		} else {
			String nameAuthor = creatRequest.getName().trim();
			if (authorRepository.existsByName(nameAuthor) && !author.getName().equals(nameAuthor)) {
				throw new APIException(HttpStatus.BAD_REQUEST, "Tên tác giả đã tồn tại");
			} else {
				author.setName(nameAuthor);
				author.setImageUrl(creatRequest.getImageUrl());
				author.setDescription(creatRequest.getDescription());
				authorRepository.save(author);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thêm mới tác giả thành công", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchAuthor(SearchRequest searchRequest, int indexPage) {
		String text = searchRequest.getSearchText();
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Author> listAuthor = authorRepository.listAuthor(text, pageable);

		List<AuthorDto> list = new ArrayList<>();

		for (Author item : listAuthor.getContent()) {
			AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
			list.add(authorDto);
		}

		CustomPage<AuthorDto> pageResponse = new CustomPage<>(list, indexPage, size, listAuthor.getTotalElements(),
				listAuthor.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> listAuthor(int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Author> listAuthor = authorRepository.getListAuthor(pageable);
		List<AuthorDto> list = new ArrayList<>();

		for (Author item : listAuthor.getContent()) {
			AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
			list.add(authorDto);
		}

		CustomPage<AuthorDto> pageResponse = new CustomPage<>(list, indexPage, size, listAuthor.getTotalElements(),
				listAuthor.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Danh sách tác giả", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getBookOfAuthor(Long authorId, int indexPage) {
		int size = 16;
		int page = indexPage - 1;
		Pageable pageable = PageRequest.of(page, size);

		Author author = authorRepository.getById(authorId);
		if (Objects.isNull(author)) {
			throw new NotFoundException("Không tìm thấy tác giả");
		} else {
			Page<Book> listBook = bookRepository.listBookByAuthorId(authorId, pageable);
			AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);

			List<SearchDto> list = new ArrayList<>();

			for (Book book : listBook.getContent()) {
				MapperObject mapper = new MapperObject(modelMapper);
				SearchDto searchDto = mapper.convertToSearchDto(book);
				list.add(searchDto);
			}

			List<ViewBookAuthorDto> viewBookAuthorDtoList = new ArrayList<>();

			list.forEach(v -> {
				ViewBookAuthorDto dto = ViewBookAuthorDto.builder().id(v.getBookId()).title(v.getTitle())
						.description(v.getDesciption()).imageUrl(v.getImageUrl()).copies(v.getCopies()).copies_available(v.getCopies_available()).build();
				viewBookAuthorDtoList.add(dto);
			});

			CustomPage<ViewBookAuthorDto> pageResponse = new CustomPage<>(viewBookAuthorDtoList, indexPage, size,
					listBook.getTotalElements(), listBook.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
				{
					put("authorDetail", authorDto);
					put("bookList", pageResponse);
				}
			}));
		}
	}


}
