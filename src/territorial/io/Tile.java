package territorial.io;
import java.awt.Color;
import java.awt.Graphics;

public class Tile {
int x;
int y;

	public Tile(int x, int y /* insert nation variable */) {
		this.x = x;
		this.y = y;
		
	}
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 5, 5);
	}
	
}
