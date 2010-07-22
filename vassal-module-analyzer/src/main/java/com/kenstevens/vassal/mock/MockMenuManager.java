package com.kenstevens.vassal.mock;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import VASSAL.tools.menu.MenuBarProxy;
import VASSAL.tools.menu.MenuManager;

public class MockMenuManager extends MenuManager {

	@Override
	public JMenuBar getMenuBarFor(JFrame fc) {
		return null;
	}

	@Override
	public MenuBarProxy getMenuBarProxyFor(JFrame fc) {
		return new MockMenuBarProxy();
	}

}
