package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.presentation.payload.request.CreateDPRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface DepartmentService {

	public ResponseEntity<ApiResponse> createDepartment(CreateDPRequest creatRequest);

	public ResponseEntity<ApiResponse> editDepartment(CreateDPRequest creatRequest, Long publisherId);

	public ResponseEntity<ObjectResponse> searchDepartment(SearchRequest searchRequest, int indexPage);

	public ResponseEntity<ObjectResponse> listDepartment(int indexPage);

	public ResponseEntity<ObjectResponse> getBookOfDepartment(Long departmentId);
}
