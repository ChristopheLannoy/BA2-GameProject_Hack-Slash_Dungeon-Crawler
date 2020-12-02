package Model;


public class Potion extends PassiveObject { //implements MovableObserver

	private int lifes;
	
	public Potion(int X, int Y,int lifes) {
		super(X, Y, 4); //4 représente couleur rouge
		this.lifes = lifes;
	}
	
	public int getLifes(){
		return this.lifes;
	}
	

	@Override
	public boolean isObstacle() {
		return false;
	}
	
	@Override
	public boolean istookable() {
		return false;
	}

	@Override
	public boolean isDrinkable() {
		return true;
	}
	
}

