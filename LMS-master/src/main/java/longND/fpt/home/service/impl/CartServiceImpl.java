package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.CartDto;
import longND.fpt.home.data.dto.CartItemDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Cart;
import longND.fpt.home.data.modal.CartItem;
import longND.fpt.home.data.modal.Role;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.modal.Voucher;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.CartItemRepository;
import longND.fpt.home.data.repository.CartRepository;
import longND.fpt.home.data.repository.RoleRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.data.repository.VoucherRepository;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.BadRequestAlertException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.presentation.payload.request.AddItemRequest;
import longND.fpt.home.service.CartItemService;
import longND.fpt.home.service.CartService;
import longND.fpt.home.util.SecurityUtils;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Cart createCart(User user) {

		Cart cart = new Cart();
		cart.setUser(user);
		cart.setOrdered(false);
		Cart createdCart = cartRepository.save(cart);
		return createdCart;
	}

	@Override
	public CartDto findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);

		CartDto cartDto = new CartDto();

		if (cart != null) {
			int totalPrice = 0;
			int totalDiscountedPrice = 0;
			int totalItem = 0;
			for (CartItem cartsItem : cart.getCartItems()) {
				totalPrice += cartsItem.getPrice();
				totalDiscountedPrice += cartsItem.getDiscountedPrice();
				totalItem += cartsItem.getQuantity();
			}

			cart.setTotalPrice(totalPrice);
			cart.setTotalDiscountedPrice(totalDiscountedPrice);
			cart.setDiscounte(totalPrice - totalDiscountedPrice);
			cart.setTotalItem(totalItem);

			cartRepository.save(cart);

			cartDto.setId(cart.getId());
			cartDto.setUserId(cart.getUser().getId());

			List<CartItemDto> cartItemDtos = new ArrayList<>();

			List<CartItem> cartItems = cart.getCartItems();

			for (CartItem cartItem : cartItems) {
				CartItemDto cartItemDto = modelMapper.map(cartItem, CartItemDto.class);
				cartItemDto.setTitle(cartItem.getBook().getTitle());
				cartItemDto.setImageUrl(cartItem.getBook().getImageUrl());
				cartItemDtos.add(cartItemDto);
			}

			cartDto.setCartItemDtos(cartItemDtos);
			cartDto.setTotalPrice(cart.getTotalPrice());
			cartDto.setTotalItem(cart.getTotalItem());
			cartDto.setTotalDiscountedPrice(totalDiscountedPrice);
			cartDto.setDiscounte(cart.getDiscounte());
		}
		return cartDto;

	}

	@Override
	public CartItemDto addCartItem(Long userId, AddItemRequest req) {

		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new AuthException("Bạn hãy đăng kí thành viên để đặt đơn mượn sách online");
		}
		User user = userRepository.findUserById(userId);
		Cart cart = cartRepository.findByUserId(userId);
		Book book = bookRepository.getBookById(req.getBookId());
		CartItemDto cartItemDto = new CartItemDto();
		CartItem createdCartItem = null;

		if (Objects.isNull(cart)) {
			System.out.println("cart null");
			Cart carts = new Cart();
			carts.setUser(user);
			cartRepository.save(carts);

			CartItem isPresent = cartItemService.isCartItemExist(carts, book, userId);

			if (Objects.isNull(isPresent)) {

				CartItem cartItem = new CartItem();
				cartItem.setBook(book);
				cartItem.setCart(carts);
				if (book.getCopies_available() < req.getQuantity()) {
					throw new BadRequestAlertException(
							"Hiện tại trong thư viện chỉ còn " + book.getCopies_available() + " cuốn");
				}
				cartItem.setQuantity(req.getQuantity());
				cartItem.setUser(user);

				cartItem.setPrice(req.getQuantity() * book.getPrice());
				Voucher voucher = voucherRepository.getVoucherByUser_Id(userId);
				if (Objects.isNull(voucher)) {
					cartItem.setDiscountedPrice(0);
					carts.setDiscounte(0);
				} else {
					cartItem.setVoucher(voucher);
					double price = req.getQuantity() * (book.getPrice() * (1 - (voucher.getPercent() / 100)));
					cartItem.setDiscountedPrice(price);
					carts.setDiscounte((double) voucher.getPercent());
				}

				createdCartItem = cartItemService.createCartItem(cartItem);

				List<CartItem> listCaItem = new ArrayList<>();
				listCaItem.add(createdCartItem);
				carts.setCartItems(listCaItem);
				carts.setTotalPrice(cartItem.getPrice());
				carts.setTotalItem(1);
				carts.setTotalDiscountedPrice(cartItem.getDiscountedPrice());

				cartRepository.save(carts);
			}
		} else {
			CartItem isPresent = cartItemService.isCartItemExist(cart, book, userId);

			if (Objects.isNull(isPresent)) {
				CartItem cartItem = new CartItem();
				cartItem.setBook(book);
				cartItem.setCart(cart);
				if (book.getCopies_available() < req.getQuantity()) {
					throw new BadRequestAlertException(
							"Hiện tại trong thư viện chỉ còn " + book.getCopies_available() + " cuốn");
				}
				cartItem.setQuantity(req.getQuantity());
				cartItem.setUser(user);

				cartItem.setPrice(req.getQuantity() * book.getPrice());
				Voucher voucher = voucherRepository.getVoucherByUser_Id(userId);
				if (Objects.isNull(voucher)) {
					cartItem.setDiscountedPrice(0);
					cart.setDiscounte(0);
				} else {
					double price = req.getQuantity() * (book.getPrice() * (1 - (voucher.getPercent() / 100)));
					cartItem.setDiscountedPrice(price);
					cartItem.setVoucher(voucher);
					cart.setDiscounte((double) voucher.getPercent());
				}

				createdCartItem = cartItemService.createCartItem(cartItem);

				int totalPrice = 0;
				int totalDiscountedPrice = 0;
				int totalItem = 0;
				for (CartItem cartsItem : cart.getCartItems()) {
					totalPrice += cartsItem.getPrice();
					totalDiscountedPrice += cartsItem.getDiscountedPrice();
					totalItem += cartsItem.getQuantity();
				}

				cart.setTotalPrice(totalPrice);
				cart.setTotalItem(totalItem);
				cart.setTotalDiscountedPrice(totalDiscountedPrice);
				cart.setDiscounte(totalPrice - totalDiscountedPrice);

				cartRepository.save(cart);
			} else {
				// update cart item in cart
				int newQuantity = isPresent.getQuantity() + 1;
				isPresent.setQuantity(newQuantity);
				if (book.getCopies_available() < isPresent.getQuantity()) {
					throw new BadRequestAlertException(
							"Hiện tại trong thư viện chỉ còn " + book.getCopies_available() + " cuốn");
				}
				isPresent.setPrice(newQuantity * book.getPrice());
				Voucher voucher = voucherRepository.getVoucherByUser_Id(userId);
				if (Objects.isNull(voucher)) {
					isPresent.setDiscountedPrice(0);
					cart.setDiscounte(0);
				} else {
					double price = newQuantity * (book.getPrice() * (1 - (voucher.getPercent() / 100)));
					isPresent.setDiscountedPrice(price);
					isPresent.setVoucher(voucher);
					cart.setDiscounte((double) voucher.getPercent());
				}

				createdCartItem = isPresent;
				cartItemRepository.save(isPresent);
			}
		}

		cartItemDto.setId(createdCartItem.getId());
		cartItemDto.setCartId(createdCartItem.getCart().getId());
		cartItemDto.setBookId(createdCartItem.getBook().getId());
		cartItemDto.setQuantity(createdCartItem.getQuantity());
		cartItemDto.setPrice(createdCartItem.getPrice());
		cartItemDto.setDiscountedPrice(createdCartItem.getDiscountedPrice());
		if (!Objects.isNull(createdCartItem.getVoucher())) {
			cartItemDto.setVoucherId(createdCartItem.getVoucher().getId());
		}

		return cartItemDto;
	}

}
