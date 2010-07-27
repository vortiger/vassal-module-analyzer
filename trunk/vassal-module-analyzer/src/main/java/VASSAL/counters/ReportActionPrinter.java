package VASSAL.counters;

import com.kenstevens.vassal.util.Printer;

public class ReportActionPrinter {

	public static void print(int depth, ReportState reportAction) {
		Printer.println(depth, "Keys in: "+Printer.toString(reportAction.keys));
		Printer.println(depth, "Report format: "+reportAction.reportFormat);
	}
}
