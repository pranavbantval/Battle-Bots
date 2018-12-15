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

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	// member variables
	Timer timer;
	int currentState = 1;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INSTRUCTIONS = 3;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 48);
	Font subtitleFont = new Font("Times New Roman", Font.PLAIN, 25);
ObjectManager sam = new ObjectManager();
Timer ptimer;
// constructor
	public GamePanel() {
		timer = new Timer(1000 / 60, this);
ptimer = new Timer(1000/60, this);
	}

	// methods
	public void startGame() {
		timer.start();

	}

	// draw/update state methods

	public void updateMenuState() {

	}

	public void updateGameState() {

sam.manageEnemies();
sam.checkCollision();
sam.purgeObjects();
sam.update();
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
		g.drawString("Press keys 1 and 2 to buy robots ", 320, 200);
		g.drawString("Press key 3 to upgrade the robots", 320, 300);
		g.drawString("Press SPACE to return", 370, 400);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(800, 180, 100, 300);
		g.fillRect(100, 180, 100, 300);
		g.setColor(Color.WHITE);
		g.fillRect(410, 450, 50, 50);
		g.setColor(Color.black);
		g.fillRect(510, 450, 50, 50);
		g.setColor(Color.green);
		g.fillRect(350, 450, 50, 50);
		sam.draw(g);
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

	// key methods & action listener

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
		if (currentState == GAME_STATE) {
				if (e.getKeyCode() == KeyEvent.VK_1) {
					if(sam.usermoney-sam.rPrice>0) {
					sam.addRobotron(750, 400, 25, 25);
					}
					else {
						System.out.println("bankrupt!");
					}
				} else if (e.getKeyCode() == KeyEvent.VK_2) {
					if(sam.usermoney-sam.iPrice>0) {
					sam.addIronFist(750, 400, 50, 75);
					}
					else {
						System.out.println("bankrupt!");
					}
				} 
				
				  else if(e.getKeyCode()==KeyEvent.VK_4) {
			 sam.addEnemy(100, 400, 30, 30); }
				 
			}
	/*	if (currentState >= 3) {
			currentState = 0;
		} else {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (currentState == END_STATE) {
					currentState = MENU_STATE;

				} else if (currentState == GAME_STATE || currentState== MENU_STATE) {
					currentState++;
					System.out.println(currentState);
					repaint();
				}
			}
			if (currentState == 0 || currentState == 3) {

				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (currentState == INSTRUCTIONS) {
						currentState = MENU_STATE;
					} else {
						currentState = INSTRUCTIONS;
					}
				}
			}
			
		}*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
