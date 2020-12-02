import java.io.FileNotFoundException;

import Controller.Keyboard;
import Model.Game;
import View.Window;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		Game game= Game.ReadLevel("Level1.txt");
		Window window = new Window();
		game.setWindow(window);
		
		
		
		//Game game = new Game(window);
		Keyboard keyboard = new Keyboard(game);
		window.setKeyListener(keyboard);
	}
}
