package longND.fpt.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Rating;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.modal.key.BookRatingKey;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.RatingRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.RatingRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.service.RatingService;
import longND.fpt.home.util.SecurityUtils;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<ApiResponse> rateBook(RatingRequest request) {
		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new AuthException("Bạn hãy đăng nhập để đánh giá cho sách");
		}
		User userLogin = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		
		Rating ratingExist = ratingRepository.findRatingByUserIdAndBookId(userLogin.getId(), request.getBookId());
		if (ratingExist != null) {
			throw new BadRequestAlertException("Bạn đã đánh giá sách trước đây rồi");
		}
		Rating rating = new Rating();
		
		Book bookRate = bookRepository.findById(request.getBookId()).orElse(null);
		if (bookRate == null) {
			throw new NotFoundException("Sách không tồn tại");
		}
		BookRatingKey id = new BookRatingKey();
		id.setBookId(bookRate.getId());
		id.setUserId(userLogin.getId());
		rating.setId(id);
		rating.setBookRate(bookRate);
		rating.setMessage(request.getMessage());
		rating.setStars(request.getStars());
		rating.setUserRate(userLogin);
		ratingRepository.save(rating);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Đánh giá sách thành công", 200));
	}
}
