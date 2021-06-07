package oop.ex6.main.variable;

import oop.ex6.main.KeywordCheck;
import oop.ex6.main.SJavaException;
import oop.ex6.main.operation.VarDeclarationLineOperation;
import oop.ex6.main.parser.Regex;
import oop.ex6.main.scope.InvalidKeyWordUseException;

import java.util.regex.Matcher;

/**
 * A Static factory class, responsible of creating Variable objects out of Operation's objects.
 */
public class VariableFactory {
	/**
	 * This method gets a VarDeclarationLineOperation object, and returns the variable objects that has been
	 * declared.
	 * @param varDeclaration - VarDeclarationLineOperation object
	 * @return Variable object.
	 * @throws SJavaException
	 */
	public static Variable createVariable(VarDeclarationLineOperation varDeclaration) throws SJavaException {
		String varName = varDeclaration.getVarName();
		validateVarName(varName);
		return new Variable(varName, varDeclaration.getVarType(), varDeclaration.isFinal(), false);
	}

	/**
	 * This method validates variable name according to the regex rules.
	 * @param varName - Variable name to be checked.
	 * @throws SJavaException - InvalidVariableNameException if regex is not matched,
	 * InvalidKeyWordUseException if varName is a reserved keyword of s-java.
	 */
	public static void validateVarName(String varName) throws SJavaException {
		Matcher varNameMatcher = Regex.validVarPattern.matcher(varName);
		if (!varNameMatcher.matches()) {
			throw new InvalidVariableNameException();
		}
		if (KeywordCheck.isKeyword(varName)) {
			throw new InvalidKeyWordUseException();
		}
	}
}
