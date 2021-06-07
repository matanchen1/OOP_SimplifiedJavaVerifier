package oop.ex6.main.variable;

import oop.ex6.main.SJavaException;

/**
 * This class represent an exception that evoked while attempting to declaration or assignment a variable with
 * illegal name.
 */

public class InvalidVariableNameException extends SJavaException {
	private final static String ERR_MSG = "Invalid variable name error.";

	/**
	 * The Default constructor of the exception.
	 */
	public InvalidVariableNameException() {
		super(ERR_MSG);
	}

}
