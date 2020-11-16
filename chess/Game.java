package chess;

class Game {
	private Board board;
	private Turn turn;
	private Player[] players;
	
	Game() {
		this.board = new Board();
		this.turn = new Turn();
		this.players = new Player[2];
        players[0] = new Player(Colour.WHITE);
        players[1] = new Player(Colour.BLACK);
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
