package longND.fpt.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.CustomPage;
import longND.fpt.home.data.dto.ViewSearchDto;
import longND.fpt.home.data.modal.Book;
import longND.fpt.home.data.modal.Favorite;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.BookRepository;
import longND.fpt.home.data.repository.FavoriteRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.exception.SaveDataException;
import longND.fpt.home.exception.UpdateDataException;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.FavoriteSevice;
import longND.fpt.home.util.SecurityUtils;

@Service
public class FavoriteSeviceImpl implements FavoriteSevice {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public ResponseEntity<ObjectResponse> createLikeBookByUser(Long bookId) {
		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new NotFoundException("Hãy đăng hập để thêm vào danh sách thích");
		}
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		Book book = bookRepository.getBookById(bookId);

		if (Objects.isNull(book)) {
			throw new NotFoundException("book_id không tồn tại");
		} else {

			Favorite favorite = favoriteRepository.findFavoriteByUserIdAndBookId(user.getId(), bookId);
			if (!Objects.isNull(favorite)) {
				favorite.setFavorite(!favorite.isFavorite());
				Favorite update = favoriteRepository.save(favorite);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ObjectResponse("Sửa Like or dislike thành công", new HashMap<>() {
							{
								put("favoriteId", update.getId());
								put("type", true);
								put("statusFavorite", update.isFavorite());
							}
						}));
			} else {
				Favorite favorites = Favorite.builder().book(book).user(user).isFavorite(true).build();

				Favorite save = favoriteRepository.save(favorites);

				if (Objects.isNull(save)) {
					throw new SaveDataException("Like không thành công");
				} else {
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ObjectResponse("Like or dislike thành công", new HashMap<>() {
								{
									put("favoriteId", save.getId());
									put("statusFavorite", save.isFavorite());
								}
							}));
				}
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> editLikeBookByUser(Long bookId) {
		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new NotFoundException("Hãy đăng hập để thêm vào danh sách thích");
		}
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		Favorite favorite = favoriteRepository.findFavoriteByUserIdAndBookId(user.getId(), bookId);

		if (Objects.isNull(favorite)) {
			throw new NotFoundException("favarite-ID không tồn tại");
		} else {
			if (user.getId() != favorite.getUser().getId()) {
				throw new NotFoundException("User không phải là người like or dislike");
			} else {
				favorite.setFavorite(!favorite.isFavorite());
				Favorite update = favoriteRepository.save(favorite);

				if (Objects.isNull(update)) {
					throw new UpdateDataException("Update không thành công");
				} else {
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ObjectResponse("Update Like or dislike success", new HashMap<>() {
								{
									put("favoriteId", update.getId());
									put("type", true);
									put("statusFavorite", update.isFavorite());
								}
							}));
				}
			}
		}
	}

	@Override
	public ResponseEntity<ObjectResponse> getAllBookFavoriteByUserId(int indexPage) {
		int size = 8;
		int page = indexPage - 1;

		Pageable pageable = PageRequest.of(page, size);

		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new NotFoundException("User không tồn tại");
		} else {
			User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());
			Page<Favorite> favorites = favoriteRepository.findFavoriteByUserId(user.getId(), pageable);

			List<Book> books = new ArrayList<>();
			for (Favorite favorite : favorites) {
				Book book = new Book();
				book.setId(favorite.getBook().getId());
				book.setTitle(favorite.getBook().getTitle());
				book.setDescription(favorite.getBook().getDescription());
				book.setPrice(favorite.getBook().getPrice());
				book.setImageUrl(favorite.getBook().getImageUrl());
				book.setCopies(favorite.getBook().getCopies());
				book.setCopies_available(favorite.getBook().getCopies_available());
				book.setLanguage(favorite.getBook().getLanguage());
				book.setForUser(favorite.getBook().isForUser());
				book.setAuthors(favorite.getBook().getAuthors());
				book.setDepartments(favorite.getBook().getDepartments());
				book.setPublisher(favorite.getBook().getPublisher());
				books.add(book);
			}

			List<ViewSearchDto> viewSearchDtoList = new ArrayList<>();
			books.forEach(v -> {
				ViewSearchDto dto = ViewSearchDto.builder().bookId(v.getId()).title(v.getTitle())
						.description(v.getDescription()).price(v.getPrice()).imageUrl(v.getImageUrl()).build();
				viewSearchDtoList.add(dto);
			});

			CustomPage<ViewSearchDto> pageResponse = new CustomPage<>(viewSearchDtoList, indexPage, size,
					favorites.getTotalElements(), favorites.getTotalPages());
			return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("list book like", new HashMap<>() {
				{
					put("userId", user.getId());
					put("litsFavorite", pageResponse);
				}
			}));
		}
	}

}
