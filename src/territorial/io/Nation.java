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
	ArrayList<Attack> contAttacks = new ArrayList<Attack>();
	static ArrayList<Nation> neyberingNations;

	public Nation(int troops, Color color, int nationNum) {
		this.troops = troops;
		this.color = color;
		this.id = nationNum;
	}

	int attack(Nation target, int strength) {
		if (target == this)
			return strength;
		
		ArrayList<Tile> ownTiles = WorldManager.getNationTiles(this);

		numberOfPixels = WorldManager.getNationTiles(this).size();

		if (numberOfPixels > 0) {
			troopsPerPixel = troops / numberOfPixels;

		}
		if (target.numberOfPixels > 0) {
			target.numberOfPixels = WorldManager.getNationTiles(target).size();
			target.troopsPerPixel = target.troops / target.numberOfPixels;
		}
		bill: for (Tile t : ownTiles) {

			for (Tile tile : t.getNeybers()) {

				if (tile.nation.id == target.id) {

					if (strength <= 0) {
						break bill;
					} else {
						strength = strength - target.troopsPerPixel;
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
		if (troops <= 0) {
			troops = 0;
		}
		if (target.troops <= 0) {
			target.troops = 0;
		}
		if (strength >= 0) {

			boolean attackExists = false;
			for (int i = 0; i < contAttacks.size(); i++) {
				if (contAttacks.get(i).target == target) {
					attackExists = true;

				}
			}
			if (!attackExists) {
				contAttacks.add(new Attack(target, this, strength));
			}
		}
		return strength;
	}

	void contAttack() {
		for (int i = 0; i < contAttacks.size(); i++) {
			if (contAttacks.get(i).strength > 0) {
				contAttacks.get(i).strength = attack(contAttacks.get(i).target, contAttacks.get(i).strength);
				
			} else {
				contAttacks.remove(i);
				i -= 1;
			}
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
			if (troops <= 0) {
				troops = 0;
			}
		}
		if (troops > numberOfPixels * 150) {
			troops = numberOfPixels * 150;
		}

		if (lazyTimer < 3) {
			lazyTimer++;
		} else {
			if (troops >= numberOfPixels * 150) {
				return;
			} else {
				troops = troops + numberOfPixels;
				lazyTimer = 0;
			}
		}

	}

	static void aiActions() {

		// System.out.print(ran.nextInt(10));
		if (ran.nextInt(4) % 2 == 0) {
			for (int i = /*
							 * once upon a time there was a boy named bob who was a really bad boy. Once he
							 * was playing tag on the street and then SQUASH
							 */0; i < bots.size(); i++) {

				// bots.get(i).attack(bots.get(neyberingNations.get(0).id)); figure out way to
				// make the nation attack their neybers

			}
		} else {
			for (int j = 0; j < bots.size(); j++) {
				if (ran.nextInt(2) == 0) {
					bots.get(j).attack(WorldManager.player, bots.get(j).troops);

				} else {
					bots.get(j).attack(WorldManager.none, bots.get(j).troops);
				}
			}
		}
	}

}
