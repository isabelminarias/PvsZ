package logic;

public class SunflowerList {
	Sunflower[] sfList; 
	int lastPosition;
	boolean isEmpty;
	Game game;
	
	public void add(Sunflower sf) {
		this.sfList[lastPosition] = sf;
		this.lastPosition++;
		isEmpty = false;
	}
	
	public SunflowerList() {
		this.sfList = new Sunflower[32];
		this.lastPosition = 0;
		isEmpty = true;
	}
	
	public Sunflower getByPosition(int x, int y) {
		Sunflower sfAux;
		for (int i = 0; i < lastPosition; i++) {
			sfAux = sfList[i];
			if(sfAux.position()[0]==x && sfAux.position()[1]==y) {
				return sfAux;
			}
		}
		return null;
	}
	
	public void clear() {
		for (int i = 0; i < lastPosition; i++) {
			if(sfList[i].isAlive()<=0) {
				this.delete(sfList[i].position()[0], sfList[i].position()[1]);
			}
		}
	}
	
	public int getLastPos() {
		return this.lastPosition;
	}
	
	public Sunflower getSunflower(int i) {
		return this.sfList[i];
	}
	
	Sunflower delete(int x, int y) {
		Sunflower sfAux;
		for (int i = 0; i < lastPosition; i++) {
			sfAux = this.sfList[i];
			if (sfAux.position()[0] == x && sfAux.position()[1] == y) {
				
				for (int j = i; j < lastPosition; j++) {
					sfList[j]= sfList[j+1];
				}
				sfList[lastPosition] = null;
				lastPosition--;
				if (lastPosition == 0) {
					isEmpty = true;
				}
				return sfAux;
				
			}
		}
		
		return null;
		
		
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	public int getSuns(int turn) {
		int sunCount=0;
		for (int i = 0; i < lastPosition; i++) {
			sunCount += sfList[i].update(turn);
		}
		return sunCount;
	}
	
}
