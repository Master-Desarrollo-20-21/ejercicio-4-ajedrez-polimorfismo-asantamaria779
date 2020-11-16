package chess;

class Bishop extends Piece {
	
	private static Direction[] legalDirections = {Direction.DIAGONAL,Direction.REVERSE_DIAGONAL};
	
    public Bishop(char name, Colour colour) {
        super(name, colour);
    }
    
	@Override
	protected boolean check(Movement movement) {
		return movement.getIsWayClear() && this.isLegalDirection(movement.getDirection(),Bishop.legalDirections);
	}
}
