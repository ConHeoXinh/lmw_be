package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.modal.OrderItem;
import longND.fpt.home.presentation.payload.request.ExtendABookRequest;
import longND.fpt.home.presentation.payload.request.FilterDateRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface OrderItemService {
	public ResponseEntity<ApiResponse> createOrderItem(OrderItem orderItem);

	public ResponseEntity<ObjectResponse> getHistory(int indexPage);

	public ResponseEntity<ApiResponse> extendOneBook(ExtendABookRequest extendABookRequest);

	public ResponseEntity<ObjectResponse> Top10BookFilter(FilterDateRequest filterDateRequest);

	public ResponseEntity<ApiResponse> returnBook(Long orderItemId);
	
	public ResponseEntity<ObjectResponse> findOrderItemByCode(SearchRequest searchRequest, int indexPage);
}
