package com.kenstevens.vassal.walker;

import java.util.Stack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import VASSAL.build.Buildable;
import VASSAL.build.Builder;
import VASSAL.build.IllegalBuildException;

import com.kenstevens.vassal.keystroke.FindKeyVisitor;
import com.kenstevens.vassal.mock.MockBuildable;

public class ModuleWalker {
	private int pieceCount = 0;

	private void walk(Stack<Buildable> path, Element buildElement, ModuleVisitor moduleVisitor) {
		String tagName = buildElement.getTagName();
		Buildable buildable = getBuildable(buildElement, tagName);
		if (buildable == null) {
			buildable = new MockBuildable(buildElement);
		} else if (ColourPicker.isOtherPlayer(buildable)) {
			return;
		}

		moduleVisitor.startVisit(path, buildable);
		// In practice, either we'll be walking pieces or visiting children here...
		PieceWalker pieceWalker = new PieceWalker(moduleVisitor);
		pieceCount += pieceWalker.visitPieces(path, buildElement, buildable);
		visitChildren(path, buildElement, moduleVisitor, buildable);
		moduleVisitor.endVisit(path, buildable);
	}

	private void visitChildren(Stack<Buildable> path, Element buildElement,
			ModuleVisitor moduleVisitor, Buildable buildable) {
		path.push(buildable);
		for (Node child = buildElement.getFirstChild(); child != null; child = child
				.getNextSibling()) {
			if (Node.ELEMENT_NODE == child.getNodeType()) {
				Element childNode = (Element) child;
				walk(path, childNode, moduleVisitor);
			}
		}
		path.pop();
	}

	private Buildable getBuildable(Element buildElement, String tagName) {
		Buildable retval = null;
		try {
			Class.forName(tagName);
			retval = Builder.create(buildElement);
		} catch (IllegalBuildException e) {
			// Skip
		} catch (ClassNotFoundException e) {
			// Skip
		}
		return retval;
	}

	public void walk(Element buildElement, ModuleVisitor moduleVisitor) {
		walk(new Stack<Buildable>(), buildElement, moduleVisitor);
	}

	public int getPieceCount() {
		return pieceCount;
	}
}