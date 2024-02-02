package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class User {
	private int id;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String gender;
	private String[] hobbies;
	
	
	public User(String userName, String password, String fullName, String email, String gender, String[] hobbies) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.gender = gender;
		this.hobbies = hobbies;
	}


	public User(int id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}






}