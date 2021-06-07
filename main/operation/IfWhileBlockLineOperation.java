package oop.ex6.main.operation;

/**
 * This class represents a line that opens if\while block.
 */
public class IfWhileBlockLineOperation extends LineOperation {
	private final String[] conditions; //array of the condition inside the if() or while().

	/**
	 * Constructor
	 * @param conditions array of the condition inside the block opener.
	 */
	public IfWhileBlockLineOperation(String[] conditions) {
		super(LineType.OPEN_IF_WHILE_BLOCK);
		this.conditions = conditions;
	}

	/**
	 * @return conditions.
	 */
	public String[] getConditions() {
		return conditions;
	}
}
