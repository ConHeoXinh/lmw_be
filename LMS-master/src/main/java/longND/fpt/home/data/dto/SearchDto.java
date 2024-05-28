package longND.fpt.home.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import longND.fpt.home.data.modal.Book;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SearchDto {

	private Long bookId;
	private String title;
	private String desciption;
	private double price;
	private String imageUrl;
	private int copies;
	private int copies_available;
	private String language;
	private List<DepartmentDto> departmentDtoList;
	private List<AuthorDto> authorDtoList;
	private PublisherDto publisheDto;
	private double rate;
	
	public static SearchDto convertToSearchDtoV2(Book book) {
		SearchDto bookDto = new SearchDto();
		bookDto.setBookId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setDesciption(book.getDescription());
		bookDto.setImageUrl(book.getImageUrl());
		bookDto.setCopies_available(book.getCopies_available());
		bookDto.setCopies(book.getCopies());
		return bookDto;
	}
}
