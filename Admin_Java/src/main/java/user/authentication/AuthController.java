package user.authentication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "*" }, maxAge = 4800)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@GetMapping("/GetActiveUsers")
	List<UserDetails> getActiveUserDetails() {
		List<UserDetails> activeUsers = authService.getActiveUserDetails();
		for (UserDetails userDetails : activeUsers) {
		}
		return activeUsers;
	}

	@GetMapping("/GetAllUsers")
	List<UserDetailsInfo> getAllUserDetails() throws IOException {
		List<UserDetailsInfo> allUSers = new ArrayList<>();
		for (UserDetails userDetails : authService.getAllUserDetails()) {
			UserProfilePic image = this.getImage(userDetails.getUserId());
			UserDetailsInfo user = new UserDetailsInfo(userDetails, image != null ? image.getPicByte() : null);
			allUSers.add(user);
		}
		return allUSers;
	}

	@GetMapping("/GetUser/{id}")
	Optional<UserDetails> getUserDetails(@PathVariable(value = "id") Integer id) {
		return authService.getUserDetailsDetails(id);
	}

	@GetMapping("/GetUserByUsername/{username}")
	UserDetailsInfo getUserDetailsByUsername(@PathVariable(value = "username") String username) throws IOException {
		UserDetails user = authService.getUserDetailsDetailsByUsername(username);
		UserProfilePic image = this.getImage(user.getUserId());
		UserDetailsInfo info = new UserDetailsInfo(user, image != null ? image.getPicByte() : null);
		return info;
	}

	@PostMapping("/CreateUser")
	UserDetails createUserDetails(@RequestBody UserDetails details) {

		UserDetails createUserDetails = authService.createUserDetails(details);

		return createUserDetails;
	}

	@PostMapping("/UpdateUser")
	UserDetails updateUserDetails(@RequestBody UserDetails details) {
		return authService.updateUserDetails(details);
	}

	@PostMapping("/enableDisableUser")
	UserDetails enableDisableUser(@RequestBody UserDetails details) {
		return authService.enableDisableUser(details);
	}

	@PostMapping("/UserDetails/{id}")
	String deleteUserDetails(@PathVariable(value = "id") Integer id) {
		return authService.deleteUserDetails(id);
	}

	@PostMapping("/Authenticate")
	boolean authenticate(@RequestBody UserDetails details) {
		return authService.authenticate(details.getUserName(), details.getPassword());
	}

	@Autowired
	UserProfileRepo userRepo;

	@PostMapping("/uploadProfilePic")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		Integer userId = Integer.valueOf(file.getOriginalFilename());
		UserProfilePic user = new UserProfilePic();
		user.setUserId(userId);
		user.setImgname(file.getOriginalFilename());
		user.setImgtype(file.getContentType());
		user.setPicByte(compressBytes(file.getBytes()));
		userRepo.save(user);
		return ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping(path = { "/getImageData/{id}" })
	public UserProfilePic getImage(@PathVariable("id") Integer id) throws IOException {

		Optional<UserProfilePic> retrievedImage = userRepo.findById(id);
		if (retrievedImage.isPresent()) {
			UserProfilePic img = new UserProfilePic(id, retrievedImage.get().getImgname(),
					retrievedImage.get().getImgtype(), decompressBytes(retrievedImage.get().getPicByte()));
			return img;
		} else {
			return null;
		}
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
