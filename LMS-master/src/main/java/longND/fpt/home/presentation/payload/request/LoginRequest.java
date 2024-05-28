package longND.fpt.home.presentation.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter

public class LoginRequest {
	@NotBlank
    private String username;
    @NotBlank
    private String password;
}
