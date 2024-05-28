package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import longND.fpt.home.data.dto.LoginDto;
import longND.fpt.home.data.modal.Role;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.RoleRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.exception.APIException;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.ChangePasswordRequest;
import longND.fpt.home.presentation.payload.request.ForgotPasswordRequest;
import longND.fpt.home.presentation.payload.request.LoginRequest;
import longND.fpt.home.presentation.payload.request.RegisterRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.security.jwt.JwtTokenProvider;
import longND.fpt.home.security.jwt.UserDetailsImpl;
import longND.fpt.home.service.AuthService;
import longND.fpt.home.util.JavaMail;
import longND.fpt.home.util.SecurityUtils;

@Service
public class AuthServiceImpl implements AuthService {
	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JavaMail javaMail;

	@Override
	public ResponseEntity<ObjectResponse> register(RegisterRequest registerRequest, HttpServletRequest servletRequest) {
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Tên tài khoàn đã tồn tại");
		} else if (userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Email đã tồn tại");
		} else {
			User user = new User(registerRequest.getEmail(), registerRequest.getPhoneNumber(),
					registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));

			user.setFirstName("first name");
			user.setLastName("last name");
			user.setDob(LocalDateTime.now());
			user.setGender(true);
			user.setCreateAt(LocalDateTime.now());

			String strRoles = registerRequest.getRole();

			if (strRoles == null || strRoles.isEmpty()) {
				Role userRole = roleRepository.findByName("ROLE_USER")
						.orElseThrow(() -> new NotFoundException("không tìm thấy quyền USER"));
				user.setRoles(userRole);
				user.setUserStatus(true);
			} else {
				switch (strRoles) {
				case "admin": {
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
							.orElseThrow(() -> new NotFoundException("không tìm thấy quyền Admin"));
					user.setRoles(adminRole);
					user.setUserStatus(false);
					break;
				}
				case "employee": {
					Role emplRole = roleRepository.findByName("ROLE_EMPLOYEE")
							.orElseThrow(() -> new NotFoundException("không tìm thấy quyền EMPLOYEE"));
					user.setRoles(emplRole);
					user.setUserStatus(false);
					break;
				}
				default:
					Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new NotFoundException("không tìm thấy quyền USER"));
					user.setRoles(userRole);
					user.setUserStatus(true);
				}
			}

			userRepository.save(user);

			LoginDto userDto = modelMapper.map(user, LoginDto.class);
			userDto.setRole(user.getRoles().getName());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ObjectResponse("Đăng ký tài khoản thành công", new HashMap<>() {
						{
							put("user", userDto);
						}
					}));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> forgotPassword(String email, HttpServletRequest servletRequest) {
		User user = userRepository.getUserByEmail(email);

		if (Objects.isNull(user)) {
			throw new NotFoundException("email không tồn tại");
		} else {
			String resetPassword = UUID.randomUUID().toString();
//			String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest).replacePath(null).build()
//					.toUriString();
			String fullName = user.getFirstName() + user.getLastName();
			String toEmail = user.getEmail();
			String subject = "Xác nhận tài khoản!";
			String text = "Chào " + fullName + ",\n"
					+ "Bạn cần xác nhận đó là bạn. Bấm vào đường dẫn bên dưới để xác nhận tài khoản: \n"
					+ "http://localhost:3000/api/auth/confirm-forgot-password?otp=" + resetPassword + "\n"
//					+ "http://fe-lms-free.s3-website-ap-southeast-2.amazonaws.com/api/auth/confirm-forgot-password?otp=" + resetPassword + "\n" 
					+ "\nTrân trọng,\n" + "\nĐội ngũ LMS.";

			user.setCodeActive(resetPassword);
			userRepository.save(user);
			javaMail.sentEmail(toEmail, subject, text);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponse("Làm mới mật khẩu thành công, hãy kiểm tra email", 200));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> login(LoginRequest loginRequest) {
		User user = userRepository.getUserByUsername(loginRequest.getUsername());

		if (Objects.isNull(user)) {
			throw new AuthException("Tài khoản không tồn tại");
		} else {
			if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
								loginRequest.getPassword()));
				if (user.isUserStatus() == false) {
					throw new AuthException("Tài khoản bị chặn");
				} else {
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String jwt = jwtTokenProvider.generateToken(authentication);
					UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

					List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
							.collect(Collectors.toList());

					LoginDto userDto = LoginDto.builder().email(userDetails.getEmail())
							.username(userDetails.getUsername()).role(roles.get(0)).userStatus(user.isUserStatus())
							.build();
					Map<String, Object> data = new HashMap<String, Object>();

					Date currentDate = new Date();

					Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

					data.put("userID", user.getId());
					data.put("user", userDto);
					data.put("expireDate", expireDate);
					data.put("token", "Bearer " + jwt);
					return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Đăng nhập thành công", data));
				}
			} else {
				throw new BadRequestAlertException("Mật khẩu không đúng");
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> existAccountByUsername(String username) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (userRepository.existsByUsername(username.trim())) {
			data.put("user", username + " đã tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("USERNAME_EXIST", data));
		} else {
			data.put("user", username + " chưa tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("USERNAME_NOT_EXIST", data));
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> existAccountByEmail(String email) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (userRepository.existsByEmail(email.trim())) {
			data.put("user", email + " đã tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("EMAIL_EXIST", data));
		} else {
			data.put("user", email + " chưa tồn tại");
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("EMAIL_NOT_EXIST", data));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Đăng xuất thành công!", 200));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Đăng xuất thất bại!", 400));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> confirmResetPassword(String token) {
		User user = userRepository.getUserByCodeActive(token);

		if (Objects.isNull(user)) {
			throw new NotFoundException("otp không tồn tại");
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponse("Chấp nhận làm mới mật khẩu thành công/" + user.getEmail(), 200));
		}
	}

	@Override
	public ResponseEntity<ApiResponse> changePassword(ChangePasswordRequest changePasswordRequest) {

		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new NotFoundException("Người dùng không tồn tại");
		} else {
			User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
			if (passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
				throw new BadRequestAlertException("Mật khẩu hiện tại không đúng");
			}
			if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), user.getPassword())) {
				throw new BadRequestAlertException("Mật khẩu mới không được trùng mật khẩu cũ");
			} else {

				user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
				userRepository.save(user);
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Thay đổi mật khẩu thành công!", 200));
			}
		}
	}

	@Override
	public ResponseEntity<ApiResponse> resetPassword(ForgotPasswordRequest forgotPasswordRequest) {
		User user = userRepository.getUserByEmail(forgotPasswordRequest.getEmail());

		if (Objects.isNull(user)) {
			throw new NotFoundException("email không tồn tại");
		} else {
			user.setPassword(passwordEncoder.encode(forgotPasswordRequest.getPassword()));
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Làm mới mật khẩu thành công", 200));
		}
	}

}
