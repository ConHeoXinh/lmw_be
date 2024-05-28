package longND.fpt.home.presentation.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	private String status;
	private Object object;
}
