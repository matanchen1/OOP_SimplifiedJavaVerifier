package oop.ex6.main.analyzer;

import oop.ex6.main.SJavaException;

/**
 * This class represents illegal
 */
public class InvalidLineFormatException extends SJavaException {
	private final static String ERR_MSG = "line syntax error in global scope.";

	/**
	 * Constructor
	 */
	public InvalidLineFormatException() {
		super(ERR_MSG);
	}

}
