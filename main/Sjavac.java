package oop.ex6.main;

import oop.ex6.main.analyzer.GlobalAnalyzer;
import oop.ex6.main.analyzer.MethodsAnalyzer;
import oop.ex6.main.parser.Parser;
import oop.ex6.main.parser.preparsing.Cleaner;
import oop.ex6.main.parser.preparsing.FileReader;
import oop.ex6.main.scope.GlobalScope;
import oop.ex6.main.scope.Method;

import java.io.IOException;
import java.util.List;

/**
 * Driver Class of the program.
 */
public class Sjavac {
	private static final int VALID_ARG_NUM = 1; //legal number of args.
	private static final int SUCCESS = 0; //printed code upon success.
	private static final int FAILURE = 1; //printed code upon failure.

	/**
	 * Main method of the program.
	 * @param args - String array of length 1, where args[0] is a path to sjava file.
	 */
	public static void main(String[] args) {
		try {
			if (args.length != VALID_ARG_NUM) {
				throw new InvalidUsageException();
			}
			List<String> lines = FileReader.getLines(args[0]);
			Cleaner.cleanLines(lines);

			Parser.getInstance().setLines(lines);

			GlobalAnalyzer globalAnalyzer = new GlobalAnalyzer(lines);
			GlobalScope globalScope = globalAnalyzer.analyzeGlobalLines();

			MethodsAnalyzer methodsAnalyzer = new MethodsAnalyzer(globalScope, lines);
			for (Method method : globalScope.getMethods().values()) {
				methodsAnalyzer.AnalyzeMethod(method);
			}
			System.out.println(SUCCESS);
		} catch (SJavaException | InvalidUsageException | IOException e) {
			System.out.println(FAILURE);
			System.err.println(e.getMessage());
		}
	}
}
