package chess;

class Turn {

    private int value;

    public Turn() {
    	value = 0;
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
