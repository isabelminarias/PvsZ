package game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import control.Controller;
import logic.Game;

public class PlantsVsZombies {
	 	
	public static void main(String[] args) throws IOException {
		boolean playing = true;
		boolean help = false;
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		Game g;
		Controller c;
		String input;
		
		// ----- ----- Game Initialization ----- -----
		do {
			do {
			System.out.println("Welcome to the Game! Would you like to play a game?");
			input = sc.readLine();
				if(input.isEmpty()) {
					help = true;
				}
				else {
					help = false;
				}
			} while(help);
			
			// ----- ----- Game Settings ----- -----
			
			if (input.toUpperCase().charAt(0)=='Y') {
				System.out.println("Would you like to customize your game? (Level & Seed)");
				input = sc.readLine();
				
				// ----- ----- Customized Settings Block ----- -----
				if (input.toLowerCase().charAt(0)=='y') {
					boolean valid = false;
					do {
						
						// ----- ----- Level Input ----- -----
						System.out.println("Input your level (1-3)");
						int lvl = sc.read();
						if (lvl > 0 && lvl < 4) {
							valid = true;
							
							// ----- ----- Seed Input ----- -----
							System.out.println("Input your seed (0-99)");
							int seed;
							do {
								seed = sc.read();
								if (seed > 100 || seed < 0) {
									System.out.println("Input error. Try again.");	
								}
								else {
									g = new Game(lvl,seed);
									c = new Controller(lvl, seed, g);
									c.run();
								}
							}while (seed < 0 || seed > 99);
						}
						else {
							System.out.println("Input error. Try again.");
						}
					}while(valid == false);	
				}
				
				// ----- ----- Default Settings Block ----- -----
				else {
					g = new Game(1,0);
					c = new Controller(1, 0, g);
					c.run();
				}
			}
			// ----- ----- Exit Option ----- -----
			else {
				playing = false;
				System.exit(0);
			}
		} while(playing == true);
		
	}

}
