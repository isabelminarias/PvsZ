package logic;

import java.util.Random;

public class ZombieManager {
	
	ZombieList list;
	int zombieCount;
	int zombieStatus;
	int zombieKilled;
	double frequency;
	Random r;
	Random row;
	
	
	
	public ZombieManager (ZombieList list, int seed) {
		this.list = list;
		this.zombieCount = list.getLevel();
		this.zombieStatus = 0;
		this.frequency = list.getFrequency();
		this.r = new Random(seed);
		zombieKilled = 0;
	}
	
	public boolean isZombieAdded() {
		if (r.nextDouble() <= frequency && zombieCount > 0) {
			zombieCount--;
			zombieStatus++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void zombieKilled() {
		this.zombieStatus--;
		this.zombieKilled++;
	}
	
	public boolean noMoreZombies() {
		if( this.list.getLevel() == this.zombieKilled) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getCurrentZombies() {
		return this.zombieStatus;
	}
	
	public int zombiesRemaining() {
		return (this.list.getLevel() - this.zombieKilled);
	}
	
	public boolean zombieArrived() {
		for (int i = 0; i < list.lastPosition; i++) {
			if((list.zList[i].isArrived())) {
				return true;
			}
		}
		return false;
	}
}
