package oop.ex6.main.variable;

import oop.ex6.main.SJavaException;

/**
 * This class represent a exception that evoked while attempt to initialize a variable with a name that is
 * already token by another variable (in the same scope)
 */
public class DuplicatedVariableNameException extends SJavaException {

	private final static String ERR_MSG = "S-java does not support declaration of variable with the same" +
										  " name in the same scope.";

	/**
	 * The Default constructor of the exception.
	 */
	public DuplicatedVariableNameException() {
		super(ERR_MSG);
	}

}
