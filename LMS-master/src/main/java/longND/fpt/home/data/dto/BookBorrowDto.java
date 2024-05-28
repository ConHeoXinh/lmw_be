package longND.fpt.home.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BookBorrowDto {
	private Long orderId;
	private String nameBook;
	private String username;
	private int quantity;
	private LocalDateTime checkoutDate;
	private LocalDateTime returnDate;
	private String codeOrder;
	private boolean isPayed;
}
