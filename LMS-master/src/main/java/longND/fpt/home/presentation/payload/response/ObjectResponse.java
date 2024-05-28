package longND.fpt.home.presentation.payload.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse {
	private String message;
	private Map<String, Object> data;
}
