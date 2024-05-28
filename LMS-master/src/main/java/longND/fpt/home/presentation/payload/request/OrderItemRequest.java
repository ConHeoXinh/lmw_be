package longND.fpt.home.presentation.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
	private Long cartItemId;
	private Long bookId;
	private int quantity;
	private String codeVoucher;
	private String checkoutDate;
	private String returnDate;
}
