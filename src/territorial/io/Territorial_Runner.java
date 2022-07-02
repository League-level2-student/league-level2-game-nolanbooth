package territorial.io;

import javax.swing.JFrame;



public class Territorial_Runner {
	JFrame frame;
	GamePanel gamepanel;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	public static void main(String[]args) {
		Territorial_Runner terry = new Territorial_Runner();
		terry.setup();
	}
	public Territorial_Runner() {
		frame = new JFrame();
		gamepanel = new GamePanel();
	}
	
	void setup(){
		frame.add(gamepanel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
