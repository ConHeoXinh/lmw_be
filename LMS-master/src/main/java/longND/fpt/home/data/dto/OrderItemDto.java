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
public class OrderItemDto {

	private Long orderItemId;

	private Long orderId;

	private BookDto bookdto;

	private LocalDateTime checkoutDate;

	private LocalDateTime returnDate;

	private int quantity;

	private double price;

	private double discountedPrice;
	private boolean isReturnEd;

	private UserDto employeeDto;

	private UserDto userDto;

	private VoucherDto voucherDto;

	private String codeOrder;

	private boolean isPayed;
}
