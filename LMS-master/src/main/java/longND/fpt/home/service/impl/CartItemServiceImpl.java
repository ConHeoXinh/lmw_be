package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.CartDto;
import longND.fpt.home.data.dto.CartItemDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Cart;
import longND.fpt.home.data.modal.CartItem;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.modal.Voucher;
import longND.fpt.home.data.repository.CartItemRepository;
import longND.fpt.home.data.repository.CartRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.data.repository.VoucherRepository;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.CartItemRequest;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.CartItemService;
import longND.fpt.home.util.Util;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private VoucherRepository voucherRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
		if (Objects.isNull(cartItem.getVoucher())) {
			cartItem.setVoucher(null);
			cartItem.setDiscountedPrice(cartItem.getBook().getPrice() * cartItem.getQuantity());
		} else {
			double discountPrice = ((double) cartItem.getVoucher().getPercent()) / 100;
			cartItem.setDiscountedPrice(cartItem.getBook().getPrice() * cartItem.getQuantity() * (1 - discountPrice));
		}
		cartItem.setCheckoutDate(null);
		cartItem.setReturnDate(null);
		CartItem createdCartItem = cartItemRepository.save(cartItem);

		return createdCartItem;
	}

	@Override
	public ResponseEntity<ObjectResponse> updateCartItem(Long userId, Long id, CartItemRequest cartItemRequest) {

		CartItem item = findCartItemById(id);
		User user = userRepository.findUserById(item.getUser().getId());

		if (user.getId().equals(userId)) {

			LocalDateTime checkoutDate = null;
			LocalDateTime returnDate = null;
			if (!cartItemRequest.getCheckoutDate().isBlank() || !cartItemRequest.getCheckoutDate().isEmpty()) {
				checkoutDate = Util.convertStringToLocalDateTime(cartItemRequest.getCheckoutDate());
			}

			if (!cartItemRequest.getReturnDate().isBlank() || !cartItemRequest.getReturnDate().isEmpty()) {
				returnDate = Util.convertStringToLocalDateTime(cartItemRequest.getReturnDate());
			}

			String codeVoucher = cartItemRequest.getCodeVoucher();

			item.setCheckoutDate(checkoutDate);
			item.setReturnDate(returnDate);
			if (item.getBook().getCopies_available() < cartItemRequest.getQuantity()) {
				throw new BadRequestAlertException(
						"Hiện tại trong thư viện chỉ còn " + item.getBook().getCopies_available() + " cuốn");
			}
			item.setQuantity(cartItemRequest.getQuantity());

			Double price = item.getBook().getPrice() * cartItemRequest.getQuantity();

			// To charge when have 2 date
			if (returnDate != null && checkoutDate != null) {
				long daysBetween = ChronoUnit.DAYS.between(checkoutDate, returnDate);
				if (daysBetween > 0) {
					price = price * (int) daysBetween;
				}
			}

			// check have voucher
			if (!codeVoucher.isBlank() || !codeVoucher.isEmpty()) {
				Voucher voucher = voucherRepository.getVoucherByCode(codeVoucher);
				if (!Objects.isNull(voucher)) {
					double discountPrice = (double)voucher.getPercent() / 100;
					item.setDiscountedPrice(price * discountPrice);
					double resultPrice = price - price*((double)voucher.getPercent() / 100);
					item.setPrice(discountPrice);
					item.setPrice(resultPrice);
				}

			} else {
				item.setDiscountedPrice(price);
				item.setPrice(price);
			}

			CartItem updatedCartItem = cartItemRepository.save(item);
			CartItemDto cartItemDto = new CartItemDto().convertCartItem(updatedCartItem);

			// update cart
			Cart cart = cartRepository.findByUserId(userId);

			List<CartItem> cartItems = cart.getCartItems();

			double totalPrice = 0;
			int totalItem = 0;
			double totalDiscountedPrice = 0;

			for (CartItem cartItem1 : cartItems) {
				totalPrice += cartItem1.getPrice();
				totalDiscountedPrice += cartItem1.getDiscountedPrice();
				totalItem += cartItem1.getQuantity();
			}

			cart.setCartItems(cartItems);
			cart.setTotalPrice(totalPrice);
			cart.setTotalItem(totalItem);
			cart.setTotalDiscountedPrice(totalDiscountedPrice);

			cartRepository.save(cart);

			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ObjectResponse("Thông tin order item", new HashMap<>() {
						{
							put("cartItem", cartItemDto);
						}
					}));

		} else {
			throw new NotFoundException("Bạn không thể update vào giỏ hàng của người khác");
		}

	}

	@Override
	public CartItem isCartItemExist(Cart cart, Book book, Long customerId) {
		CartItem cartItem = new CartItem();

		cartItem = cartItemRepository.isCartItemExist(cart.getId(), book.getId(), customerId);

		return cartItem;
	}

	@Override
	public CartDto removeCartItem(Long userId, Long cartItemId) {

		CartItem cartItem = findCartItemById(cartItemId);
		User user = userRepository.findUserById(cartItem.getUser().getId());
		User reqUser = userRepository.findUserById(userId);

		if (user.getId().equals(reqUser.getId())) {

			Cart cart = cartRepository.findByUserId(userId);

			List<CartItem> cartItems = cart.getCartItems();

			cartItems.remove(cartItem);

			cartItemRepository.deleteById(cartItemId);

			double totalPrice = 0;
			int totalItem = 0;
			double totalDiscountedPrice = 0;

			for (CartItem cartItem1 : cartItems) {
				totalPrice += cartItem1.getPrice();
				totalDiscountedPrice += cartItem1.getDiscountedPrice();
				totalItem += cartItem1.getQuantity();
			}

			cart.setUser(reqUser);
			cart.setCartItems(cartItems);
			cart.setTotalPrice(totalPrice);
			cart.setTotalItem(totalItem);
			cart.setTotalDiscountedPrice(totalDiscountedPrice);

			cartRepository.save(cart);

			CartDto cartDto = new CartDto();
			List<CartItemDto> cartItemDtos = new ArrayList<>();

			for (CartItem cartItem2 : cartItems) {
				CartItemDto cartItemDto = modelMapper.map(cartItem2, CartItemDto.class);
				cartItemDtos.add(cartItemDto);
			}
			cartDto.setId(cart.getId());

			cartDto.setUserId(cart.getUser().getId());
			cartDto.setCartItemDtos(cartItemDtos);
			cartDto.setTotalPrice(cart.getTotalPrice());
			cartDto.setTotalItem(cart.getTotalItem());
			cartDto.setTotalDiscountedPrice(totalDiscountedPrice);
			cartDto.setDiscounte(cart.getDiscounte());

			return cartDto;
		} else {
			throw new NotFoundException("bạn không thể xóa item của người khác");
		}

	}

	@Override
	public CartItem findCartItemById(Long cartItemId) {
		Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new NotFoundException("cartItem không tìm thấy có id : " + cartItemId);
	}

}
