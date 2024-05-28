package longND.fpt.home.data.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import longND.fpt.home.data.modal.Book;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class BookDto {

	private Long id;

	private String title;

	private String description;

	private double price;

	private String imageUrl;

	private LocalDateTime createAt;

	private int copies;

	private int copies_available;

	private String language;

	private boolean forUser;

	private int page;

	private List<AuthorDto> authors;

	private List<DepartmentDto> departments;

	private PublisherDto publisher;
	
	private boolean isLiked;
	
	private double rate;
	private int totalRate;
}
