package Model;

public class WizardStick extends Weapon{
	
	public WizardStick(int x, int y){
		super(x,y,3,25, true);
	}
	
	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public boolean istookable() {
		return true;
	}

	@Override
	public boolean isDrinkable() {
		return false;
	}
		
	}
