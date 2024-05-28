package longND.fpt.home.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.presentation.payload.request.CartItemRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.service.CartItemService;
import longND.fpt.home.util.SecurityUtils;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId) {

		Long userId = SecurityUtils.getPrincipal().getId();
		cartItemService.removeCartItem(userId, cartItemId);
		ApiResponse res = new ApiResponse("Item Remove From Cart", 200);

		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}

	// chua hieu ban than dang viet j
	@PutMapping("/{cartItemId}")
	public ResponseEntity<?> updateCartItemHandler(@PathVariable Long cartItemId,
			@RequestBody CartItemRequest cartItemRequest) {

		Long userId = SecurityUtils.getPrincipal().getId();

		return cartItemService.updateCartItem(userId, cartItemId, cartItemRequest);
	}
}
