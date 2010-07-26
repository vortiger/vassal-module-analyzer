package com.kenstevens.vassal.mock;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import VASSAL.build.Buildable;

public class MockBuildable implements Buildable {

	private Element element;

	public MockBuildable(Element element) {
		this.element = element;
	}
	
	@Override
	public void add(Buildable child) {
	}

	@Override
	public void addTo(Buildable parent) {
	}

	@Override
	public void build(Element element) {
		this.element = element;
	}

	@Override
	public Element getBuildElement(Document doc) {
		return element;
	}

}
