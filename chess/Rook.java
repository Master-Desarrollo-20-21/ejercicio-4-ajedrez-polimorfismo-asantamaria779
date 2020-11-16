package chess;

class Rook extends Piece {
	
	private static Direction[] legalDirections = {Direction.HORIZONTAL,Direction.VERTICAL};
	
    public Rook(char name, Colour colour) {
        super(name, colour);
    }

	@Override
	protected boolean check(Movement movement) {
		return movement.getIsWayClear() && this.isLegalDirection(movement.getDirection(),Rook.legalDirections);
	}
}
