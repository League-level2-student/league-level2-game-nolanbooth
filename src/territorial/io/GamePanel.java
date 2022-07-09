package territorial.io;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	Timer frameDraw;
	int currentState;
	Tile tile = new Tile(50,50);
	Graphics g;
	public GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}
	
	void drawMenuState() {
		
	}
	void drawGameState() {
	
	}void drawEndState(){
		
	}
	
	void updateMenuState() {
		
	}void updateGameState(){
		
	}void updateEndState(){
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		// System.out.println("action");
		repaint();
	}

}
