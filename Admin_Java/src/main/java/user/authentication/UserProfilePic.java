package user.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "userProfile",catalog = "admin")
public class UserProfilePic {
	
	@Id
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "imgname")
	private String imgname;

	@Column(name = "imgtype")
	private String imgtype;

	@Column(name = "picByte")
	private byte[] picByte;

	public UserProfilePic(Integer userId, String imgname, String imgtype, byte[] picByte) {
		super();
		this.userId = userId;
		this.imgname = imgname;
		this.imgtype = imgtype;
		this.picByte = picByte;
	}
	
	
	
	
	
}
