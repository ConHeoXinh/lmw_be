package longND.fpt.home.data.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import longND.fpt.home.data.modal.CartItem;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class CartItemDto {
	private Long id;
	private Long cartId;
	private Long bookId;
	private String title;
	private String imageUrl;
	private int quantity;
	private double price;
	private double discountedPrice;
	private Long voucherId;

	private LocalDateTime checkoutDate;

	private LocalDateTime returnDate;

	public CartItemDto convertCartItem(CartItem cartItem) {
		CartItemDto cartItemDto = new CartItemDto();

		cartItemDto.setId(cartItem.getId());
		cartItemDto.setCartId(cartItem.getCart().getId());
		cartItemDto.setBookId(cartItem.getBook().getId());
		cartItemDto.setTitle(cartItem.getBook().getTitle());
		cartItemDto.setImageUrl(cartItem.getBook().getImageUrl());
		cartItemDto.setQuantity(cartItem.getQuantity());
		cartItemDto.setPrice(cartItem.getPrice());
		cartItemDto.setDiscountedPrice(cartItem.getDiscountedPrice());
		if (!Objects.isNull(cartItem.getVoucher())) {
			cartItemDto.setVoucherId(cartItem.getVoucher().getId());
		}

		return cartItemDto;
	}
}
