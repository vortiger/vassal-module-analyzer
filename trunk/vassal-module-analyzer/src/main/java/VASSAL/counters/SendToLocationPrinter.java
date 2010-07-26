package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class SendToLocationPrinter {

	public static void print(int depth, SendToLocation sendToLocation) {
		Printer.println(depth, "In Key: "+Printer.toString(sendToLocation.key));
		if (sendToLocation.backKey != null) {
			Printer.println(depth, "Back Key: "+Printer.toString(sendToLocation.backKey));
		}
		Printer.println(depth, "Destination: "+sendToLocation.destination);
		Printer.println(depth, "Map: "+sendToLocation.mapId.getText());
		Printer.println(depth, "Board: "+sendToLocation.boardName.getText());

	}

}
