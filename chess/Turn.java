package chess;

class Turn {

    private int value;

    public Turn(Colour colour) {
    	this.value = colour.ordinal();
    }

    public int isTurn() {
        return value;
    }

    public int notIsTurn() {
        return (value + 1) % 2;
    }

    public void change() {
    	value = this.notIsTurn();
    }
}
