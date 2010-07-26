package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class UsePrototypePrinter {

	public static void print(int depth, UsePrototype prototype) {
		Printer.println(depth, "Prototype name: "+prototype.getPrototypeName());
	}
}
