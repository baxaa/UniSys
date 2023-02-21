package student;

public enum Degree
{
	PhD, master, bachelor;
	public static Degree getDegree(String string) {
		if(string.equalsIgnoreCase("phd")) return PhD;
		if(string.equalsIgnoreCase("master")) return master;
		if(string.equalsIgnoreCase("bachelor")) return bachelor;
		return null;
	}
}
