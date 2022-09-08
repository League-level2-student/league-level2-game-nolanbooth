package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Nation {
	int x;
	int y;
	int troops;
	Color color;
	int numberOfPixels;
	int nationNum;

	public Nation(int troops, Color color, int nationNum) {
		this.troops = troops;
		this.color = color;
		this.nationNum = nationNum;
	}

	void attack(Nation target) {
		ArrayList<Tile> ownTiles = WorldManager.getNationTiles(this);
		System.out.println(ownTiles.size());
		for (Tile t : ownTiles) {
			
			for (Tile tile : t.getNeybers()) {
				System.out.println("Neybers have been got");

				if (tile.nation.nationNum == target.nationNum) {
					tile.setNation(this);
					System.out.println("if statement reached!");
					
					// changing ownership of tiles and subtracting troops from both sides
				}
			}

		}

	}

	void update() {

	}
}
