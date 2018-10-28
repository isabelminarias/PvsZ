package logic;

public class PeashooterList {

	Peashooter[] psList; 
	int lastPosition = 0;
	boolean isEmpty;
	Game game;
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void add(Peashooter ps) {
		this.psList[lastPosition] = ps;
		this.lastPosition++;
		isEmpty = false;
	}
	
	public PeashooterList() {
		this.psList = new Peashooter[32];
		this.lastPosition = 0;
		isEmpty = true;
	}
	
	public Peashooter getByPosition(int x, int y) {
		Peashooter sfAux;
		for (int i = 0; i < lastPosition; i++) {
			sfAux = psList[i];
			if(sfAux.position()[0]==x && sfAux.position()[1]==y) {
				return sfAux;
			}
		}
		return null;
	}
	
	public void clear() {
		for (int i = 0; i < lastPosition; i++) {
			if(psList[i].isAlive()<=0) {
				this.delete(psList[i].position()[0], psList[i].position()[1]);
			}
		}
	}
	
	public void shoot() {
		Peashooter sfAux;
		Zombie z;
		for (int i = 0; i < lastPosition; i++) {
			sfAux = psList[i];
			for (int j = 0; j < game.getZombieList().getLastPos(); j++) {
				z = game.getZombieList().getZombie(j);
				if (z.position()[1]==sfAux.position()[1]) {
					z.decreaseLife(sfAux.DMG);
					j+=10;
				}
			}
		}
		
	}
	
	public Peashooter getPeashooter(int i) {
		return this.psList[i];
	}
	
	Peashooter delete(int x, int y) {
		Peashooter sfAux;
		for (int i = 0; i < lastPosition; i++) {
			sfAux = this.psList[i];
			if (sfAux.position()[0] == x && sfAux.position()[1] == y) {
				
				for (int j = i; j < lastPosition; j++) {
					psList[j]= psList[j+1];
				}
				psList[lastPosition] = null;
				lastPosition--;
				if (lastPosition == 0) {
					isEmpty = true;
				}
				return sfAux;
				
			}
		}
		
		return null;
		
	}
	
}
