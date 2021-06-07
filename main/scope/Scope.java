package oop.ex6.main.scope;

import oop.ex6.main.SJavaException;
import oop.ex6.main.operation.VarAssignmentLineOperation;
import oop.ex6.main.operation.VarDeclarationLineOperation;
import oop.ex6.main.variable.*;

import java.util.HashMap;

/**
 * This class represent a single Scope abstract class.
 */
public abstract class Scope {
	protected final Scope parent;
	protected final HashMap<String, Variable> variableHashMap;

	/**
	 * This is the constructor of Scope
	 * @param parent - every scope is in an inner scope of another scope (except The first scope that )
	 * @param variableHashMap - Hash map that Contains all variables that declared within the current
	 * 		scope.
	 */
	public Scope(Scope parent, HashMap<String, Variable> variableHashMap) {
		this.parent = parent;
		this.variableHashMap = variableHashMap;
	}

	/**
	 * This method gets a string value that represent a variable name, and searches for a Variable object
	 * with
	 * the same in the scope.
	 * @param varName - the name of the requested variable
	 * @return Variable object
	 */
	protected abstract Variable searchVariable(String varName);


	/**
	 * This method check if assignment is valid and if not throw a SJavaException
	 * @param variable - The variable that we want to assign the value into it.
	 * @param value - value to assign into variable.
	 * @throws SJavaException - syntax error
	 */
	private void validateVarAssign(Variable variable, String value) throws SJavaException {
		if (variable.getType().validateValue(value)) {
			variable.setIsInitialized();
			return;
		}
		// check if value is maybe an exist variable name
		VariableFactory.validateVarName(value);
		Variable result = searchVariable(value);

		if (result == null || !(variable.getType().validateType(result.getType()))
			|| variable == result) {
			throw new InvalidVariableAssignmentException();
		}
		if (result.isNotInitialized()) {
			throw new InvalidUninitializedAssignmentsException();
		}
	}

	/**
	 * This method handle cases of variable assignment line operation.
	 * @param operation - line operation object
	 * @throws SJavaException - syntax error
	 */
	public void handleAssignment(VarAssignmentLineOperation operation) throws SJavaException {
		String value = operation.getValue();
		Variable variable = searchVariable(operation.getVarName());
		if (variable == null) {
			throw new InvalidVariableAssignmentException();
		}
		if (variable.isFinal()) {
			throw new InvalidFinalVariableAssignmentException();
		}
		validateVarAssign(variable, value);
	}

	/**
	 * This method handle cases of lines in which there is an operation of declaring a variable.
	 * @param operation - a line operation
	 * @throws SJavaException - syntax error
	 */
	public void handleDeclaration(VarDeclarationLineOperation operation) throws SJavaException {
		//check if exist in local variable map
		if (variableHashMap.containsKey(operation.getVarName())) {
			throw new DuplicatedVariableNameException();
		}
		//try to create var
		Variable var = VariableFactory.createVariable(operation);
		variableHashMap.put(var.getName(), var);

		//check if its also a line assignment.
		String value = operation.getValue();
		if (var.isFinal() && value == null) {
			throw new InvalidUninitializedFinalVariableException();
		}
		if (value != null) {
			validateVarAssign(var, value);
		}
	}
}

