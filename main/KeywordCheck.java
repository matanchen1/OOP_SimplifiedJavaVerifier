package oop.ex6.main;

import oop.ex6.main.variable.Type;

import java.util.Arrays;
import java.util.HashSet;

/**
 * This class responsible of validating all the reserved keywords in s-java.
 */
public class KeywordCheck {
	// container that contain all the S-java reserved String-words
	// excluding Type reserved word. Which are managed independently by the Type class
	private static final HashSet<String> keywordsOthers; //using hashset for O(1)

	static {
		// initialization of S-java Key words (excluding Type reserved word).
		keywordsOthers = new HashSet<>(Arrays.asList
				("void", "final", "return", "if", "while", "true", "false"));
	}

	/**
	 * This method get a string and return true iff its a key word.
 	 * @param stringToCheck - string to check
	 * @return true iff stringToCheck is a keyword.
	 */
	public static boolean isKeyword(String stringToCheck) {
		return (Type.getType(stringToCheck) != null || keywordsOthers.contains(stringToCheck));
	}
}
