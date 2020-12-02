package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Game;

public class Keyboard implements KeyListener{
	private Game game;
	
	public Keyboard(Game game){ //constructeur
		this.game = game;
	}
	
	public void setGame(Game game){
		this.game = game;
	}

	@Override 
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode(); //Returns the integer keyCode associated with the key in this event.
		int i = 1;
		
		switch (key){ 
		
			case KeyEvent.VK_RIGHT: 
				game.getPlayer().setDirection('r');
				game.movePlayer(i, 0);
				break;
			case KeyEvent.VK_LEFT:
				game.getPlayer().setDirection('l');
				game.movePlayer(-i, 0);
				break;
			case KeyEvent.VK_DOWN:
				game.getPlayer().setDirection('d');
				game.movePlayer(0, i);
				break;
			case KeyEvent.VK_UP:
				game.getPlayer().setDirection('u');
				game.movePlayer(0, -i);
				break;	
			case KeyEvent.VK_D:
			try {
				game.drinkPotion();
			} catch (Exception notPotion) {
				// TODO Auto-generated catch block
				System.out.println(notPotion.getMessage());
			}
				break;
			case KeyEvent.VK_T:
			try {
				game.takeObject();
			} catch (Exception full_inventory) {
				// TODO Auto-generated catch block
				System.out.println(full_inventory.getMessage());
			}
				game.notifyView();
				break;
				
			case KeyEvent.VK_N:
				game.changeLevel();
				break;
				
			case (KeyEvent.VK_1):
				game.getPlayer().useObject1();
				game.notifyView();
				break;
			case (KeyEvent.VK_NUMPAD1):
				game.getPlayer().useObject1();
				game.notifyView();
				break;
			case KeyEvent.VK_2:
				game.getPlayer().useObject2();
				game.notifyView();
				break;
			case (KeyEvent.VK_NUMPAD2):
				game.getPlayer().useObject2();
				game.notifyView();
				break;
				
			case KeyEvent.VK_SPACE:
			try {
				game.getPlayer().attack(game.getCharacters());
			} catch (Exception unequipped) {
				System.out.println(unequipped.getMessage());
			}
				game.notifyView();
				break;
				
			case KeyEvent.VK_P:
				game.teleportation();
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
