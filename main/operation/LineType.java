package oop.ex6.main.operation;

/**
 * This enum class specifies all legal line types in sjava.
 */
public enum LineType {
	VAR_DEC, //Declaration of a variable.
	VAR_ASSIGN, //Assignment of a variable.
	RETURN, //Return command.
	METHOD_INIT, //Declaration of a method.
	METHOD_CALL, //Calling a method.
	OPEN_IF_WHILE_BLOCK, //Opening a if\while block.
	CLOSE_SCOPE, //Closing method\if\while block.
	UNKNOWN_PATTERN //Unknown line.
}
