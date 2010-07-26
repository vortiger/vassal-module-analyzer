package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class MarkerPrinter {

	public static void print(int depth, Marker marker) {
		for (int i = 0; i < marker.keys.length; ++i) {
			Printer.println(depth, "Property name: "+marker.keys[i]);
			Printer.println(depth, "Property value: "+marker.values[i]);
		}
	}
}
