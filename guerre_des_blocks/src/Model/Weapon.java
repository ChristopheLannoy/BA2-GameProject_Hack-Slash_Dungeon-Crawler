package Model;

public abstract class Weapon extends PassiveObject {

	private int dammage;
	private boolean areaWeapon;
	private boolean isEquipped = false;
	
	public Weapon(int X, int Y, int color,int dammage,boolean areaWeapon) {
		super(X, Y, color);
		this.dammage= dammage;
		this.areaWeapon= areaWeapon;
	}
	
	////////Getter/Setter/////////////////
	public void setIsEquipped(boolean b){
		this.isEquipped= b;
	}
	public boolean getIsEquipped(){
		return this.isEquipped;
	}
	public int getDammage(){
		return this.dammage;
	}
	public void setDammage(int dammage){
		this.dammage = dammage;
	}
	public boolean getAreaWeapon(){
		return this.areaWeapon;
	}

}
