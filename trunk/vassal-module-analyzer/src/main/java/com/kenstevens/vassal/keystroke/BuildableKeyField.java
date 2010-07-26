package com.kenstevens.vassal.keystroke;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.KeyStroke;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.configure.HotKeyConfigurer;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.kenstevens.vassal.mock.MockBuildable;
import com.kenstevens.vassal.util.Printer;

public class BuildableKeyField {
	private final List<Buildable> path;
	private final Field field;
	private final String description;
	private final KeyStroke keyStroke;

	public BuildableKeyField(List<Buildable> path, String description,
			Field field, KeyStroke keyStroke) {
		this.path = Lists.newArrayList(path);
		this.description = description;
		this.field = field;
		this.keyStroke = keyStroke;
	}

	public List<Buildable> getPath() {
		return path;
	}

	public Field getField() {
		return field;
	}

	public String getDescription() {
		return description;
	}

	public String getShortDescription() {
		return getFieldDescription() + " = " + print(getKeyStroke());

	}

	private String getFieldDescription() {
		return description + "." + field.getName();
	}

	public String getLongDescription() {
		List<String>parts = new ArrayList<String>();
		for (Buildable buildable : path) {
			if (buildable instanceof MockBuildable) {
				continue;
			}
			parts.add(Printer.getDescription(buildable));
		}
		parts.add(getFieldDescription());
		return Joiner.on(".").join(parts);
	}

	private String print(KeyStroke keyStroke) {
		return HotKeyConfigurer.getString(keyStroke);
	}

	public int getPathSize() {
		return path.size();
	}

	public KeyStroke getKeyStroke() {
		return keyStroke;
	}

}
