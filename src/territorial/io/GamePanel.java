package territorial.io;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;

public class GamePanel extends JPanel{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	Timer frameDraw;
	int currentstate;
	Tile tile = new Tile(0,0);
	
	void drawGameState() {
	//	tile.draw();
	}

}
