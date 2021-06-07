package oop.ex6.main.scope;

import oop.ex6.main.KeywordCheck;
import oop.ex6.main.analyzer.DuplicatedMethodNameException;
import oop.ex6.main.SJavaException;
import oop.ex6.main.operation.MethodInitLineOperation;
import oop.ex6.main.operation.VarDeclarationLineOperation;
import oop.ex6.main.variable.DuplicatedVariableNameException;
import oop.ex6.main.variable.Variable;
import oop.ex6.main.variable.VariableFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This class represent a single GlobalScope.
 */

public class GlobalScope extends Scope {
	private final HashMap<String, Method> methods;

	/**
	 * defult Constructor of GlobalScope
	 */
	public GlobalScope() {
		super(null, new HashMap<>());// global parent is always null
		methods = new HashMap<>();
	}

	/**
	 * @return an HashMap that contain all methods object in the global scope.
	 */
	public HashMap<String, Method> getMethods() {
		return methods;
	}

	/**
	 * This method check if method is exist in methods
	 * @param methodName - method name to check
	 * @return true iff method is exist in methods
	 */
	public boolean isMethodExist(String methodName) {
		return methods.containsKey(methodName);
	}

	/**
	 * This method create a new method
	 * @param methodLineOperation line operation that contain the method declaration details.
	 * @throws SJavaException - syntax error
	 */
	public void createNewMethod(MethodInitLineOperation methodLineOperation) throws SJavaException {
		validateMethodName(methodLineOperation.getMethodName());
		LinkedHashMap<String, Variable> parameters = initMethodParams(methodLineOperation);
		Method method = new Method(methodLineOperation, parameters);
		methods.put(method.getName(), method);
	}

	/*
	 * This method validate if the method has a valid name. if not, throw an exception. otherwise do nothing.
	 * @param methodName name of the method
	 * @throws SJavaException InvalidKeyWordUseException or DuplicatedMethodNameException
	 */
	private void validateMethodName(String methodName) throws SJavaException {
		if (KeywordCheck.isKeyword(methodName)) {
			throw new InvalidKeyWordUseException();
		}
		if (methods.containsKey(methodName)) {
			throw new DuplicatedMethodNameException();
		}

	}

	/*
	 * This methpd get a method line operation and return an hash-map container with order (LinkedHashMap)
	 * that stored the method params.
	 * @param methodLineOperation line operation that contain the method declaration details.
	 * @return hash-map container with order (LinkedHashMap) that stored the method params.
	 * @throws SJavaException InvalidMethodLineFormatException or DuplicatedVariableNameException
	 */
	private LinkedHashMap<String, Variable> initMethodParams(MethodInitLineOperation methodLineOperation)
			throws SJavaException {

		LinkedHashMap<String, Variable> parameters = new LinkedHashMap<>();
		for (VarDeclarationLineOperation varOperation : methodLineOperation.getParams()) {
			validateParamValue(varOperation.getValue(), parameters, varOperation.getVarName());
			Variable param = VariableFactory.createVariable(varOperation);
			param.setIsInitialized(); //param always init. (that's a method call)
			parameters.put(varOperation.getVarName(), param);
		}
		return parameters;
	}

	/*
	This method validate the param value
	 */
	private void validateParamValue(String value, LinkedHashMap<String, Variable> params, String varName)
			throws SJavaException {
		if (value != null) {
			throw new InvalidMethodLineFormatException();
		}
		if (params.containsKey(varName)) {
			throw new DuplicatedVariableNameException();
		}
	}

	/**
	 * This method gets a string value that represent a variable name, and searches for a Variable object
	 * with*the same name in the global scope.
	 * @param varName - the name of the requested variable
	 * @return Variable object
	 */
	@Override
	protected Variable searchVariable(String varName) {
		return variableHashMap.get(varName);
	}
}
