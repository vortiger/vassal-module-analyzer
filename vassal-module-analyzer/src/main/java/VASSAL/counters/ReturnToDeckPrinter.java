package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class ReturnToDeckPrinter {

	public static void print(int depth, ReturnToDeck returnToDeck) {
		Printer.println(depth, "Deck: "+returnToDeck.deckId);
		Printer.println(depth, "In Key: "+Printer.toString(returnToDeck.returnKey));
	}
}
