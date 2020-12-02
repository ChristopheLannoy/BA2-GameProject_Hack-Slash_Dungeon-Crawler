package Model;

import java.util.ArrayList;

import View.Map;

public class Player extends Character implements Movable, Runnable{

	//private int lifes;
	private final int maxLifes = 20;
	private char direction;
	private boolean drunk = false;
	private boolean equippedArea;
	private boolean equipped =false;
	private int dammageBonnus;
	private int dammageReduction;
	
	
	private ArrayList<MovableObserver> moveObservers = new ArrayList<MovableObserver>();
	private ArrayList<Weapon> inventoryObjects = new ArrayList<Weapon>();

	
	public Player(int x, int y, int lifes){
		super(x,y,2,lifes);
		this.lifes = lifes;
		this.direction = 'u';
		this.dammageBonnus = 1;
		this.dammageReduction = 1;
	}
	
	////////////Getter/Setter//////////
	public ArrayList<MovableObserver> getMoveObservers(){
		return moveObservers;
	}
	public void setMoveObservers(ArrayList<MovableObserver> moveObservers){
		this.moveObservers = moveObservers;
	}
	public int getLifes(){
		return this.lifes;
	}
	public void setLifes(int lifes){
		this.lifes= lifes;
	}
	public int getMaxLifes(){
		return this.maxLifes;
	}
	public int getDammageReduction(){
		return dammageReduction;
	}
	public char getDirection(){
		return direction;
	}
	public void setDirection(char direction){
		this.direction = direction;
	}
	public boolean getDrunk(){
		return drunk;
	}
	public void setDrunk(boolean drunk){
		this.drunk= drunk;
	}
	public ArrayList<Weapon> getInventory(){
		return this.inventoryObjects;
	}
	
	
	
	public void addObject(Weapon w){
		this.inventoryObjects.add(w);
	}
	
	public void readParchment(Parchment parchment){
		this.dammageBonnus = parchment.getDammageBonus();
	}
	
	public void setArmure(Armure armure){
		this.dammageReduction = armure.getDammageReduction();	
	}

	public void move(int X, int Y, boolean drunk){
		int i = 1;
		if (drunk == true){
			i=-1;
		}
		super.posX = this.posX + i*X;
		this.posY = this.posY + i*Y;
		this.movableNotifyObserver();
	}
	
	public void drinkPotion(PassiveObject potion){
		if (this.lifes<=9){
			this.lifes += ((Potion)potion).getLifes();
			this.drunk = true;
			Thread t1= new Thread(this);
			t1.start();
		}else{
			this.lifes = 19;
			this.drunk = true;
			Thread t1= new Thread(this);
			t1.start();
		}
	}
	
	public void attack(ArrayList<Character> characters) throws Exception{
		for(Character c : characters){  
			if (c.isAttackable()){
				for(Weapon weap : inventoryObjects){
					int dammage = weap.getDammage();
					//boolean area = weap.getAreaWeapon();
					if ((!equippedArea && this.isInFront(c) )
							|| (equippedArea && this.isNear(c, 2) )){
								c.receiveDammage(dammage*dammageBonnus);
					}
					
						
					}
				if(equipped==false){
					throw new Exception ("Aucune arme équipée!");
				}
			}
		}
	}
	
	public boolean isInFront(GameObject object){
		int xFront = posX;
		int yFront = posY; 
		
		if (direction == 'u'){
			yFront -= 1;
		}
		else if (direction == 'd'){
			yFront += 1;
		}
		else if (direction == 'r'){
			xFront += 1;
		}
		else if (direction == 'l'){
			xFront -= 1;
		}
		return (xFront == object.getPosX() && yFront == object.getPosY());
	}
	
	public void teleportation(int X, int Y){
		this.posX = X;
		this.posY = Y;
	}
	
	public void useObject1(){
		//if(inventoryObjects.size() >= 1){
		if (inventoryObjects.size() >= 1){
			boolean area = inventoryObjects.get(0).getAreaWeapon();
			this.equippedArea = area;
			this.equipped = true;
			inventoryObjects.get(0).setIsEquipped(true);
			if(inventoryObjects.size() >= 2){
				inventoryObjects.get(1).setIsEquipped(false);
			}
			//notifyView();
		}
	}
	public void useObject2(){
		if (inventoryObjects.size() >= 2){
			boolean area = inventoryObjects.get(1).getAreaWeapon();
			this.equippedArea = area;
			this.equipped = true;
			inventoryObjects.get(0).setIsEquipped(false);
			inventoryObjects.get(1).setIsEquipped(true);
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run(){
		try{
			Thread.sleep(5000);			
			this.drunk =false;
		}
		catch(Exception e){};
			
	}
	
	int count = 0;
	@Override
	public void receiveDammage(int dammage){
		count ++;
		if (dammageReduction == count){
			this.lifes -= dammage;
			if (lifes <= 0){
				Map.gameOver();
			}
			count = 0;
		}
		
		
	}
	
	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public boolean isAttackable() {
		return false;
	}
	
	@Override
	public synchronized void movableAttach(MovableObserver mo) {
		this.moveObservers.add(mo);
	}
	@Override
	public synchronized void movableNotifyObserver() {
		for (MovableObserver mo : moveObservers){
			mo.moved(this);
			
		}
	}
	@Override
	public synchronized void movableDisattach(MovableObserver mo){
		this.moveObservers.remove(mo); 
	}
	
	
}
