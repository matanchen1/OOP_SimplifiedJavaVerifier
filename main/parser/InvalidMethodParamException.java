package oop.ex6.main.parser;

import oop.ex6.main.SJavaException;

/**
 * This class represents illegal method parameter.
 */
public class InvalidMethodParamException extends SJavaException {
	private final static String ERR_MSG = "Invalid method parameter error.";

	/**
	 * Constructor.
	 */
	public InvalidMethodParamException() {
		super(ERR_MSG);
	}
}
