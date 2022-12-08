
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
			Color.ORANGE, Color.PINK, Color.RED, Color.DARK_GRAY };

	int size;
	Random random = new Random();

	JFrame stats = new JFrame();
	JLabel statsLabel = new JLabel();

	WorldManager() {
		// interesting test, apparently it returns out of bounds error
		// int[] test = {1,2,3,4,5,};
		// System.out.println(test.length);
		// System.out.println(test[5]);

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
		int playerX = 123;
		int playerY = 98;

		for (int i = playerX; i < playerX + 2; i++) {
			for (int e = playerY; e < playerY + 2; e++) {
				tileArray[i][e].nation = player;
			}
		}

		for (int i = 0; i < 7; i++) {
			Nation nation = new Nation(0, colorSelect[i], i + 3);
			bots.add(nation);
			System.out.println(nation); // make bots have spawning places surrounding the player

			/*
			 * for (int ii = 10 * i; ii < 10 * i + 3; ii++) {
			 * 
			 * for (int iii = 10 * i; iii < 10 * i + 3; iii++) {
			 * 
			 * tileArray[ii][iii].nation = nation; } }
			 */

		}
		// setting up bot #1 land

		for (int k = playerX; k < playerX + 2; k++) {
			for (int j = playerY - 50; j < playerY - 48; j++) {
				tileArray[k][j].nation = bots.get(0);
			}
		}
		// setting up bot #2 land
		for (int k = playerX; k < playerX + 2; k++) {
			for (int j = playerY + 50; j < playerY + 52; j++) {
				tileArray[k][j].nation = bots.get(1);
			}
		}
		// setting up bot #3 land
		for (int k = playerX - 50; k < playerX - 48; k++) {
			for (int j = playerY; j < playerY + 2; j++) {
				tileArray[k][j].nation = bots.get(2);
			}
		}
		// setting up bot #4 land
		for (int k = playerX + 50; k < playerX + 52; k++) {
			for (int j = playerY; j < playerY + 2; j++) {
				tileArray[k][j].nation = bots.get(3);
			}
		}
		// bot 5
		for (int k = playerX + 50; k < playerX + 52; k++) {
			for (int j = playerY - 50; j < playerY - 48; j++) {
				tileArray[k][j].nation = bots.get(4);
			}
		}
		// bot 6
		for (int k = playerX + 50; k < playerX + 52; k++) {
			for (int j = playerY + 50; j < playerY + 52; j++) {
				tileArray[k][j].nation = bots.get(5);
			}
		}
		// bot 7
		for (int k = playerX + 50; k < playerX + 52; k++) {
			for (int j = playerY - 50; j < playerY - 48; j++) {
				tileArray[k][j].nation = bots.get(6);
			}
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

		if (e.getSource() == GamePanel.updateTroops) {
			player.updateTroops();
			for (int i = 0; i < bots.size(); i++) {
				bots.get(i).updateTroops();
			}
			Nation.aiActions();
		} else if (e.getSource() == GamePanel.attackTimer) {
			player.contAttack();
			for (int i = 0; i < bots.size(); i++) {
				bots.get(i).contAttack();
			}
		}else if(e.getSource() == GamePanel.endTimer) {
			int time = 300;
			if(time < 300) {
				time++;
			}else {
				GamePanel.currentState = GamePanel.END;
				
			}
		}
	}
}
