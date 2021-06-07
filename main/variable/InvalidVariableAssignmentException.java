package oop.ex6.main.variable;

import oop.ex6.main.SJavaException;

/**
 * This class represent an exception that evoked while attempting to make an illegal variable assignment.
 */

public class InvalidVariableAssignmentException extends SJavaException {
	private final static String ERR_MSG = "Invalid variable assignment error.";

	/**
	 * The Default constructor of the exception.
	 */
	public InvalidVariableAssignmentException() {
		super(ERR_MSG);
	}

}
