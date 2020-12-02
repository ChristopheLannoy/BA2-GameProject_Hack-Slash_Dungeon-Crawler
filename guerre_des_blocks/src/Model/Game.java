package Model;
import View.Window;
import View.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	
	private static ArrayList<String> LEVELS=new ArrayList<String>();
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<PassiveObject> passiveObjects = new ArrayList<PassiveObject>();
	private ArrayList<Case> cases = new ArrayList<Case>();
	private ArrayList<Character> characters = new ArrayList<Character>();
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private Player player;
	private Window window;
	
	private static int LEVEL= 1;
	private int inventorySize=3;
	private int count = 0;
	
	public Game(){
		LEVELS.add("Level1.txt"); LEVELS.add("Level2.txt"); LEVELS.add("Level3.txt");
	}
	public static Game ReadLevel(String filename) throws FileNotFoundException{
		int count = 0;
		int XTeleportation = 0;
		int YTeleportation = 0;
		Game newGame = new Game();
		Scanner scanner = new Scanner(new File(filename));
		scanner.useDelimiter("\r\n");
		String lineSize = scanner.next();
		int size = Integer.valueOf(lineSize);
		Map.setSize(size);
		
		while (scanner.hasNext()) {
			for(int j = 0; j < size; j++){
				String line = scanner.next();
				String[] cells = line.split(" ");
				for(int i = 0; i < size; i++){
					if(cells[i].equals("1")){
						Player player = new Player(i,j,10);
						newGame.setPlayer(player);
						//newGame.characters.add(player);
					}
					else if (cells[i].equals("2")){
						//Add A Ennemi
						Ennemi ennemi = new Ennemi(i, j, 500, newGame, 100);
						newGame.objects.add(ennemi);
						newGame.characters.add(ennemi);
						
					}
					
					else if (cells[i].equals("P")){
						//Add A Potion
						Potion potion = new Potion(i, j,10);
						//Player player = newGame.getPlayer();
						newGame.objects.add(potion);
						newGame.passiveObjects.add(potion);
						//player.movableAttach(potion);
					}
					
					else if(cells[i].equals("M")){ 
						// Add a wall 
						Wall wall = new Wall(i,j);
						newGame.objects.add(wall);
						newGame.cases.add(wall);
					}
					
					else if(cells[i].equals("S")){ 
						// Add a Sword 
						Sword sword = new Sword(i,j);
						newGame.objects.add(sword);
						newGame.passiveObjects.add(sword);
						newGame.weapons.add(sword);
					}
					
					else if(cells[i].equals("B")){ 
						// Add a Baton
						WizardStick wzs = new WizardStick(i,j);
						newGame.objects.add(wzs);
						newGame.passiveObjects.add(wzs);
						newGame.weapons.add(wzs);
					}
					
					else if(cells[i].equals("T")){ 
						// Add a TeleportationBlock
						
						if(count == 0){
							XTeleportation = i;
							YTeleportation = j;
							
						}
						else if(count == 1){
							TeleportationBlock teleblock1 = new TeleportationBlock(i,j,XTeleportation,YTeleportation);
							newGame.objects.add(teleblock1);
							newGame.cases.add(teleblock1);
							TeleportationBlock teleblock2 =new TeleportationBlock(XTeleportation,YTeleportation,i,j);
							newGame.objects.add(teleblock2);
							newGame.cases.add(teleblock2);
						}
						count += 1;
					}
					else if(cells[i].equals("L")){ 
						// Add a Parchment
						Parchment parchment = new Parchment(i,j, newGame);
						newGame.objects.add(parchment);
						newGame.passiveObjects.add(parchment);
						Player player = newGame.getPlayer();
						player.movableAttach(parchment);
						
					}else if(cells[i].equals("A")){ 
						// Add a Armur 
						Armure armure  = new Armure(i,j, newGame);
						newGame.objects.add(armure);
						newGame.passiveObjects.add(armure);
						Player player = newGame.getPlayer();
						player.movableAttach(armure);
					}
				}	
			}	
		}
		scanner.close();
		return newGame;
	}
	public void changeLevel(){
		System.out.println("NIVEAU SUIVANT");
		Game newLevel =null;
		Game.LEVEL +=  1; 
		if(1 <= LEVEL && LEVEL <= 3){
			try {
				newLevel = Game.ReadLevel(LEVELS.get(LEVEL-1));
				this.player.teleportation(newLevel.getPlayer().getPosX(),newLevel.getPlayer().getPosY() );
				this.player.setMoveObservers(newLevel.getPlayer().getMoveObservers());
				newLevel.setPlayer(this.player);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			newLevel.setWindow(this.window);
			this.window.keyboard.setGame(newLevel);
		}
		else{
			System.out.println("NIVEAU MAX");
		}
	}

	
	/////// Getter/Setter///////////
	public Player getPlayer(){
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<GameObject> getObjects(){
		return this.objects;
	}
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	public ArrayList<PassiveObject> getPassiveObjects(){
		return this.passiveObjects;
	}
	public ArrayList<Character> getCharacters(){
		return this.characters;
	}
	public ArrayList<Weapon> getWeapons(){
		return this.weapons;
	}
	public ArrayList<GameObject> getGameObjects(){
		return this.objects;
	}
	public void setWindow(Window window){
		this.window = window;
		window.setGameObjects(this.getGameObjects());
		window.setPlayer(this.player);
	}
	
	public void movePlayer(int x, int y){
		//Player player = ((Player) objects.get(playerNumber));
		boolean drunk = player.getDrunk();
		int i = 1;
		if (drunk){
			i=-1;
		}
		int nextX = player.getPosX()+i*x;
		int nextY = player.getPosY()+i*y;
		if(isObstacleAtPosition(nextX, nextY) == false){
			player.move(x,y,drunk);
		}
		notifyView();
	}
	public void teleportation(){
		for(Case c : cases){
			if (c.getPosX() == player.getPosX() && c.getPosY() == player.getPosY() ){
				int nextX = ((TeleportationBlock)c).getExitX();
				int nextY = ((TeleportationBlock)c).getExitY();
				player.teleportation(nextX, nextY);
				notifyView();
				/*if (isObstacleAtPosition(nextX, nextY) == false){
					player.teleportation(nextX, nextY);
					notifyView();
				}*/
				break;
			}
		}
	}
	public boolean isObstacleAtPosition(int X, int Y){
		boolean obstacle = false;
		for(GameObject object : objects){
			if(object.isAtPosition(X, Y)){
				obstacle = object.isObstacle();
			}
			if(obstacle == true){
				break;
			}
		}
		return obstacle;
		
	
	}
	
	public void drinkPotion() throws Exception{
		for(PassiveObject object :passiveObjects){
			if (player.getPosX()== object.getPosX() && player.getPosY() == object.getPosY() ){
				if ( object.isDrinkable()){
					objects.remove(object);
					passiveObjects.remove(object);
					notifyView();
					player.drinkPotion(object);
					}
				else { throw new Exception ("Tu ne peux boire que des potions voyons!");}
			}
		}
	}
	public void takeObject() throws Exception{
		if (count <= inventorySize-1){
			for(Weapon w :weapons){
				if (w.getPosX()== player.getPosX() && w.getPosY() == player.getPosY()){
					player.getInventory().add(w);
					count++;
					objects.remove(w);
					weapons.remove(w);
					notifyView();
					break;
				}
				
			}
		}
		else {throw new Exception ("Inventaire est rempli!");}
	}
	
	public void removeEnnemi(Ennemi ennemi){
		objects.remove(ennemi);
		characters.remove(ennemi);
		if (characters.size() == 0){
			ExitBlock exitBlock = new ExitBlock(2,2,this);
			player.movableAttach(exitBlock);
			objects.add(exitBlock);
			cases.add(exitBlock);
		}
		notifyView();
	}
	public void notifyView(){   
		window.update();
	}
}
