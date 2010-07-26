package com.kenstevens.vassal.keystroke;

import java.util.List;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;

import com.kenstevens.vassal.util.Printer;

public class PrintKeyStrokeVisitor implements KeyStrokeVisitor {

	@Override
	public void visit(BuildableKeyField buildableKeyField) {
		Printer.println(buildableKeyField.getPathSize(), buildableKeyField.getShortDescription());
	}

	@Override
	public void visit(List<Buildable> path, Buildable buildable) {
		String description = Printer.getDescription(buildable);
		Printer.println(path.size(), description);
	}

}
