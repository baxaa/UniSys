package student;

import java.util.*;

import main.*;
import teacher.Teacher;
import user.User;
import user.UserType;

public class Student extends User {
	private Degree degree;
	YearOfStudy YOS;
	Faculty faculty;
	private int credits;
	private double GPA;
	public Vector<Course> courses = new Vector<>();

	public Student(UserType type, String login, String password, String firstName, String lastName, Degree degree,
			YearOfStudy YOS, Faculty faculty) {
		super(type, login, password, firstName, lastName);
		this.degree = degree;
		this.YOS = YOS;
		this.faculty = faculty;
	}

//	public void viewInfoAboutTeacher() {
//		for (Teacher i : t) {
//			System.out.println(i);
//		}
//	}

	// public void viewTranscript( HashMap<Course, double> hm) {
	//
	// }
//	public void rateTeachers(Vector<Teacher> t, double d) {
//		for (Teacher i : t) {
//			System.out.println(i.getFirstName() + " " + i.getFirstName() + ":\n" + "rate this teacher at 10 marks: ");
//		}
//	}

	public void viewMarks(Course c) {
		for (Lesson i : c.lessons) {
			if (i.mark.containsKey(this)) {
				System.out.println(i.mark.get(this));
			}
		}
	}

	public boolean addCourse(Course c) {
		for (Course c1 : courses) {
			if (c.getName() == c1.getName())
				return false;
		}
		credits += c.getCreditsAmount();
		courses.add(c);
		return true;
	}

	public String getOptions() {
		return super.getOptions()
				+ "\n3)view marks\n4)rate teachers\n5)request to book\n6)view news\n7)view schedule\n8)register for course\n9)return book\n10)view atestation\n11)view courses";
	}

	public String viewNews(String s) {
		return s;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Degree getDegree() {
		return degree;
	}

	public int getCredits() {
		return credits;
	}

	public Vector<Teacher> getTeachers() {
		Vector<Teacher> v = new Vector<>();
		for (Course c : courses) {
			v.add(c.getTeacher());
		}
		return v;
	}

}
