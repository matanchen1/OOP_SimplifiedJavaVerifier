package oop.ex6.main.parser;

import oop.ex6.main.SJavaException;

/**
 * This class represents a illegal condition inside if \ while block.
 */
public class InvalidConditionException extends SJavaException {
	private static final String ERR_MSG = "Invalid condition in if\\while block.";

	/**
	 * Constructor.
	 */
	public InvalidConditionException() {
		super(ERR_MSG);
	}
}
