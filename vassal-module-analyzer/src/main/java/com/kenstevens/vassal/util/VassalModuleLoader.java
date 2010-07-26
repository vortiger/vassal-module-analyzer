package com.kenstevens.vassal.util;

import java.io.IOException;

import VASSAL.build.GameModule;
import VASSAL.tools.icon.IconFactory;

import com.kenstevens.vassal.mock.MockMenuManager;

public class VassalModuleLoader {

	public GameModule load(String filename) throws IOException {
		VassalModuleReader vassalModuleReader = new VassalModuleReader();
		new MockMenuManager();
		new IconFactory();
		return vassalModuleReader.read(filename);
	}

}
