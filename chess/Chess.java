package chess;

class Chess {
	 final String title="\n  ------------- CHESS -------------\n";
	 private void play() {
		 do {
			 new Console().out(title);
			 new Game().play();
		 } while(this.resume());
	}

	private boolean resume() {
		String answer;
		do {
			answer = new Console().readString("RESUME? (y/n): ");
		} while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));
		return answer.equalsIgnoreCase("y");
	}

	public static void main(String[] args) {
		new Chess().play();
	}
}
