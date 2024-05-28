package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.presentation.payload.request.EditProfileRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.JwtResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface UserService {

	public ResponseEntity<ObjectResponse> findUserById(Long userId);
	public ResponseEntity<JwtResponse> profile();
	public ResponseEntity<ApiResponse> editProfile(EditProfileRequest editProfileRequest);

	public ResponseEntity<ApiResponse> changeStatus(Long id);

	public ResponseEntity<ObjectResponse> editAvatar(MultipartFile file);

	public ResponseEntity<ObjectResponse> getAllEmployees(int indexPage);
	public ResponseEntity<ObjectResponse> getAllUsers(int indexPage);
	
	public ResponseEntity<ObjectResponse> searchEmployeeByUseranme(int indexPage, String username);
}
