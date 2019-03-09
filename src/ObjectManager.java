import java.applet.AudioClip;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JApplet;

public class ObjectManager {
	// member variables
	ArrayList<Robotron> robotrons = new ArrayList<Robotron>();
	ArrayList<IronFist> ironfists = new ArrayList<IronFist>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Boss> bosses = new ArrayList<Boss>();
	long enemyTimer = 0;
	int enemySpawnTime = 2000;
	int usermoney = 1000;
	int rPrice = 40;
	int iPrice = 80;
	int bounty = 60;
	Random enemyRoll = new Random();
	int playerbase = 150;
	int enemybase = 200;
	int bossHP = 120;
	int slowly = 1;
	int slowness = 1;
	int one = 0;
	int enemys;

	// constructor
	public ObjectManager() {

	}

	// methods
	public void update() {

		for (Robotron robotron : robotrons) {
			robotron.update();

		}
		for (IronFist ironfist : ironfists) {
			ironfist.update();

		}
		for (Enemy enemy : enemies) {
			enemy.update();
		}

		for (Boss boss : bosses) {
			boss.update();
		}

	}

	public void draw(Graphics g) {

		for (IronFist ironfist : ironfists) {
			ironfist.draw(g);
		}

		for (Robotron robotron : robotrons) {
			robotron.draw(g);
		}

		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
		for (Boss boss : bosses) {
			boss.draw(g);
		}

	}

	public void addMoney() {

		slowly++;
		if (slowly % slowness == 0) {
			usermoney++;
		}
		if (usermoney >= 10000) {
			slowness = 10;
		} else if (usermoney <= 9000) {
			slowness = 2;
		}
		// System.out.println(usermoney);
	}

	public void addRobotron(int x, int y, int width, int height) {
		if (usermoney - rPrice >= 0) {
			usermoney = usermoney - rPrice;
			robotrons.add(new Robotron(x, y, width, height));
		} else {
			System.out.println("bankrupt!");
		}
	}

	public void addIronFist(int x, int y, int width, int height,int hp) {
		if (usermoney - iPrice >= 0) {
			usermoney = usermoney - iPrice;
			ironfists.add(new IronFist(x, y, width, height,hp));

		} else {
			System.out.println("bankrupt!");
		}
	}

	public void addEnemy(int x, int y, int width, int height) {
		enemies.add(new Enemy(x, y, width, height));
	}

	public void addBoss(int x, int y, int width, int height) {
		bosses.add(new Boss(x, y, width, height));
	}

	public void manageEnemies() {
		if(one==0) {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			enemys = enemyRoll.nextInt(5) + 1;
			for (int i = 0; i < enemys; i++) {
				if (i == 0) {
					enemies.add(new Enemy(100, 370, 100, 100));
				} else if (i == 1) {
					enemies.add(new Enemy(50, 370, 100, 100));
				} else if (i == 2) {
					enemies.add(new Enemy(150, 370, 100, 100));
				}
				else if (i == 3) {
					enemies.add(new Enemy(200, 370, 100, 100));
				}
				else if (i == 4) {
					enemies.add(new Enemy(250, 370, 100, 100));
				}
			}

			enemyTimer = System.currentTimeMillis();
		}
		}
		
		else if (one==1) {
			if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
				enemys = enemyRoll.nextInt(5) + 1;
				for (int i = 0; i < enemys; i++) {
					if (i == 0) {
						enemies.add(new Enemy(100, 370, 100, 100));
					} else if (i == 1) {
						enemies.add(new Enemy(50, 370, 100, 100));
					} else if (i == 2) {
						enemies.add(new Enemy(150, 370, 100, 100));
					}
		}
			
	}
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void purgeObjects() {

		for (int i = 0; i < robotrons.size(); i++) {

			if (!(robotrons.get(i).isAlive)) {
				robotrons.remove(i);
				// System.out.println(robotrons.size());

			}
		}
		for (int j = 0; j < enemies.size(); j++) {

			if ((!enemies.get(j).isAlive)) {
				usermoney = usermoney + bounty;
				enemies.remove(j);
				// System.out.println(enemies.size());

			}
		}
		for (int k = 0; k < ironfists.size(); k++) {

			if (!(ironfists.get(k).isAlive)) {
				ironfists.remove(k);
				// System.out.println(ironfists.size());

			}
		}
		for (int k = 0; k < bosses.size(); k++) {

			if (!(bosses.get(k).isAlive)) {
				usermoney = usermoney + 100000;
				bosses.remove(k);

			}
		}
	}

	public void checkCollision() {
		for (Enemy enemy : enemies) {
			for (Robotron robotron : robotrons) {
				if (robotron.collisionBox.intersects(enemy.collisionBox)) {
					robotron.isAlive = false;
					enemy.isAlive = false;
				}
				if (enemy.collisionBox.intersects(robotron.collisionBox)) {
					enemy.isAlive = false;
					robotron.isAlive = false;
				}
			}
			for (IronFist ironfist : ironfists) {
				if (ironfist.collisionBox.intersects(enemy.collisionBox)) {
					ironfist.hp=ironfist.hp-1;
					if(ironfist.hp<=0) {
						ironfist.isAlive=false;
					}
					enemy.isAlive = false;
				}
				if (enemy.collisionBox.intersects(ironfist.collisionBox)) {
					enemy.isAlive = false;
					ironfist.hp=ironfist.hp-1;
					if(ironfist.hp<=0) {
						ironfist.isAlive=false;
					}
				}
			}
		}
	}

	public void bossCollision() {
		for (Boss boss : bosses) {
			for (Robotron robotron : robotrons) {
				if (robotron.collisionBox.intersects(boss.collisionBox)) {
					robotron.isAlive = false;
					bossHP = bossHP - 2;
				}
			}
			for (IronFist ironfist : ironfists) {
				if (ironfist.collisionBox.intersects(boss.collisionBox)) {
					ironfist.isAlive = false;
					bossHP = bossHP - 3;
				}
			}
			if (bossHP <= 0) {
				boss.isAlive = false;
			}
		}
	}

	public void BaseDamage() {
		for (Enemy enemy : enemies) {
			if (enemy.x > 770) {
				playerbase = playerbase - 4;
				playSound("OW.wav");
				enemy.isAlive = false;
				usermoney = usermoney - bounty;
			}
		}
		for (IronFist ironfist : ironfists) {
			if (ironfist.x < 200) {
				ironfist.isAlive = false;
				enemybase = enemybase - 3;

			}
		}
		for (Robotron robotron : robotrons) {
			if (robotron.x < 200) {
				robotron.isAlive = false;
				enemybase = enemybase - 2;
			}
		}
		for (Boss boss : bosses) {
			if (boss.x > 650) {
				playerbase = 0;
			}
		}
		// System.out.println(enemybase);
	}

	public void spawnBoss() {
		if (enemybase <= 150) {
			if (bosses.size() <= 0) {
				if (one == 0) {
					addBoss(200, 300, 200, 200);
					one++;
				}
			}
		}
	}

	public void PurgeAll() {
		for (Boss boss : bosses) {
			boss.isAlive = false;
		}
		for (Enemy enemy : enemies) {
			enemy.isAlive = false;
		}
		for (Robotron robotron : robotrons) {
			robotron.isAlive = false;
		}
		for (IronFist ironfist : ironfists) {
			ironfist.isAlive = false;
		}
		purgeObjects();
		playerbase = 150;
		enemybase = 200;
		slowly = 1;
		slowness = 1;
		usermoney = 1000;
		bossHP =120;
		one = 0;
	}

	public void playSound(String fileName) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
	}
}
