package chess;

class Queen extends Piece {
	
	private static Direction[] legalDirections= {Direction.DIAGONAL,Direction.REVERSE_DIAGONAL,Direction.HORIZONTAL,Direction.VERTICAL};

    public Queen (char name, Colour colour) {
        super(name, colour);
    }

	@Override
	protected boolean check(Movement movement) {
		assert movement != null;
		return movement.getIsWayClear() && this.isLegalDirection(movement.getDirection(),Queen.legalDirections);
	}
}
