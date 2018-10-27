import java.awt.Dimension;

import javax.swing.JFrame;

public class BattleBots {
	// member variables
	JFrame frame;
	final static int WIDTH = 1000;
	final static int HEIGHT = 500;
	GamePanel gamepanel;

	// constructor
	public BattleBots() {
		frame = new JFrame();
		gamepanel = new GamePanel();
	}

	// methods
	public void setup() {
		frame.add(gamepanel);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamepanel.startGame();
	}

	public void createUI() {
		setup();
		frame.addMouseListener(gamepanel);

	}

	public static void main(String[] args) {
		BattleBots battle = new BattleBots();
		battle.createUI();
	}
}
