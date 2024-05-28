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
	
	@Query(value = "value = "SELECT role.*, user_role.user_id FROM role LEFT JOIN user_role ON role.id = user_role.role_id WHERE user_role.user_id = :userId", nativeQuery = true)
	Role findRoleByUserId(@Param("userId") Long userId);
}
