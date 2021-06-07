package oop.ex6.main.analyzer;

import oop.ex6.main.SJavaException;
import oop.ex6.main.operation.*;
import oop.ex6.main.parser.InvalidConditionException;
import oop.ex6.main.parser.Parser;
import oop.ex6.main.parser.Regex;
import oop.ex6.main.scope.*;
import oop.ex6.main.variable.Type;
import oop.ex6.main.variable.Variable;
import java.util.*;
import java.util.regex.Matcher;

/**
 * This class represent a MethodsAnalyzer object.
 */
public class MethodsAnalyzer extends LinesAnalyzer {
	private static final String[] EMPTY_PARAMS = new String[]{""};
	//in order to avoid empty parameters called in foo();

	private static GlobalScope globalScope; // global scope of the methods.
	private static Stack<MethodsScope> scopeStack; // stack that contain all the inner MethodsScopes.

	/**
	 * Constructor of MethodsAnalyzer
	 * @param globalScope - the global scope of the methods.
	 * @param lines the lines of the S-java file.
	 */
	public MethodsAnalyzer(GlobalScope globalScope, List<String> lines) {
		super(lines);
		MethodsAnalyzer.globalScope = globalScope;

	}

	/*
	 * This method get a method object and init a new Method Scope.
	 */
	private void initNewMethodScope(Method method) {
		MethodsScope mainMethodScope = new MethodsScope(globalScope, method.getParameters());
		MethodsScope.setMainMethodScope(mainMethodScope);
		scopeStack = new Stack<>();
		scopeStack.push(mainMethodScope);
	}

	/**
	 * This class analyzes one method
	 * @param method - method object to analyzes. throws SJavaException
	 */
	public void AnalyzeMethod(Method method) throws SJavaException {
		Parser parser = Parser.getInstance();
		initNewMethodScope(method);

		int endingIndex = method.getEndLineIndex();

		for (int lineIndex = method.getStartingIndex(); lineIndex <= endingIndex; lineIndex++) {
			validateReturnEnding(method);
			LineOperation[] lineOperations = parser.getLineOperation(lineIndex);
			for (LineOperation lineOperation : lineOperations) {
				handleLineOperationCases(lineOperation);
			}
		}

	}

	/**
	 * This method get a line operation and handle it.
	 * @throws SJavaException - InvalidLineFormatException()
	 */
	protected void handleLineOperationCases(LineOperation lineOperation) throws SJavaException {
		MethodsScope currentScope = scopeStack.peek();
		switch (lineOperation.getLineType()) {
		case METHOD_CALL: // line of method call inside method
			methodCallHandle((MethodCallLineOperation) lineOperation);
			break;
		case VAR_DEC: // line of variable declaration
			currentScope.handleDeclaration((VarDeclarationLineOperation) lineOperation);
			break;
		case VAR_ASSIGN: // line of variable assignment
			currentScope.handleAssignment((VarAssignmentLineOperation) lineOperation);
			break;
		case CLOSE_SCOPE: // line that equals to "}"
			scopeStack.pop();
			break;
		case OPEN_IF_WHILE_BLOCK:  // line with a pattern of While or If block
			validateConditions((IfWhileBlockLineOperation) lineOperation);
			MethodsScope scope = new MethodsScope(scopeStack.peek(), new HashMap<>());
			scopeStack.push(scope);
			break;
		case RETURN:// // return statement line.
			break;
		default:
			throw new InvalidMethodLineFormatException();
		}

	}

	/*
	This class validate condition inside if or while blocks.
	if condition is invalid throw an InvalidConditionException() otherwise do nothing.
	 */
	private void validateConditions(IfWhileBlockLineOperation operation) throws SJavaException {
		for (String cond : operation.getConditions()) {
			if (!Type.BOOLEAN.validateValue(cond)) {
				Variable var = scopeStack.peek().searchVariable(cond);
				if (var == null || !Type.BOOLEAN.validateType(var.getType()) || var.isNotInitialized()) {
					throw new InvalidConditionException();
				}
			}
		}
	}

	/*
	This class validate that method end with return statement line, otherwise throw
	an InvalidReturnException()
	 */
	private void validateReturnEnding(Method method) throws SJavaException {
		String lastLineOfMethod = lines.get(method.getEndLineIndex() - 1);
		Matcher returnMatcher = Regex.returnPattern.matcher(lastLineOfMethod);
		if (!returnMatcher.matches()) {
			throw new InvalidReturnException();
		}
	}

	/*
	 * This class handle "method call line" case.
	 * @param lineOperation - not null LineOperation
	 * @throws SJavaException InvalidMethodCallException
	 */
	private void methodCallHandle(MethodCallLineOperation lineOperation) throws SJavaException {
		if (!globalScope.isMethodExist(lineOperation.getMethodName())) {
			throw new InvalidMethodCallException();
		}
		validateMethodCall(lineOperation);
	}

	/*
	This class validate that params methods are valid.  otherwise throw an InvalidMethodCallException.
	 */
	private void validateMethodCall(MethodCallLineOperation operation) throws SJavaException {
		//assuming method exists.
		String[] calledMethodArgs = operation.getArguments();
		Method calledMethod = globalScope.getMethods().get(operation.getMethodName());
		validateMethodCallNumberOfArgs(calledMethod, calledMethodArgs);
		compareMethodParamsToArgs(calledMethod.getParameters(), calledMethodArgs);
	}

	/*
	Checks if number of arguments of method call is equal to method's parameters' original number.
	 */
	private void validateMethodCallNumberOfArgs(Method calledMethod, String[] calledMethodArgs)
			throws InvalidMethodCallException {
		int paramAmount = Arrays.equals(calledMethodArgs, EMPTY_PARAMS) ? 0 : calledMethodArgs.length;
		if (calledMethod.getParameters().size() != paramAmount) {

			throw new InvalidMethodCallException();
		}
	}

	/*
	Iterates over method arguments and check if they are legal according to method's parameter list.
	 */
	private void compareMethodParamsToArgs(HashMap<String, Variable> methodsOriginalParams,
										   String[] calledMethodArgs) throws InvalidMethodCallException {
		int methodArgIndex = 0;
		for (Variable param : methodsOriginalParams.values()) {
			Type currentType = param.getType();
			if (!currentType.validateValue(calledMethodArgs[methodArgIndex])) {
				//if current argument value is not a legal type, it may be a variable of the same type,
				//so we search for it and then check its type.
				Variable calledVar = scopeStack.peek().searchVariable(calledMethodArgs[methodArgIndex]);
				if (calledVar == null || calledVar.isNotInitialized() ||
					!currentType.validateType(calledVar.getType())) {
					throw new InvalidMethodCallException();
				}
			}
			methodArgIndex++;
		}
	}

}
