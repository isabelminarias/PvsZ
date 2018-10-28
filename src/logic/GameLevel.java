package logic;


public class GameLevel {
	
	private int zombies;
	private double frequency;

	GameLevel(int zombies, double frequency){
		this.zombies = zombies;
		this.frequency = frequency;
	}
	
	void easy() {
		this.zombies = 3;
		this.frequency = 0.1;
	}
	
	void hard() {
		this.zombies = 5;
		this.frequency = 0.2;
	}
	
	void insane() {
		this.zombies = 10; 
		this.frequency = 0.3;
	}
	
	int getZombies() {
		return this.zombies;
	}
	
	double getFrequency() {
		return this.frequency;
	}
	
	
	
	
	
}
