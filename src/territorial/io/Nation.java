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
		target.numberOfPixels = WorldManager.getNationTiles(target).size();
		if (target.numberOfPixels > 0) {

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
			troops = (int) (troops * 1.2);
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

	Nation getWeakestNation() {
		int leastTroops = neyberingNations.get(0).troops;
		Nation weakestNation = neyberingNations.get(0);
		for (int i = 0; i < neyberingNations.size(); i++) {
			if (neyberingNations.get(i).troops < leastTroops) {
				leastTroops = neyberingNations.get(i).troops;
				weakestNation = neyberingNations.get(i);
			}
		}
		return weakestNation;
	}

	static void aiActions() {

		for (int i = 0; i < bots.size(); i++) {
			bots.get(i).getNeybers();
			if (neyberingNations.size() == 0) {

			} else {
				bots.get(i).attack(bots.get(i).getWeakestNation(), (int) (bots.get(i).troops));
				bots.get(i).troops = (int)(bots.get(i).troops);
			}
		}

	}

}
