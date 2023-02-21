package main;

import user.*;

import java.util.Collection;
import java.util.Vector;

import admin.*;
import librarian.*;
import librarian.Librarian;
import manager.Manager;
import student.Degree;
import student.Faculty;
import student.Student;
import student.YearOfStudy;
import teacher.Teacher;
import teacher.TeacherDegree;

public class Test2 {
	public static void main(String args[]) {
		DB.addUser("v",new Teacher(UserType.TEACHER,"v","v", "vacation", "lname",0, TeacherDegree.LECTOR ) );
		DB.addUser("a",SuperAdmin.getInstance() );
		DB.addUser("l", Librarian.getInstance());
		DB.addUser("m", new Manager(UserType.MANAGER, "m", "m", "m","m", 0));
		DB.addUser("s", new Student(UserType.STUDENT, "s", "s", "s","s",Degree.bachelor, YearOfStudy.FIRST, Faculty.BS));
		DB.addUser("t",new Teacher(UserType.TEACHER,"t","t", "fname ", "lname",0, TeacherDegree.LECTOR ) );
//		DB.start();
//		for(User u:DB.users.values()) {
//			System.out.println(u.toString());
//		}
////		
//		for (Vector<Course> c : DB.courses.values()) {
//			System.out.println(c);
//			System.out.println(c.get(0).lessons.get(0).mark.keySet());
//
//		}
//		System.out.println(((Student)DB.users.get("s")).courses);
//		System.out.println();
//		System.out.println();
//		System.out.println(getStudents(DB.courses.get("oop").get(0)));
//		DB.libraryBooks.add(new Book("name1", "author1", 80));
//		DB.libraryBooks.add(new Book("name2", "author2", 80));
//		DB.libraryBooks.add(new Book("name3", "author3", 80));
//		DB.libraryBooks.add(new Book("name4", "author4", 80));
////		
		DB.save();
//		DB.addUser("l", new Student(UserType.STUDENT, "l", "p", "p", "p", Degree.bachelor, YearOfStudy.FIRST,Faculty.BS ));
//		System.out.println(DB.users.values());
//		Manager m = new Manager(UserType.MANAGER, "l", "p", "n", "n", 500);
//		Course c  = new Course("s", "s", 5, 5);
//		c.setTeacher(t);
//		m.addCourse(c);
//		t.getCourses();
	}

	public static Vector<Course> getCourses(Teacher t) {
		Vector<Course> v = new Vector<>();
		for (Vector<Course> h : DB.courses.values()) {
			for (Course c : h) {
				if (c.getTeacher().equals(t)) {
					v.add(c);
				}
			}
		}
		return v;
	}

	public static Vector<Student> getStudents(Course c) {
		System.out.println(c);
		Vector<Student> v = new Vector<>();
		for (User u : DB.users.values()) {
			if (u instanceof Student && ((Student) u).courses.contains(c)) {
				System.out.println(u);
				v.add((Student) u);
			}
		}
		return v;
	}

}