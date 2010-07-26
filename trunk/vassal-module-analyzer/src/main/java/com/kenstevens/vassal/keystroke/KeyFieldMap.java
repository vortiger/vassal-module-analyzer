package com.kenstevens.vassal.keystroke;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class KeyFieldMap {
	private static final Map<String, KeyFieldType> fieldMap = new HashMap<String, KeyFieldType>();

	private static final KeyFieldType listener = new KeyFieldType(true);
	private static final KeyFieldType sender = new KeyFieldType(false);
	static {
		// FIXME complete these
		fieldMap.put("ActionButton.stroke", sender);
		fieldMap.put("CounterGlobalKeyCommand.key", listener);
		// TODO globalKey only sends if piece matches filter
		fieldMap.put("CounterGlobalKeyCommand.globalKey", sender);
		fieldMap.put("Delete.key", listener);
		fieldMap.put("Embellishment.decreaseKeyStroke", listener);
		fieldMap.put("Embellishment.resetKey", listener);
		fieldMap.put("Embellishment.increaseKeyStroke", listener);
		fieldMap.put("Embellishment.activateKeyStroke", listener);
		fieldMap.put("Obscurable.keyCommand", listener);
		fieldMap.put("RestrictCommands.watchKeys", listener);
		fieldMap.put("ReturnToDeck.returnKey", listener);
		fieldMap.put("ReportState.keys", listener);
		fieldMap.put("SendToLocation.key", listener);
		fieldMap.put("TriggerAction.actionKeys", sender);
		fieldMap.put("TriggerAction.watchKeys", listener);
		fieldMap.put("TriggerAction.key", listener);
	}

	private KeyFieldMap() {
	}

	static boolean isListener(Field field) {
		return fieldMap.get(nameOf(field)).isListener();
	}

	public static String nameOf(Field field) {
		return field.getDeclaringClass().getSimpleName() + "."
				+ field.getName();
	}
}
