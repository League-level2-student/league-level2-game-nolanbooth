package territorial.io;

import javax.swing.JFrame;

public class Territorial_Runner {
	public static JFrame frame;
	public static GamePanel gamepanel;
	public static final int WIDTH = 1001;
	public static final int HEIGHT = 900;

	public static void main(String[] args) {
		Territorial_Runner terry = new Territorial_Runner();
		terry.setup();
	}

	public Territorial_Runner() {
		frame = new JFrame();
		gamepanel = new GamePanel();
	}

	void setup() {
		frame.add(gamepanel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(gamepanel);
		frame.addMouseListener(gamepanel);

	}

}