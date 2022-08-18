package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tile {
	int x;
	int y;
	Nation nation;
	Tile[] neybers = {};
	
	
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
		
	}void calculateNeybers(){
		ArrayList<Tile> count = new ArrayList<Tile>();
		
		if(x-1 >= 0) {
			count.add(WorldManager.tileArray[x-1][y]);
		}if(x+1 < WorldManager.tileArray[0].length) {
			count.add(WorldManager.tileArray[x+1][y]);
		}if(y-1 >= 0) {
			count.add(WorldManager.tileArray[x][y-1]);
		}if(y+1 < WorldManager.tileArray.length) {
			count.add(WorldManager.tileArray[x][y+1]);
		
		}
	}

}
