package com.kenstevens.vassal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.w3c.dom.Element;

import VASSAL.build.Builder;

import com.kenstevens.vassal.keystroke.FieldPairVisitor;
import com.kenstevens.vassal.keystroke.FindKeyVisitor;
import com.kenstevens.vassal.util.Printer;
import com.kenstevens.vassal.walker.ModuleWalker;

public class FieldPairVisitorTest extends VassalModuleLoaderTest {

	@Test
	public void walkModule() throws IOException {
		Printer.writeTo("by_keys.txt");
		org.w3c.dom.Document doc = Builder.createNewDocument();
	    Element buildElement = module.getBuildElement(doc);
	    assertEquals("VASSAL.launch.BasicModule", buildElement.getTagName());

		ModuleWalker moduleWalker = new ModuleWalker();
		FieldPairVisitor fieldPairVisitor = new FieldPairVisitor();
		moduleWalker.walk(buildElement, new FindKeyVisitor(fieldPairVisitor));
		System.out.println("Finished walk.");
		fieldPairVisitor.printPairs();
		System.out.println("Finished print.");
	}
}
