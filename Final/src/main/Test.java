package main;

import java.util.*;

import admin.*;
import librarian.Book;
import librarian.Librarian;
import manager.*;
import teacher.*;
import user.*;
import student.*;

public class Test {
	public static User user;
	public static String cur;
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		DB.start();
		while (true) {
			if (login()) {
				executeOption();
			} else {
				break;
			}
		}
		DB.save();
		in.close();
	}

	public static boolean login() {
		System.out.println("Enter login:");
		cur = in.nextLine();
		if (cur.equalsIgnoreCase("0"))
			return false;
		if (DB.isExists(cur)) {
			user = DB.users.get(cur);
			System.out.println("Enter password:");
			cur = in.nextLine();
			if (cur.equals(user.getPassword())) {
				return true;
			} else {
				System.out.println("Wrong password\n");
			}
		} else {
			System.out.println("Wrong login\n");
		}
		return login();
	}

	public static void executeOption() {
		System.out.println("\n" + user.getOptions() + "\n0)exit\n");
		cur = in.nextLine();
		if (cur.equals("0")) {
			return;
		} else if (cur.equals("123")) {
			System.out.println(user.viewNews());
		} else if (cur.equals("111")) {
			for(User u : user.messages.keySet()) {
				System.out.println(u.getFullName() + ": " + user.messages.get(u));
			}
		} else if (cur.equals("112")) {
			System.out.println("Enter user:");
			int i = 1;
			Vector<User> vu = new Vector<>();
			for(User u : DB.users.values()) {
				System.out.println(String.format("%s) %s", i, u.getFullName()));
				vu.add(u);
				i++;
			}
			String sel = in.nextLine();
			System.out.println("Enter your message:");
			String mess = in.nextLine();
			int a = Integer.parseInt(sel);
			vu.get(a - 1).sendMessage(user, mess);
			System.out.println("Succes!");			
		} else if (cur.equals("1")) {
			System.out.println("\n" + user.toString());
		} else if (cur.equals("2")) {
			System.out.println("\nEnter new password(0 to out):");
			cur = in.nextLine();
			while (true) {
				if (cur == "cancel")
					break;
				if (user.changePassword(cur)) {
					System.out.println("Succes!");
					break;
				}
				System.out.println("Invalid password");
				cur = in.nextLine();
			}
		} else if (user.getType() == UserType.ADMIN) {
			if (cur.equals("3")) {
				while (true) {
					System.out.println("Enter user type(0 to out):");
					cur = in.nextLine();
					if (cur.equals("0"))
						break;
					if (UserType.getType(cur) != null) {
						System.out.println("fill fields");
						System.out.print("Enter(login, password, firstame, lastname");

						if (cur.equalsIgnoreCase("student")) {
							System.out.println(", degree, yearofstudy, faculty)");
						} else if (cur.equalsIgnoreCase("teacher")) {
							System.out.println(", salary, degree)");
						} else if (cur.equalsIgnoreCase("manager")) {
							System.out.println(", salary)");
						}

						UserFactory uf = new UserFactory();
						User u = uf.getUser(cur, in.nextLine().split(" "));
						if (((SuperAdmin) user).addUser(u)) {
							System.out.println("Succes!");
						}
						break;
					}
					System.out.println("Invalid type!\nAvailable: Student, Teacher, Manager, librarian");
					cur = in.nextLine();
				}
			} else if (cur.equals("4")) {
				while (true) {
					System.out.println("Enter user login(0 to exit):");
					cur = in.nextLine();
					if (cur.equals("0"))
						break;
					if (((SuperAdmin) user).deleteUser(cur)) {
						System.out.println("Succes!");
						break;
					}
					System.out.println("Invalid login!");
				}

			} else if (cur.equals("5")) {
				if (DB.users.get(in.nextLine()) != null) {
					System.out.println(DB.users.get(in.nextLine()));
				} else {
					System.out.println("Doesn't exist");
				}
			} else if (cur.equals("6")) {
				for (String s : DB.users.keySet()) {
					System.out.println(s);
				}
			}

		} else if (user.getType() == UserType.TEACHER) {

			if (cur.equals("3")) {
				for (Course c : ((Teacher) user).getCourses()) {
					System.out.println(c.getName());
				}
			} else if (cur.equals("4")) {
				System.out.println(((Teacher) user).getAverageRate());
			} else if (cur.equals("5")) {
				System.out.println("Select Course:");
				Vector<Course> vc = ((Teacher) user).getCourses();
				int i = 1;
				for (Course c : vc) {
					System.out.println(String.format("%s)%s", i, c.getName()));
				}
				int k = in.nextInt();
				System.out.println("Select Week:");
				int j = in.nextInt();
				Lesson vl = vc.get(k - 1).lessons.get(j - 1);
				System.out.println("Select Student");
				i = 1;
				Vector<Student> vs = new Vector<>();

				for (Student s : vl.mark.keySet()) {
					System.out.println(String.format("%s)%s", i, s.getFullName()));
					vs.add(s);
				}
				i = in.nextInt();
				System.out.println("Put Mark:");
				vl.mark.get(vs.get(i - 1)).mark = in.nextInt();
				System.out.println("Succes!");
//				i=1;
//				for(Lesson l: vl) {
//					System.out.println(String.format("%s)%s", i,l));
//				}
			}

		} else if (user.getType() == UserType.MANAGER) {
			if (cur.equals("3")) {
				System.out.println("Enter course name:");
				String s = in.nextLine();

				Course c;
				if (DB.courses.get(s) == null) {
					System.out.println("Enter course discription:");
					cur = in.nextLine();
					System.out.println("Enter creditsAmount, capacity:");
					String a = in.nextLine(), b = in.nextLine();
					c = new Course(s, cur, Integer.parseInt(a), Integer.parseInt(b));
					System.out.println("Enter number of prereqs:");
					int d = Integer.parseInt(in.nextLine());
					while (d != 0) {
						cur = in.nextLine();
						if (DB.courses.get(cur) == null) {
							System.out.println("Invalid course name");
						} else {
							c.prereqs.add(cur);
							d--;
						}
						if (cur.equals("0"))
							break;
					}
					DB.courses.put(s, new Vector<>());

				} else {
					c = DB.courses.get(s).get(0).clone();
				}
				System.out.println("Enter teacher login:");
				cur = in.nextLine();
				if ((Teacher) DB.users.get(cur) != null) {
					c.setTeacher((Teacher) DB.users.get(cur));
					DB.courses.get(s).add(c);
					System.out.println("Succes!");
				} else {
					System.out.println("Invalid login!");
				}
			} else if (cur.equals("4")) {
				System.out.println("Enter course name:");
				cur = in.nextLine();
				if (DB.courses.get(cur) != null) {
					System.out.println("Succes");
					DB.courses.remove(cur);
				} else {
					System.out.println("Invalid course name!");
				}
			} else if (cur.equals("5")) {
				System.out.println("Enter student login:");
				cur = in.nextLine();
				if (DB.isExists(cur)) {
					
					System.out.println(((Manager) user).viewStudentsInfo((Student) DB.users.get(cur)));
				} else {
					System.out.println("Invalid login!");
				}
			} else if (cur.equals("6")) {
				System.out.println("Enter teacher login:");
				cur = in.nextLine();
				if (DB.isExists(cur)) {
					System.out.println(((Manager) user).viewTeachersInfo((Teacher) DB.users.get(cur)));
				} else {
					System.out.println("Invalid login!");
				}
			} else if (cur.equals("7")) {
				for (String s : DB.courses.keySet()) {
					System.out.println(s);
				}
			} else if (cur.equals("8")) {
				Lesson l = new Lesson();
				System.out.println("select course:");
				for (String s : DB.courses.keySet()) {
					System.out.println(s);
				}
				String sel = in.nextLine();
				if (DB.courses.containsKey(sel)) {
					System.out.println("input date and time for start and end");
					int date = in.nextInt();
					int start = in.nextInt();
					int end = in.nextInt();
					Lesson l1 = new Lesson(date, start, end);
					l.time = l1.time;
					System.out.println("input classroom");
					int room = in.nextInt();
					l.classroom = room;
					System.out.println("Enetr the type of lesson:");
					String tol = in.next();
					l.TOL = TypeOfLesson.getType(tol);
					System.out.println("Enter teacher login:");
					cur = in.nextLine();
					for (String s : DB.courses.keySet()) {
						if (sel.equalsIgnoreCase(s)) {
							for (Course c : DB.courses.get(sel)) {
								if (c.getTeacher().getFirstName().equalsIgnoreCase(cur)) {
									l.teacher = c.getTeacher();
									for (Student ss : c.getStudents()) {
										l.mark.put(ss, new Mark());
									}

								}
							}
						}
					}
					DB.courses.get(sel).get(0).lessons.add(l);
					System.out.println("Succes!");
				} else {
					System.out.println("Invalid course name!");
				}
			} else if (cur.equals("9")) {
				System.out.println("Enter news:");
				cur = in.nextLine();
				((Manager)user).manageNews(cur);
				System.out.println("Succes!");
			}

		} else if (user.getType() == UserType.STUDENT) {
			if (cur.equals("8")) {
				Vector<String> vec = new Vector<>();
				String b[] = new String[DB.courses.keySet().size()];
				int i = 0;
				for (String s : DB.courses.keySet()) {
					vec.add(s);
					b[i] = "not selected";
					i++;
				}
				while (true) {
					i = 1;
					System.out.println("Select course");
					for (String s : DB.courses.keySet()) {
						System.out.println(String.format("%s)%s |%s", i, s, b[i - 1]));
						i++;
					}
					System.out.println(String.format("\ncredits %s/21", ((Student) user).getCredits()));
					i = Integer.parseInt(in.nextLine());
					if (i == 0)
						break;
					Vector<Course> c = DB.courses.get(vec.get(i - 1));
					if (((Student) user).getCredits() + c.get(0).getCreditsAmount() <= 21
							&& b[i - 1].equals("not selected")) {
						System.out.println("Select teacher");
						i = 1;
						for (Course c1 : c) {
							System.out.println(i + ")" + c1.getTeacher().getFullName());
							i++;
						}
						i = Integer.parseInt(in.nextLine());
						((Student) user).addCourse(c.get(i - 1));
						b[i - 1] = "selected";
						System.out.println("Succes");
					} else {
						System.out.println("You can't select this course!");
					}
				}
			} else if (cur.equals("5")) {
				System.out.println("Enter book:");
				for (Book b : DB.libraryBooks) {
					System.out.println(b);
				}
				Book b1 = null;
				cur = in.nextLine();
				boolean d = false;
				for (Book b : DB.libraryBooks) {
					if (b.getName().equals(cur)) {
						b1 = b;
						d = true;
						break;
					}
				}
				if (d) {
					Librarian.getInstance().requests.put(((Student) user), b1);

				}
			} else if (cur.equals("4")) {
				System.out.println("select teacher");
				int i = 1;
				Vector<Teacher> tv = ((Student) user).getTeachers();
				for (Teacher t : ((Student) user).getTeachers()) {
					System.out.println(String.format("%s)%s", i, t.getFullName()));
					i++;
				}
//				System.out.println(((Student) user).getTeachers());
				int sel = in.nextInt();
				for (Teacher t : ((Student) user).getTeachers()) {
					if (t.getFirstName().equalsIgnoreCase(tv.get(sel - 1).getFirstName())) {
						System.out.println("rate teacher (0 - 10)");
						int rate = in.nextInt();
						t.add(rate);
						System.out.println("Succes!");
						break;
					}
				}
			} else if (cur.equals("9")) {
				System.out.println("Your book:");
				Book b = null;
				for (Student s : DB.givenBooks.keySet()) {
					if (s == ((Student) user)) {
						System.out.println(DB.givenBooks.get(s).getName());
						b = DB.givenBooks.get(s);
					}
				}
				DB.givenBooks.remove(((Student) user));
				Librarian.getInstance().addBook(b);
				System.out.println("Succes!");
			} else if (cur.equals("3")) {
				System.out.println("Select Course:");
				int i = 1;
				for (Course c : ((Student) user).courses) {
					System.out.println(String.format("%s)%s", i, c.getName()));
					i++;
				}
				i = in.nextInt();
				for (Lesson l : ((Student) user).courses.get(i - 1).lessons) {
					System.out.println(l + " " + l.mark.get(((Student) user)));
				}

			} else if (cur.equals("10")) {
				for (Course c : ((Student) user).courses) {
					int att1 = 0, att2 = 0, total = 0;
					for (int i = 0; i < c.lessons.size(); i++) {
						if (i < 6) {
							att1 += c.lessons.get(i).mark.get(((Student) user)).mark;
						} else {
							att2 += c.lessons.get(i).mark.get(((Student) user)).mark;
						}
					}
					total = att1 + att2;
					System.out.println(String.format("%s, att1:%s, att2:%s, total^%s", c.getName(), att1, att2, total));
				}
			} else if (cur.equals("7")) {
				for (Course c : ((Student) user).courses) {
					System.out.println(String.format("%s : %s", c.getName(), c.lessons.get(0)));
				}
			} else if (cur.equals("11")) {
				for (Course c : ((Student) user).courses) {
					System.out.println(c.getName());
				}

			}

		} else if (user.getType() == UserType.LIBRARIAN) {
			if (cur.equals("3")) {

			} else if (cur.equals("5")) {
				Vector<Student> vs = new Vector<>();
				int i = 1;
				String s1;
				System.out.println("Select student");
				for (Student s : Librarian.getInstance().requests.keySet()) {
					System.out.println(
							i + ")" + s.getFullName() + " " + Librarian.getInstance().requests.get(s).getName());
					vs.add(s);
					i++;
				}

				i = in.nextInt();
				Librarian.getInstance().giveBook(Librarian.getInstance().requests.get(vs.get(i - 1)), vs.get(i - 1));
				Librarian.getInstance().deleteBook(Librarian.getInstance().requests.get(vs.get(i - 1)));
			} else if (cur.equals("6")) {
				System.out.println("Books:");
				for (Book b : DB.libraryBooks) {
					System.out.println(b);
				}

			}

		} else {
			System.out.println("Invalid option");
		}
		executeOption();
	}
//	public static void addUser(){
//		
//	}
}
