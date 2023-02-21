package main;

import java.io.*;
import java.util.*;

import admin.SuperAdmin;
import librarian.*;
import student.Student;
import user.User;
import teacher.Teacher;

public class DB {
	public static HashMap<String, User> users = new HashMap<>();
	public static HashMap<String, Vector<Course>> courses = new HashMap<>();
	public static Vector<Book> libraryBooks = new Vector<>();
	public static Vector<String> news = new Vector<>();
	public static HashMap<Student, Book> givenBooks = new HashMap<>();

	public static boolean isExists(String login) {
		if (users.get(login) != null)
			return true;
		return false;
	}

	public static void addUser(String login, User user) {
		users.put(login, user);
	}

	public static void deleteUser(String login) {
		users.remove(login);
	}

	@SuppressWarnings("unchecked")
	public static void start() {
		try {
			FileInputStream fis1 = new FileInputStream("DB/users.bin");
			FileInputStream fis2 = new FileInputStream("DB/courses.bin");
			FileInputStream fis3 = new FileInputStream("DB/books.bin");
			FileInputStream fis4 = new FileInputStream("DB/given_books.bin");
			FileInputStream fis5 = new FileInputStream("DB/news.bin");
			if (fis5.read() != -1) {
				fis5 = new FileInputStream("DB/news.bin");
				ObjectInputStream ois5 = new ObjectInputStream(fis5);
				news = (Vector<String>) ois5.readObject();
				ois5.close();
			}
			if (fis4.read() != -1) {
				fis4 = new FileInputStream("DB/given_books.bin");
				ObjectInputStream ois4 = new ObjectInputStream(fis4);
				givenBooks = (HashMap<Student, Book>) ois4.readObject();
				ois4.close();
			}

			if (fis3.read() != -1) {
				fis3 = new FileInputStream("DB/books.bin");
				ObjectInputStream ois3 = new ObjectInputStream(fis3);
				libraryBooks = (Vector<Book>) ois3.readObject();
				ois3.close();
			}

			if (fis2.read() != -1) {
				fis2 = new FileInputStream("DB/courses.bin");
				ObjectInputStream ois2 = new ObjectInputStream(fis2);
				courses = (HashMap<String, Vector<Course>>) ois2.readObject();
				ois2.close();
			}
			ObjectInputStream ois1 = new ObjectInputStream(fis1);
			users = (HashMap<String, User>) ois1.readObject();
			ois1.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			FileOutputStream fos1 = new FileOutputStream("DB/users.bin", false);
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			FileOutputStream fos2 = new FileOutputStream("DB/courses.bin", false);
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			FileOutputStream fos3 = new FileOutputStream("DB/books.bin", false);
			ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
			FileOutputStream fos4 = new FileOutputStream("DB/given_books.bin", false);
			ObjectOutputStream oos4 = new ObjectOutputStream(fos4);
			FileOutputStream fos5 = new FileOutputStream("DB/news.bin", false);
			ObjectOutputStream oos5 = new ObjectOutputStream(fos5);
			oos1.writeObject(users);
			oos2.writeObject(courses);
			oos3.writeObject(libraryBooks);
			oos4.writeObject(givenBooks);
			oos5.writeObject(news);
			oos1.close();
			oos2.close();
			oos3.close();
			oos4.close();
			oos5.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
