package longND.fpt.home.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import longND.fpt.home.data.modal.Cart;



@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "SELECT * FROM cart WHERE user_id=:userId AND is_ordered is false", nativeQuery = true)
	public Cart findByUserId(@Param("userId") Long userId);
}
