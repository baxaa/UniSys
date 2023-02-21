package admin; 
 
import java.util.*;

import main.DB;
import user.User;
import user.UserType; 
 
public class SuperAdmin extends User{ 
	private static SuperAdmin instance = new SuperAdmin(UserType.ADMIN,"a", "a", "Admin", "Adminovich");
	
	private SuperAdmin(UserType type,String login,String password, String firstName, String lastName){ 
		super(type,login,password, firstName,lastName); 
	} 
	public static SuperAdmin getInstance() {
		return instance;
	}
	public boolean isExists(String login) {
		if(DB.isExists(login)) return true;
		return false;
	}
	@Override
	public String getOptions() {
		return super.getOptions()+"\n3)add user\n4)delete user\n5)view user info\n6)view users";
	}
	public boolean addUser(User u) { 
		if(isExists(u.getLogin())) return false;
		DB.addUser(u.getLogin(), u);
//		System.out.println(DataBase.users.get(u.getLogin()));
		return true;
	} 
	 public boolean deleteUser(String login) {
//		 System.out.println(login);
//		 System.out.println(isExists(login));
//		 System.out.println(DataBase.users.get(login));
		 if(isExists(login)) {			 
			 DB.deleteUser(login);
			 return true;
		 }
		 return false;
	 } 
	 public boolean updateUser(String login, String password) { 
		 return(DB.users.get(login).changePassword(password)); 
	 }
	 
}
