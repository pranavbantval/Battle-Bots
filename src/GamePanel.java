import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	// member variables
	Timer timer;
	int currentState = 0;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INSTRUCTIONS = 3;
	final int WINNER = -1;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 48);
	Font subtitleFont = new Font("Times New Roman", Font.PLAIN, 25);
	ObjectManager sam = new ObjectManager();

	// constructor
	public GamePanel() {
		timer = new Timer(1000 / 60, this);

	}

	// methods
	public void startGame() {
		timer.start();

	}

	public void endGame() {
		if (sam.playerbase == 0) {
			sam.PurgeAll();
			currentState = END_STATE;
		} else if (sam.enemybase <= 0) {
			currentState = WINNER;
		}

	}
	// draw/update state methods

	public void updateMenuState() {

	}

	public void updateGameState() {

		sam.manageEnemies();
		sam.BaseDamage();
		sam.spawnBoss();
		sam.checkCollision();
		sam.bossCollision();
		sam.purgeObjects();
		sam.update();
		sam.addMoney();
		endGame();
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

		g.drawString("Press SPACE to return", 370, 300);
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
		g.setFont(subtitleFont);
		g.setColor(Color.black);
		g.drawString("$" + String.valueOf(sam.usermoney), 900, 20);
		g.drawString(String.valueOf(sam.enemybase) + "/150", 100, 175);
		g.drawString(String.valueOf(sam.playerbase) + "/100", 800, 175);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("YOU LOSE", 370, 250);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to try again", 370, 300);
	}

	public void drawWinnerState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("WOW, YOU WON :D", 330, 250);
		g.drawString("THANKS FOR PLAYING.", 250, 300);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to return to menu", 360, 350);

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
		} else if (currentState == WINNER) {
			drawWinnerState(g);
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

				sam.addRobotron(750, 375, 25, 25);

			} else if (e.getKeyCode() == KeyEvent.VK_2) {

				sam.addIronFist(750, 325, 50, 75);

			}

			else if (e.getKeyCode() == KeyEvent.VK_4) {
				sam.addEnemy(100, 370, 30, 30);
			} else if (e.getKeyCode() == KeyEvent.VK_7) {
				sam.addBoss(100, 200, 150, 200);
			}
		}

		if (currentState >= 3) {
			currentState = 0;
		} else {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (currentState == END_STATE) {

					currentState = MENU_STATE;

				} else if (currentState == GAME_STATE || currentState == MENU_STATE || currentState == WINNER) {
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

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
