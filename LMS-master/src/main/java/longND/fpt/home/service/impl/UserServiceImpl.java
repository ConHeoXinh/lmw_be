package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.UserDto;
import longND.fpt.home.data.modal.Role;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.EditProfileRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.JwtResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.UserService;
import longND.fpt.home.util.SecurityUtils;
import longND.fpt.home.util.Util;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CloudinaryService cloudinaryService;

	@Override
	public ResponseEntity<ObjectResponse> findUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<JwtResponse> profile() {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (Objects.isNull(user)) {
			throw new NotFoundException("Người dùng không tồn tại");
		} else {
			UserDto dto = UserDto.builder().id(user.getId()).username(user.getUsername()).createAt(user.getCreateAt())
					.dob(user.getDob()).phoneNumber(user.getPhoneNumber()).firstName(user.getFirstName())
					.lastName(user.getLastName()).email(user.getEmail()).gender(user.isGender())
					.userStatus(user.isUserStatus()).roles(user.getRoles().getName()).imageUrl(user.getAvatarUrl())
					.build();

			return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(HttpStatus.OK.name(), dto));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> editProfile(EditProfileRequest editProfileRequest) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		if (editProfileRequest.getFirstName().isBlank() || editProfileRequest.getFirstName().isEmpty()) {
			throw new BadRequestAlertException("Hãy nhập họ của bạn");
		}

		if (editProfileRequest.getLastName().isBlank() || editProfileRequest.getLastName().isEmpty()) {
			throw new BadRequestAlertException("Hãy nhập tên của bạn");
		}

		if (editProfileRequest.getPhoneNumber().isBlank() || editProfileRequest.getPhoneNumber().isEmpty()) {
			throw new BadRequestAlertException("Hãy nhập số điện thoại của bạn");
		}

		if (editProfileRequest.getEmail().isBlank() || editProfileRequest.getEmail().isEmpty()) {
			throw new BadRequestAlertException("Hãy nhập email của bạn");
		}
		if (userRepository.existsByEmail(editProfileRequest.getEmail())
				&& !user.getEmail().equals(editProfileRequest.getEmail())) {
			throw new BadRequestAlertException("Email đã tồn tại ở một tài khoản khác");
		}
		if (userRepository.existsByPhoneNumber(editProfileRequest.getPhoneNumber())
				&& !user.getPhoneNumber().equals(editProfileRequest.getPhoneNumber())) {
			throw new BadRequestAlertException("Số điện thoại đã tồn tại ở một tài khoản khác");
		}

		user.setFirstName(editProfileRequest.getFirstName());
		user.setLastName(editProfileRequest.getLastName());
		user.setPhoneNumber(editProfileRequest.getPhoneNumber());
		user.setEmail(editProfileRequest.getEmail());
		user.setGender(editProfileRequest.isGender());

		String utilDate = editProfileRequest.getDob();

		LocalDateTime localDateTime = Util.convertStringToLocalDateTime(utilDate);

		user.setDob(localDateTime);
		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Sửa thông tin cá nhân thành công", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> editAvatar(MultipartFile file) {

		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
		if (Objects.isNull(user)) {
			throw new NotFoundException("người dùng không tồn tại");
		} else {

			String nameFile = cloudinaryService.upload(file);

			user.setAvatarUrl(nameFile);
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ObjectResponse("Sửa avatar thành công", new HashMap<>() {
						{
							put("imageLink", nameFile);
						}
					}));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllEmployees(int indexPage) {
		int page = indexPage - 1;
		int size = 5; // sửa size ở đây
		Pageable pageable = PageRequest.of(page, size);

		List<UserDto> listUsers = new ArrayList<UserDto>();

		Page<User> users = userRepository.findAllByRoles_Id(pageable, 2L);
		if (users.getTotalElements() == 0) {
			throw new NotFoundException("Không tìm thấy nhân viên");
		} else {
			for (User user : users.getContent()) {
				UserDto dto = UserDto.builder().id(user.getId()).username(user.getUsername())
						.createAt(user.getCreateAt()).dob(user.getDob()).phoneNumber(user.getPhoneNumber())
						.firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail())
						.userStatus(user.isUserStatus()).roles(user.getRoles().getName()).imageUrl(user.getAvatarUrl())
						.build();
				listUsers.add(dto);
			}

		}

		CustomPage<UserDto> pageResponse = new CustomPage<>(listUsers, indexPage, size, users.getTotalElements(),
				users.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("danh sách nhân viên", new HashMap<>() {
			{
				put("employeeList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ApiResponse> changeStatus(Long id) {
		User user = userRepository.findUserById(id);
		Role role = user.getRoles();
		if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse("không thể thay đổi trạng thái", 400));
		}
		if (Objects.isNull(user)) {
			throw new NotFoundException("Không tìm thấy nhân viên");
		}
		user.setUserStatus(!user.isUserStatus());
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thay đổi trạng thái thành công", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllUsers(int indexPage) {
		int page = indexPage - 1;
		int size = 5; // sửa size ở đây
		Pageable pageable = PageRequest.of(page, size);

		List<UserDto> listUsers = new ArrayList<UserDto>();

		Page<User> users = userRepository.findAllByRoles_Id(pageable, 3L);
		if (users.getTotalElements() == 0) {
			throw new NotFoundException("Không tìm thấy nhân viên");
		} else {
			for (User user : users.getContent()) {
				UserDto dto = UserDto.builder().id(user.getId()).username(user.getUsername())
						.createAt(user.getCreateAt()).dob(user.getDob()).phoneNumber(user.getPhoneNumber())
						.firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail())
						.userStatus(user.isUserStatus()).roles(user.getRoles().getName()).imageUrl(user.getAvatarUrl())
						.build();
				listUsers.add(dto);
			}

		}

		CustomPage<UserDto> pageResponse = new CustomPage<>(listUsers, indexPage, size, users.getTotalElements(),
				users.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("danh sách nhân viên", new HashMap<>() {
			{
				put("employeeList", pageResponse);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> searchEmployeeByUseranme(int indexPage, String username) {
		int page = indexPage - 1;
		int size = 5; // sửa size ở đây
		Pageable pageable = PageRequest.of(page, size);

		List<UserDto> listUsers = new ArrayList<UserDto>();

		Page<User> users = userRepository.findAllByRoles_Id(pageable, 2L);
		if (users.getTotalElements() == 0) {
			throw new NotFoundException("Không tìm thấy nhân viên");
		} else {
			for (User user : users.getContent()) {
				if(user.getUsername().contains(username)) {
					UserDto dto = UserDto.builder().id(user.getId()).username(user.getUsername())
							.createAt(user.getCreateAt()).dob(user.getDob()).phoneNumber(user.getPhoneNumber())
							.firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail())
							.userStatus(user.isUserStatus()).roles(user.getRoles().getName()).imageUrl(user.getAvatarUrl())
							.build();
					listUsers.add(dto);
				}
			}

		}

		CustomPage<UserDto> pageResponse = new CustomPage<>(listUsers, indexPage, size, users.getTotalElements(),
				users.getTotalPages());

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("danh sách nhân viên", new HashMap<>() {
			{
				put("employeeList", pageResponse);
			}
		}));
	}
}
