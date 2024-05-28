package longND.fpt.home.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import longND.fpt.home.data.modal.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
	
	Boolean existsByPhoneNumber(String phoneNumber);

	Boolean existsByEmail(String email);

	Boolean existsByPassword(String password);

	User getUserByCodeActive(String otp);

	User getUserByEmail(String email);

	User findUserById(Long id);

	User getUserByUsername(String username);

	@Query(value = "SELECT count(id) FROM user ", nativeQuery = true)
	int totalAccount();

	Page<User> findAllByRoles_Id(Pageable pageable, Long rolesId);
}
