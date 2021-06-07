package oop.ex6.main.analyzer;

import oop.ex6.main.SJavaException;
import oop.ex6.main.operation.*;
import oop.ex6.main.scope.GlobalScope;

import java.util.List;

/**
 * This class represent a GlobalAnalyzer class.
 */
public class GlobalAnalyzer extends LinesAnalyzer {
	private final GlobalScope globalScope; // global scope object

	/**
	 * Constructor of GlobalAnalyzer
	 * @param lines - global lines to analyze.
	 */
	public GlobalAnalyzer(List<String> lines) {
		super(lines);
		globalScope = new GlobalScope();
	}

	/**
	 * This class read all global lines and return a GlobalScope object. throws SJavaException
	 */
	public GlobalScope analyzeGlobalLines() throws SJavaException {
		for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
			LineOperation lineOperation = analyzeLine(lineIndex);
			if (lineOperation.getLineType() == LineType.METHOD_INIT) {
				lineIndex = ((MethodInitLineOperation) lineOperation)
						.getEndLineIndex(); // GlobalAnalyzer doesn't analyzed method lines
			}
		}
		return globalScope;
	}

	/**
	 * This method get a line operation and handle it.
	 * @throws SJavaException - InvalidLineFormatException()
	 */
	protected void handleLineOperationCases(LineOperation lineOperation) throws SJavaException {
		switch (lineOperation.getLineType()) {
		case VAR_ASSIGN: // line of variable assignment
			globalScope.handleAssignment((VarAssignmentLineOperation) lineOperation);
			break;
		case VAR_DEC: // line of variable declaration
			globalScope.handleDeclaration((VarDeclarationLineOperation) lineOperation);
			break;
		case METHOD_INIT: // line of method declaration
			globalScope.createNewMethod((MethodInitLineOperation) lineOperation);
			break;
		default:
			throw new InvalidLineFormatException();
		}
	}
}
