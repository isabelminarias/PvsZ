package logic;

public class MoneyManager {
	int currentSunCoins;
	
	MoneyManager (int sunCoins){
		this.currentSunCoins = sunCoins;
	}
	
	public void addFunds(int n){
		this.currentSunCoins += n;
	}
	
	
	
	public int spend(int cost) {
		if (cost > this.currentSunCoins) {
			return -1;
		}
		else {
			this.currentSunCoins = this.currentSunCoins - cost;
			return this.currentSunCoins;
		}
	}
	
	public int getSunCoins() {
		return this.currentSunCoins;
	}
}
