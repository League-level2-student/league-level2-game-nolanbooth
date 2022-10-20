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
	int troopsPerPixel;
	

	public Nation(int troops, Color color, int nationNum) {
		this.troops = troops;
		this.color = color;
		this.nationNum = nationNum;
	}

	void attack(Nation target) {
		ArrayList<Tile> ownTiles = WorldManager.getNationTiles(this);
		//System.out.println(ownTiles.size());
		numberOfPixels = WorldManager.getNationTiles(this).size();
		
		target.numberOfPixels = WorldManager.getNationTiles(target).size();
		for (Tile t : ownTiles) {
			
			for (Tile tile : t.getNeybers()) {
				//System.out.println("Neybers have been got");

				if (tile.nation.nationNum == target.nationNum) {
					troopsPerPixel = troops / numberOfPixels;
					target.troopsPerPixel = target.troops / target.numberOfPixels;
					if(troops <= 0) {
						System.out.println("No troops left");
						return;
					}else{
						troops = troops - target.troopsPerPixel;
						target.troops = target.troops - target.troopsPerPixel;
					//System.out.println("Troops subtracted");
					}
					tile.setNation(this);
				}
			}

		}
		numberOfPixels = WorldManager.getNationTiles(this).size();
		if(target.numberOfPixels > 0) {
		target.troopsPerPixel = target.troops / target.numberOfPixels;
		}		
		if(numberOfPixels > 0) {
		troopsPerPixel = troops / numberOfPixels;
		}
		
	}static void updateTroops(Nation nation){
		if (nation.troops < nation.numberOfPixels * 150) {
			nation.troops = (int) (nation.troops * 1.1);
		} else {
			nation.troops = nation.numberOfPixels * 150;
		}
		
		if(nation.troops > nation.numberOfPixels * 150) {
			nation.troops = nation.numberOfPixels * 150;
		}
	}
	

	void update() {

	}
}
