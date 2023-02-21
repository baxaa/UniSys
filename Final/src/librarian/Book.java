package librarian;

import java.io.Serializable;

public class Book implements Serializable {

	private String name;
	private String author;
	public int numberOfBooks;

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public Book(String name, String author, int numberOfBooks) {
		this.name = name;
		this.author = author;
		this.numberOfBooks = numberOfBooks;
	}

	@Override
	public String toString() {
		return "Book [name = " + name + ", author = " + author + ", numberOfBooks = " + numberOfBooks + "]";
	}
}
