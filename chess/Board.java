package chess;

import java.util.ArrayList;

class Board {
	
    private static final int DIMENSION = 8; 
    private static final String EMPTY = " ";
    
    private Piece[][] squares;
    private boolean isOver;
    
    public Board() {
        this.squares = new Piece[DIMENSION][DIMENSION];
        this.isOver = false;
        this.initialize();
    }
       
	private void initialize() {
		Piece pieces[] = {new Rook('R',Colour.WHITE),new Knight('N',Colour.WHITE),new Bishop('B',Colour.WHITE),new Queen('Q',Colour.WHITE),
						  new King('K',Colour.WHITE),new Bishop('B',Colour.WHITE),new Knight('N', Colour.WHITE),new Rook('R',Colour.WHITE),
						  new Rook('r',Colour.BLACK),new Knight('n',Colour.BLACK),new Bishop('b',Colour.BLACK),new Queen('q',Colour.BLACK),
	                      new King('k',Colour.BLACK),new Bishop('b',Colour.BLACK),new Knight('n', Colour.BLACK),new Rook('r',Colour.BLACK)};
		for(int i=0;i<DIMENSION;i++) {
			this.squares[0][i]=pieces[i];
			this.squares[7][i]=pieces[i+DIMENSION];
			this.squares[1][i] = new Pawn('P',Colour.WHITE);
			this.squares[6][i] = new Pawn('p',Colour.BLACK);			
		}
	}
	
    public Movement nextMovement(Player player) {
    	
    	Coordinate origin=this.readOrigin(player);
    	Coordinate destination=this.readDestination(player); 	
    	boolean isWayClear=isWayClear(origin,destination);
    	
    	return new Movement(origin,destination,isWayClear,this.getPieceAt(destination));
    }
	
    private Coordinate readOrigin(Player player) {
    	Coordinate origin;
    	String error;
    	do {
    		error="";
    		int[] originAux = player.readOrigin(DIMENSION);
    		origin=new Coordinate(originAux[0],originAux[1]);
    		if (!this.isOccupied(origin)) {
    			error="La casilla de origen no puede estar vacía\n";
    		} else if (getPieceAt(origin).getColour()!=player.getColour()) {
    			error="No puedes mover una ficha de tu enemigo\n";
    		}
    		if (!error.isEmpty()) {
    			new Console().out(error);
    		}
    	} while (!error.isEmpty());
    	return origin;
    }
    
    private Coordinate readDestination(Player player) {
    	int[] destinationAux = player.readDestination(DIMENSION);
    	return new Coordinate(destinationAux[0],destinationAux[1]);
    }    
    
	private Piece getPieceAt (Coordinate coordinate) {
		assert coordinate != null;
		return this.squares[coordinate.getRow()][coordinate.getColumn()];
	}
	
	private boolean isOccupied(Coordinate coordinate) {
		assert coordinate != null;
		return this.squares[coordinate.getRow()][coordinate.getColumn()] != null;
	}
	
	private boolean isWayClear(Coordinate origin,Coordinate destination) {
		assert origin!=null && destination!=null;
		ArrayList<Coordinate> coordinates=origin.coordinatesBetween(destination);
		if (coordinates != null) {
			for(Coordinate coordinate : coordinates) {
				if (this.isOccupied(coordinate)) return false;
			}
		}
		return true;
	}
	
	public String isLegal(Movement movement) {
		assert movement != null;
		if (movement.getDirection() == Direction.OTHER) {
			return "El tipo de movimiento no válido\n";
		}
		if (!this.getPieceAt(movement.getOrigin()).isLegal(movement)) {
			return "Movimiento no válido para esta pieza\n";
		}
		return "";
	}
		
	public void move(Movement movement) {
	   assert movement != null;
	   int row = movement.getRowDestination();
	   int column = movement.getColumnDestination();
	   if (this.isOccupied(movement.getDestination())) {
		   if (getPieceAt(movement.getDestination()).isAKing()) {	   
			   this.isOver=true;
		   }
	   }
	   getPieceAt(movement.getOrigin()).setAsMoved();
	   squares[row][column]=getPieceAt(movement.getOrigin());
	   squares[movement.getRowOrigin()][movement.getColumnOrigin()]=null;	   
	}
	
    public boolean isOver() {
        return this.isOver;
    }
    
    public void show() {
        Console console = new Console();
        console.out("\n");
        this.showRowSeparator(console);
        for (int i = DIMENSION; i > 0; i--) {
        	console.out(i + " ");
            for (int j = 0; j < DIMENSION; j++) {
            	console.out("| ");
            	if (this.squares[i-1][j] != null) {
            		this.squares[i-1][j].show();
            	}
            	else {
            		console.out(EMPTY);
            	}
            	console.out(" ");
            }
            console.out("|\n");
            this.showRowSeparator(console);
        }
        this.showColumnNames(console);
    }
        
	private void showRowSeparator(Console console) {
       String border = "+---";
       console.out("  ");
       for (int i = 1; i <= DIMENSION; i++) {
       	console.out(border);
       }
       console.out("+\n");
	}
	
	private void showColumnNames(Console console) {
		assert console != null;
	       console.out("    ");
	       char[] columnNames = {'A','B','C','D','E','F','G','H'};
	       for (int i = 0; i < columnNames.length; i++) {
	       	console.out(columnNames[i] + "   ");	       	
	       }
	       console.out("\n\n");
	}
}
