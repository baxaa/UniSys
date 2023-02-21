package main;

import user.UserType;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public enum TypeOfLesson {
	PRACTICE, LECTURE;
	public static TypeOfLesson getType(String string) {
		if(string.equalsIgnoreCase("PRACTICE")) return PRACTICE;
		if(string.equalsIgnoreCase("LECTURE")) return LECTURE;
		return null;
		
	}
}
