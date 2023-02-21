package user;

public enum UserType
{
	STUDENT, TEACHER, MANAGER, LIBRARIAN, ADMIN;
	public static UserType getType(String string) {
		if(string.equalsIgnoreCase("student")) return STUDENT;
		if(string.equalsIgnoreCase("TEACHER")) return TEACHER;
		if(string.equalsIgnoreCase("MANAGER")) return MANAGER;
		if(string.equalsIgnoreCase("LIBRARIAN")) return LIBRARIAN;
		return null;
		
	}
}
