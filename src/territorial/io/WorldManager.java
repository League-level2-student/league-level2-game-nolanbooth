package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class WorldManager {
	// NOTE! countries can be every color except GRAY AND BLUE.
	Nation none;
	Nation player;

	static Tile[][] tileArray = new Tile[250][200];
	Color[] colorSelect = { Color.BLACK,  Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.LIGHT_GRAY,
			Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW };
	int size;
	Random random = new Random();

	WorldManager() {
		player = new Nation(100, colorSelect[7]);
		
		none = new Nation(0, Color.GRAY);
		none.numberOfPixels = 50000;
		none.troops = none.numberOfPixels * 2;
		
		System.out.println(none.numberOfPixels);
		System.out.println(none.troops);
	
		for (int i = 0; i < tileArray.length; i++) {
			for (int k = 0; k < tileArray[i].length; k++) {
				tileArray[i][k] = new Tile(i * 4, k * 4, none);
			}
		}

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
