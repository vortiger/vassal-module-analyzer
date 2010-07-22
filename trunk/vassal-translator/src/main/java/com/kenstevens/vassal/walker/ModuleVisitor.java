package com.kenstevens.vassal.walker;

import org.w3c.dom.Element;

import VASSAL.build.Buildable;

public interface ModuleVisitor {

	void visit(int depth, Element buildElement, Buildable buildable);

}
