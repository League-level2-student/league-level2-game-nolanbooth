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
		player = new Nation(10, colorSelect[7], 1);
		test = new Nation(10, colorSelect[8], 2);

		none = new Nation(0, Color.GRAY, 0);
		none.numberOfPixels = 50000;
		none.troops = none.numberOfPixels * 2;

		//System.out.println(none.numberOfPixels);
		//System.out.println(none.troops);

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
	//	tileArray[125][100].nation = player;
		for(int i = 123; i<125; i++){
			for(int e = 98; e<100; e++) {
				tileArray[i][e].nation = player;
			}
		}
		// tileArray[50][50].nation = test;
		for(int i = 119; i <122; i++) {
			for(int e = 100; e<103; e++) {
				tileArray[i][e].nation = test;
			}
		}
	}

	//void update() {

	//}

	void draw(Graphics g) {
		for (Tile[] t : tileArray) {
			for (Tile tile : t) {
				tile.draw(g);
			}

		}
		Territorial_Runner.frame.setTitle("Player Troops = " + player.troops + " None Troops = " + none.troops + " None Tpp = " + none.troopsPerPixel);

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
	
	//System.out.println("timer");
		test.attack(player);
	if (test.troops < test.numberOfPixels * 150) {
			test.troops = (int) (test.troops * 1.1);
		} else {
			test.troops = test.numberOfPixels * 150;
		}
		if(lazyTimer < 3) {
			lazyTimer++;
		}else {
			test.troops = test.troops + test.numberOfPixels;
			player.troops = player.troops + player.numberOfPixels;
			lazyTimer = 0;
		}
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

		

		if (player.troops < player.numberOfPixels * 150) {
			player.troops = (int) (player.troops * 1.1);
		} else {
			player.troops = player.numberOfPixels * 150;
		}
		
		if(player.troops > player.numberOfPixels * 150) {
			player.troops = player.numberOfPixels * 150;
		}
	}
}
