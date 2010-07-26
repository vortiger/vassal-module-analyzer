package com.kenstevens.vassal.util;

import java.io.IOException;

import VASSAL.build.GameModule;
import VASSAL.launch.BasicModule;
import VASSAL.tools.ArchiveWriter;

public class VassalModuleReader {
	// Looks like I need to mock out the UI
	public GameModule read(String filename) throws IOException {
		ArchiveWriter archive = new ArchiveWriter(filename);
		BasicModule module = new BasicModule(archive);
		GameModule.init(module);
		return GameModule.getGameModule();
	}
}
