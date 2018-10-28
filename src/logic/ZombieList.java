package logic;

public class ZombieList {
	Zombie[] zList; 
	int lastPosition;
	Game game;
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	public ZombieList(int z) {
		zList = new Zombie[z];
		lastPosition = 0;
	}
	
	public void add(Zombie z) {
		this.zList[lastPosition] = z;
		this.lastPosition++;
	}
	
	public Zombie getByPosition(int x, int y) {
		Zombie zAux;
		for (int i = 0; i < lastPosition; i++) {
			zAux = zList[i];
			if(zAux.position()[0]==x && zAux.position()[1]==y) {
				return zAux;
			}
		}
		return null;
	}
	
	public int getLastPos() {
		return this.lastPosition;
	}
	
	Zombie delete(int x, int y) {
		Zombie sfAux;
		for (int i = 0; i < lastPosition; i++) {
			sfAux = this.zList[i];
			if (sfAux.position()[0] == x && sfAux.position()[1] == y) {
				
				for (int j = i; j < lastPosition; j++) {
					zList[j]= zList[j+1];
				}
				zList[lastPosition] = null;
				lastPosition--;
				return sfAux;
				
			}
		}
		
		return null;
		
		
	}
	
	public void clear() {
		for(int i = 0; i < lastPosition; i++) {
			if (zList[i].isAlive()<=0) {
				this.delete(zList[i].position()[0], zList[i].position()[1]);
				game.zManager.zombieKilled();
			}
		}
	}
	
	public int getLevel() {
		return this.zList.length;
	}
	
	public Zombie getZombie(int i) {
		return this.zList[i];
	}
	
	public double getFrequency() {
		int l = this.zList.length; double f;
		switch(l) {
			case 3:
				f = 0.1;
				break;
			case 5:
				f = 0.2;
				break;
			case 10: 
				f = 0.3;
				break;
			default:
				f = 0;
				break;
		}
			
		return f;
	}
	
	public void move(int turn) {
		for (int i = 0; i < lastPosition; i ++) {
			zList[i].update(turn);
		}
	}
	
	public void attack() {
		Peashooter pAux;
		Sunflower sfAux;
		Zombie z;
		for (int i = 0; i < lastPosition; i++) {
			z = zList[i];
			if (!game.getSunflowerList().isEmpty) {
				for(int k = 0; k < game.getSunflowerList().lastPosition; k++) {
					sfAux = game.getSunflowerList().getSunflower(k);
					if ((z.position()[1]==(sfAux.position()[1]))&&(z.position()[0]==(sfAux.position()[0]+1))){
						sfAux.decreaseLife(z.DMG);
					}
				}
			}

			if(!game.getPeashooterList().isEmpty) {
				for (int j = 0; j < game.getPeashooterList().lastPosition; j++) {
					pAux = game.getPeashooterList().getPeashooter(j);
					if ((z.position()[1]==(pAux.position()[1]))&&(z.position()[0]==(pAux.position()[0]+1))) {
						pAux.decreaseLife(z.DMG);
					}
				}
			}
			
			
		}
		
	}


	
}
