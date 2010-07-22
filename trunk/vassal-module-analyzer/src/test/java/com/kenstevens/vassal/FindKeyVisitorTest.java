package com.kenstevens.vassal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.w3c.dom.Element;

import VASSAL.build.Builder;

import com.kenstevens.vassal.walker.FindKeyVisitor;
import com.kenstevens.vassal.walker.ModuleWalker;

public class FindKeyVisitorTest extends VassalModuleLoaderTest {

	@Test
	public void walkModule() throws IOException {
		org.w3c.dom.Document doc = Builder.createNewDocument();
	    Element buildElement = module.getBuildElement(doc);
	    assertEquals("VASSAL.launch.BasicModule", buildElement.getTagName());

		ModuleWalker moduleWalker = new ModuleWalker();
		moduleWalker.walk(buildElement, new FindKeyVisitor());
	}
}
