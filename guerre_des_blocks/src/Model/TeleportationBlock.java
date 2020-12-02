package Model;

public class TeleportationBlock extends Case{
	private int exitX;
	private int exitY;
	
	public TeleportationBlock(int x, int y, int exitX, int exitY){
		super(x, y, 7);
		this.exitX = exitX;
		this.exitY = exitY;
	}
	
	public int getExitX(){
		return exitX;
	}
	
	public int getExitY(){
		return exitY;
	}
		

	@Override
	public boolean isObstacle() {
		// TODO Auto-generated method stub
		return false;
	}

	

}