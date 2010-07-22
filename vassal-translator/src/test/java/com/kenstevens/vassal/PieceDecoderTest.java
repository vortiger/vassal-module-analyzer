package com.kenstevens.vassal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import VASSAL.build.module.PrototypeDefinition;
import VASSAL.build.module.PrototypeDefinitionProxy;
import VASSAL.counters.GamePiece;
import VASSAL.counters.TriggerAction;

public class PieceDecoderTest extends VassalModuleLoaderTest {
	private final String prototypeDefString = "+/null/macro;Card is a wonder;;65,715;Wonder = True;;192\\,130	macro;Card is not a wonder;;65,715;Wonder != True;;192\\,520\\	macro;Trigger if card is a technology card;;65,130;Technology &gt; 0;;65\\,520\\\\	globalkey;;65,520;38,585;BasicName = Aristotle &amp;&amp; CurrentBoard = $playerSide$ Civ;false;1;false;true;;Send Command to Aristotle if card is technology card;-1\\\\\\	hideCmd;Hide Ctrl A when in a player hand;Hide;CurrentBoard = Blue Hand || CurrentBoard = Red Hand || CurrentBoard = Yellow Hand || CurrentBoard = White Hand || CurrentBoard = Play Board;65\\,130\\\\\\\\	sendto;;192,520;$playerSide$ Hand;$playerSide$ Hand;120;180;;;0;0;0;0;Send to player's hand;L;;;\\\\\\\\\\	macro;Add Card to Hand;Add Card to Hand;65,130;;;65\\,715\\\\\\\\\\\\	piece;;;;/	\\	\\\\	\\\\\\	\\\\\\\\	;;\\\\\\\\\\	\\\\\\\\\\\\	null;395;0;";
	private final Class<TriggerAction> clazz = VASSAL.counters.TriggerAction.class;

	@Test
	public void decodePrototypeDefinition() {
		PrototypeDefinition def = new PrototypeDefinition();
		PrototypeDefinitionProxy defProxy = new PrototypeDefinitionProxy(def);
		GamePiece piece = defProxy.getPiece(prototypeDefString);
		assertNotNull(piece);
		assertEquals(clazz, piece.getClass());
	}
}
