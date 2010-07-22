package com.kenstevens.vassal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

import VASSAL.build.GameModule;

public class VassalModuleLoaderTest {
	static protected final String FILENAME = "src/test/resources/NewTTA_2.48.zip";
	protected GameModule module;

	@Before
	public void loadModule() throws IOException {
		VassalModuleLoader vassalModuleLoader = new VassalModuleLoader();
		module = vassalModuleLoader.load(FILENAME);
		Printer.writeTo("TTA.txt");
	}

	@After
	public void close() throws IOException {
		Printer.close();
	}

	public void moduleLoaded() {
		assertNotNull(module);
		assertEquals("Through The Ages", module.getConfigureName());
	}
}
