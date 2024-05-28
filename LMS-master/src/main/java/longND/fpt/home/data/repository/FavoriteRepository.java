package longND.fpt.home.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

	@Query(value = "SELECT * FROM favorite WHERE user_id =:userId AND is_favorite = true", countQuery = "SELECT COUNT(id) FROM favorite WHERE user_id =:userId AND is_favorite = 1", nativeQuery = true)
	Page<Favorite> findFavoriteByUserId(@Param("userId") Long userId, Pageable pageable);

	Favorite getById(Long id);

	@Query(value = "SELECT * FROM organica.favorite \r\n"
			+ "WHERE book_id = :bookId AND user_id= :userId", nativeQuery = true)
	Favorite findFavoriteByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

	@Query(value = "SELECT * FROM organica.favorite WHERE user_id= :userId AND is_favorite = true AND book_id = :bookId", nativeQuery = true)
	Favorite findFavoriteByUserId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
