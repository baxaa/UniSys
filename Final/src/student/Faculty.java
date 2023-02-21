package student;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public enum Faculty {
	FIT, ISE, BS;

	public static Faculty getFaculty(String string) {
		if (string.equalsIgnoreCase("fit"))
			return FIT;
		if (string.equalsIgnoreCase("ISE"))
			return ISE;
		if (string.equalsIgnoreCase("BS"))
			return BS;
		return null;
	}
}
