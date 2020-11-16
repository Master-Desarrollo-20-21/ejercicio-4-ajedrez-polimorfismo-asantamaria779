package chess;

class King extends Piece {
	
	private static Direction[] legalDirections= {Direction.DIAGONAL,Direction.REVERSE_DIAGONAL,Direction.HORIZONTAL,Direction.VERTICAL};
	
    public King(char name, Colour colour) {
        super(name, colour);
    }

	@Override
	protected boolean check(Movement movement) {
		return this.isLegalDirection(movement.getDirection(),King.legalDirections) &&
			   (movement.getHorizontalDistance() == 1 && movement.getVerticalDistance() == 1) ||
			   (movement.getHorizontalDistance() + movement.getVerticalDistance() == 1) ;
	}
}
