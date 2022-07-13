package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class WorldManager {
	Nation nation;
	Tile[][] tileArray = new Tile[200][160];
	Color[] colorSelect = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
			Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW };
	int size;
	Random random = new Random();

	WorldManager(Nation nation) {
		this.nation = nation;
	}

	void update() {

	}

	void draw(Graphics g) {
		for (Tile[] t : tileArray) {
			for (Tile tile : t) {
				tile.draw(g);
			}

		}
	}

}
