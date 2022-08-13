package territorial.io;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	int x;
	int y;
	Nation nation;

	public Tile(int x, int y, Nation nation) {
		this.x = x;
		this.y = y;
		this.nation = nation;
	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 4, 4);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 4, 4);
		
	}

}
