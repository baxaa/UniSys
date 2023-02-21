package librarian;

import java.io.Serializable;
import java.util.*;

import employee.Employee;
import main.DB;
import student.Student;
import user.UserType;

public class Librarian extends Employee implements Serializable {
	public static Librarian instance = new Librarian(UserType.LIBRARIAN, "l", "l", "fname", "lname", 500);
	private Vector<Book> libraryBooks = DB.libraryBooks;
	private HashMap<Student, Book> givenBooks = DB.givenBooks;
	public HashMap<Student, Book> requests = new HashMap<>();

	private Librarian(UserType type, String login, String password, String firstName, String lastName, int salary) {
		super(type, login, password, firstName, lastName, salary);
	}

	public static Librarian getInstance() {
		return instance;
	}

	public void addBook(Book parameter) {
		parameter.numberOfBooks += 1;
		;
	}

	public void deleteBook(Book parameter) {
		parameter.numberOfBooks -= 1;
		;
	}

	public void giveBook(Book parameter, Student s) {
		givenBooks.put(s, parameter);
	}

	public Vector<Book> viewBooks() {
		return libraryBooks;
	}

	public String getOptions() {
		return super.getOptions() + "\n3)add book\n4)delete book\n5)give book\n6)view books";
	}
}
