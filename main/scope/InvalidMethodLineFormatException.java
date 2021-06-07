package oop.ex6.main.scope;

import oop.ex6.main.SJavaException;

/**
 * This class represent an Invalid Method Line Format Exception.
 */
public class InvalidMethodLineFormatException extends SJavaException {
	private final static String ERR_MSG = "line syntax error in method.";

	/**
	 * Default constructor.
	 */
	public InvalidMethodLineFormatException() {
		super(ERR_MSG);
	}
}
