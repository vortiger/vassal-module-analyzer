package com.kenstevens.vassal.walker;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.Buildable;

public final class ColourPicker {
	private ColourPicker() {
	}

	public static boolean isOtherPlayer(Buildable buildable) {
		if (!(buildable instanceof AbstractConfigurable)) {
			return false;
		}
		AbstractConfigurable abstractConfigurable = (AbstractConfigurable) buildable;

		String configureName = abstractConfigurable.getConfigureName();
		if (configureName == null) {
			return false;
		}
		if (configureName.startsWith("Red")
				|| configureName.startsWith("White")
				|| configureName.startsWith("Yellow")) {
			return true;
		}
		return false;
	}

}
