package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.AuthorDto;
import longND.fpt.home.data.dto.BookDto;
import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.DepartmentDto;
import longND.fpt.home.data.dto.PublisherDto;
import longND.fpt.home.data.dto.SearchDto;
import longND.fpt.home.data.dto.ViewSearchDto;
import longND.fpt.home.data.modal.Author;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Department;
import longND.fpt.home.data.modal.Publisher;
import longND.fpt.home.data.repository.AuthorRepository;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.DepartmentRepository;
import longND.fpt.home.data.repository.PublisherRepository;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.SearchFilterRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.SearchService;
import longND.fpt.home.util.MapperObject;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ObjectResponse> searchByTitle(SearchRequest searchRequest, int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Page<Book> searchListByTitle = bookRepository.searchBookByTitle(searchRequest.getSearchText(),
				PageRequest.of(page, size));

		List<BookDto> bookDtos = new ArrayList<>();
		for (Book item : searchListByTitle.getContent()) {
			MapperObject mapper = new MapperObject(modelMapper);
			BookDto bookDto = mapper.convertToBookDto(item, null);
			bookDtos.add(bookDto);
		}

		CustomPage<BookDto> pageResponse = new CustomPage<>(bookDtos, indexPage, size,
				searchListByTitle.getTotalElements(), searchListByTitle.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
			{
				put("books", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> searchFilter(SearchFilterRequest searchFilterRequest, int indexPage) {
		int size = 16;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Book> listBook = bookRepository.listSearchFilter(searchFilterRequest.getAuthor(),
				searchFilterRequest.getDepartment(), searchFilterRequest.getPublisher(), searchFilterRequest.getTitle(),
				searchFilterRequest.getLanguage(), pageable);

		List<SearchDto> list = new ArrayList<>();

		for (Book book : listBook.getContent()) {
			MapperObject mapper = new MapperObject(modelMapper);
			SearchDto searchDto = mapper.convertToSearchDtoV2(book);
			list.add(searchDto);
		}

		List<SearchDto> resultSearch = list;

		List<SearchDto> sort = new ArrayList<>();

		if (searchFilterRequest.getStatusSortPrice() == 1) {
			System.out.println(searchFilterRequest.getStatusSortPrice());
			sort = resultSearch.stream().sorted(Comparator.comparing(SearchDto::getPrice)).toList();
		} else if (searchFilterRequest.getStatusSortPrice() == 2) {
			System.out.println(searchFilterRequest.getStatusSortPrice());
			sort = resultSearch.stream().sorted(Comparator.comparing(SearchDto::getPrice).reversed()).toList();
		} else {
			sort = resultSearch;
		}

		List<SearchDto> price = new ArrayList<>();

		if (searchFilterRequest.getMinPrice() == 0 || searchFilterRequest.getMaxPrice() == 0) {
			price = sort;
		} else {
			price = sort.stream().filter(searchDto -> searchDto.getPrice() >= searchFilterRequest.getMinPrice()
					&& searchDto.getPrice() <= searchFilterRequest.getMaxPrice()).toList();
//					.collect(Collectors.toList());
		}

		List<SearchDto> rate = new ArrayList<>();
		if (searchFilterRequest.getMinRate() == 0) {
			rate = price;
		} else {
			rate = price.stream().filter(searchDto -> searchDto.getRate() >= searchFilterRequest.getMinRate()).toList();
//            		.collect(Collectors.toList());
		}

//		List<SearchDto> lang = new ArrayList<>();
//		if (searchFilterRequest.getLanguage().isEmpty() || searchFilterRequest.getLanguage().isBlank()) {
//			lang = rate;
//		} else {
//			lang = rate.stream().filter(searchDto -> searchDto.getLanguage().equals(searchFilterRequest.getLanguage())).toList();
////					.collect(Collectors.toList());
//		}

		if (rate.isEmpty()) {
			throw new NotFoundException("Không tìm thấy sách");
		} else {

			List<ViewSearchDto> viewSearchDtoList = new ArrayList<>();
			// System.err.println("total: "+ listBook.getTotalElements());
			rate.forEach(v -> {
				ViewSearchDto dto = ViewSearchDto.builder().bookId(v.getBookId()).title(v.getTitle())
						.description(v.getDesciption()).price(v.getPrice()).imageUrl(v.getImageUrl())
						.copies(v.getCopies()).copies_available(v.getCopies_available()).rate(v.getRate())
						.language(v.getLanguage()).build();
				viewSearchDtoList.add(dto);
			});

			CustomPage<ViewSearchDto> pageResponse = new CustomPage<>(viewSearchDtoList, indexPage, size,
					listBook.getTotalElements(), listBook.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
				{
					put("searchList", pageResponse);
				}
			}));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getTextSearch(SearchRequest searchRequest) {
		List<Book> bookList = bookRepository.listSearchBookByTitle(searchRequest.getSearchText());

		List<ViewSearchDto> vDtos = new ArrayList<>();
		for (Book book : bookList) {
			ViewSearchDto viewSearchDto = modelMapper.map(book, ViewSearchDto.class);
			vDtos.add(viewSearchDto);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
			{
				put("listBook", vDtos);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getFilterMenu() {
		List<Author> authors = authorRepository.findAll();
		List<Department> departments = departmentRepository.findAll();
		List<Publisher> publishers = publisherRepository.findAll();

		List<DepartmentDto> departmentDtoList = new ArrayList<>();
		List<AuthorDto> authorDtoList = new ArrayList<>();
		List<PublisherDto> publisherDtoList = new ArrayList<>();

		for (Department item : departments) {
			DepartmentDto departmentDto = modelMapper.map(item, DepartmentDto.class);
			departmentDtoList.add(departmentDto);
		}

		for (Author item : authors) {
			AuthorDto authorDto = modelMapper.map(item, AuthorDto.class);
			authorDtoList.add(authorDto);
		}

		for (Publisher item : publishers) {
			PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
			publisherDtoList.add(publisherDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("menu tìm kiếm", new HashMap<>() {
			{
				put("listAuthor", authorDtoList);
				put("listDepartment", departmentDtoList);
				put("listPublisher", publisherDtoList);
			}
		}));
	}
}
