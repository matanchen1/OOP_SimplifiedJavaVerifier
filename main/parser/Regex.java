package oop.ex6.main.parser;

import oop.ex6.main.variable.Type;

import java.util.regex.Pattern;

/**
 * This class holds every regex related items the program uses.
 */
public class Regex {
	/**
	 * Regex of two or more adjacent spaces.
	 */
	public static final String TWO_OR_MORE_SPACES = "\\s{2,}";
	/**
	 * Valid int value regex.
	 */
	public static final String VALID_INT_VALUE = " ?[-+]?\\d+ ?";
	/**
	 * Valid double value regex.
	 */
	public static final String VALID_DOUBLE_VALUE = " ?[+-]?(?:(?:\\.\\d)+|(?:\\d+\\.?\\d*)) ?";
	/**
	 * Valid string value regex.
	 */
	public static final String VALID_STRING_VALUE = " ?\".*\" ?";
	/**
	 * Valid char value regex.
	 */
	public static final String VALID_CHAR_VALUE = " ?'.' ?";
	/**
	 * Valid boolean value regex.
	 */
	public static final String VALID_BOOLEAN_VALUE
			= " ?(?:true|false|[+-]?(?:(?:\\.\\d)+|(?:\\d+\\.?\\d*)) ?) ?";
	/**
	 * key of group 'final'.
	 */
	protected static final String FINAL_GROUP = "final";
	/**
	 * key of group 'type'
	 */
	protected static final String TYPE_GROUP = "type";
	/**
	 * key of group 'rest'
	 */
	protected static final String VARS_GROUP = "rest";
	/**
	 * key of group 'name'
	 */
	protected static final String NAME_GROUP = "name";
	/**
	 * key of group 'value'
	 */
	protected static final String VALUE_GROUP = "value";
	/**
	 * key of group 'params'
	 */
	protected static final String PARAM_LINE_GROUP = "params";
	/**
	 * key of group 'condition'
	 */
	protected static final String CONDITION_GROUP = "condition";
	/**
	 * block closer char.
	 */
	protected static final String BLOCK_CLOSE = "}";
	/**
	 * regex separator char.
	 */
	public static final String REGEX_SEPARATOR = "|";
	/**
	 * delimeter between items.
	 */
	protected static final String DELIMITER = ",";

	/**
	 * Condition separator.
	 */
	public static final String CONDITION_OPERATORS_REGEX = "&&|\\|\\|";
	/**
	 * Valid variable name regex.
	 */
	private static final String VALID_VAR_NAME_PATTERN = "(?:[a-zA-Z]\\w*)|_\\w+";
	/**
	 * Valid variable name Pattern object.
	 */
	public static final Pattern validVarPattern = Pattern.compile(VALID_VAR_NAME_PATTERN);

	/**
	 * Valid return line regex.
	 */
	public static final String RETURN_REGEX = "return ?;";
	/**
	 * Valid return line Pattern object.
	 */
	public static Pattern returnPattern = Pattern.compile(RETURN_REGEX);
	/*
	zero or more white space chars regex.
	 */
	private static final String ONLY_WHITESPACE_REGEX = "\\s*";
	/**
	 * Empty line Pattern object.
	 */
	public static final Pattern isEmptyLinePattern = Pattern.compile(ONLY_WHITESPACE_REGEX);
	/*
	Comment line regex.
	 */
	private static final String COMMENT_PREFIX_REGEX = "^[/]{2}";
	/**
	 * Comment line Pattern object.
	 */
	public static final Pattern isCommentLinePattern = Pattern.compile(COMMENT_PREFIX_REGEX);
	/*
	Variable declaration regex.
	 */
	private static final String DEC_PATTERN_REGEX = "^(?:(?<" + FINAL_GROUP + ">final )? ?(?<" + TYPE_GROUP +
													">" + Type.getTypeRegexLine() + "))(?<" + VARS_GROUP +
													">.+);";
	/**
	 * Var declaration Pattern object.
	 */
	protected static final Pattern varDeclarationPattern = Pattern.compile(DEC_PATTERN_REGEX);
	/*
	Single var assignment regex.
	 */
	private static final String SINGLE_ASSIGN_OPT_REGEX = "(?<" + NAME_GROUP + ">" + VALID_VAR_NAME_PATTERN +
														  ")(?: ?= ?(?<" + VALUE_GROUP + ">.+))?";
	/**
	 * Single optional assignment Pattern object.
	 */
	protected static final Pattern singleOptAssignmentPattern = Pattern.compile(SINGLE_ASSIGN_OPT_REGEX);

	/*
	Non-optional assignment variable regex.
	 */
	private static final String NON_OPT_ASSIGNMENT_REGEX = "(?<" + VARS_GROUP +
														   ">(?:[a-zA-Z]\\w*|_\\w+) ?=(?:.+));";
	/**
	 * Non-optional variable assignment Pattern object.
	 */
	protected static final Pattern nonOptAssignmentPattern = Pattern.compile(NON_OPT_ASSIGNMENT_REGEX);

	/*
	Method initialization regex.
	 */
	private static final String METHOD_INIT_REGEX = "void (?<" + NAME_GROUP + ">(?:[a-zA-Z]\\w*)) ?\\((?<" +
													PARAM_LINE_GROUP + ">.*)\\) ?\\{";
	/**
	 * Method initialization Pattern object.
	 */
	public static final Pattern methodInitPattern = Pattern.compile(METHOD_INIT_REGEX);

	/*
	Parameter declaration regex.
	 */
	private static final String PARAM_DEC_REGEX = "(?:(?<" + FINAL_GROUP + ">final )? ?(?<" + TYPE_GROUP +
												  ">" + Type.getTypeRegexLine() + ")) (?<" + NAME_GROUP +
												  ">" + VALID_VAR_NAME_PATTERN + ")";
	/**
	 * Parameter declaration Pattern object.
	 */
	protected static final Pattern paramPattern = Pattern.compile(PARAM_DEC_REGEX);

	/*
	Method call regex.
	 */
	private static final String METHOD_CALL_REGEX = "(?<" + NAME_GROUP + ">" + VALID_VAR_NAME_PATTERN +
													") ?\\((?<" + PARAM_LINE_GROUP + ">.*)\\) ?;";
	/**
	 * Method call Pattern object.
	 */
	protected static final Pattern methodCallPattern = Pattern.compile(METHOD_CALL_REGEX);
	/*
	If\while block regex.
	 */
	private static final String IF_WHILE_BLOCK_REGEX = "(?:if|while) ?\\((?<" + CONDITION_GROUP +
													   ">.+)\\) ?\\{";
	/**
	 * if While block Pattern.
	 */
	protected static final Pattern ifWhileBlockPattern = Pattern.compile(IF_WHILE_BLOCK_REGEX);

	/**
	 * Invalid char assignment regex.
	 */
	public static final String INVALID_CHAR_ASSIGNMENT_REGEX = "'  +'";

}