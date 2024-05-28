package longND.fpt.home.presentation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.data.dto.CartDto;
import longND.fpt.home.data.dto.CartItemDto;
import longND.fpt.home.data.dto.VoucherDto;
import longND.fpt.home.presentation.payload.request.AddItemRequest;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.CartService;
import longND.fpt.home.service.VoucherService;
import longND.fpt.home.util.SecurityUtils;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private VoucherService voucherService;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> findUserCartHandler() {

		Long userId = SecurityUtils.getPrincipal().getId();

		CartDto cart = cartService.findUserCart(userId);

		List<VoucherDto> dtoList = voucherService.getAllVoucherByUserID();

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Cart data", new HashMap<>() {
			{
				put("cart", cart);
				put("voucher", dtoList);
			}
		}));
	}

	@PutMapping("/add")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<CartItemDto> addItemToCart(@RequestBody AddItemRequest req) {

		Long userId = SecurityUtils.getPrincipal().getId();
		System.out.println("user id: " + userId);
		CartItemDto createdCartItem = cartService.addCartItem(userId, req);

		return new ResponseEntity<>(createdCartItem, HttpStatus.ACCEPTED);

	}
}
