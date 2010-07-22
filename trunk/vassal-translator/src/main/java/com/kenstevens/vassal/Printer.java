package com.kenstevens.vassal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public final class Printer {

	private static OutputStream outputStream;
	private static PrintStream printStream;

	private Printer() {
	}

	public static void writeTo(String filename) throws FileNotFoundException {
		outputStream = new FileOutputStream(filename);
		printStream = new PrintStream(outputStream);
	}

	public static void close() throws IOException {
		printStream.close();
		outputStream.close();
	}

	public static void println(String string) {
		printStream.println(string);
	}
}
