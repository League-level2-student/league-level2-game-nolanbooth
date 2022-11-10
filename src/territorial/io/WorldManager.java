package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WorldManager implements ActionListener {
	// NOTE! countries can be every color except GRAY AND BLUE.
	static Nation none;
	static Nation player;
	static int timer = 0;
	static Tile[][] tileArray = new Tile[250][200];
	static ArrayList<Nation> bots = new ArrayList<Nation>();
	Color[] colorSelect = { Color.BLACK, Color.CYAN, Color.PINK, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.RED, Color.DARK_GRAY};
	
	
	int size;
	Random random = new Random();

	JFrame stats = new JFrame();
	JLabel statsLabel = new JLabel();

	WorldManager() {
		//interesting test, apparently it returns out of bounds error
		//int[] test = {1,2,3,4,5,};
		//System.out.println(test.length);
		//System.out.println(test[5]);
		
		intialStats();
		player = new Nation(0, colorSelect[9], 1); // player is pink

		none = new Nation(0, Color.GRAY, 0);
		none.numberOfPixels = 50000;
		none.troops = none.numberOfPixels * 2;

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

		for (int i = 123; i < 125; i++) {
			for (int e = 98; e < 100; e++) {
				tileArray[i][e].nation = player;
			}
		}

		for (int i = 0; i < 7; i++) {
			Nation nation = new Nation(0, colorSelect[i], i + 3);
			bots.add(nation);
			System.out.println(nation); //make bots have spawning places surrounding the player

			/*for (int ii = 10 * i; ii < 10 * i + 3; ii++) {

				for (int iii = 10 * i; iii < 10 * i + 3; iii++) {

					tileArray[ii][iii].nation = nation;
				}
			}*/
			

		}

	}
	
	void draw(Graphics g) {
		for (Tile[] t : tileArray) {
			for (Tile tile : t) {
				tile.draw(g);
			}

		}
		Territorial_Runner.frame.setTitle("Player Troops = " + player.troops);
		drawStats();
	}

	void intialStats() {
		stats.setVisible(true);
		stats.setSize(400, 600);
		stats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stats.add(statsLabel);

		// stats.pack();

	}

	void drawStats() {
		String botStats = "<html>";
		for (int i = 0; i < bots.size(); i++) {
			botStats += " bot " + i + " troops = " + bots.get(i).troops + " <br> ";
		}
		statsLabel.setText(botStats);
		stats.repaint();
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
		player.updateTroops();
		for (int i = 0; i < bots.size(); i++) {
			bots.get(i).updateTroops();
		}
		Nation.aiActions();
	}
}
