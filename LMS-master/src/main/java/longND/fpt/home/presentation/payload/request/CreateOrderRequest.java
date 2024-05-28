package longND.fpt.home.presentation.payload.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter

public class CreateOrderRequest {
	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String email;

	private List<OrderItemRequest> listOrderItem;
}
