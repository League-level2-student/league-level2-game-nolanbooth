package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class WorldManager implements ActionListener {
	// NOTE! countries can be every color except GRAY AND BLUE.
	static Nation none;
	static Nation player;
	static Nation test;
	static int lazyTimer = 0;
	static int timer = 0;
	static Tile[][] tileArray = new Tile[250][200];
	Color[] colorSelect = { Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW };
	int size;
	Random random = new Random();

	WorldManager() {
		player = new Nation(100, colorSelect[7], 1);
		test = new Nation(100, colorSelect[8], 2);

		none = new Nation(0, Color.GRAY, 0);
		none.numberOfPixels = 50000;
		none.troops = none.numberOfPixels * 2;

		System.out.println(none.numberOfPixels);
		System.out.println(none.troops);

		for (int i = 0; i < tileArray.length; i++) {
			for (int k = 0; k < tileArray[i].length; k++) {
				tileArray[i][k] = new Tile(i, k, none);
			}
		}
		for (int i = 0; i < tileArray.length; i++) {
			for (int e = 0; e < tileArray[i].length; e++) {
				tileArray[i][e].calculateNeybers();
			}
		}
		tileArray[100][100].nation = player;
		tileArray[50][50].nation = test;
	}

	//void update() {

	//}

	void draw(Graphics g) {
		for (Tile[] t : tileArray) {
			for (Tile tile : t) {
				tile.draw(g);
			}

		}
		//Territorial_Runner.frame.setTitle("Target = " + target);

	}

	public static ArrayList<Tile> getNationTiles(Nation nation) {
		ArrayList<Tile> tiles = new ArrayList<>();
		for (int i = 0; i < tileArray.length; i++) {
			for (int k = 0; k < tileArray[i].length; k++) {
				if (tileArray[i][k].nation == nation) {
					tiles.add(tileArray[i][k]);
				}
			}
		}
		return tiles;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(timer <= 1000) {
		timer++;
		
		}else {
		test.troops += test.numberOfPixels;
		if (lazyTimer <= 2) {
			lazyTimer++;
		} else {
			test.attack(player);
			lazyTimer = 0;
		}
		if (test.troops < test.numberOfPixels * 150) {
			test.troops = (int) (test.troops * 1.1);
		} else {
			test.troops = test.numberOfPixels * 150;
		}

		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

		player.troops += player.numberOfPixels;

		if (player.troops < player.numberOfPixels * 150) {
			player.troops = (int) (player.troops * 1.1);
		} else {
			player.troops = player.numberOfPixels * 150;
		}
timer = 0;
	}
	}
}
