package chess;

import java.util.ArrayList;

class Coordinate {

    private int row;
    private int column;

    private static final Interval LIMITS = new Interval(0, 7);
    
    public static boolean isValid(int row, int column) {
        return LIMITS.include(row) && LIMITS.include(column);
    }

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
        assert Coordinate.isValid(row,column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean equals(Coordinate coordinate) {
        assert coordinate != null;
        return row == coordinate.row && column == coordinate.column;
    }

    public int horizontalDistance(Coordinate coordinate) {
    	return Math.abs(this.column - coordinate.column);
    }
 
    public int verticalDistance(Coordinate coordinate) {
    	return Math.abs(this.row - coordinate.row);
    }
    
    public ArrayList<Coordinate> coordinatesBetween (Coordinate coordinate) {
    	ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    	Direction direction=this.direction(coordinate);
    	switch (direction) {
    		case HORIZONTAL:
    			coordinates=this.coordinatesBetweenInHorizontal(coordinate);
    			break;
    		case VERTICAL:
    			coordinates=this.coordinatesBetweenInVertical(coordinate);  			
    			break;
    		case DIAGONAL:
    			coordinates=this.coordinatesBetweenInDiagonal(coordinate);
    			break;
    		case REVERSE_DIAGONAL:
    			coordinates=this.coordinatesBetweenInReverseDiagonal(coordinate);
    			break;
    		case IN_L,OTHER:
    			return coordinates;
    	}
    	return coordinates;
    }
    
    public Direction direction(Coordinate coordinate) {
        if (row == coordinate.row){
            return Direction.HORIZONTAL;
        }
        if (column == coordinate.column){
            return Direction.VERTICAL;
        }        
        if (this.horizontalDistance(coordinate) == this.verticalDistance(coordinate)) { 
        	Coordinate destination = this.furthestRight(coordinate);
        	Coordinate origin = this.furthestLeft(coordinate);
        	if (origin.getRow()<destination.getRow()) {
        		return Direction.DIAGONAL;
        	}
        	return Direction.REVERSE_DIAGONAL;
        }
		if ((this.verticalDistance(coordinate) == 2 && this.horizontalDistance(coordinate) == 1) ||
			   (this.verticalDistance(coordinate) == 1 && this.horizontalDistance(coordinate) == 2)) {
			return Direction.IN_L;
		}
        return Direction.OTHER;
    }
    
    public ArrayList<Coordinate> coordinatesBetweenInHorizontal (Coordinate coordinate) { 	
    	ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    	Coordinate origin=this.furthestLeft(coordinate);
    	Coordinate destination=this.furthestRight(coordinate);    	
    	for(int i=origin.getColumn()+1;i<destination.getColumn();i++) {
    		coordinates.add(new Coordinate(origin.getRow(),i));
    	}
    	return coordinates;	
    }
        
    public ArrayList<Coordinate> coordinatesBetweenInDiagonal (Coordinate coordinate) {
    	ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    	Coordinate origin=this.furthestLeft(coordinate);
    	Coordinate destination=this.furthestRight(coordinate);  
    	for(int i=origin.getRow()+1,j=origin.getColumn()+1;i<destination.getRow()-1;i++,j++) {
    		coordinates.add(new Coordinate(i,j));
    	}
    	return coordinates;
    }
    
    public ArrayList<Coordinate> coordinatesBetweenInReverseDiagonal (Coordinate coordinate) {
    	ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    	Coordinate origin=this.furthestLeft(coordinate);
    	Coordinate destination=this.furthestRight(coordinate);  
    	for(int i=origin.getRow()-1,j=origin.getColumn()+1;i<=destination.getRow()+1;i--,j++) {
    		coordinates.add(new Coordinate(i,j));
    	}
    	return coordinates;	
    }
    
    public ArrayList<Coordinate> coordinatesBetweenInVertical (Coordinate coordinate) {
    	ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    	Coordinate destination=this.furthestUp(coordinate);
    	Coordinate origin=this.furthestDown(coordinate);
    	for(int i=origin.getRow()+1;i<destination.getRow();i++) {
    		coordinates.add(new Coordinate(i,origin.getRow()));
    	}
    	return coordinates;	
    }
    
    public boolean furtherUp(Coordinate coordinate) {    	
    	return this.row > coordinate.row;
    }

    public Coordinate furthestUp(Coordinate coordinate) {
    	if (this.furtherUp(coordinate)) {
    		return this;
    	}
    	return coordinate;
    } 
    
    public boolean furtherRight(Coordinate coordinate) {
    	return this.column > coordinate.column;
    }    
   
    public Coordinate furthestRight(Coordinate coordinate) {
    	if (this.furtherRight(coordinate)) {
    		return this;
    	}
    	return coordinate;
    }
    
    public Coordinate furthestLeft(Coordinate coordinate) {
    	if (this.furtherRight(coordinate)) {
    		return coordinate;
    	}
    	return this;
    }
    
    public Coordinate furthestDown(Coordinate coordinate) {
    	if (this.furtherUp(coordinate)) {
    		return coordinate;
    	}
    	return this;
    }
}
