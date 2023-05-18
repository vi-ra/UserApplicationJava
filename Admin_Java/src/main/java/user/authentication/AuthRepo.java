package user.authentication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepo extends JpaRepository<UserDetails, Integer>{

	@Query("SELECT u FROM UserDetails u WHERE u.userName = ?1")
	UserDetails findUserByUserName(String username);

	@Query("SELECT u FROM UserDetails u WHERE u.activeflag = ?1")
	List<UserDetails> findActiveUserDetails(boolean b);

	@Query( value = "SELECT * FROM e_commerce.user_details;",nativeQuery = true)
	List<UserDetails> findAllUsers();
	
	@Query("SELECT max(userId) FROM UserDetails")
	Integer getMaxUserId();

}
