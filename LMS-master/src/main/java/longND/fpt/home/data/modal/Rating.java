package longND.fpt.home.data.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import longND.fpt.home.data.modal.key.BookRatingKey;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Data
@Table(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

	@EmbeddedId
    BookRatingKey id;

    private String message;
    private Double stars;

    @JsonIgnore
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name="book_id", nullable=false)
    private Book bookRate;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User userRate;

}
