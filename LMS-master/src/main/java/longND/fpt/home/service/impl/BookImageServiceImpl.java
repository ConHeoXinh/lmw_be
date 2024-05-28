package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.JwtResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.BookImageService;

@Service
public class BookImageServiceImpl implements BookImageService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CloudinaryService cloudinaryService;

	@Override
	public ResponseEntity<ObjectResponse> getAllBookImageByBookID(Long bookID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> insertBookImage(MultipartFile multipartFile) {

		if (multipartFile.isEmpty()) {
			throw new NotFoundException("File trống");
		} else {
			String fileName = cloudinaryService.upload(multipartFile);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ObjectResponse("Upload ảnh thành công", new HashMap<>() {
						{
							put("imageLink", fileName);
						}
					}));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> insertBookImageOneByBookID(MultipartFile multipartFile, Long bookID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ObjectResponse> editBookImageByBookImageID(Long bookID, MultipartFile multipartFile) {
		if (!bookRepository.existsById(bookID)) {
			throw new NotFoundException("book-id không tồn tại");
		} else {
			Book book = bookRepository.getBookById(bookID);

			String fileName = cloudinaryService.upload(multipartFile);

			book.setImageUrl(fileName);
			bookRepository.save(book);

			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Edit ảnh thành công", new HashMap<>() {
				{
					put("imageLink", fileName);
				}
			}));
		}
	}

	@Override
	public ResponseEntity<JwtResponse> downloadImage(Long bookID) {
		if (!bookRepository.existsById(bookID)) {
			throw new NotFoundException("book-id không tồn tại");
		} else {

			List<byte[]> bytes = new ArrayList<>();

			Book book = bookRepository.getBookById(bookID);

			List<String> list = List.of(book.getImageUrl().split("/"));
			byte[] b = cloudinaryService.download(list.get(3));
			bytes.add(b);

			return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(HttpStatus.OK.name(), bytes));
		}
	}

}
