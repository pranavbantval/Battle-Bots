import java.awt.Color;
import java.awt.Graphics;
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
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INSTRUCTIONS = 3;
	int current_state = 0;

	// constructor
	public GamePanel() {
		timer = new Timer(1000 / 60, this);

	}

	// methods
	public void startGame() {
		timer.start();

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
		// g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("BATTLE BOTS", 20, 200);
		// g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 120, 350);
		g.drawString("Press SPACE for instructions", 120, 450);
	}

	public void drawInstructions(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.drawString("LEFT-CLICK on robot buttons and upgrade button to buy them", 120, 350);
		g.drawString("Press SPACE to return", 120, 450);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(800, 30, 100, 400);
		g.fillRect(100, 30, 100, 400);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
	}

	public void paintComponent(Graphics g) {
		if (current_state == MENU_STATE) {
			drawMenuState(g);
		} else if (current_state == GAME_STATE) {
			drawGameState(g);
		} else if (current_state == END_STATE) {
			drawEndState(g);
		}
	}

	// mouse event methods & action listener
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (current_state == END_STATE) {
			current_state = MENU_STATE;

		} else {
			current_state++;
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (current_state == MENU_STATE) {
			updateMenuState();
		} else if (current_state == GAME_STATE) {
			updateGameState();
		} else if (current_state == END_STATE) {
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
			if (current_state == END_STATE) {
				current_state = MENU_STATE;

			} else {
				current_state++;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (current_state == INSTRUCTIONS) {
				current_state = MENU_STATE;
			} else {
				current_state = INSTRUCTIONS;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
