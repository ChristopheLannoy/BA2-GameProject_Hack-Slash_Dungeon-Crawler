package Model;

public class Parchment extends PassiveObject implements MovableObserver {
	private int dammageBonus;
	private Game game;

	public Parchment(int X, int Y, Game game){
		super(X, Y,9);
		this.dammageBonus = 2;	
		this.game = game;
	}
	
	public int getDammageBonus(){
		return dammageBonus;
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
			player.readParchment(this);
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
