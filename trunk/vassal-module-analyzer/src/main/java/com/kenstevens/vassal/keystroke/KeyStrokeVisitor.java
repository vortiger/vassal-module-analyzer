package com.kenstevens.vassal.keystroke;

import java.util.List;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;


public interface KeyStrokeVisitor {
	void visit(BuildableKeyField buildableKeyField);

	void visit(List<Buildable> path, Buildable buildable);

}
