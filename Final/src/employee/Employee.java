package employee;

import user.*;

public class Employee extends User {
	protected int salary;

	public Employee(UserType type, String login, String password, String firstName, String lastName, int salary) {
		super(type, login, password, firstName, lastName);
		this.setSalary(salary);
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void sendMessage(Employee e, String s) {
		super.sendMessage(e, s);
	}

	public void getMessage(Employee e, String s) {
		super.getMessage(e, s);
	}

	public String viewNews() {
		return super.viewNews();
	}

	public String getOptions() {
		return super.getOptions();
	}

}
