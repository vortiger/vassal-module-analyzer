package com.kenstevens.vassal;

import java.io.IOException;

import com.kenstevens.vassal.mock.MockMenuManager;

import VASSAL.build.GameModule;
import VASSAL.tools.icon.IconFactory;

public class VassalModuleLoader {

	public GameModule load(String filename) throws IOException {
		VassalModuleReader vassalModuleReader = new VassalModuleReader();
		new MockMenuManager();
		new IconFactory();
		return vassalModuleReader.read(filename);
	}

}
