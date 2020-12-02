package Model;

public abstract class Character extends GameObject {
	
	protected int lifes;
	
	public Character(int X, int Y, int color,int life) {
		super(X, Y, color);
		this.lifes = life;
	}
	
	public abstract boolean isAttackable();
	
	
	public abstract void receiveDammage(int dammage);
	
	public int getLife(){
		return this.lifes;
	}


}
