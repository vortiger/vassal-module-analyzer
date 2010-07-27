package VASSAL.counters;

import java.util.ArrayList;
import java.util.List;

import VASSAL.tools.NamedKeyStroke;

import com.kenstevens.vassal.util.Printer;

public class TriggerActionPrinter {

	public static void print(int depth, TriggerAction triggerAction) {
		Printer.println(depth, "Menu: "+triggerAction.command);
		Printer.println(depth, "When: "+triggerAction.propertyMatch.getExpression());
		List<NamedKeyStroke>inKeys = new ArrayList<NamedKeyStroke>();
		if (triggerAction.key != null) {
			inKeys.add(triggerAction.key);
		}
		for (NamedKeyStroke key : triggerAction.watchKeys) {
			inKeys.add(key);
		}
		Printer.println(depth, "In Keys: "+Printer.toString(inKeys));
		Printer.println(depth, "Out Keys: "+Printer.toString(triggerAction.actionKeys));
	}

}
