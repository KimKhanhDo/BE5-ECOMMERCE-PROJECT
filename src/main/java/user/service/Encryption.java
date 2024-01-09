package user.service;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;


public class Encryption {
	
	public static String convertToSHA1(String userPassword) {
		String salt = "asb@h;ds$vdghy?";
		String result = null;
		
		userPassword = userPassword + salt;
		
		try {
			byte[] dataBytes = userPassword.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(messageDigest.digest(dataBytes));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(convertToSHA1("123456"));
	}

}
