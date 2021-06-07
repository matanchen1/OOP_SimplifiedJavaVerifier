package oop.ex6.main;

/**
 * This class is evoked while the program get an invalid number of arguments.
 */
public class InvalidUsageException extends Exception {
	private static final String ERR_MSG = "ERROR: Invalid number of arguments.\n " +
										  "Usage: oop.ex6.main.sjava file.sjava";

	/**
	 * Constructor.
	 */
	public InvalidUsageException() {
		super(ERR_MSG);
	}
}
