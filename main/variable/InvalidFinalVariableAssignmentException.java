package oop.ex6.main.variable;

import oop.ex6.main.SJavaException;

/**
 * This class represent a exception that evoked while attempting to preform an assignment into a final
 * variable
 */
public class InvalidFinalVariableAssignmentException extends SJavaException {
	private final static String ERR_MSG = "Invalid assignment into a final variable";

	/**
	 * The Default constructor of the exception.
	 */
	public InvalidFinalVariableAssignmentException() {
		super(ERR_MSG);
	}

}
