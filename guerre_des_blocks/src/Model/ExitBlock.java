package Model;

public class ExitBlock extends Case implements MovableObserver{
	private Game game;
	
	public ExitBlock(int x, int y, Game game){
		super(x, y, 8);
		this.game = game;
	}
	
	@Override
	public synchronized void moved(Movable e) {
		Player player = (Player) e;
		if (this.getPosX()== player.getPosX() && this.getPosY() == player.getPosY()){
			System.out.println("reach exitBlock");
			
			player.movableDisattach(this);
			game.changeLevel();
		}
		
	}
	
	@Override
	public boolean isObstacle() {
		return false;
	}
	

}
