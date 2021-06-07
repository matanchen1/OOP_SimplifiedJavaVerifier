package oop.ex6.main.operation;

/**
 * An abstract class of every possible operation the sjava file describes, method init, var init, method call
 * and so on.
 */
public abstract class LineOperation {
	/**
	 * Every operation corresponds to a different type of line.
	 */
	protected LineType lineType;

	/**
	 * Constructor.
	 * @param type LineType object.
	 */
	protected LineOperation(LineType type) {
		this.lineType = type;
	}

	/**
	 * Getter of the LineType object.
	 * @return LineType object.
	 */
	public LineType getLineType() {
		return lineType;
	}
}
