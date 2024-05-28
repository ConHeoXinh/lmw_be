package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.presentation.payload.request.CreateDPRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface PublisherService {

	public ResponseEntity<ApiResponse> createPublisher(CreateDPRequest creatRequest);

	public ResponseEntity<ApiResponse> editPublisher(CreateDPRequest creatRequest, Long publisherId);

	public ResponseEntity<ObjectResponse> searchPublisher(SearchRequest searchRequest, int indexPage);

	public ResponseEntity<ObjectResponse> listPublisher(int indexPage);
	
	public ResponseEntity<ObjectResponse> getBookOfPublisher(Long publisherId, int indexPage);
}
