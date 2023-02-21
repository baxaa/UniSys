package student;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public enum YearOfStudy
{
	FIRST, SECOND, THIRD, FOURTH;
	public static YearOfStudy getYearOfStudy(String string) {
	if(string.equalsIgnoreCase("FIRST")) return FIRST;
	if(string.equalsIgnoreCase("SECOND")) return SECOND;
	if(string.equalsIgnoreCase("THIRD")) return THIRD;
	if(string.equalsIgnoreCase("FOURTH")) return FOURTH;
	return null;
	}
}
