package user.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthRepo authRepo;


	@Override
	public List<UserDetails> getAllUserDetails() {
		List<UserDetails> allUsers = authRepo.findAll();
		return allUsers;
	}

	@Override
	public Optional<UserDetails> getUserDetailsDetails(Integer id) {
		return authRepo.findById(id);
	}

	@Override
	public UserDetails createUserDetails(UserDetails details) {
		Integer maxUserId = authRepo.getMaxUserId() == null ? 1 : authRepo.getMaxUserId() + 1;
		details.setUserId(maxUserId);
		details.setPassword(Authentication.encriptPassword("Mes@1234"));
		details.setActiveflag(Boolean.TRUE);
		authRepo.saveAndFlush(details);
		return details;
	}

	@Override
	public UserDetails updateUserDetails(UserDetails details) {
		Optional<UserDetails> user = authRepo.findById(details.getUserId());
		if (user.isPresent()) {
			UserDetails userInfo = user.get();
			if ( !userInfo.getPassword().equals(details.getPassword())) {
				String newPassword = Authentication.encriptPassword(details.getPassword());
				details.setPassword(newPassword);
			}
			authRepo.saveAndFlush(details);
		}
		return details;
	}

	@Override
	public String deleteUserDetails(Integer id) {
		String message = "";
		UserDetails details = null;
		if (!authRepo.findById(id).isPresent()) {
			Optional<UserDetails> findById = authRepo.findById(id);
			details = findById.get();
			details.setActiveflag(false);
			authRepo.saveAndFlush(details);
			message = "UserDetails deleted Sucessfully " + details.toString();
		} else {
			message = "UserDetails Not Created, Id Duplicate" + details.toString();
		}
		return message;
	}

	@Override
	public List<UserDetails> getActiveUserDetails() {
		return authRepo.findActiveUserDetails(true);
	}

	@Override
	public boolean authenticate(String userName, String password) {
		UserDetails userInfo = authRepo.findUserByUserName(userName);
		Boolean sucessFlag = Boolean.FALSE;
		if (userInfo != null) {
			sucessFlag = Authentication.authenticate(userInfo.getPassword(), password);
		} else {
			sucessFlag = false;
		}
		return sucessFlag;
	}

	@Override
	public UserDetails enableDisableUser(UserDetails user) {
		return authRepo.save(user);
	}

	@Override
	public UserDetails getUserDetailsDetailsByUsername(String username) {
		return authRepo.findUserByUserName(username);
	}

	

}
