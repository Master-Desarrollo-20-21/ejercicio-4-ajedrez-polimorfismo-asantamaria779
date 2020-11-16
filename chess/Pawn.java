package chess;

class Pawn extends Piece {
	
	private static Direction[] legalDirections = {Direction.DIAGONAL,Direction.REVERSE_DIAGONAL,Direction.VERTICAL};
			
	private VerticalWay verticalWay;
	
    public Pawn (char name, Colour colour) {
        super(name, colour);
        if (colour==Colour.WHITE) {
        	this.verticalWay=VerticalWay.BOTTOM_UP;
        } else {
        	this.verticalWay=VerticalWay.TOP_DOWN;
        }
    }

	@Override
	protected boolean check(Movement movement) {
		assert movement != null;
		Direction direction=movement.getDirection();
		if (!(this.isLegalDirection(direction,Pawn.legalDirections))) {
			return false;
		}
		if (this.verticalWay != movement.getVerticalWay()) {
			return false;
		}
		if (movement.getDirection()==Direction.VERTICAL) {
			if (destinationBusy(movement)) return false;
			if (!this.wasMovedBefore && movement.getVerticalDistance()==2 && movement.getIsWayClear()) return true;
			return movement.getVerticalDistance()==1;
		}
		if (destinationBusy(movement) &&
			movement.getVerticalDistance()+movement.getHorizontalDistance() == 2){
			return true;
		}		
		return false;
	}
	
}
