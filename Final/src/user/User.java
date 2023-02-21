package user;

import java.util.*;
import main.DB;
import java.io.*;

public abstract class User implements Cloneable, Serializable {
	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private UserType type;
	public HashMap<User, String> messages = new HashMap<>();

	public User(UserType type, String login, String password, String firstName, String lastName) {
		id = Objects.hash(login);
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public UserType getType() {
		return type;
	}

	public boolean changePassword(String parameter) {
		if (password.equals(parameter) || parameter.contains(" ") || parameter.length() < 8)
			return false;
		password = parameter;
		return true;
	}

	public String getPassword() {
		return password;
	}

	public String getOptions() {
		return "123)view news\n111)view messages\n112)send message\n1)view profile\n2)change password";
	}

	public boolean equals(Object a) {
		if (a == null)
			return false;
		if (this.getClass() != a.getClass())
			return false;
		if (a == this)
			return true;
		return id == this.id;
	}

	public int hashCode() {
		return id;
	}

	public String toString() {
		return String.format("%s %s\nLogin:%s, Password:%s\n", firstName, lastName, login, password);
	}

	public String getFullName() {
		return firstName + lastName;
	}

	public String getFirstName() {
		return firstName;
	}
//	public void getNews(String news1) {
//		news.add(news1);
//	}

	public String viewNews() {
		for(String s : DB.news) {
			System.out.println(s);
		}
		return "";
	}

	public void sendMessage(User u, String mess) {
		this.getMessage(u, mess);
	}
	public void getMessage(User u, String mess) {
		this.messages.put(u, mess);
	}
}
