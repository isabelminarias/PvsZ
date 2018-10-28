package logic;

public class GameDrawing {
	static final int DIM_X = 8;
	static final int DIM_Y = 4;
	Game game; 
	
	
	String drawLine() { 
		return "--------------------------------------------------------- \n";
	}
	
	String drawRow(int y) {
		String row = "|";
		for (int i = 0; i < DIM_X; i++) {
			if (game.getSunflowerList().getByPosition(i, y) != null) {
				row += " "+ game.getSunflowerList().getByPosition(i, y).asString()+" |";
			}
			else if (game.getPeashooterList().getByPosition(i, y) != null) {
				row += " "+  game.getPeashooterList().getByPosition(i, y).asString()+" |";
			}
			else if(game.getZombieList().getByPosition(i, y) != null) {
				row += " "+  game.getZombieList().getByPosition(i, y).asString()+" |";
			}
			else {
				row += "  *   |";
			}
		}
		row += "\n";
		return row;
	}
	public String board() {
		String board;
		
		board = drawLine();
		for (int i = 0; i < DIM_Y; i++) {
			board= board+drawRow(i);
			board= board+drawLine();
		}
		
		
		return board;
	}
	
	public GameDrawing(Game game){
		this.game = game;
	}
}
