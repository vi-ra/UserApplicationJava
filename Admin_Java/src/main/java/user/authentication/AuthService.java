package user.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


public interface AuthService {

	List<UserDetails> getAllUserDetails();

	Optional<UserDetails> getUserDetailsDetails(Integer id);

	UserDetails createUserDetails(UserDetails details);

	String deleteUserDetails(Integer id);

	UserDetails updateUserDetails(UserDetails details);

	List<UserDetails> getActiveUserDetails();

	boolean authenticate(String userName, String password);

	UserDetails getUserDetailsDetailsByUsername(String username);

	UserDetails enableDisableUser(UserDetails details);

}
