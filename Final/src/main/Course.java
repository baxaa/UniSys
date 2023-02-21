package main;

import java.util.*;
import java.io.*;
import student.Student;
import teacher.Teacher;
import user.User;

public class Course implements Clonable, Serializable {
	private int id;
	private String name;
	private String description;
	private int creditsAmount;
	private int capacity;
	private Teacher teacher = (Teacher) DB.users.get("v");
	public Vector<Lesson> lessons = new Vector<>();
	public Vector<String> prereqs = new Vector<>();

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course(String name, String description, int creditsAmount, int capacity) {
		id = Objects.hash(name);
		this.name = name;
		this.description = description;
		this.creditsAmount = creditsAmount;
		this.capacity = capacity;
	}
	public Vector<Student> getStudents() {
		Vector<Student> v = new Vector<>();
		for (User u : DB.users.values()) {
			if (u instanceof Student && ((Student) u).courses.contains(this)) {
				v.add((Student) u);
			}
		}
		return v;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, teacher);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return id == other.id && Objects.equals(teacher, other.teacher);
	}

	public void addPrereqs(String c) {

		prereqs.add(c);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public int getCreditsAmount() {
		return creditsAmount;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", description=" + description + ", creditsAmount=" + creditsAmount
				+ ", capacity=" + capacity + ", teacher=" + teacher + ", prereqs=" + prereqs + "]";
	}

	@SuppressWarnings("unchecked")
	public Course clone() {
		Course n = new Course(name, description, creditsAmount, capacity);
		n.prereqs = (Vector<String>) prereqs.clone();
		return n;
	}

}
