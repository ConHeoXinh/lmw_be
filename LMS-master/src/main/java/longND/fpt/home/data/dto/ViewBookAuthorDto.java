package longND.fpt.home.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import longND.fpt.home.data.modal.Book;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewBookAuthorDto {
	private Long id;
	private String title;
	private String description;
	private String imageUrl;
	private int copies;
	private int copies_available;
	
	public static ViewBookAuthorDto convertToVBADtoV2(Book book) {
		ViewBookAuthorDto bookDto = new ViewBookAuthorDto();
		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		bookDto.setImageUrl(book.getImageUrl());
		bookDto.setCopies_available(book.getCopies_available());
		bookDto.setCopies(book.getCopies());
		return bookDto;
	}
}
