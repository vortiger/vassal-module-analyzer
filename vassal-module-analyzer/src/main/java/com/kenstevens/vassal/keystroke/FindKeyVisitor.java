package com.kenstevens.vassal.keystroke;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Stack;

import javax.swing.KeyStroke;

import VASSAL.build.Buildable;
import VASSAL.build.module.Map;
import VASSAL.counters.GamePiece;
import VASSAL.tools.NamedKeyStroke;

import com.kenstevens.vassal.util.Printer;
import com.kenstevens.vassal.walker.ModuleVisitor;

public class FindKeyVisitor implements ModuleVisitor {

	private final KeyStrokeVisitor keyStrokeVisitor;

	public FindKeyVisitor(KeyStrokeVisitor keyStrokeVisitor) {
		this.keyStrokeVisitor = keyStrokeVisitor;
	}

	@Override
	public void visit(List<Buildable> path, Map map) {
		try {
			keyStrokeVisitor.visit(new BuildableKeyField(path, map
					.getConfigureName(), map.getClass().getField("moveKey"),
					map.getMoveKey()));
		} catch (NoSuchFieldException e) {
			// whatever
		}
	}

	@Override
	public void visit(List<Buildable> path, GamePiece piece) {
		Class<? extends GamePiece> clazz = piece.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			findKeyStrokesAndVisit(path, piece, fields);
		} catch (IllegalAccessException e) {
			// Can't happen
		}
	}

	private void findKeyStrokesAndVisit(List<Buildable> path, GamePiece piece,
			Field[] fields) throws IllegalAccessException {
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(piece);
			if (value == null) {
				continue;
			}
			Class<?> type = field.getType();
			String description = Printer.getDescription(piece);
			if (type.getComponentType() == null) {
				visitKeyStroke(path, description, field, value);
			} else {
				// TODO Optimize
				Object[] array = (Object[]) value;
				for (Object item : array) {
					visitKeyStroke(path, description, field, item);
				}
			}
		}
	}


	private void visitKeyStroke(List<Buildable> path, String description,
			Field field, Object value) {
		if (value instanceof NamedKeyStroke) {
			NamedKeyStroke namedKeyStroke = (NamedKeyStroke) value;
			if (namedKeyStroke != null && namedKeyStroke.getKeyStroke() != null) {
				keyStrokeVisitor.visit(new BuildableKeyField(path, description,
						field, namedKeyStroke.getKeyStroke()));
			}
		} else if (value instanceof KeyStroke) {
			KeyStroke keyStroke = (KeyStroke) value;
			if (keyStroke != null) {
				keyStrokeVisitor.visit(new BuildableKeyField(path, description,
						field, keyStroke));
			}
		}
	}

	@Override
	public void startVisit(List<Buildable> path, Buildable buildable) {
		keyStrokeVisitor.visit(path, buildable);
	}

	@Override
	public void endVisit(Stack<Buildable> path, Buildable buildable) {
	}

}
