package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tile {
	int x;
	int y;
	boolean isBorder = true;
	static Tile[] emptyNeybers = new Tile[0];
	
	Nation nation;
	Tile[] neybers = {};
	
	
	public Tile(int x, int y, Nation nation) {
		this.x = x;
		this.y = y;
		this.nation = nation;
		
	}

	void draw(Graphics g) {
		g.setColor(nation.color);
		g.fillRect(x*4, y*4, 4, 4);
		g.setColor(Color.BLUE);
		g.drawRect(x*4, y*4, 4, 4);
		
	}void calculateNeybers(){
		ArrayList<Tile> count = new ArrayList<Tile>();
		
		if(x-1 >= 0) {
			count.add(WorldManager.tileArray[x-1][y]);
		}if(x+1 < WorldManager.tileArray.length) {
			count.add(WorldManager.tileArray[x+1][y]);
		}if(y-1 >= 0) {
			count.add(WorldManager.tileArray[x][y-1]);
		}if(y+1 < WorldManager.tileArray[x].length) {
			count.add(WorldManager.tileArray[x][y+1]);
		
		}
		neybers = new Tile[count.size()];
		int i = 0;
		for(Tile t : count) {
			neybers[i++] = t;

		}
	}public Tile[] getNeybers(){
		
		if(isBorder == false) {
			return emptyNeybers;
			
		}else {
			return neybers;
		}
	}public void checkNeybers() {
		boolean isABorder = false;
		for(Tile t : neybers) {
			if(t.nation.nationNum != nation.nationNum) {
			isABorder = true;
			t.isBorder = true;
			}
		}
		isBorder = isABorder;
	}public void setNation(Nation nation) {
		this.nation = nation;
		checkNeybers();
		
	}

}
