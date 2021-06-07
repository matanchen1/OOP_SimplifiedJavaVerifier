package oop.ex6.main.operation;

import java.util.List;

/**
 * This class represents method declaration line.
 */
public class MethodInitLineOperation extends LineOperation {
	private final String methodName; //method name
	private final List<VarDeclarationLineOperation> params; //method parameters, is a variable dec line of
	// itself.
	private int beginLineIndex; //line of which the method begins.
	private int endLineIndex; //line of which the method ends.

	/**
	 * Constructor.
	 * @param methodName - name of method.
	 * @param params - List of parameters initializations objects (Operation objects).
	 * @param beginLineIndex - begin line of method.
	 * @param endLineIndex - end line of method.
	 */
	public MethodInitLineOperation(String methodName, List<VarDeclarationLineOperation> params, int beginLineIndex,
								   int endLineIndex) {
		super(LineType.METHOD_INIT);
		this.methodName = methodName;
		this.params = params;
		this.beginLineIndex = beginLineIndex;
		this.endLineIndex = endLineIndex;
	}

	/**
	 * @return the beginning line index of this method.
	 */
	public int getBeginLineIndex() {
		return beginLineIndex;
	}

	/**
	 * @return the beginning line index of this method.
	 */
	public int getEndLineIndex() {
		return endLineIndex;
	}

	/**
	 * @returns line of parameters.
	 */
	public List<VarDeclarationLineOperation> getParams() {
		return params;
	}

	/**
	 * @return method name.
	 */
	public String getMethodName() {
		return methodName;
	}
}




