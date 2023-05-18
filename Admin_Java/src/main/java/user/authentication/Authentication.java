package user.authentication;

import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class Authentication {
//	public static void main(String[] args) throws Exception {
//
//		UserInfo dbCerd = new UserInfo("MES",
//				"jMuGTas9ZmDi/LCgCZTct8+38t5J9LOWuIsJFk2ROvxmGtNszV3LHZW8jxOajuIpD04Y+HpGjIbghSCw6Rd8Gg==");
//		UserInfo userCred = new UserInfo("MES", "Mes@12345");
//
//		System.out.print(authenticate(userCred, dbCerd));
//	}

	public static String encriptPassword(String password) {
		String enPass = null;
		MessageDigest md = null;
		String msgByte1 = "SHA-512";
		String utfMsg = "UTF-8";
		synchronized (Authentication.class) {
			try {
				md = MessageDigest.getInstance(msgByte1);
				md.update(password.getBytes(utfMsg));
				byte[] rawDigest = md.digest();
				enPass = Base64.getEncoder().encodeToString(rawDigest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return enPass;
	}

	public static boolean authenticate(String dbPass, String userPass) {
		String enteredPassword = Authentication.encriptPassword(userPass);
		return enteredPassword.equals(dbPass);
	}
}
