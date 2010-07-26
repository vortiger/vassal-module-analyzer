package com.kenstevens.vassal.keystroke;

public class KeyFieldType {
	private final boolean listener;

	public KeyFieldType(boolean listener) {
		this.listener = listener;
		
	}

	public boolean isListener() {
		return listener;
	}
}
