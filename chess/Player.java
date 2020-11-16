package chess;

class Player {

    private Colour colour;

    public Player(Colour colour) {
        assert colour == Colour.WHITE || colour == Colour.BLACK;
        this.colour = colour;
    }
    
    protected Colour getColour() {
    	return this.colour;
    }
    
    public int[] readOrigin(int dimension) {
    	int[] origin = new int[2];
        new Console().out("Introduce la coordenada de origen ("+ this.colour +"):\n");   	
        origin[0]=this.readRow(dimension);
        origin[1]=this.readColumn(dimension);            
        return origin;
    }

    public int[] readDestination(int dimension) {
    	int[] destination = new int[2];
        new Console().out("Introduce la coordenada de destino ("+ this.colour +"):\n");   	
        destination[0]=this.readRow(dimension);
        destination[1]=this.readColumn(dimension);            
        return destination;
    }
   
	private int readRow(int dimension) {
        Console console = new Console();
        boolean error = false;
        Integer row;
        do {
            console.out("Introduce fila [1,8]: ");
            row = console.inInt()-1;
            error = !(row >=0 && row < dimension);
            if (error) console.out("Error!!! Valores fuera de rango\n");
        } while (error);
        return row;
    }
   
	private int readColumn(int dimension) {
        Console console = new Console();
        boolean error = false;
        int column;
        do {
            console.out("Introduce columna [A,H]: ");   
            column = console.inCharacter().hashCode()-65;           
            error = (!(column >= 0 && column < dimension || column >= 32 && column < 32+dimension));
            if (error) console.out("Error!!! Valores fuera de rango\n");
        } while (error);        
        if (column >= 0 && column < dimension) {
        	return column;
        }
        return column-32;
    }
	
    public void declareVictory() {
        new Console().out("eoeoeoeoeoeoeoeoe! " + this.colour + " are the best\n");

    }
}
