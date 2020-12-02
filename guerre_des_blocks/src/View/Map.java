package View;
import Model.Game;
import Model.GameObject;
import Model.Player;
import Model.Weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel {
	private static int size;  // 9 <= size
	private static boolean gameOver = false;
	
	
	private static double ratio = (double)20/(double)size;
	private static int blockDimention1 = (int)(40*ratio);
	private static int blockDimention2 = (int)(38*ratio);
	private Player player;
	
	private ArrayList<GameObject> objects;
	private int inventorySize=3;

	
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public static void setSize(int size){
		Map.size= size;
		Map.ratio = (double)20/(double)size;
		blockDimention1 = (int)(40*ratio);
		blockDimention2 = (int)(38*ratio);
	}
	
	public static void gameOver(){
		Map.gameOver = true;
	}
	
	private void setColor(Graphics g, GameObject object){
		int color = object.getColor();			
		
		if(color == 0){
			g.setColor(Color.DARK_GRAY);
		}else if(color == 1){
			g.setColor(Color.GRAY);
		}else if(color == 2){
			g.setColor(Color.BLUE);
		}else if(color == 3){
			g.setColor(Color.GREEN);
		}else if(color == 4){
			g.setColor(Color.RED);
		}else if(color == 5){
			g.setColor(Color.ORANGE);
		}else if(color == 6){
			g.setColor(Color.BLACK);
		}else if(color == 7){
			g.setColor(Color.WHITE);
		}else if(color == 8){
			g.setColor(Color.black);
		}else if(color == 9){
			g.setColor(new Color(226,169,106)); 
		}else if(color == 10){
			g.setColor(Color.CYAN); 
		}
		
	}
	public void paint(Graphics g) { 
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// PARTIE MAP DE JEU
		for(int i = 0; i<size; i++){							// Vire la valeur 20 et parametrer ca
			for(int j = 0; j<size; j++){
				int x = i;
				int y = j;
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x*blockDimention1, y*blockDimention1, blockDimention2, blockDimention2); // fillRect(int x, int y, int width, int height) 
				g.setColor(Color.BLACK);
				g.drawRect(x*blockDimention1,y*blockDimention1, blockDimention2, blockDimention2);//drawRect(int x, int y, int width, int height)
			}
		}
		
		
		
		for(GameObject object : this.objects){ //for object in objects...
			int x = object.getPosX();
			int y = object.getPosY();		
			
			setColor(g,object);

			g.fillRect(x*blockDimention1,y*blockDimention1, blockDimention2, blockDimention2);
			g.setColor(Color.BLACK);
			g.drawRect(x*blockDimention1,y*blockDimention1, blockDimention2, blockDimention2); 
		}
		
		/////// JOUEUR ////////
		
		int x = player.getPosX();
		int y = player.getPosY();
		int color = player.getColor();			
		
		if(color == 0){
			g.setColor(Color.DARK_GRAY);
		}else if(color == 1){
			g.setColor(Color.GRAY);
		}else if(color == 2){
			g.setColor(Color.BLUE);
		}else if(color == 3){
			g.setColor(Color.GREEN);
		}else if(color == 4){
			g.setColor(Color.RED);
		}else if(color == 5){
			g.setColor(Color.ORANGE);
		}else if(color == 6){
			g.setColor(Color.BLACK);
		}
		else if(color == 7){
			g.setColor(Color.WHITE);
		}

		g.fillRect(x*blockDimention1,y*blockDimention1, blockDimention2, blockDimention2);
		g.setColor(Color.BLACK);
		g.drawRect(x*blockDimention1,y*blockDimention1, blockDimention2, blockDimention2);
		
		////////////////  PARTIE INVENTAIRE/////////////////////
		for(int i=1; i<inventorySize+1;i++){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(1000, 40*i, 38, 38);
			g.setColor(Color.BLACK);
			g.drawRect(1000,40*i,38,38);
		}
		int count = 1;
		for(Weapon w : player.getInventory()){ 
				int X = 1000;
				color = w.getColor();			
			
			if(color == 0){
				g.setColor(Color.DARK_GRAY);
			}else if(color == 1){
				g.setColor(Color.GRAY);
			}else if(color == 2){
				g.setColor(Color.BLUE);
			}else if(color == 3){
				g.setColor(Color.GREEN);
			}else if(color == 4){
				g.setColor(Color.RED);
			}else if(color == 5){
				g.setColor(Color.ORANGE);
			}else if(color == 6){
				g.setColor(Color.BLACK);
			}
			else if(color == 7){
				g.setColor(Color.WHITE);
			}

			g.fillRect(X, count*40, 38, 38);
			if (w.getIsEquipped()){
				g.setColor(Color.RED);
				g.drawRect(X,count*40, 38, 38);
				g.drawRect(X+1,count*40+1, 36, 36);
				g.drawRect(X+2,count*40+2, 34, 34);
				g.drawRect(X+3,count*40+3, 32, 32);
				}
			
			else {
				g.setColor(Color.BLACK);
				g.drawRect(X,count*40, 38, 38);
			}
			
			count ++;
		}
		
		///////////////  PARTIE VIES////////////////////////////
		int lifes = getPlayer().getLifes();
		int maxLifes = getPlayer().getMaxLifes();
		for(int i=1; i<maxLifes;i++){
			g.setColor(Color.gray);
			g.fillRect(1080, 40*i, 38, 38);
			g.setColor(Color.BLACK);
			g.drawRect(1080,40*i,38,38);
		}
		
		for(int i=1; i<lifes+1;i++){
			g.setColor(Color.RED);
			g.fillRect(1080, 40*i, 38, 38);
			g.setColor(Color.BLACK);
			g.drawRect(1080,40*i,38,38);
		}
		//////////////// Direction du joueur////////////
		
		int x1 = this.getPlayer().getPosX();
		int y1 = this.getPlayer().getPosY();
		char direction = this.getPlayer().getDirection();
		
		if (direction == 'u'){
			x1 = x1*blockDimention1 + (int)(14*ratio);     
			y1 = y1*blockDimention1 ;
		}
		
		else if (direction == 'd'){
			x1 = x1*blockDimention1 + (int)(14*ratio);
			y1 = y1*blockDimention1 + blockDimention2 - (int)(10*ratio); 
		}
		
		else if (direction == 'l'){
			x1 = x1*blockDimention1 ;
			y1 = y1*blockDimention1 + (int)(14*ratio);
		}
		
		else if (direction == 'r'){
			x1 = x1*blockDimention1 + blockDimention2 - (int)(10*ratio);
			y1 = y1*blockDimention1 + (int)(14*ratio);
		}
		
		g.setColor(Color.cyan);
		g.fillRect(x1, y1, (int)(10*ratio), (int)(10*ratio));
		////////ARMURE ///////////
		
		if(this.getPlayer().getDammageReduction() >= 2 ){
			x1 = this.getPlayer().getPosX();
			y1 = this.getPlayer().getPosY();
			g.setColor(Color.CYAN);
			g.drawRect(x*blockDimention1,y*blockDimention1, blockDimention2, blockDimention2);
			g.drawRect(x*blockDimention1+1,y*blockDimention1+1, blockDimention2-2, blockDimention2-2);
			g.drawRect(x*blockDimention1+2,y*blockDimention1+2, blockDimention2-4, blockDimention2-4);
			g.drawRect(x*blockDimention1+3,y*blockDimention1+3, blockDimention2-6, blockDimention2-6);

			
		}
		/////// game-over//////////
		if(gameOver){
			//g.setColor(Color.BLACK);
			//g.fillRect(0, 0, getWidth(), getHeight());
			Image image_map = null;
			try {
				image_map = ImageIO.read(new File("GameOver.jpg"));
			} catch (IOException e) {}
				g.drawImage(image_map, 0,0, getWidth(), getHeight(), null );
		}
	}
	
	public Map getMap(){
		return this;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public void setObjects(ArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void redraw(){
		this.repaint();
	}

	
}

