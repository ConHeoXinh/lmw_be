package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;

import longND.fpt.home.presentation.payload.request.RatingRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;

public interface RatingService {

	ResponseEntity<ApiResponse> rateBook(RatingRequest request);
}
