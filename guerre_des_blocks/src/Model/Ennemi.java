package Model;

public class Ennemi extends Character implements Runnable {
	
	private Thread thread;
	private int waitTime;
	private Game game;
	private int rangeVision = 2;
	//private int life;
	
	public Ennemi(int x, int y, int waitTime, Game game, int life){
		super(x,y,5,life);
		this.waitTime = waitTime;
		this.game = game;
		this.lifes = life;
		this.init();
	}
	
	public void moveEnnemi(int x, int y){
		int nextX = posX+x;
		int nextY = posY+y;
		
		boolean obstacle = false;
		
		for(GameObject object : game.getGameObjects()){
			if(object.isAtPosition(nextX, nextY)){
				obstacle = object.isObstacle();
			}
			if(obstacle == true){
				break;
			}
		}
		if(game.getPlayer().isAtPosition(nextX, nextY) ){
			obstacle = true;
		}
		
		
		if(obstacle == false){
			super.posX = this.posX + x;
			this.posY = this.posY + y;
			game.notifyView();
		}
	}
	
	public void ennemiPattern(){
		
		if(Math.abs(game.getPlayer().getPosX()-posX) + Math.abs(game.getPlayer().getPosY()-posY) < rangeVision) {
			
			game.getPlayer().receiveDammage(1);
			game.notifyView();
			
		}
		
		
		
		if(Math.abs(game.getPlayer().getPosX()- posX) >= Math.abs(game.getPlayer().getPosY()- posX)){
			//Si je suis plus loin selon X que selon Y
			
			if (game.getPlayer().getPosX()< posX){moveEnnemi(-1, 0);}
			
			else if (game.getPlayer().getPosX()> posX){moveEnnemi(1,0);}
		}
		
		else{
			//Si je suis plus loin selon X que selon Y
			if (game.getPlayer().getPosY()< posY){moveEnnemi(0, -1);}
			else if (game.getPlayer().getPosY()> posY){moveEnnemi(0,1);}
		}
	}
	
	
	@Override
	public void run(){
		
		
		while(lifes > 0){
			
			
			try{
				Thread.sleep(waitTime);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			ennemiPattern();
		}
		
		game.removeEnnemi(this);
	}
	
	
	
	
	private void init(){
		thread = new Thread(this);
		thread.start();
	}
	
	
	public boolean isObstacle() {
		return true;
	}

	
	@Override
	public void receiveDammage(int dammage){
		this.lifes -= dammage;
	}
	
	@Override
	public boolean isAttackable() {
		return true;
	}
	

}