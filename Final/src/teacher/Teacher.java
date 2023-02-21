package teacher;

import java.util.*;

import employee.Employee;
import main.*;
import student.*;
import user.UserType;

public class Teacher extends Employee {
	TeacherDegree TG;
	private int averageRate; 
	 int count = 0;

	public Teacher(UserType type, String login, String password, String firstName, String lastName, int salary,
			TeacherDegree TG) {
		super(type, login, password, firstName, lastName, salary);
		this.TG = TG;
	}

	public Vector<Course> getCourses() {
		Vector<Course> v = new Vector<>();
		for (Vector<Course> h : DB.courses.values()) {
			for (Course c : h) {
				if (c.getTeacher().equals(this)) {
					v.add(c);
				}
			}
		}
		return v;
	}

	public boolean putAttendance(Course c, int week, Student s) {
		if (getCourses().contains(c)) {
			c.lessons.get(week - 1).mark.get(s).atteandanse = true;
			return true;
		}
		return false;
	}

	public boolean putMark(Course c, int week, Student s, int mark) {
		if (getCourses().contains(c)) {
			c.lessons.get(week - 1).mark.get(s).mark = mark;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "Teacher [TD=" + TG + "]\n";
	}

	@Override
	public String getOptions() {
		return super.getOptions() + "\n3)view courses\n4)view avarage\n5)put mark";
	}

	public void add(int k) {
		this.averageRate += k;
		count++;
	}

	public double getAverageRate	() {
		return averageRate / count;
	}

}
