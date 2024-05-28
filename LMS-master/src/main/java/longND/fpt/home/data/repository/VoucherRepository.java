package longND.fpt.home.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

	Voucher getVoucherById(Long id);

	Optional<Voucher> findVoucherById(Long id);

	@Query(value = "SELECT * FROM voucher WHERE user_id=:userID AND status = :statusI", nativeQuery = true)
	List<Voucher> getVoucherByUser_IdAndStatus(@Param("userID") Long userID, @Param("statusI") int status);

	Voucher getVoucherByUser_Id(Long userID);

	// co su nham lan xu li giua employee voi user
	Voucher getVoucherByIdAndUser_Id(Long voucherID, Long userID);

	List<Voucher> getVoucherByStatus(int status);

	@Query(value = "SELECT * FROM voucher WHERE code like :code AND status = 1", nativeQuery = true)
	Voucher getVoucherByCode(@Param("code") String code);

	Boolean existsByCode(String code);
}
