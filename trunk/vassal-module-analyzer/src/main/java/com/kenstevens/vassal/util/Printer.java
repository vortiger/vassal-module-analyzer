package com.kenstevens.vassal.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.KeyStroke;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;
import VASSAL.configure.HotKeyConfigurer;
import VASSAL.counters.GamePiece;
import VASSAL.i18n.TranslatablePiece;
import VASSAL.tools.NamedKeyStroke;

import com.google.common.base.Joiner;

public final class Printer {

	private static OutputStream outputStream;
	private static PrintStream printStream;

	private Printer() {
	}

	public static void writeTo(String filename) throws FileNotFoundException {
		outputStream = new FileOutputStream(filename);
		printStream = new PrintStream(outputStream);
	}

	public static void close() throws IOException {
		printStream.close();
		outputStream.close();
	}

	public static void println(String string) {
		printStream.println(string);
	}

	public static void println(int depth, String string) {
		String indent = "";
		for (int i = 0; i < depth; ++i) {
			indent += "  ";
		}
		println(indent + string);
	}

	public static String getConfigureName(Buildable buildable) {
		String retval = null;
		if (buildable instanceof AbstractConfigurable) {
			AbstractConfigurable abstractConfigurable = (AbstractConfigurable) buildable;
			retval = abstractConfigurable.getConfigureName();
		}
		return retval;
	}

	public static String getDescription(Buildable buildable) {
		String retval;
		String simpleName = buildable.getClass().getSimpleName();
		String configureName = getConfigureName(buildable);
		if (configureName == null) {
			retval = simpleName;
		} else {
			retval = simpleName + "[" + configureName + "]";
		}
		return retval;
	}

	public static String getStartTag(Buildable buildable) {
		String retval;
		String simpleName = buildable.getClass().getSimpleName();
		String configureName = getConfigureName(buildable);
		if (configureName == null) {
			retval = simpleName;
		} else {
			retval = simpleName + " name=\"" + configureName + "\"";
		}
		return "<"+retval+">";
	}
	
	public static String getEndTag(Object object) {
		return "</"+object.getClass().getSimpleName()+">";
	}

	public static String getPieceDescription(GamePiece piece) {
		String retval = null;
		if (piece instanceof TranslatablePiece) {
			TranslatablePiece translatablePiece = (TranslatablePiece) piece;
			retval = translatablePiece.getDescription();
		}
		return retval;
	}

	
	public static String getDescription(GamePiece piece) {
		String retval;
		String simpleName = piece.getClass().getSimpleName();
		String description = getPieceDescription(piece);
		if (description == null) {
			retval = simpleName;
		} else {
			retval = simpleName + "[" + description + "]";
		}
		return retval;
	}

	public static String getStartTag(GamePiece piece) {
		String retval;
		String simpleName = piece.getClass().getSimpleName();
		String description = getPieceDescription(piece);
		if (description == null) {
			retval = simpleName;
		} else {
			retval = simpleName + " name=\"" + description + "\"";
		}
		return "<"+retval+">";
	}

	public static String toString(NamedKeyStroke[] keys) {
		return toString(Arrays.asList(keys));
	}
	
	public static String toString(List<NamedKeyStroke> keys) {
		List<String>list = new ArrayList<String>();
		for (NamedKeyStroke key : keys) {
			if (key.getKeyStroke() != null) {
				list.add(HotKeyConfigurer.getString(key.getKeyStroke()));
			}
		}
		return Joiner.on(", ").join(list);
	}

	public static String toString(NamedKeyStroke key) {
		return HotKeyConfigurer.getString(key.getKeyStroke());
	}

	public static String toString(KeyStroke[] keys) {
		List<String>list = new ArrayList<String>();
		for (KeyStroke key : keys) {
			if (key != null) {
				list.add(HotKeyConfigurer.getString(key));
			}
		}
		return Joiner.on(", ").join(list);
	}

}
