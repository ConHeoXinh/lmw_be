package longND.fpt.home.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AuthorDto {
	private Long id;
	private String name;
	private String imageUrl;
	private String description;
}
