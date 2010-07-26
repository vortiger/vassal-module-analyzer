package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class CounterGlobalKeyCommandPrinter {

	public static void print(int depth, CounterGlobalKeyCommand globalKeyCommand) {
		Printer.println(depth, "When: "+globalKeyCommand.propertiesFilter.getExpression());
		Printer.println(depth, "In Key: "+Printer.toString(globalKeyCommand.key));
		Printer.println(depth, "Out Key: "+Printer.toString(globalKeyCommand.globalKey));
		Printer.println(depth, "Restrict Range: "+globalKeyCommand.restrictRange);
		Printer.println(depth, "Fixed Range: "+globalKeyCommand.fixedRange);
		Printer.println(depth, "Range: "+globalKeyCommand.range);
		Printer.println(depth, "Range Property: "+globalKeyCommand.rangeProperty);
	}
}
