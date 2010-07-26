package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class DeletePrinter {

	public static void print(int depth, Delete delete) {
		Printer.println(depth, "Key in: " + Printer.toString(delete.key));
	}
}
