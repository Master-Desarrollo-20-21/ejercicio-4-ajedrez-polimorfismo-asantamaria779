package chess;

class Knight extends Piece {
	
	private static Direction[] legalDirections = {Direction.IN_L};
	
    public Knight (char name, Colour colour) {
        super(name, colour);
    }

	@Override
	protected boolean check(Movement movement) {
		return this.isLegalDirection(movement.getDirection(),Knight.legalDirections);
	}
}
