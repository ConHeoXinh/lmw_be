package longND.fpt.home.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import longND.fpt.home.data.modal.Book;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ViewBookBorrowDto {
	private Long id;

	private String title;

	private String description;

	private double price;

	private String imageUrl;

	private boolean forUser;

	public ViewBookBorrowDto convertToBookDto(Book book) {
		ViewBookBorrowDto bookDto = new ViewBookBorrowDto();

		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		bookDto.setPrice(book.getPrice());
		bookDto.setImageUrl(book.getImageUrl());
		bookDto.setForUser(book.isForUser());

		return bookDto;
	}
}
