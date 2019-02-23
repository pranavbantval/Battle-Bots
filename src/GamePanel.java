import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
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

	public static BufferedImage enemyImg;
	public static BufferedImage robotronImg;
	public static BufferedImage ironbotImg;
	public static BufferedImage enemybossImg;
	public static BufferedImage enemybaseImg;
	public static BufferedImage botbaseImg;
	public static BufferedImage background;

	// constructor
	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		try {
			enemybaseImg = ImageIO.read(this.getClass().getResourceAsStream("1EnemyBase.png"));
			enemyImg = ImageIO.read(this.getClass().getResourceAsStream("1enemy.png"));
			robotronImg = ImageIO.read(this.getClass().getResourceAsStream("1robotron.png"));
			ironbotImg = ImageIO.read(this.getClass().getResourceAsStream("1ironbot.png"));
			enemybossImg = ImageIO.read(this.getClass().getResourceAsStream("1enemy boss.png"));
			botbaseImg = ImageIO.read(this.getClass().getResourceAsStream("1BotBase.png"));
			background = ImageIO.read(this.getClass().getResourceAsStream("Background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// methods
	public void startGame() {
		timer.start();

	}

	public void endGame() {
		if (sam.playerbase == 0) {
			sam.playSound("Failure.wav");
			sam.PurgeAll();
			currentState = END_STATE;
		}

		else if (sam.enemybase <= 0) {
			sam.playSound("Victory.wav");
			sam.PurgeAll();
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
		g.drawString("ROBOT TOWER DEFENSE", 230, 200);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 375, 300);
		g.drawString("Press SPACE for instructions", 345, 400);
	}

	public void drawInstructions(Graphics g) {
		g.setColor(Color.green);

		g.fillRect(0, 0, BattleBots.WIDTH, BattleBots.HEIGHT);
		g.setFont(subtitleFont);
		g.setColor(Color.BLACK);
		g.drawString("Try to defeat the boss", 380, 50);
		g.drawString("BIG CHUNGUS", 400, 100);
		g.drawString("From taking over the world!", 350, 150);
		g.drawString("Press keys 1 and 2 to buy robots ", 320, 200);
g.drawString("Robot 1 costs less money and is faster", 300, 250);

g.drawString("Robot 2 costs more money, but does triple damage to the boss", 190, 300);
		g.drawString("Press SPACE to return", 370, 350);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawImage(background, 0, 0, BattleBots.WIDTH, BattleBots.HEIGHT, null);
		g.setColor(Color.BLUE);
		g.drawImage(botbaseImg, 600, 180, 500, 300, null);
		g.drawImage(enemybaseImg, 100, 160, 150, 300, null);
		sam.draw(g);

		g.setFont(subtitleFont);
		g.setColor(Color.white);
		g.drawString("$" + String.valueOf(sam.usermoney), 880, 30);
		g.drawString(String.valueOf(sam.enemybase) + "/150", 100, 150);
		g.drawString(String.valueOf(sam.playerbase) + "/100", 800, 150);
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

				sam.addRobotron(750, 390, 75, 75);

			} else if (e.getKeyCode() == KeyEvent.VK_2) {

				sam.addIronFist(750, 325, 100, 150);

			}

			else if (e.getKeyCode() == KeyEvent.VK_4) {
				sam.addEnemy(100, 375, 100, 100);
			} else if (e.getKeyCode() == KeyEvent.VK_7) {
				sam.addBoss(100, 290, 200, 200);
			}
		}

		if (currentState >= 3) {
			currentState = 0;
		} else {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (currentState == END_STATE || currentState == WINNER) {
					sam.PurgeAll();
					
					currentState = MENU_STATE;

				} else if (currentState == MENU_STATE) {
					currentState++;
					// System.out.println(currentState);
					repaint();
				} else if (currentState == GAME_STATE) {
					currentState++;
					sam.playSound("Failure.wav");
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


