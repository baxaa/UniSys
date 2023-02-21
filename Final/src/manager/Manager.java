package manager;

import java.util.*;

import employee.Employee;
import student.*;
import teacher.Teacher;
import main.*;
import user.*;

public class Manager extends Employee {
	HashMap<String, Vector<Course>> courses = DB.courses;

	public Manager(UserType type, String login, String password,String firstName, String lastName, int salary) {
		super(type, login, password, firstName, lastName, salary);
	}
	
	public boolean addCourse(Course c) {
		if (courses.get(c.getName()) == null) {
			courses.put(c.getName(), new Vector<Course>());
		}
//		if(!courses.get(c.getName()).contains(c) || c.getTeacher().equals(DB.users.get("v"))) {
//		}
			courses.get(c.getName()).add(c);			
		return true;
	}

	public void manageNews(String s) {
		DB.news.add(s);
	}

	public boolean viewRequests() {
		return false;
	}

	public String viewStudentsInfo(Student s) {
		return s.toString();
	}
	
	public String viewTeachersInfo(Teacher t) {
		return t.toString();
	}

	public boolean deleteCourse(Course c) {
		if(DB.courses.get(c.getName())==null) return false;
		courses.remove(c.getName());
		return true;
	}

	public String getOptions() {
		return super.getOptions() + "\n3)add course\n4)delete course\n5)view student's info\n6)view teacher's info\n7)view courses\n8)create lessons\n9)manage news";
	}
}
