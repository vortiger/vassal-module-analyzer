package com.kenstevens.vassal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.w3c.dom.Element;

import VASSAL.build.Builder;

import com.kenstevens.vassal.keystroke.FindKeyVisitor;
import com.kenstevens.vassal.keystroke.PrintKeyStrokeVisitor;
import com.kenstevens.vassal.piece.PieceVisitor;
import com.kenstevens.vassal.util.Printer;
import com.kenstevens.vassal.walker.ModuleWalker;

public class PieceVisitorTest extends VassalModuleLoaderTest {

	@Test
	public void walkModule() throws IOException {
		Printer.writeTo("TTA_code.txt");

		org.w3c.dom.Document doc = Builder.createNewDocument();
	    Element buildElement = module.getBuildElement(doc);
	    assertEquals("VASSAL.launch.BasicModule", buildElement.getTagName());

		ModuleWalker moduleWalker = new ModuleWalker();
		moduleWalker.walk(buildElement, new PieceVisitor());
	}
}
