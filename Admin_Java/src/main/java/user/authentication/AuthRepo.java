package user.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepo extends JpaRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.activeflag = ?1")
	List<User> findActiveUserDetails(boolean b);

	@Query( value = "SELECT * FROM e_commerce.user_details;",nativeQuery = true)
	List<User> findAllUsers();
	
	@Query("SELECT max(userId) FROM User")
	Integer getMaxUserId();

	Optional<User> findByUserName(String username);

}
