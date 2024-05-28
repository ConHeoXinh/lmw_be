package longND.fpt.home.presentation.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendABookRequest {
	private Long orderItemId;
	private String returnDate;
}
