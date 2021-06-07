package oop.ex6.main.operation;

/**
 * This class represents a line of method call.
 */
public class MethodCallLineOperation extends LineOperation {
	private final String methodName; //the method that being called.
	private final String[] arguments; //the method's arguments.

	/**
	 * Constructor.
	 * @param methodName
	 * @param arguments
	 */
	public MethodCallLineOperation(String methodName, String[] arguments) {
		super(LineType.METHOD_CALL);
		this.methodName = methodName;
		this.arguments = arguments;
	}

	/**
	 * @return the method name.
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @return the arguments.
	 */
	public String[] getArguments() {
		return arguments;
	}
}
