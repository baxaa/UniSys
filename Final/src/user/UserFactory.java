package user;

import manager.Manager;
import student.*;
import teacher.*;
public class UserFactory {
	public User getUser(String cur,String arr[]) {
		if(cur.equalsIgnoreCase("student")) {
			return new Student(UserType.getType(cur), arr[0],arr[1],arr[2],arr[3],Degree.getDegree(arr[4]),YearOfStudy.getYearOfStudy(arr[5]),Faculty.getFaculty(arr[6]));
		}
		if(cur.equalsIgnoreCase("teacher")) {
			return new Teacher(UserType.getType(cur), arr[0],arr[1],arr[2],arr[3],Integer.parseInt(arr[4]),TeacherDegree.getDegree(arr[5]));
		}
		if(cur.equalsIgnoreCase("manager")) {
			return new Manager(UserType.getType(cur), arr[0],arr[1],arr[2],arr[3],Integer.parseInt(arr[4]));
		}
		return null;
	}
}
