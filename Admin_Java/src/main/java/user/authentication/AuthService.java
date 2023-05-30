package user.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


public interface AuthService {

	List<User> getAllUserDetails();

	Optional<User> getUserDetailsDetails(Integer id);

	User createUserDetails(User details);

	String deleteUserDetails(Integer id);

	User updateUserDetails(User details);

	List<User> getActiveUserDetails();

	boolean authenticate(String userName, String password);

	User getUserDetailsDetailsByUsername(String username);

	User enableDisableUser(User details);

}
