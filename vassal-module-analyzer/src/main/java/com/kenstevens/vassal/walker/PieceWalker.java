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

	public int visitPieces(Stack<Buildable> path, Element buildElement,
			Buildable buildable) {

		if (!(buildable instanceof AbstractConfigurable)) {
			return 0;
		}
		if (ColourPicker.isOtherPlayer(buildable)) {
			return 0;
		}
		AbstractConfigurable abstractConfigurable = (AbstractConfigurable) buildable;
		path.push(buildable);
		int pieceCount = visitPieces(path, abstractConfigurable);
		path.pop();
		return pieceCount;
	}

	private int visitPieces(List<Buildable> path,
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
			return 0;
		}
		Collections.reverse(pieces);
		int pieceCount = 0;
		for (GamePiece piece : pieces) {
			moduleVisitor.visit(path, piece);
			++pieceCount;
		}
		return pieceCount;
	}
}
