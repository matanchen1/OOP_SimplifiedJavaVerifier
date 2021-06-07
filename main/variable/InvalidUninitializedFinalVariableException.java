package oop.ex6.main.variable;

import oop.ex6.main.SJavaException;

/**
 * This class represent an exception that evoked while attempting to declaration on Final variable without
 * assignment.
 */
public class InvalidUninitializedFinalVariableException extends SJavaException {
	private final static String ERR_MSG = "Final variable must be initialized.";

	/**
	 * The Default constructor of the exception.
	 */
	public InvalidUninitializedFinalVariableException() {
		super(ERR_MSG);
	}

}
