package chess;

class Game {
	private Board board;
	private Turn turn;
	private Player[] players;
	
	Game() {
		this.board = new Board();
		this.turn = new Turn(Colour.WHITE);
		this.players = new Player[Colour.values().length];
		int i=0;
		for(Colour colour : Colour.values()) {
			players[i]=new Player(colour);
			i++;
		}
	}
	
	public void play() {		
		do {
			String checkedMovement;
			Movement movement;
			this.board.show();
			do {
				movement=this.board.nextMovement(players[turn.isTurn()]);
				checkedMovement = board.isLegal(movement);
				if (!isLegal(checkedMovement)) {
					new Console().out(checkedMovement);
				}
			} while (!isLegal(checkedMovement));
			this.board.move(movement);
			this.turn.change();
		} while (!board.isOver());
		this.players[turn.notIsTurn()].declareVictory();
	}
	
	private boolean isLegal(String checkedMovement) {
		return checkedMovement.isEmpty();
	}
}
