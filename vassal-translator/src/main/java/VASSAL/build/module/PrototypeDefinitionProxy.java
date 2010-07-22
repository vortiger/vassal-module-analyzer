package VASSAL.build.module;

import VASSAL.counters.GamePiece;

public class PrototypeDefinitionProxy {

	private final PrototypeDefinition def;

	public PrototypeDefinitionProxy(PrototypeDefinition def) {
		this.def = def;
	}

	public GamePiece getPiece(String prototypeDefString) {
		return def.getPiece(prototypeDefString);
	}

}
