package teacher;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public enum TeacherDegree
{
	TUTOR, ASSISTANT, LECTOR, PROFESSOR, SENIOR_LECTOR;
	public static TeacherDegree getDegree(String string) {
		if(string.equalsIgnoreCase("tutor")) return TUTOR;
		if(string.equalsIgnoreCase("ASSISTANT")) return ASSISTANT;
		if(string.equalsIgnoreCase("LECTOR")) return LECTOR;
		return null;
	}
}
