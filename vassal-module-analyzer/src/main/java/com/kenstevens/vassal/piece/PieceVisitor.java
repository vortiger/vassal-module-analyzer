package com.kenstevens.vassal.piece;

import java.util.List;
import java.util.Stack;

import VASSAL.build.Buildable;
import VASSAL.build.module.Map;
import VASSAL.counters.CounterGlobalKeyCommand;
import VASSAL.counters.CounterGlobalKeyCommandPrinter;
import VASSAL.counters.Delete;
import VASSAL.counters.DeletePrinter;
import VASSAL.counters.DynamicProperty;
import VASSAL.counters.DynamicPropertyPrinter;
import VASSAL.counters.GamePiece;
import VASSAL.counters.Marker;
import VASSAL.counters.MarkerPrinter;
import VASSAL.counters.ReportActionPrinter;
import VASSAL.counters.ReportState;
import VASSAL.counters.RestrictCommands;
import VASSAL.counters.RestrictCommandsPrinter;
import VASSAL.counters.ReturnToDeck;
import VASSAL.counters.ReturnToDeckPrinter;
import VASSAL.counters.SendToLocation;
import VASSAL.counters.SendToLocationPrinter;
import VASSAL.counters.TriggerAction;
import VASSAL.counters.TriggerActionPrinter;
import VASSAL.counters.UsePrototype;
import VASSAL.counters.UsePrototypePrinter;

import com.kenstevens.vassal.util.Printer;
import com.kenstevens.vassal.walker.ModuleVisitor;

public class PieceVisitor implements ModuleVisitor {

	@Override
	public void startVisit(List<Buildable> path, Buildable buildable) {
		Printer.println(path.size(), Printer.getStartTag(buildable));
	}

	@Override
	public void endVisit(Stack<Buildable> path, Buildable buildable) {
		Printer.println(path.size(), Printer.getEndTag(buildable));
	}


	@Override
	public void visit(List<Buildable> path, GamePiece piece) {
		int depth = path.size();
		Printer.println(depth, Printer.getStartTag(piece));
		int indepth = depth+2;
		if (piece instanceof TriggerAction) {
			TriggerActionPrinter.print(indepth, (TriggerAction)piece);
		} else if (piece instanceof SendToLocation) {
			SendToLocationPrinter.print(indepth, (SendToLocation)piece);
		} else if (piece instanceof RestrictCommands) {
			RestrictCommandsPrinter.print(indepth, (RestrictCommands)piece);
		} else if (piece instanceof CounterGlobalKeyCommand) {
			CounterGlobalKeyCommandPrinter.print(indepth, (CounterGlobalKeyCommand)piece);
		} else if (piece instanceof ReturnToDeck) {
			ReturnToDeckPrinter.print(indepth, (ReturnToDeck)piece);
		} else if (piece instanceof Marker) {
			MarkerPrinter.print(indepth, (Marker)piece);
		} else if (piece instanceof UsePrototype) {
			UsePrototypePrinter.print(indepth, (UsePrototype)piece);
		} else if (piece instanceof ReportState) {
			ReportActionPrinter.print(indepth, (ReportState)piece);
		} else if (piece instanceof DynamicProperty) {
			DynamicPropertyPrinter.print(indepth, (DynamicProperty)piece);
		} else if (piece instanceof Delete) {
			DeletePrinter.print(indepth, (Delete)piece);
		} else {
			System.err.println("No handler for "+piece.getClass().getSimpleName());
		}
		Printer.println(depth, Printer.getEndTag(piece));
	}

	@Override
	public void visit(List<Buildable> path, Map map) {
	}
}
