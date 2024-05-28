package longND.fpt.home.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.Order;
import longND.fpt.home.data.modal.User;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT * FROM orderbook WHERE user_id = :userId AND employee_id IS NULL AND order_status = false", nativeQuery = true)
	Order existsOrderByUseIdAndEmployeeIdEndOrderStatus(@Param("userId") Long userId);

	List<Order> findByUserId(Long userId);

	Order getById(Long id);

	@Query(value = "SELECT * FROM orderbook WHERE employee_id IS NULL AND order_status = false", nativeQuery = true)
	List<Order> findAll();

	List<Order> findAllByUser(User user);

	@Query(value = "SELECT * FROM orderbook WHERE employee_id IS NULL AND order_status = false", countQuery = "SELECT COUNT(orderbook.id) FROM orderbook WHERE employee_id IS NULL AND order_status = false", nativeQuery = true)
	Page<Order> findAllOrders(Pageable pageable);

	@Query(value = "SELECT * FROM organica.orderbook ORDER BY return_date DESC", countQuery = "SELECT * FROM organica.orderbook ORDER BY return_date DESC", nativeQuery = true)
	Page<Order> findAllOrdersExpire(Pageable pageable);

	boolean existsByCode(String code);

	@Query(value = "SELECT * FROM orderbook WHERE code LIKE %:code%", countQuery = "SELECT COUNT(orderbook.id) FROM orderbook WHERE code LIKE %:code%", nativeQuery = true)
	Page<Order> searchByCode(@Param("code") String code, Pageable pageable);

	// select all order
	@Query(value = "SELECT * FROM organica.orderbook WHERE created_at between cast(:dateSelect1 AS date) AND cast(:dateSelect2 AS date) ORDER BY created_at ASC", nativeQuery = true)
	List<Order> getAllOrderDashboard(@Param("dateSelect1") String dateSelect1,
			@Param("dateSelect2") String dateSelect2);
}
