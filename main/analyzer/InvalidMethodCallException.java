package oop.ex6.main.analyzer;

import oop.ex6.main.SJavaException;

/**
 * This class represent is evoked while recognizing an invalid method call is
 */
public class InvalidMethodCallException extends SJavaException {

	private final static String ERR_MSG = "Invalid method call.";

	/**
	 * Constructor.
	 */
	public InvalidMethodCallException() {
		super(ERR_MSG);
	}

}
