package oop.ex6.main.operation;


/**
 * This class represents a variable assignment line.
 */
public class VarAssignmentLineOperation extends LineOperation {
	private final String varName; //var name that being assigned.
	private final String value; //the value that being assigned.

	/**
	 * Constructor.
	 * @param varName - name of var.
	 * @param value - the value.
	 */
	public VarAssignmentLineOperation(String varName, String value) {
		super(LineType.VAR_ASSIGN);
		this.varName = varName;
		this.value = value;
	}

	/**
	 * @return the var name.
	 */
	public String getVarName() {
		return varName;
	}

	/**
	 * @return the value.
	 */
	public String getValue() {
		return value;
	}

}
