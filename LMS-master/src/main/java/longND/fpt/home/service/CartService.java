package longND.fpt.home.service;

import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.CartDto;
import longND.fpt.home.data.dto.CartItemDto;
import longND.fpt.home.data.modal.Cart;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.presentation.payload.request.AddItemRequest;

@Service
public interface CartService {
	public Cart createCart(User user);

	public CartItemDto addCartItem(Long userId, AddItemRequest req);

	public CartDto findUserCart(Long userId);

}
