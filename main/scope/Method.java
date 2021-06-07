package oop.ex6.main.scope;

import oop.ex6.main.operation.MethodInitLineOperation;
import oop.ex6.main.variable.Variable;

import java.util.LinkedHashMap;

/**
 * This class represent a single Method object.
 */
public class Method {
	// Container that stores the parameters of the method.
	private final LinkedHashMap<String, Variable> parameters;
	private final String name; //name of the method
	private final int beginLineIndex; // the first index of the method in lines file
	private final int endLineIndex; // the last index of the method in lines file

	/**
	 * The constructor of method
	 * @param methodInitOperation - the line operation (for line deatials)
	 * @param parameters - parameters of the method.
	 */
	public Method(MethodInitLineOperation methodInitOperation, LinkedHashMap<String, Variable> parameters) {
		this.name = methodInitOperation.getMethodName();
		this.beginLineIndex = methodInitOperation.getBeginLineIndex();
		this.endLineIndex = methodInitOperation.getEndLineIndex();
		this.parameters = parameters;
	}

	/**
	 * return end line index
	 */
	public int getEndLineIndex() {
		return endLineIndex;
	}

	/**
	 * return end line index
	 */
	public int getStartingIndex() {
		return beginLineIndex;
	}

	/**
	 * return the container of parameters
	 */
	public LinkedHashMap<String, Variable> getParameters() {
		return parameters;
	}

	/**
	 * return the name of the method
	 */
	public String getName() {
		return name;
	}

}
