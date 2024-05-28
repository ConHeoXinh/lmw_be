package longND.fpt.home.presentation.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateAuthorRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String imageUrl;
	@NotBlank
	private String description;
}
