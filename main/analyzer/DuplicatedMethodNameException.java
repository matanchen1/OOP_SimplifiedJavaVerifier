package oop.ex6.main.analyzer;

import oop.ex6.main.SJavaException;

/**
 * This class represents duplicate method names.
 */
public class DuplicatedMethodNameException extends SJavaException {

	private final static String ERR_MSG = "S-java does not support declaration of methods with the same" +
										  "name.";

	/**
	 * Default constructor of the exception.
	 */
	public DuplicatedMethodNameException() {
		super(ERR_MSG);
	}
}
