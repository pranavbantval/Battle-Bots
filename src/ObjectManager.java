import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
//member variables
	ArrayList<Robotron> robotrons = new ArrayList<Robotron>();
	ArrayList<IronFist> ironfists = new ArrayList<IronFist>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	long enemyTimer = 0;
	int enemySpawnTime = 2000;
	//constructor
	public ObjectManager() {
		
	}
	//methods
	public void update() {
		for(Robotron robotron:robotrons) {
			robotron.update();
			for (Enemy enemy : enemies) {
				if(robotron.collisionBox.intersects(enemy.collisionBox)) {
					robotron.isAlive=false;
					enemy.isAlive=false;
				}
			}
		}
		for(IronFist ironfist:ironfists) {
			ironfist.update();
			for (Enemy enemy : enemies) {
				if(ironfist.collisionBox.intersects(enemy.collisionBox)) {
					ironfist.isAlive=false;
					enemy.isAlive=false;
				}
		}
			}
		for (Enemy enemy : enemies) {
		enemy.update();	}
		}	
		public void draw(Graphics g) {

			for(IronFist ironfist : ironfists) {
				ironfist.draw(g);
			}

			for (Robotron robotron : robotrons) {
				robotron.draw(g);
			}

			for (Enemy enemy : enemies) {
				enemy.draw(g);
			}
		
		}
	public	void addRobotron(int x, int y,  int width, int height) {
			robotrons.add(new Robotron(x, y, width, height));
		}
	public	void addIronFist(int x, int y,  int width, int height) {
		ironfists.add(new IronFist(x, y, width, height));
	}
	public	void addEnemy(int x, int y,  int width, int height) {
		enemies.add(new Enemy(x, y, width, height));
	}
	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			enemies.add(new Enemy ( 100,400,30,30));

			enemyTimer = System.currentTimeMillis();
		}
	}
	public void purgeObjects() {
	for (int j = 0; j < enemies.size(); j++) {
		//System.out.println(martians.size());
		if (!(enemies.get(j).isAlive)) {
			enemies.remove(j);
			

		}
	}
	}
}
