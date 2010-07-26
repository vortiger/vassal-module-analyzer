package com.kenstevens.vassal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.w3c.dom.Element;

import com.kenstevens.vassal.util.VassalModuleLoader;

import VASSAL.build.Builder;
import VASSAL.build.GameModule;

public class VassalModuleWriterTest {
	static protected final String FILENAME = "src/test/resources/NewTTA_2.48.zip";

	@Test
	public void loadModule() throws IOException {
		VassalModuleLoader vassalModuleLoader = new VassalModuleLoader();
		GameModule module = vassalModuleLoader.load(FILENAME);
		org.w3c.dom.Document doc = Builder.createNewDocument();
	    Element buildElement = module.getBuildElement(doc);
	    assertEquals("VASSAL.launch.BasicModule", buildElement.getTagName());
		doc.appendChild(buildElement);
	}
}
