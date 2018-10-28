package control;

import java.util.Random;
import java.util.Scanner;

import logic.Game;
import logic.GameDrawing;
import logic.Peashooter;
import logic.Sunflower;
import logic.Zombie;

public class Controller {

	Game game;
	Scanner sc = new Scanner(System.in);
	int level; 
	int seed;
	Random r;
	
	
	// ----- ----- Constructor ----- -----
	public Controller(int lvl, int s, Game game) {
		this.level = lvl;
		this.seed = s;
		this.game = game;
		this.r = new Random(s);
	}
	
	// ----- -----  ----- -----
	
	boolean update() {
		//Sunflowers give you money
		game.getMoneyManager().addFunds(game.getSunflowerList().getSuns(game.getCycleTurn()));
		//Peashooters do their thing
		game.getPeashooterList().shoot();
		//Zombies move
		game.getZombieList().move(game.getCycleTurn());
		//Zombies attacc
		game.getZombieList().attack();	
		//Creatures die
		game.getZombieList().clear();
		game.getPeashooterList().clear();
		game.getSunflowerList().clear();
		//Check game status
		if(game.getManager().noMoreZombies()) {
			System.out.println("\n\n\n\n ******** \n\n Player Wins! \n\n  ******** \n\n\n\n");
			return true;
		}
		else if(game.getManager().zombieArrived()) {
			System.out.println("\n\n\n\n ******** \n\n Zombie Wins! \n\n  ******** \n\n\n\n");
			return true;
		}
		return false;
		
	}
	
	void draw(GameDrawing board) {
		
		System.out.println(board.board());
		
	
		
	}
	
	boolean userCommand() {
		
		int choice = 0;
		boolean userDecides = false;
		boolean gaming = true;
		do {
			System.out.print("Command > ");
			String in = sc.nextLine();
			
			if (in.isEmpty()) {
				choice = 6; 
				userDecides = true;
				}				
			else if((in.toLowerCase().charAt(0) == 'a' || in.toLowerCase().matches("add(.*)")) && (in.split(" ").length > 3)) {
			choice = 1;
			userDecides = true;
			}
			else if(in.toLowerCase().charAt(0) == 'l' || in.toLowerCase().matches("list")) {
			choice = 2;
			}
			else if(in.toLowerCase().charAt(0) == 'r' || in.toLowerCase().matches("reset")) {
			choice = 3;	
			userDecides = true;
			}
			else if(in.toLowerCase().charAt(0) == 'h' || in.toLowerCase().matches("help")) {
			choice = 4;	
			}
			else if(in.toLowerCase().charAt(0) == 'e' || in.toLowerCase().matches("exit") ) {
			choice = 5;
			System.out.println("Dude... GAME OVER! Bai.");
			userDecides = true;
			}
			String[] inst = in.split(" ");
			switch(choice) {
			//Agregar una planta
			case 1:
				//Elige un Sunflower
				if((inst[1].toLowerCase().charAt(0)=='s' || inst[1].toLowerCase().matches("sunflower")) && inst.length>3) {
					//Checkea que tenga soles
					if(game.getMoneyManager().getSunCoins() > 20) {
						//Checkea que la posicion es valida
						int x = Integer.parseInt(inst[2]);
						int y = Integer.parseInt(inst[3]);
						if (y>=0 && y<4 && x>=0 && x<9) {
							//El check cell dice si es posible meter algo aqui (true si esta vacio)
							if(game.getSunflowerList().getByPosition(x, y) == null && game.getPeashooterList().getByPosition(x, y) == null) {
								game.getSunflowerList().add(new Sunflower(game,game.getCycleTurn(),x,y));
								game.getMoneyManager().spend(Sunflower.COST);
							}
							else {
								System.out.println("Space unavailable.");
							}
							
						}
						else {
							System.out.println("Wrong coordinates.");
						}
						
					}
					else {
						System.out.println("Insuficient sun coins");
						//Como no puede, lo regresamos para que vuelva a meter un comando
						userDecides = false;
					}
				}
				//Elige un Peashooter
				else if ((inst[1].toLowerCase().charAt(0)=='p' || inst[1].toLowerCase().matches("peashooter"))&& inst.length > 3) {
					if(game.getMoneyManager().getSunCoins() > 20) {
						//Checkea que la posicion es valida
						int x = Integer.parseInt(inst[2]);
						int y = Integer.parseInt(inst[3]);
						if (y>=0 && y<4 && x>=0 && x<9) {
							//El check cell dice si es posible meter algo aqui (true si esta vacio)
							if(game.getPeashooterList().getByPosition(x, y) == null && game.getSunflowerList().getByPosition(x, y) == null) {
								game.getPeashooterList().add(new Peashooter(game,game.getCycleTurn(),x,y));
								game.getMoneyManager().spend(Peashooter.COST);
							}
							else {
								System.out.println("Space unavailable.");
							}
							
						}
						else {
							System.out.println("Wrong coordinates.");
						}
						
					}
					else {
						System.out.println("Insuficient sun coins");
						//Como no puede, lo regresamos para que vuelva a meter un comando
						userDecides = false;
					}
				}
				else {
					System.out.println("There's an input error. Please check again.");
					userDecides = false;
				}
				
				
				break;
			//Listar las plantas disponibles
			case 2:
				System.out.println("[S]unflower: 	Cost - 20  suncoins |	Harm - 0");
				System.out.println("[P]eashooter: 	Cost - 50 suncoins |	Harm - 1");
				break;
			//Resetea el juego
			case 3: 
				gaming = false;
				userDecides = true;
				game.endGame();
				break;
			//Imprime la lista de comandos disponibles.
			case 4:
				System.out.println("Add <plant><x><y>: Adds a plant in position x, y.");
				System.out.println("List: prints the list of available plants.");
				System.out.println("Reset: Starts a new game.");
				System.out.println("Help: prints this help message.");
				System.out.println("Exit: terminates the program.");
				System.out.println("[none]: Skips cycle.");
				break;
			//Cierra la consola
			case 5:
				System.exit(0);
				break;
			case 6:
				//Nothing happens. Player skips a turn.
				break;
			default:
				System.out.println("There's an input error. Please check again.");
			}
			
		}while(userDecides == false);
			
		return gaming;
		
	}
	
	void computerAction() {
		if(game.getManager().isZombieAdded()) {
			int row;
			boolean available = false;
			do {
			row = r.nextInt(3);
			if ((game.getSunflowerList().getByPosition(7, row) == null) && (game.getPeashooterList().getByPosition(7, row) == null) && (game.getZombieList().getByPosition(7, row) == null)) {
				game.getZombieList().add(new Zombie(game, game.getCycleTurn(), 7, row));
				available = true;
			}
			else {
				available = false;
			}
			}while(available == false);
		}
	}
	
	public void run() {
		boolean gaming = true;
		boolean win;
		
		while(!game.isFinished() && gaming) {
			
		}
		
		
		// ----- ----- Original System ----- -----
		
		GameDrawing board = new GameDrawing(game);
		game.getSunflowerList().setGame(game);
		game.getPeashooterList().setGame(game);
		game.getZombieList().setGame(game);
		do {
			System.out.println("Turn > "+game.getTurn());
			System.out.println("Current SunCoins available > "+game.getMoneyManager().getSunCoins());
			System.out.println("Zombies remaining > "+game.getManager().zombiesRemaining());
			System.out.println("\n\n");
			win = update();
			if (win == false) {
				draw(board);
				gaming = userCommand();
				computerAction();
				game.nextTurn();
			}
			else {
				gaming = false;
			}
		}while (gaming == true);
		
		
	}
}
