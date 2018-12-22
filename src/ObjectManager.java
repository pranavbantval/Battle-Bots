import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	// member variables
	ArrayList<Robotron> robotrons = new ArrayList<Robotron>();
	ArrayList<IronFist> ironfists = new ArrayList<IronFist>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	long enemyTimer = 0;
	int enemySpawnTime = 2000;
	int usermoney = 100;
	int rPrice = 15;
	int iPrice = 40;
	int bounty = 0;

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

	}

	public void addMoney() {
		if (usermoney <= 15) {
			usermoney = usermoney + 100;
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

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {

			enemies.add(new Enemy(100, 400, 30, 30));
			enemies.add(new Enemy(50, 400, 30, 30));
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

}
