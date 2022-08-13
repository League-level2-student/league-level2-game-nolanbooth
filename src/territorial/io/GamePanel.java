package territorial.io;

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
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	Timer frameDraw;
	int currentState;
	Graphics g;
	Font titleFont;
	Font regFont;
	Font massFont;
	WorldManager manager = new WorldManager();

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
	}

	void drawGameState(Graphics g) {
		g.setColor(new Color(130, 138, 58));
		g.fillRect(0, 0, Territorial_Runner.WIDTH, Territorial_Runner.HEIGHT);

		manager.draw(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(new Color(0, 0, 10));
		g.fillRect(0, 0, Territorial_Runner.WIDTH, Territorial_Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(new Color(110, 7, 7));
		g.drawString("GAME OVER", Territorial_Runner.WIDTH / 3, 200);

	}

	void updateMenuState() {

	}

	void updateGameState() {

	}

	void updateEndState() {

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
				currentState = GAME;
			} else if (currentState == END) {
				System.out.println("end to menu");
				currentState = MENU;
			} else {
				currentState++;
				System.out.println("current state added");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
