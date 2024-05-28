package longND.fpt.home.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.Role;	

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

	boolean existsByName(String name);
	
	@Query(value = "SELECT roles.* FROM roles LEFT JOIN user ON roles.role_id = user.role_id WHERE ur.user_id = :userId", nativeQuery = true)
	Role findRoleByUserId(@Param("userId") Long userId);
}
