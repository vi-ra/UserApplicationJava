package user.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthRepo authRepo;

	@Override
	public List<User> getAllUserDetails() {
		List<User> allUsers = authRepo.findAll();
		return allUsers;
	}

	@Override
	public Optional<User> getUserDetailsDetails(Integer id) {
		return authRepo.findById(id);
	}

	@Override
	public User createUserDetails(User details) {
		Integer maxUserId = authRepo.getMaxUserId() == null ? 1 : authRepo.getMaxUserId() + 1;
		details.setUserId(maxUserId);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		details.setPassword(passwordEncoder.encode("Mes@1234"));
		details.setActiveflag(Boolean.TRUE);
		authRepo.saveAndFlush(details);
		return details;
	}

	@Override
	public User updateUserDetails(User details) {
		Optional<User> user = authRepo.findById(details.getUserId());
		if (user.isPresent()) {
			User userInfo = user.get();
			if (!userInfo.getPassword().equals(details.getPassword())) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String newPassword = passwordEncoder.encode(details.getPassword());
				details.setPassword(newPassword);
			}
			authRepo.saveAndFlush(details);
		}
		return details;
	}

	@Override
	public String deleteUserDetails(Integer id) {
		String message = "";
		User details = null;
		if (!authRepo.findById(id).isPresent()) {
			Optional<User> findById = authRepo.findById(id);
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
	public List<User> getActiveUserDetails() {
		return authRepo.findActiveUserDetails(true);
	}

	@Override
	public boolean authenticate(String userName, String password) {
		User userInfo = authRepo.findByUserName(userName).get();
		Boolean sucessFlag = Boolean.FALSE;
		if (userInfo != null) {
			sucessFlag = Authentication1.authenticate(userInfo.getPassword(), password);
		} else {
			sucessFlag = false;
		}
		return sucessFlag;
	}

	@Override
	public User enableDisableUser(User user) {
		return authRepo.save(user);
	}

	@Override
	public User getUserDetailsDetailsByUsername(String username) {
		return authRepo.findByUserName(username).get();
	}

}
