package longND.fpt.home.data.dto;

import java.time.LocalDateTime;

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
public class OrderDto {

	private Long OrderId;

	private double totalPrice;

	private double totalDiscountedPrice;

	private double discounte;

	private boolean orderStatus;

	private int totalItem;

	private UserDto userDto;
	private UserDto employeeDto;

	private LocalDateTime createAt;
	private String code;
}
