package Creatures;

import logic.Game;

public abstract class Creature {
	int life; 		//Initial Health Points
	int hp;			//Current Health Points
	int turn; 		//Creation Turn
	int dmg;		//Damage points 
	int speed;		//How fast they move across the board
	int rotation;	//If they have a rotation speed (move every two turns, or generate something every turn).
	String name;	//Creature Name
	int[] position;	//Position it's created at
	Game game; 		//Game where they're being used

	
	// ----- ----- Default Constructor ----- -----
	
	public void create(Game game, int turn, int[] position) {
		this.game = game;
		this.turn = turn; 
		this.position = position;
		name = "Creature";
		rotation = 1;
		speed = 0;
	}
	
	// ----- ----- Printing Method for Board ----- -----
	public String asString() {
		String msg = "";
		
		msg+=name.toUpperCase().charAt(0)+"["+"hp"+"]";
		
		return msg;
	}
	
	// ----- ----- Move Method for Board ----- -----
	public void move(int currentTurn) {
		if((currentTurn - turn)%rotation == 0) {
			position[0]= position[0]+speed;
		}
	}
}
