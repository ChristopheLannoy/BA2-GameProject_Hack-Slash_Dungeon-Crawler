package Model;

public class Wall extends Case{

	public Wall(int X, int Y) {
		super(X, Y, 0); 
	}

	@Override
	public boolean isObstacle() {
		return true;
	}
	
}
