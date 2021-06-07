package oop.ex6.main.scope;

import oop.ex6.main.variable.Variable;

import java.util.HashMap;

/**
 * This class represent a single Method Scope class.
 */
public class MethodsScope extends Scope {
	private static MethodsScope mainMethodScope; // the first Scope of the method (means, the method itself)

	/**
	 * The constructor of MethodsScope
	 * @param parent - every MethodScope is in an inner scope of another MethodScope (except The
	 * 		mainMethodScope that its parent is GlobalScope)
	 * @param variableHashMap - Hash map that Contains all variables that declared within the current
	 * 		scope.
	 */
	public MethodsScope(Scope parent, HashMap<String, Variable> variableHashMap) {
		super(parent, variableHashMap);
	}

	/**
	 * init a MethodScope
	 * @param mainMethodScope - the first Scope of the method
	 */
	public static void setMainMethodScope(MethodsScope mainMethodScope) {
		MethodsScope.mainMethodScope = mainMethodScope;
	}

	/**
	 * This method gets a string value that represent a variable name, and searches for a Variable object
	 * with
	 * the same name in its method scope tree
	 * @param varName - the name of the requested variable
	 * @return Variable object
	 */
	public Variable searchVariable(String varName) {
		Variable result = searchVariableInScopeTree(varName);
		if (result == null) {
			// if we got here, current scope is the method main scope (means curScope.parent.parent is null)
			result = searchVariableInGlobalScope(varName);
		}
		return result;
	}

	/*
	This method search variable in the current Scope method Tree
	more details about implementation can be found at the README file
 	*/
	protected Variable searchVariableInScopeTree(String varName) {
		Scope current = this;
		Variable result;
		while (true) {
			result = current.variableHashMap.get(varName);
			if (result != null) {
				return result;
			}
			if (current == mainMethodScope) {
				break;
			}
			current = current.parent;
		}
		return null; // in case we didn't find varName
	}

	/*
	This method search variable only in "Global Scope"
	more details about implementation can be found at the README file
	 */
	private Variable searchVariableInGlobalScope(String varName) {
		Scope globalScope = mainMethodScope.parent;
		Variable result = (globalScope.variableHashMap.get(varName));
		if (result != null) {
			if (result.isNotInitialized()) {
				Variable newCloneVar = new Variable(result);
				mainMethodScope.variableHashMap.put(newCloneVar.getName(), newCloneVar);
			}
		}
		return result;
	}
}
