package VASSAL.counters;

import VASSAL.build.module.properties.IncrementProperty;
import VASSAL.build.module.properties.PropertySetter;
import VASSAL.counters.DynamicProperty.DynamicKeyCommand;

import com.kenstevens.vassal.util.Printer;

public class DynamicPropertyPrinter {

	public static void print(int depth, DynamicProperty dynamicProperty) {
		Printer.println(depth, "Property name: "+dynamicProperty.key);
		Printer.println(depth, "Property value: "+dynamicProperty.value);
		Printer.println(depth, "Property min value: "+dynamicProperty.minValue);
		Printer.println(depth, "Property max value: "+dynamicProperty.maxValue);
		
		for (int i = 0; i < dynamicProperty.keyCommands.length; ++i) {
			DynamicKeyCommand keyCommand = dynamicProperty.keyCommands[i];
			Printer.println(depth, i+") Key In: "+Printer.toString(keyCommand.namedKeyStroke));
			if (keyCommand.propChanger instanceof PropertySetter) {
				Printer.println(depth, i+") value: "+((PropertySetter)keyCommand.propChanger).getRawValue());
			} else if (keyCommand.propChanger instanceof IncrementProperty) {
				Printer.println(depth, i+") value: increment by "+((IncrementProperty)keyCommand.propChanger).getIncrement());
			} else {
				System.err.println("No handler for "+keyCommand.propChanger.getClass().getSimpleName());
			}
		}
	}
}
