package VASSAL.counters;

import java.util.ArrayList;
import java.util.List;

import VASSAL.build.module.PrototypeDefinition;
import VASSAL.build.widget.PieceSlot;

public final class GamePieceExploder {
	private GamePieceExploder() {
	}

	public static List<GamePiece> getPieces(PrototypeDefinition protoDef) {
		GamePiece piece = protoDef.getPiece();
		return getPieces(piece);
	}

	private static List<GamePiece> getPieces(GamePiece piece) {
		List<GamePiece> retval = new ArrayList<GamePiece>();
		if (piece != null) {
			retval.add(piece);
			while (piece instanceof Decorator) {
				piece = ((Decorator)piece).piece;
				retval.add(piece);
			}

		}
		return retval;
	}

	public static List<GamePiece> getPieces(PieceSlot pieceSlot) {
		GamePiece piece = pieceSlot.getPiece();
		return getPieces(piece);
	}

}
