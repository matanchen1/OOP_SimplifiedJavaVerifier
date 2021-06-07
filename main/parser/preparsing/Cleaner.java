package oop.ex6.main.parser.preparsing;

import oop.ex6.main.parser.Regex;

import java.util.List;
import java.util.ListIterator;

/**
 * This class responsible of cleaning the lines from empty lines, comments line.
 */
public class Cleaner {
	/*
	We replace every two or more spaces with a single space.
	We might replace the following line:
		char z = '  ';
	with:
		char z = ' ';
	And thus making this invalid line into a valid line.
	In order to avoid that, we first replace the pattern '  +' with '..' so the invalidity of the line
	remains. 
	 */
	private static final String INVALID_CHAR_ASSIGNMENT_REPLACEMENT = "'..'";
	private static final String SINGLE_SPACE = " "; //single space

	/**
	 * This method gets list of lines and cleans them.
	 * @param lines lines to be cleaned.
	 */
	public static void cleanLines(List<String> lines) {
		lines.removeIf(Cleaner::toRemove);
		for (ListIterator<String> i = lines.listIterator(); i.hasNext(); ) {
			i.set(i.next().trim().replaceAll(Regex.INVALID_CHAR_ASSIGNMENT_REGEX,
											 INVALID_CHAR_ASSIGNMENT_REPLACEMENT)
						  .replaceAll(Regex.TWO_OR_MORE_SPACES, SINGLE_SPACE));
		}
	}

	/*
	returns true iff we supposed to remove this line.
	 */
	private static boolean toRemove(String line) {
		return isEmpty(line) || isComment(line);
	}

	/*
	returns true iff this line is empty or composed of spaces.
	 */
	private static boolean isEmpty(String line) {
		return Regex.isEmptyLinePattern.matcher(line).matches();
	}

	/*
	returns true iff line is a comment line.
	 */
	private static boolean isComment(String line) {
		return Regex.isCommentLinePattern.matcher(line).lookingAt();
	}
}
