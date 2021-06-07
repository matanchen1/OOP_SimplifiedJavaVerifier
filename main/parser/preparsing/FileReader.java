package oop.ex6.main.parser.preparsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * static class responsible for reading an entire file.
 */
public class FileReader {
	private static final String IO_ERROR_MESSAGE = "2";

	/**
	 * Static method to retrieve the lines from a file.
	 * @param filePath - path to the file to read.
	 * @return - Queue of lines.
	 */
	public static List<String> getLines(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			throw new IOException(IO_ERROR_MESSAGE);
		}
		return lines;
	}
}