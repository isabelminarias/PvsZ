package logic;

public class Zombie {
	private int pos_x;
	private int pos_y;
	private int hp;
	private int createdAt;
	public static int life = 5;
	public static int DMG = 1;
	Game game;
	
	public Zombie (Game game, int turn, int x, int y){
		this.game = game;
		this.createdAt = turn;
		this.pos_x = x;
		this.pos_y = y;
		this.hp = life;
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
	
	String asString() {
		String msg = "Z["+hp+"]";
		return msg;
	}
	
	void update(int turn) {
		if ((turn - createdAt) % 2 == 0) {
			//check position
			if ((game.getPeashooterList().getByPosition(this.pos_x-1, this.pos_y) == null) 
					&& (pos_x - 1 >= 0)
					&& (game.getSunflowerList().getByPosition(this.pos_x - 1, this.pos_y) == null
					&& (game.getZombieList().getByPosition(this.pos_x - 1, this.pos_y) == null))) {
				this.pos_x--;
			}
		}
	}
	
	public boolean isArrived() {
		if (pos_x == 0){
			return true;
		}
		else 
			return false;
	}
	
	int[] position() {
		int[] coordinates = new int[2];
		coordinates[0] = this.pos_x;
		coordinates[1] = this.pos_y;
		return coordinates;
	}
	
}
