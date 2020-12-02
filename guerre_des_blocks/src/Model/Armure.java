package Model;

public class Armure extends PassiveObject implements MovableObserver{
	private int dammageReduction;
	private Game game;
	
	public Armure(int X, int Y, Game game){
		super(X, Y, 10);
		this.game = game;
		this.dammageReduction = 2;
	}
	
	public int getDammageReduction(){
		return dammageReduction;
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
	public synchronized void moved(Movable e) {
		Player player = (Player) e;
		if (this.getPosX()== player.getPosX() && this.getPosY() == player.getPosY()){
			player.setArmure(this);
			player.movableDisattach(this);
			game.getGameObjects().remove(this);
			game.getPassiveObjects().remove(this);
			
		}
		
	}

	@Override
	public boolean isDrinkable() {
		return false;
	}

}
