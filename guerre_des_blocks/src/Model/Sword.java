package Model;

public class Sword extends Weapon{
	
	public Sword(int x, int y){
		super(x,y,6,20, false);
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public boolean istookable() {
		return true;
	}
	@Override
	public boolean isDrinkable() {
		return false;
	}

	

}
