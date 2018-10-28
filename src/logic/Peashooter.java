package logic;

public class Peashooter {
	private int pos_x;
	private int pos_y;
	private int hp;
	private int createdAt;
	public static int life = 3;
	public static int COST = 50;
	public static int DMG = 1;
	Game game;
	
	public Peashooter(Game game, int turn, int x, int y){
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
	
	void update() {
		//if ZOmbie, hit them
	}
	
	int[] position() {
		int[] coordinates = new int[2];
		coordinates[0] = this.pos_x;
		coordinates[1] = this.pos_y;
		return coordinates;
	}
	
	String asString() {
		String msg = "P["+hp+"]";
		return msg;
	}
}
