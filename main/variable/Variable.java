package oop.ex6.main.variable;

/**
 * This class represent a single Variable object.
 */
public class Variable {
	private final String name; // name of the variable.
	private final Type type; // type of the variable.
	private final boolean isFinal; // true iff variable is final.
	private boolean isInitialized; // true iff variable is init.

	/**
	 * @param name name of the variable.
	 * @param type type of the variable.
	 * @param isFinal true iff variable is final.
	 * @param isInitialized true iff variable is init.
	 */
	public Variable(String name, Type type, boolean isFinal, boolean isInitialized) {
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
		this.isInitialized = isInitialized;
	}

	/**
	 * Copy constructor.
	 * @param copyVar - Variable object to be copied.
	 */
	public Variable(Variable copyVar){
		this(copyVar.name, copyVar.type, copyVar.isFinal, copyVar.isInitialized);
	}

	/**
	 * @return variable name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return variable type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return isFinal data member.
	 */
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * @return !isInitialized data member.
	 */
	public boolean isNotInitialized() {
		return !isInitialized;
	}

	/**
	 * set isInitialized as true.
	 */
	public void setIsInitialized() {
		this.isInitialized = true;
	}


}
