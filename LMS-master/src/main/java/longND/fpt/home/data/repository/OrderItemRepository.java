package longND.fpt.home.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.OrderItem;
import longND.fpt.home.data.modal.User;

@Repository	
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findAllOrderItemsByOrderId(Long orderId);

	@Query(value = "SELECT * FROM organica.order_item WHERE user_id = :userId AND id = :orderItemId", nativeQuery = true)
	OrderItem findOrderItemByUserIdAndOrderItemId(@Param("userId") Long userId, @Param("orderItemId") Long orderItemId);

	Page<OrderItem> findAllByUser(User user, Pageable pageable);

	Page<OrderItem> findAllByEmployee(User user, Pageable pageable);

	@Query(value = "SELECT order_item.* FROM organica.order_item\r\n"
			+ "LEFT JOIN orderbook ON order_item.order_id = orderbook.id\r\n"
			+ "WHERE order_item.user_id= :userId AND orderbook.order_status is true ORDER BY is_return ASC", countQuery = "SELECT COUNT(order_item.id) FROM organica.order_item LEFT JOIN orderbook ON order_item.order_id = orderbook.id WHERE order_item.user_id= :userId AND orderbook.order_status is true", nativeQuery = true)
	Page<OrderItem> getAllOrderItemByUserId(@Param("userId") Long userId, Pageable pageable);

	@Query(value = "SELECT order_item.* FROM organica.order_item\r\n"
			+ " LEFT JOIN orderbook ON order_item.order_id = orderbook.id \r\n"
			+ " WHERE orderbook.order_status is true\r\n"
			+ " ORDER BY is_return ASC", countQuery = "SELECT order_item.* FROM organica.order_item\r\n"
					+ " LEFT JOIN orderbook ON order_item.order_id = orderbook.id \r\n"
					+ " WHERE orderbook.order_status is true\r\n", nativeQuery = true)
	Page<OrderItem> getAllOrderItemByEmployee(Pageable pageable);

	@Query(value = "SELECT * FROM order_item WHERE checkout_date >= cast( :checkoutDate AS DATE) AND return_date <= cast( :returnDate AS DATE) ORDER BY book_id DESC", nativeQuery = true)
	List<OrderItem> getTop10BookFilterDate(@Param("checkoutDate") String checkoutDate,
			@Param("returnDate") String returnDate);

	@Query(value = "SELECT * FROM order_item WHERE code_order LIKE %:code%", countQuery = "SELECT COUNT(order_item.id) FROM order_item WHERE code_order LIKE %:code%", nativeQuery = true)
	Page<OrderItem> searchByCode(@Param("code") String code, Pageable pageable);
}
