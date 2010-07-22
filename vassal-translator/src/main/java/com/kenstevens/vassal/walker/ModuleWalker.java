package com.kenstevens.vassal.walker;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import VASSAL.build.Buildable;
import VASSAL.build.Builder;
import VASSAL.build.IllegalBuildException;

public class ModuleWalker {

	private void walk(int depth, Element buildElement, ModuleVisitor visitor) {
		String tagName = buildElement.getTagName();
		visitElement(depth, buildElement, visitor, tagName);

		for (Node child = buildElement.getFirstChild(); child != null; child = child
				.getNextSibling()) {
			if (Node.ELEMENT_NODE == child.getNodeType()) {
				Element childNode = (Element) child;
				walk(depth + 1, childNode, visitor);
			}
		}
	}

	private void visitElement(int depth, Element buildElement, ModuleVisitor visitor,
			String tagName) {
		try {
			Class.forName(tagName);
			Buildable buildable = Builder.create(buildElement);
			visitor.visit(depth, buildElement, buildable);
		} catch (IllegalBuildException e) {
			// Skip
		} catch (ClassNotFoundException e) {
			// Skip
		}
	}

	public void walk(Element buildElement, FindKeyVisitor findKeyVisitor) {
		walk(0, buildElement, findKeyVisitor);
	}
}