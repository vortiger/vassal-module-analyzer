package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class RestrictCommandsPrinter {

	public static void print(int depth, RestrictCommands restrictCommands) {
		Printer.println(depth, "When: "+restrictCommands.propertyMatch.getExpression());
		Printer.println(depth, "Restriction: "+restrictCommands.action);
		Printer.println(depth, "Restricted Keys: "+Printer.toString(restrictCommands.watchKeys));
	}

}
