import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements MouseListener, ActionListener, KeyListener {
	// member variables
	Timer timer;
	int currentState = 0;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INSTRUCTIONS = 4;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 48);
	Font subtitleFont = new Font("Times New Roman", Font.PLAIN, 25);
	// constructor
	public GamePanel() {
		timer = new Timer(1000 / 60, this);

	}

	// methods
	public void startGame() {
		timer.start();

	}
	
	public void buttons() {
		
		
	}
	// draw/update state methods

	public void updateMenuState() {
	
	}

	
	public void updateGameState() {

	}


	public void updateEndState() {
	
	}


	public void updateInstructions() {
	
	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("BATTLE BOTS", 340, 200);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 375, 300);
		g.drawString("Press SPACE for instructions", 345, 400);
	}

	public void drawInstructions(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setFont(subtitleFont);
		g.setColor(Color.BLACK);
		g.drawString("LEFT-CLICK on a button to buy the item", 290, 200);
		g.drawString("Press SPACE to return", 370, 300);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(800, 180, 100, 300);
		g.fillRect(100, 180, 100, 300);
	g.setColor(Color.WHITE);
	g.fillRect(450, 450, 50, 50);
	g.fillRect(510, 450, 50, 50);
	g.fillRect(350, 450, 50, 50);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		} else if (currentState == INSTRUCTIONS) {
		drawInstructions(g);
		}
	}

	// mouse event methods & action listener
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END_STATE) {
				currentState = MENU_STATE;

			} else {
				currentState++;
				System.out.println(currentState);
				repaint();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == INSTRUCTIONS) {
				currentState = MENU_STATE;
			} else {
				currentState = INSTRUCTIONS;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	
	}

}
