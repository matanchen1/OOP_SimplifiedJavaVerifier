package oop.ex6.main.operation;

import oop.ex6.main.variable.Type;

/**
 * This class represents a variable declaration line.
 */
public class VarDeclarationLineOperation extends LineOperation {

	private final String varName; //the var name
	private final boolean isFinal; //is the variable is final.
	private final Type type; //type of the variable.
	private final String value; // optional value that is being assigned to the var.

	/**
	 * Constructor.
	 * @param varName - var name.
	 * @param isFinal - is final or not.
	 * @param type - Type of variable.
	 * @param value - optional value.
	 */
	public VarDeclarationLineOperation(String varName, boolean isFinal, Type type, String value) {
		super(LineType.VAR_DEC);
		this.varName = varName;
		this.isFinal = isFinal;
		this.type = type;
		this.value = value;
	}

	/**
	 * @return type of the var.
	 */
	public Type getVarType() {
		return type;
	}

	/**
	 * @return value of the var.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return var name.
	 */
	public String getVarName() {
		return varName;
	}

	/**
	 * @return true iff the variable is final.
	 */
	public boolean isFinal() {
		return isFinal;
	}
}
