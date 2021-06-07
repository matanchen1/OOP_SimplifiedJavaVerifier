package oop.ex6.main.parser;

import oop.ex6.main.SJavaException;
import oop.ex6.main.scope.InvalidMethodLineFormatException;
import oop.ex6.main.variable.InvalidVariableAssignmentException;
import oop.ex6.main.operation.*;
import oop.ex6.main.variable.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
/**
 * A Singleton class responsible for every parsing process in the program. This class gets a line index, and
 * return a LineOperation object of this line.
 */
public class Parser {
	private static final int SPLIT_LIMIT = -1; //limit .split() method.
	private List<String> lines; //lines of the s-java file.
	private static Parser instance; //current instance of Parser.

	/*
	Private constructor.
	 */
	private Parser() {
	}

	/**
	 * Static method that retrieves the current Parser instance.
	 * @return Current Parser instance.
	 */
	public static Parser getInstance() {
		if (instance == null) {
			instance = new Parser();
		}
		return instance;
	}

	/**
	 * Should be called in the beginning of every Sjavac run. Initializes the lines of the sjava file.
	 * @param lines - List of lines, of sjava file.
	 */
	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	/**
	 * This method gets an index of a line and returns the LineOperation object of this line.
	 * @param index - index of line.
	 * @return LineOperation object.
	 * @throws SJavaException - Syntax error
	 */
	public LineOperation[] getLineOperation(int index) throws SJavaException {
		String line = lines.get(index).trim();
		Matcher decLineMatcher = Regex.varDeclarationPattern.matcher(line);
		if (decLineMatcher.matches()) { //dec var
			return createVarDeclarationOperation(decLineMatcher).toArray(new VarDeclarationLineOperation[0]);
		}
		Matcher assignLineMatcher = Regex.nonOptAssignmentPattern.matcher(line);
		if (assignLineMatcher.matches()) { //assign var
			return createAssignmentOperation(assignLineMatcher).toArray(new VarAssignmentLineOperation[0]);
		}
		Matcher methodDeclarationMatcher = Regex.methodInitPattern.matcher(line);
		if (methodDeclarationMatcher.matches()) { //method dec
			return createMethodInitOperation(methodDeclarationMatcher, index);
		}
		Matcher methodCallMatcher = Regex.methodCallPattern.matcher(line);
		if (methodCallMatcher.matches()) { //method call
			return createMethodCallOperation(methodCallMatcher);
		}
		Matcher ifWhileBlockMatcher = Regex.ifWhileBlockPattern.matcher(line);
		if (ifWhileBlockMatcher.matches()) { //if while block
			return createIfWhileBlockOperation(ifWhileBlockMatcher);
		}
		Matcher returnMatcher = Regex.returnPattern.matcher(line);
		if (returnMatcher.matches()) { //return
			return new LineOperation[]{new ReturnLineOperation()};
		}
		if (line.equals(Regex.BLOCK_CLOSE)) { //a '}'
			return new LineOperation[]{new BlockCloseLineOperation()};
		}
		//default is unknown
		return new LineOperation[]{new UnknownLineOperation()};
	}

	/*
	creating new ifWhileBlockOperation object out of its matcher.
	 */
	private IfWhileBlockLineOperation[] createIfWhileBlockOperation(Matcher ifWhileBlockMatcher)
			throws InvalidConditionException {
		String[] cond = getConditions(ifWhileBlockMatcher.group(Regex.CONDITION_GROUP));
		return new IfWhileBlockLineOperation[]{new IfWhileBlockLineOperation(cond)};
	}

	/*
	This method gets a condition line and returns array of each condition.
	 */
	private String[] getConditions(String cond) throws InvalidConditionException {
		String[] conditionParts = cond.split(Regex.CONDITION_OPERATORS_REGEX, SPLIT_LIMIT);
		for (int i = 0; i < conditionParts.length; i++) {
			conditionParts[i] = conditionParts[i].trim();
			if (conditionParts[i].length() == 0) {
				throw new InvalidConditionException();
			}
		}
		return conditionParts;
	}

	/*
	This method creates a MethodCallLineOperation object.
	 */
	private MethodCallLineOperation[] createMethodCallOperation(Matcher methodCall) {
		String methodName = methodCall.group(Regex.NAME_GROUP);
		String[] args = methodCall.group(Regex.PARAM_LINE_GROUP).split(Regex.DELIMITER);
		for (int i = 0; i < args.length; i++) {
			args[i] = args[i].trim();
		}
		return new MethodCallLineOperation[]{new MethodCallLineOperation(methodName, args)};
	}

	/*
	This method creates a MethodInitOperation object.
	 */
	private LineOperation[] createMethodInitOperation(Matcher methodDeclaration, int lineIndex)
			throws InvalidMethodParamException, InvalidMethodLineFormatException {
		List<VarDeclarationLineOperation> params = parseMethodParamLine(
				methodDeclaration.group(Regex.PARAM_LINE_GROUP));
		String methodName = methodDeclaration.group(Regex.NAME_GROUP);
		int endingIndex = getLastLineIndexOfMethod(lineIndex);
		return new LineOperation[]{
				new MethodInitLineOperation(methodName, params, lineIndex + 1, endingIndex)};
	}

	/*
	Given a starting line of a method, this method calculates the last line index if that method.
	 */
	private int getLastLineIndexOfMethod(int startingLineIndex) throws InvalidMethodLineFormatException {
		int counter = 1;
		while (counter > 0 && startingLineIndex < lines.size()) {
			String line = lines.get(startingLineIndex).trim();
			Matcher openBlockMatcher = Regex.ifWhileBlockPattern.matcher(line);
			if (openBlockMatcher.matches()) {
				counter++;
			} else if (line.equals(Regex.BLOCK_CLOSE)) {
				counter--;
			}
			startingLineIndex++;
		}
		if(counter != 0){
			throw new InvalidMethodLineFormatException();
		}
		return startingLineIndex - 1;
	}

	/*
	This method responsible of creating VarDeclarationLineOperation objects out of method's param line.
	 */
	private List<VarDeclarationLineOperation> parseMethodParamLine(String paramLine)
			throws InvalidMethodParamException {
		if (paramLine.trim().length() == 0) {
			//no params
			return new ArrayList<>();
		}
		List<VarDeclarationLineOperation> params = new ArrayList<>();
		for (String param : paramLine.split(Regex.DELIMITER)) {
			Matcher paramMatcher = Regex.paramPattern.matcher(param.trim());
			if (!paramMatcher.matches()) {
				throw new InvalidMethodParamException();
			}
			boolean isFinal = paramMatcher.group(Regex.FINAL_GROUP) != null;
			Type type = Type.getType(paramMatcher.group(Regex.TYPE_GROUP));
			String paramName = paramMatcher.group(Regex.NAME_GROUP);
			params.add(new VarDeclarationLineOperation(paramName, isFinal, type, null));
		}
		return params;
	}

	/*
	This method create VarAssignmentLineOperation objects.
	 */
	private List<VarAssignmentLineOperation> createAssignmentOperation(Matcher assignLinenMatcher)
			throws InvalidVariableAssignmentException {
		String[] parts = assignLinenMatcher.group(Regex.VARS_GROUP).split(Regex.DELIMITER);
		List<VarAssignmentLineOperation> varDecList = new ArrayList<>();
		for (String part : parts) {
			Matcher singleAssignMatcher = Regex.singleOptAssignmentPattern.matcher(part);
			if (!singleAssignMatcher.matches()) {
				throw new InvalidVariableAssignmentException();
			}
			String varName = singleAssignMatcher.group(Regex.NAME_GROUP);
			String varValue = singleAssignMatcher.group(Regex.VALUE_GROUP);
			varDecList.add(new VarAssignmentLineOperation(varName, varValue));
		}
		return varDecList;
	}

	/*
	This method creating VarDeclarationOperation objects.
	 */
	private List<VarDeclarationLineOperation> createVarDeclarationOperation(Matcher matcher)
			throws SJavaException {
		boolean isFinal = matcher.group(Regex.FINAL_GROUP) != null;
		Type type = Type.getType(matcher.group(Regex.TYPE_GROUP));
		String[] varParts = matcher.group(Regex.VARS_GROUP).split(Regex.DELIMITER);
		List<VarDeclarationLineOperation> varDecList = new ArrayList<>();
		for (String varPart : varParts) {
			Matcher singleAssignMatcher = Regex.singleOptAssignmentPattern.matcher(varPart.trim());
			if (!singleAssignMatcher.matches()) {
				throw new InvalidVariableAssignmentException();
			}
			String varName = singleAssignMatcher.group(Regex.NAME_GROUP);
			String varValue = singleAssignMatcher.group(Regex.VALUE_GROUP);
			varDecList.add(new VarDeclarationLineOperation(varName, isFinal, type, varValue));
		}
		return varDecList;
	}
}
