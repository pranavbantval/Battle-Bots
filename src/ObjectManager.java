import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	// member variables
	ArrayList<Robotron> robotrons = new ArrayList<Robotron>();
	ArrayList<IronFist> ironfists = new ArrayList<IronFist>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Boss> bosses = new ArrayList<Boss>();
	long enemyTimer = 0;
	int enemySpawnTime = 2000;
	int usermoney = 100;
	int rPrice = 15;
	int iPrice = 40;
	int bounty = 20;
	
	int playerbase=100;
	int enemybase=100;
int bossHP = 200;
int slowly = 1;
int slowness=2;
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
		/*LOOK HERE FOR GOALS
		 * 1.test money system slowness/slowly, bounty
		 * 2.Create winner screen
		 * 3.add usermoney to the game so the number is displayed on the screen
		 * 4.add the base healths to the game so the numbers are displayed on the screen
		 * 5.add images
		 * 6.play/test
		 */
		System.out.println(usermoney);
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
		if(slowly%slowness==0) {
		usermoney++;
		}
		if(usermoney>=1000) {
			slowness=4;
		}
		else if(usermoney<=750) {
			slowness=2;
		}
	}

	public void addRobotron(int x, int y, int width, int height) {
		if (usermoney - rPrice >= 0) {
			usermoney = usermoney - rPrice;
			robotrons.add(new Robotron(x, y, width, height));
		} else {
			System.out.println("bankrupt!");
		}
	}

	public void addIronFist(int x, int y, int width, int height) {
		if (usermoney - iPrice >= 0) {
			usermoney = usermoney - iPrice;
			ironfists.add(new IronFist(x, y, width, height));

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
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {

			enemies.add(new Enemy(100, 370, 30, 30));
			enemies.add(new Enemy(50, 370, 30, 30));
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
				usermoney=usermoney+100000;
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
					ironfist.isAlive = false;
					enemy.isAlive = false;
				}
				if (enemy.collisionBox.intersects(ironfist.collisionBox)) {
					enemy.isAlive = false;
					ironfist.isAlive = false;
				}
			}
		}
	}

	public void bossCollision() {
		for (Boss boss : bosses) {
			for (Robotron robotron : robotrons) {
				if (robotron.collisionBox.intersects(boss.collisionBox)) {
					robotron.isAlive=false;
					bossHP=bossHP-1;
				}
			}
			for (IronFist ironfist : ironfists) {
				if (ironfist.collisionBox.intersects(boss.collisionBox)) {
					ironfist.isAlive=false;
					bossHP=bossHP-3;
				}
			}
			if(bossHP<=0) {
				boss.isAlive=false;
			}
		}
	}

	public void BaseDamage() {
		for (Enemy enemy : enemies) {
			if (enemy.x > 770) {
				playerbase--;
				enemy.isAlive = false;
			}
		}
		for (IronFist ironfist : ironfists) {
			if (ironfist.x < 200) {
				ironfist.isAlive = false;
				enemybase=enemybase-2;
			}
		}
		for (Robotron robotron : robotrons) {
			if (robotron.x < 200) {
				robotron.isAlive = false;
				enemybase=enemybase-1;
			}
		}
		for (Boss boss : bosses) {
			if (boss.x > 650) {
				playerbase=0;
			}
		}

	}
	public void spawnBoss() {
		if(enemybase<=50) {
			addBoss(100, 200, 150, 200);
		}
	}

	public void PurgeAll() {
		for (int i = 0; i < robotrons.size(); i++) {

			robotrons.remove(i);

		}
		for (int j = 0; j < enemies.size(); j++) {

			enemies.remove(j);

		}
		for (int k = 0; k < ironfists.size(); k++) {

			ironfists.remove(k);

		}
	}
}
