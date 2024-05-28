package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.presentation.payload.request.CreateOrderRequest;
import longND.fpt.home.presentation.payload.request.ExtendBookRequest;
import longND.fpt.home.presentation.payload.request.FilterDateRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface OrderService {
	public ResponseEntity<ApiResponse> createOrder(CreateOrderRequest orderRequest);

	public ResponseEntity<ObjectResponse> findOrderByCode(SearchRequest searchRequest, int indexPage);

	public ResponseEntity<ObjectResponse> getAllOrdersByUsername(String username);

	public ResponseEntity<ObjectResponse> usersOrderHistory(int indexPage);
	
	public ResponseEntity<ObjectResponse> getAllOrderExpire(int indexPage);

	public ResponseEntity<ApiResponse> confirmedOrder(Long orderId);

	public ResponseEntity<ApiResponse> cancledOrder(Long orderId);

	public ResponseEntity<ObjectResponse> getAllOrders(int indexPage);
	
	public ResponseEntity<ObjectResponse> getOrderDetail(Long orderId);

	public ResponseEntity<ApiResponse> deleteOrder(Long orderId);
	
	public ResponseEntity<ApiResponse> extendOrder(ExtendBookRequest extendBookRequest);

	public ResponseEntity<ObjectResponse> getAllBookByUser(int indexPage);
	
	public ResponseEntity<ObjectResponse> getDashboard(FilterDateRequest filterDateRequest);
}
