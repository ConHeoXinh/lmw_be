package longND.fpt.home.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ViewRankingBookDto {
	private Long id;
	private String title;
	private String imageUrl;
	private int quantity;
	private double totalPrice;
}
