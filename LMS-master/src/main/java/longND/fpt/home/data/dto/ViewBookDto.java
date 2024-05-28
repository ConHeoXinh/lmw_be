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
public class ViewBookDto {
	private Long id;
	private String title;
	private String description;
	private String imageUrl;
	
	public static ViewBookDto convertToViewBookDto(Book book) {
		ViewBookDto bookDto = new ViewBookDto();
		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		bookDto.setImageUrl(book.getImageUrl());
		return bookDto;
	}
}
