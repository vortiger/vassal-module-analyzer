package com.kenstevens.vassal.walker;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.w3c.dom.Element;

import com.google.common.collect.Lists;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.module.Map;
import VASSAL.build.module.PrototypeDefinition;
import VASSAL.build.widget.PieceSlot;
import VASSAL.counters.GamePiece;
import VASSAL.counters.GamePieceExploder;

public class PieceWalker {

	private final ModuleVisitor moduleVisitor;

	public PieceWalker(ModuleVisitor moduleVisitor) {
		this.moduleVisitor = moduleVisitor;
	}

	public void visitPieces(Stack<Buildable> path, Element buildElement,
			Buildable buildable) {
		if (!(buildable instanceof AbstractConfigurable)) {
			return;
		}
		if (ColourPicker.isOtherPlayer(buildable)) {
			return;
		}
		AbstractConfigurable abstractConfigurable = (AbstractConfigurable) buildable;
		path.push(buildable);
		visitPieces(path, abstractConfigurable);
		path.pop();
	}

	private void visitPieces(List<Buildable> path,
			AbstractConfigurable abstractConfigurable) {
		List<GamePiece> pieces = null;
		if (abstractConfigurable instanceof PrototypeDefinition) {
			pieces = GamePieceExploder
					.getPieces((PrototypeDefinition) abstractConfigurable);
		} else if (abstractConfigurable instanceof PieceSlot) {
			pieces = GamePieceExploder
					.getPieces((PieceSlot) abstractConfigurable);
		} else if (abstractConfigurable instanceof Map) {
			moduleVisitor.visit(path, (Map) abstractConfigurable);
		}
		if (pieces == null) {
			return;
		}
		Collections.reverse(pieces);
		for (GamePiece piece : pieces) {
			moduleVisitor.visit(path, piece);
		}
	}

}
