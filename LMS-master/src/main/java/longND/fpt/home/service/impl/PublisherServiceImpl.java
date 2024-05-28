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

import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.PublisherDto;
import longND.fpt.home.data.dto.SearchDto;
import longND.fpt.home.data.dto.ViewSearchDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Publisher;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.PublisherRepository;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.CreateDPRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.PublisherService;
import longND.fpt.home.util.MapperObject;

@Service
public class PublisherServiceImpl implements PublisherService {
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BookRepository bookRepository;

	@Override
	public ResponseEntity<ApiResponse> createPublisher(CreateDPRequest creatRequest) {
		String namePublisher = creatRequest.getName().trim();
		if (publisherRepository.existsByName(namePublisher)) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên nhà xuất bản đã tồn tại");
		} else {
			Publisher publisher = new Publisher();
			publisher.setName(namePublisher);
			publisherRepository.save(publisher);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thêm mới nhà xuất bản thành công", 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editPublisher(CreateDPRequest creatRequest, Long publisherId) {
		Publisher publisher = publisherRepository.getById(publisherId);
		if (creatRequest.getName().isBlank() || creatRequest.getName().isEmpty()) {
			throw new BadRequestAlertException("Hãy điền tên nhà xuất bản");
		}
		if (Objects.isNull(publisher)) {
			throw new NotFoundException("Không tìm thấy nhà xuất bản");
		} else {
			String namePublisher = creatRequest.getName().trim();
			if (publisherRepository.existsByName(namePublisher)
					&& !publisher.getName().equals(creatRequest.getName())) {
				throw new APIException(HttpStatus.BAD_REQUEST, "Tên nhà xuất bản đã tồn tại");
			} else {
				publisher.setName(namePublisher);
				publisherRepository.save(publisher);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Sửa nhà xuất bản thành công", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> searchPublisher(SearchRequest searchRequest, int indexPage) {
		String text = searchRequest.getSearchText();
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Publisher> listPublisher = publisherRepository.listPublisher(text, pageable);

		List<PublisherDto> list = new ArrayList<>();

		for (Publisher item : listPublisher.getContent()) {
			PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
			list.add(publisherDto);
		}

		CustomPage<PublisherDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listPublisher.getTotalElements(), listPublisher.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Tìm kiếm thành công", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> listPublisher(int indexPage) {
		int size = 5;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		Page<Publisher> listPublisher = publisherRepository.getListPublisher(pageable);
		List<PublisherDto> list = new ArrayList<>();

		for (Publisher item : listPublisher.getContent()) {
			PublisherDto publisherDto = modelMapper.map(item, PublisherDto.class);
			list.add(publisherDto);
		}

		CustomPage<PublisherDto> pageResponse = new CustomPage<>(list, indexPage, size,
				listPublisher.getTotalElements(), listPublisher.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Danh sách nhà xuất bản", new HashMap<>() {
			{
				put("searchList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getBookOfPublisher(Long publisherId, int indexPage) {
		int size = 16;
		int page = indexPage - 1;
		Pageable pageable = PageRequest.of(page, size);

		Publisher publisher = publisherRepository.getById(publisherId);
		if (Objects.isNull(publisher)) {
			throw new NotFoundException("Không tìm thấy nhà xuất bản");
		} else {
			Page<Book> listBook = bookRepository.listBookByPublisherId(publisherId, pageable);

			List<SearchDto> list = new ArrayList<>();

			for (Book book : listBook.getContent()) {
				MapperObject mapper = new MapperObject(modelMapper);
				SearchDto searchDto = mapper.convertToSearchDto(book);
				list.add(searchDto);
			}

			List<ViewSearchDto> viewSearchDtoList = new ArrayList<>();

			list.forEach(v -> {
				ViewSearchDto dto = ViewSearchDto.builder().bookId(v.getBookId()).title(v.getTitle())
						.description(v.getDesciption()).price(v.getPrice()).imageUrl(v.getImageUrl()).build();
				viewSearchDtoList.add(dto);
			});

			CustomPage<ViewSearchDto> pageResponse = new CustomPage<>(viewSearchDtoList, indexPage, size,
					listBook.getTotalElements(), listBook.getTotalPages());

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ObjectResponse("Danh sách sách của nhà xuất bản ", new HashMap<>() {
						{
							put("publisherDetail", publisher.getName());
							put("bookList", pageResponse);
						}
					}));
		}
	}

}
