package territorial.io;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Nation {
	int x;
	int y;
	int troops;
	Color color;
	int numberOfPixels;
	int id;
	int troopsPerPixel;
	int lazyTimer = 0;
	static Random ran = new Random();
	static ArrayList<Nation> bots = WorldManager.bots;

	static ArrayList<Nation> neyberingNations;

	public Nation(int troops, Color color, int nationNum) {
		this.troops = troops;
		this.color = color;
		this.id = nationNum;
	}

	void attack(Nation target) {
		if (target == this)
			return;

		ArrayList<Tile> ownTiles = WorldManager.getNationTiles(this);

		numberOfPixels = WorldManager.getNationTiles(this).size();

		target.numberOfPixels = WorldManager.getNationTiles(target).size();
		for (Tile t : ownTiles) {

			for (Tile tile : t.getNeybers()) {

				if (tile.nation.id == target.id) {
					troopsPerPixel = troops / numberOfPixels;
					target.troopsPerPixel = target.troops / target.numberOfPixels;
					if (troops <= 0) {
						//System.out.println("No troops left");
						return;
					} else {
						troops = troops - target.troopsPerPixel;
						target.troops = target.troops - target.troopsPerPixel;

					}
					tile.setNation(this);
				}
			}

		}
		numberOfPixels = WorldManager.getNationTiles(this).size();
		if (target.numberOfPixels > 0) {
			target.troopsPerPixel = target.troops / target.numberOfPixels;
		}
		if (numberOfPixels > 0) {
			troopsPerPixel = troops / numberOfPixels;
		}

	}

	void getNeybers() {
		ArrayList<Tile> ownTiles = WorldManager.getNationTiles(this);
		ArrayList<Nation> neybers = new ArrayList<>();

		for (Tile t : ownTiles) {
			for (Tile tile : t.getNeybers()) {
				if (tile.nation.id != id) {
					if (!neybers.contains(tile.nation)) {
						neybers.add(tile.nation);
					}
				}

			}
		}
		this.neyberingNations = neybers;

	}

	void updateTroops() {
		numberOfPixels = WorldManager.getNationTiles(this).size();

		if (troops < numberOfPixels * 150) {
			troops = (int) (troops * 1.5);
			if(troops <= 0) {
				troops = 0;
			}
		}if (troops > numberOfPixels * 150) {
			troops = numberOfPixels * 150;
		}

		if (lazyTimer < 3) {
			lazyTimer++;
		} else {
			if(troops >= numberOfPixels * 150) {
				return;
			}else {
			troops = troops + numberOfPixels;
			lazyTimer = 0;
			}
		}

	}

	static void aiActions() {
	
	//	System.out.print(ran.nextInt(10));
		if(ran.nextInt(4)%2 == 0) {
			for(int i = /*once upon a time there was a boy named bob who was a really bad boy. Once he was playing tag on the street and then SQUASH*/0; i < bots.size(); i++) {
				
				//bots.get(i).attack(bots.get(neyberingNations.get(0).id)); figure out way to make the nation attack their neybers
				
				 
				 
			}
		}else {
			for(int j = 0; j < bots.size(); j++) {
				if(ran.nextInt(2) == 0) {
					bots.get(j).attack(WorldManager.player);
					
				}else {
					bots.get(j).attack(WorldManager.none);
				}
			}
		}
	}

}
