package user.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_details", catalog = "admin")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "u_Name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "designation")
	private String designation;

	@Column(name = "email")
	private String email;

	@Column(name = "contact_no")
	private String contactNo;
	
	@Column(name = "active_Flag")
	private boolean activeflag;
	
	

	public UserDetails(Integer userId, String firstName, String lastName, String userName, String password,
			String designation, String email, String contactNo, String imgtype, boolean activeflag) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.designation = designation;
		this.email = email;
		this.contactNo = contactNo;
		this.activeflag = activeflag;
	}

}
