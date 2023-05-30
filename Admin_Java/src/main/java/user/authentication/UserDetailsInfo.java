package user.authentication;

import lombok.Data;

@Data
public class UserDetailsInfo {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String designation;
	private String email;
	private String contactNo;
	private boolean activeflag;
	private byte[] picByte;
	
	
	public UserDetailsInfo(User userDetails, byte[] picByte) {
		super();
		this.userId = userDetails.getUserId();
		this.firstName = userDetails.getFirstName();
		this.lastName = userDetails.getLastName();
		this.userName = userDetails.getUsername();
		this.password = userDetails.getPassword();
		this.designation = userDetails.getDesignation();
		this.email = userDetails.getEmail();
		this.contactNo = userDetails.getContactNo();
		this.activeflag = userDetails.isActiveflag();
		this.picByte = picByte;
	}
	
	
	
	
	
}
