package oop.ex6.main.scope;

import oop.ex6.main.SJavaException;

/**
 * This class represent an exception of invalid Assignments when an attempt is made to assign a value to
 * variable while the value is an uninitialized variable.
 */
public class InvalidUninitializedAssignmentsException extends SJavaException {
	private final static String ERR_MSG = "Invalid attempt to assign a value to " +
										  "variable while the value is an uninitialized variable.";

	/**
	 * Constructor.
	 */
	public InvalidUninitializedAssignmentsException() {
		super(ERR_MSG);
	}
}
