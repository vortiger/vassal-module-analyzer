package com.kenstevens.vassal.keystroke;

import java.util.Collection;
import java.util.List;

import javax.swing.KeyStroke;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.configure.HotKeyConfigurer;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.kenstevens.vassal.util.Printer;

public class FieldPairVisitor implements KeyStrokeVisitor {
	Multimap<KeyStroke, String> listeners = HashMultimap.create();
	Multimap<KeyStroke, String> senders = HashMultimap.create();

	@Override
	public void visit(BuildableKeyField buildableKeyField) {
		KeyStroke keyStroke = buildableKeyField.getKeyStroke();
		if (KeyFieldMap.isListener(buildableKeyField.getField())) {
			listeners.put(keyStroke, buildableKeyField.getLongDescription());
		} else {
			senders.put(keyStroke, buildableKeyField.getLongDescription());
		}
		
	}
	
	public void printPairs() {
		for (KeyStroke keyStroke : senders.keySet()) {
			Printer.println("\n["+HotKeyConfigurer.getString(keyStroke)+"]");
			printSenders(keyStroke);
			printListeners(keyStroke);
		}
	}

	private void printSenders(KeyStroke keyStroke) {
		Collection<String> keySenders = senders.get(keyStroke);
		if (keySenders.size() > 0) {
			Printer.println("\nSenders: ");
			for (String sender : keySenders) {
				Printer.println(sender);
			}
		}
	}

	private void printListeners(KeyStroke keyStroke) {
		Collection<String> keyListeners = listeners.get(keyStroke);
		if (keyListeners.size() > 0) {
			Printer.println("\nListeners: ");
			for (String listener : keyListeners) {
				Printer.println(listener);
			}
		}
	}

	@Override
	public void visit(List<Buildable> path, Buildable buildable) {
	}
}
