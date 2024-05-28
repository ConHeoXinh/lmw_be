package longND.fpt.home.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import longND.fpt.home.data.modal.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String username;
	private LocalDateTime dob;
	private boolean gender;
	private boolean userStatus;
	private LocalDateTime createAt;
	private String roles;
	private String imageUrl;

	public static UserDto convertToUserDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setUsername(user.getUsername());
		userDto.setDob(user.getDob());
		userDto.setGender(user.isGender());
		userDto.setUserStatus(user.isUserStatus());
		userDto.setCreateAt(user.getCreateAt());
		userDto.setRoles(user.getRoles().getName());

		return userDto;
	}
}
