package logic;

public class Sunflower {
	private int pos_x;
	private int pos_y;
	private int hp;
	private int createdAt;
	public static int life = 1;
	public static int COST = 20;
	Game game;
	
	public Sunflower(Game game, int turn, int x, int y){
		this.game = game;
		this.createdAt = turn;
		this.pos_x = x;
		this.pos_y = y;
		this.hp = 1;
	}
	
	int isAlive() {
		return hp;
	}
	
	void decreaseLife(int dmg) {
		int stat = hp - dmg;
		if (stat >0) {
			this.hp = stat;
		}
		else
			this.hp = 0;
	}
	
	int update(int turn) {
		if ((turn - createdAt) % 2 == 0) {
			return 10;
		}
		else {
			return 0;
		}
	}
	
	String asString() {
		String msg = "S["+hp+"]";
		return msg;
	}
	
	int[] position() {
		int[] coordinates = new int[2];
		coordinates[0] = this.pos_x;
		coordinates[1] = this.pos_y;
		return coordinates;
	}
}
