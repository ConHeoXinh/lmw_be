package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.CartDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Cart;
import longND.fpt.home.data.modal.CartItem;
import longND.fpt.home.presentation.payload.request.CartItemRequest;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);

	public ResponseEntity<ObjectResponse> updateCartItem(Long userId, Long id, CartItemRequest cartItemRequest);

	public CartItem isCartItemExist(Cart cart, Book book, Long userId);

	public CartDto removeCartItem(Long userId, Long cartItemId);

	public CartItem findCartItemById(Long cartItemId);
}
