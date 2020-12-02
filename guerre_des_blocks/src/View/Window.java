package View;
import Model.GameObject;
import Model.Player;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controller.Keyboard;

public class Window {
	private Map map = new Map();
	public Keyboard keyboard;
	
	public Window(){	    
	    JFrame window = new JFrame("Game");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, 1200, 1050); //donne la taille
	    window.getContentPane().setBackground(Color.gray);
	    window.getContentPane().add(this.map);
	    window.setVisible(true);		    
	}	
	
	public void setGameObjects(ArrayList<GameObject> objects){
		this.map.setObjects(objects);
		this.map.redraw();
	}
	
	public void setPlayer(Player player){
		this.map.setPlayer(player);
	}
	
	public void update(){
		this.map.redraw();
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	    this.keyboard = (Keyboard)keyboard;
	}
}
