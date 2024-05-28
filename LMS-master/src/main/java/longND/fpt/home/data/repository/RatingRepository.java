package longND.fpt.home.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.Rating;
import longND.fpt.home.data.modal.key.BookRatingKey;

@Repository
public interface RatingRepository extends JpaRepository<Rating, BookRatingKey> {

	@Query(value = "SELECT * FROM rating where user_id = :userId and book_id = :bookId", nativeQuery = true)
	Rating findRatingByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

	@Query(value = "SELECT * FROM rating where book_id = :bookId", nativeQuery = true)
	List<Rating> findRatingByBookId(@Param("bookId") Long bookId);

	@Query(value = "SELECT AVG(stars) FROM rating where book_id = :bookId", nativeQuery = true)
	double totalRatingByBookId(@Param("bookId") Long bookId);
}
