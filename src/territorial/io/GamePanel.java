package territorial.io;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	static final int MENU = 0;
	static final int GAME = 1;
	static final int END = 2;
	Timer frameDraw;
	static Timer endTimer;
	static Timer attackTimer;
	static int currentState;
	Graphics g;
	Font titleFont;
	Font regFont;
	Font massFont;
	static WorldManager manager = new WorldManager();
	static Timer updateTroops;
	static double armyPercent = 0.5;
	int barPercent = 50;

//	static Timer aiAttack;

	public GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		titleFont = new Font("Arial", Font.ITALIC, 48);
		regFont = new Font("Arial", Font.PLAIN, 20);
		massFont = new Font("Arial", Font.PLAIN, 125);

	}

	void drawMenuState(Graphics g) {
		g.setColor(new Color(12, 122, 14));
		g.fillRect(0, 0, Territorial_Runner.WIDTH, Territorial_Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(new Color(255, 255, 255));
		g.drawString("TERRITORIAL.IO", Territorial_Runner.WIDTH / 3 - 60, 400);
		g.setFont(massFont);
		g.setColor(new Color(116, 204, 118));
		g.drawString("TERRITORIAL.IO", 0, 350);
		g.setFont(regFont);
		g.setColor(new Color(255, 255, 255));
		g.drawString("P R E S S   E N T E R   TO   S T A R T", 300, 500);
		g.drawString("P R E S S  I   F O R   I N S T R U C T I O N S", 270, 600);

	}

	void drawGameState(Graphics g) {
		g.setColor(new Color(130, 138, 58));
		g.fillRect(0, 0, Territorial_Runner.WIDTH, Territorial_Runner.HEIGHT);
		manager.draw(g);
		drawPercentBar(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(new Color(0, 0, 10));
		g.fillRect(0, 0, Territorial_Runner.WIDTH, Territorial_Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(new Color(110, 7, 7));
		g.drawString("GAME OVER", Territorial_Runner.WIDTH / 3, 200);

	}

	void drawPercentBar(Graphics g) {
		g.setColor(new Color(105, 25, 91));
		g.fillRect(0, 830, (int) (Territorial_Runner.WIDTH * (barPercent / 100.0)), 35);
	}

	void updateMenuState() {

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	static void startTimer() {
		updateTroops = new Timer(1000, manager);
		updateTroops.start();
		attackTimer = new Timer(250, manager);
		attackTimer.start();
		endTimer = new Timer(2000, manager);
		endTimer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);

		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		// System.out.println("action");
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent cars) {
		// TODO Auto-generated method stub
		if (cars.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("cars are better than trains");
			if (currentState == MENU) {
				System.out.println("menu to game");
				startTimer();
				currentState = GAME;
			} else if (currentState == END) {
				System.out.println("end to menu");
				currentState = MENU;
			} else {
				currentState++;
				System.out.println("current state added");
			}
		}
		/*
		 * if (cars.getKeyCode() == KeyEvent.VK_SPACE) { //
		 * System.out.println("space button detected - attack"); int attackPower =
		 * (int)(manager.player.troops * (barPercent/100));
		 * manager.player.attack(manager.none, attackPower); WorldManager.player.troops
		 * -= attackPower; }
		 */
		if (cars.getKeyCode() == KeyEvent.VK_I) {
			JOptionPane.showMessageDialog(null,
					"Click the unclaimed land to expand. To attack nations, click on their territory. Your troop count is shown in"
					+ "the top bar. Enemy troops are shown in the window to the left. ");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		// System.out.println("mouse pressed");
		int mx = mouse.getX();
		int my = mouse.getY() - 25;
		if (mouse.getY() < 824) {
			Nation target = WorldManager.tileArray[mx / Tile.size][my / Tile.size].nation;
			int attackPower = (int) (WorldManager.player.troops * (barPercent / 100.0));
			WorldManager.player.attack(target, attackPower);
			WorldManager.player.troops -= attackPower;

		} else {
			barPercent = (int) (mx / (Territorial_Runner.WIDTH / 100.0));
			System.out.println(barPercent);

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
