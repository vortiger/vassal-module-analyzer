package com.kenstevens.vassal.walker;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.KeyStroke;

import org.w3c.dom.Element;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.module.Map;
import VASSAL.build.module.PrototypeDefinition;
import VASSAL.build.widget.PieceSlot;
import VASSAL.configure.HotKeyConfigurer;
import VASSAL.counters.GamePiece;
import VASSAL.counters.GamePieceExploder;
import VASSAL.i18n.TranslatablePiece;
import VASSAL.tools.NamedKeyStroke;

import com.kenstevens.vassal.Printer;

public class FindKeyVisitor implements ModuleVisitor {

	@Override
	public void visit(int depth, Element buildElement, Buildable buildable) {
		if (!(buildable instanceof AbstractConfigurable)) {
			return;
		}
		AbstractConfigurable abstractConfigurable = (AbstractConfigurable)buildable;
		visitComponents(depth, abstractConfigurable);
	}

	private void visitComponents(int depth, AbstractConfigurable abstractConfigurable) {

		String indent = "";
		for (int i = 0; i < depth; ++i) {
			indent += "  ";
		}
		String simpleName = abstractConfigurable.getClass().getSimpleName();
		Printer.println(indent+simpleName+"["+abstractConfigurable.getConfigureName()+"]");
		List<GamePiece> pieces = null;
		if (abstractConfigurable instanceof PrototypeDefinition) {
			pieces = GamePieceExploder.getPieces((PrototypeDefinition) abstractConfigurable);
		} else if (abstractConfigurable instanceof PieceSlot) {
			pieces = GamePieceExploder.getPieces((PieceSlot) abstractConfigurable);
		} else if (abstractConfigurable instanceof Map) {
			printMapKeyReferences("  "+indent, (Map)abstractConfigurable);
		}
		// FIXME generalize to things other than PrototypeDefinitions
		if (pieces == null) {
			return;
		}
		for (GamePiece piece : pieces) {
			try {
				printKeyReferences("  "+indent, piece);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void printMapKeyReferences(String indent,
			Map map) {
		printFieldValue(indent, "moveKey", map.getMoveKey());
	}

	private void printKeyReferences(String indent, GamePiece piece) throws IllegalArgumentException, IllegalAccessException {
		Class<? extends GamePiece> clazz = piece.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(piece);
			if (value == null) {
				continue;
			}
			Class<?> type = field.getType();
			String prefix = (piece instanceof TranslatablePiece ? ((TranslatablePiece)piece).getDescription() : piece.getClass().getSimpleName());
			String fieldName = prefix+"."+field.getName();
			if (type.getComponentType() == null) {
				printFieldValue(indent, fieldName, value);
			} else {
				// TODO Optimize
				Object[] array = (Object[])value;
				for (Object item : array) {
					printFieldValue(indent, fieldName, item);
				}
			}
		}
	}

	private void printFieldValue(String indent, String fieldName,
			Object value) {
		if (value instanceof NamedKeyStroke) {
			NamedKeyStroke namedKeyStroke = (NamedKeyStroke) value;
			if (namedKeyStroke != null && namedKeyStroke.getKeyStroke() != null) {
				Printer.println(indent + fieldName + " = " + print(namedKeyStroke.getKeyStroke()));
			}
		}
		else if (value instanceof KeyStroke) {
			KeyStroke keyStroke = (KeyStroke) value;
			if (keyStroke != null) {
				Printer.println(indent+fieldName+" = "+print(keyStroke));
			}
		}
	}

	private String print(KeyStroke keyStroke) {
		return HotKeyConfigurer.getString(keyStroke);
	}

}
