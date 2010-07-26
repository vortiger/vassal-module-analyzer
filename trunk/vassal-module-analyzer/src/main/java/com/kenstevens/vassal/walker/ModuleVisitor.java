package com.kenstevens.vassal.walker;

import java.util.List;
import java.util.Stack;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.module.Map;
import VASSAL.counters.GamePiece;

public interface ModuleVisitor {

	void visit(List<Buildable> path, GamePiece piece);

	void visit(List<Buildable> path, Map map);

	void startVisit(List<Buildable> path, Buildable buildable);

	void endVisit(Stack<Buildable> path, Buildable buildable);

}
