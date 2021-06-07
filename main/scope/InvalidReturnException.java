package oop.ex6.main.scope;

import oop.ex6.main.SJavaException;

/**
 * This class represent an InvalidReturnException
 */
public class InvalidReturnException extends SJavaException {
	private final static String ERR_MSG = "No return statement was found in the last row of the method.";

	/**
	 * Constructor of InvalidReturnException
	 */
	public InvalidReturnException() {
		super(ERR_MSG);
	}


}
