package Model;

public abstract class PassiveObject extends GameObject {

	public PassiveObject(int X, int Y, int color) {
		super(X, Y, color);
		
	}
	public abstract boolean istookable();
	
	public abstract boolean isDrinkable();


}
