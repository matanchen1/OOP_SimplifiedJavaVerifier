package oop.ex6.main.scope;

import oop.ex6.main.SJavaException;

/**
 * This class represent an exception of invalid use of "KeyWords" - reserved name in SJava.
 */
public class InvalidKeyWordUseException extends SJavaException {
	private final static String ERR_MSG = "Invalid use of Key word.";

	/**
	 * Constructor.
	 */
	public InvalidKeyWordUseException() {
		super(ERR_MSG);
	}
}
