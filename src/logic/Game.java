package logic;

public class Game{
	int cycleCount;
	MoneyManager money;
	SunflowerList sfList;
	PeashooterList psList;
	ZombieManager zManager;
	ZombieList zList;
	GameLevel lvl = new GameLevel(1,0.1);
	boolean finished;
	
	public SunflowerList getSunflowerList() {
		return this.sfList;
	}
	
	public Game (int level, int seed){
		finished = false;
		this.cycleCount = 0;
		money = new MoneyManager(50);
		sfList = new SunflowerList();
		psList = new PeashooterList();
		
		switch(level) {
		case 1: 
			lvl.easy();
			break;
		case 2:
			lvl.hard();
			break;
		case 3:
			lvl.insane();
			break;
			
		}
		zList = new ZombieList(lvl.getZombies());
		zManager = new ZombieManager(zList, seed);
	}
	
	public void nextTurn() {
		this.cycleCount++;
	}
	
	public void endGame() {
		this.cycleCount = 0;
		money = new MoneyManager(50);
		sfList = new SunflowerList();
		psList = new PeashooterList();
		zList = new ZombieList(lvl.getZombies());
		zManager = new ZombieManager(zList, 0);
	}
	
	public String getTurn() {
		return Integer.toString(cycleCount);
	}
	
	public MoneyManager getMoneyManager() {
		return this.money;
	}
	
	public int getCycleTurn() {
		return this.cycleCount;
	}
	
	public PeashooterList getPeashooterList() {
		return this.psList;
	}
	
	public ZombieList getZombieList() {
		return this.zList;
	}
	
	public ZombieManager getManager() {
		return this.zManager;
	}
	
	public boolean isFinished() {
		return this.finished;
	}
}
