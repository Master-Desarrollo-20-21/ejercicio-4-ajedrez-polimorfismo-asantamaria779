package chess;

class Movement {
	
	private Coordinate origin;
	private Coordinate destination;
	private boolean isWayClear;
	private Piece pieceAtDestination;
	private Direction direction;
	private VerticalWay verticalWay; 
	private int horizontalDistance;
	private int verticalDistance;
	
	Movement (Coordinate origin, Coordinate destination,boolean isWayClear,Piece pieceAtDestination) {
		this.origin = origin;
		this.destination = destination;
		this.isWayClear=isWayClear;
		this.pieceAtDestination=pieceAtDestination;
		this.direction=origin.direction(destination);
		this.horizontalDistance=destination.horizontalDistance(origin);
		this.verticalDistance=destination.verticalDistance(origin);
		this.setVerticalWay();
	}
	
	private void setVerticalWay() {
		if (this.origin.furtherUp(this.destination)) {
			this.verticalWay=VerticalWay.TOP_DOWN;
		} else {
			this.verticalWay=VerticalWay.BOTTOM_UP;
		}		
	}	
	
	public int getHorizontalDistance() {
		return this.horizontalDistance;
	}

	public int getVerticalDistance() {
		return this.verticalDistance;
	}
	
	public VerticalWay getVerticalWay() {
		return this.verticalWay;
	}
	
	public boolean getIsWayClear () {
		return this.isWayClear;
	}
	
	public Coordinate getOrigin() {
		return this.origin;
	}
	
	public Coordinate getDestination() {
		return this.destination;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public int getRowOrigin() {
		return this.getOrigin().getRow();
	}
	
	public int getColumnOrigin() {
		return this.getOrigin().getColumn();
	}
	
	public int getRowDestination() {
		return this.getDestination().getRow();
	}
	
	public int getColumnDestination() {
		return this.getDestination().getColumn();
	}
	
	public int getHorizonalDirection() {
		return this.getDestination().getColumn();
	}
	
	public Piece getPieceAtDestination() {
		return this.pieceAtDestination;		
	}	
}
