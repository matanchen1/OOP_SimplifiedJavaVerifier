package oop.ex6.main.analyzer;

import oop.ex6.main.SJavaException;
import oop.ex6.main.operation.LineOperation;
import oop.ex6.main.parser.Parser;

import java.util.List;

/**
 * This class represent an abstract LinesAnalyzer class.
 */
public abstract class LinesAnalyzer {
	/**
	 * lines
	 */
	protected final List<String> lines; //lines of sjava file
	private final Parser parser; //Parser object.

	/**
	 * This is the constructor of LinesAnalyzer
	 * @param lines - the lines to analyze.
	 */
	public LinesAnalyzer(List<String> lines) {
		this.lines = lines;
		parser = Parser.getInstance();
	}

	/**
	 * This method analyze one line according to class "handleLineOperationCases" implementation
	 * @param lineIndex - line index to read in lines.
	 * @return the operating
	 * @throws SJavaException
	 */
	protected LineOperation analyzeLine(int lineIndex) throws SJavaException {
		LineOperation[] operations = parser.getLineOperation(lineIndex);
		for (LineOperation operation : operations) {
			handleLineOperationCases(operation);
		}
		return operations[0]; //first operation always represent the main line operation.
	}

	/**
	 * an abstract method that wrapped by "analyzeLine" method the method get an operation and handle it
	 * according to the requirements of the class
	 * @param operation line operation to handle
	 * @throws SJavaException
	 */
	protected abstract void handleLineOperationCases(LineOperation operation) throws SJavaException;
}
