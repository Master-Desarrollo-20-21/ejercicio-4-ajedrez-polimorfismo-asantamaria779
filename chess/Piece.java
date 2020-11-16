package chess;

abstract class Piece {

	protected final static char KING = 'K';
	
    protected char name;
    protected Colour colour;
    protected boolean wasMovedBefore;
    
    protected Piece(char name, Colour colour){
    	assert colour != null;
        this.name = name;     
        this.colour = colour;
        this.wasMovedBefore=false;
    }
	
    protected void setAsMoved() {
    	this.wasMovedBefore=true;
    }
    
    protected Colour getColour() {
    	return this.colour;
    }
    
	protected boolean sameColour(Piece piece) {
		assert piece != null;
		return this.colour == piece.colour;
	}
	
	protected boolean isAKing() {
		return Character.toUpperCase(this.name) == Piece.KING;
	}
	
	protected boolean isLegalDirection(Direction direction,Direction[] directions) {
		for (Direction dir : directions) {
		    if (dir==direction) return true;
		}
		return false;
	}
	
    protected void show() {
    	new Console().out(name);
    }
    
    protected boolean destinationBusy(Movement movement) {
    	if (movement.getPieceAtDestination()!=null) return true;
    	return false;
    }
    
    protected boolean isLegal(Movement movement) {
    	if (destinationBusy(movement)) {
    		if (this.colour==movement.getPieceAtDestination().colour) return false;
    	}   	
    	return check(movement);
    }
    
	protected abstract boolean check(Movement movement);
}
