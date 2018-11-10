import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
//member variables
	ArrayList<Robotron> robotrons = new ArrayList<Robotron>();
	ArrayList<IronFist> ironfists = new ArrayList<IronFist>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	//constructor
	public ObjectManager() {
		
	}
	//methods
	public void update() {
		for(Robotron robotron:robotrons) {
			robotron.update();
		}
		for(IronFist ironfist:ironfists) {
			ironfist.update();
		}
		for (Enemy enemy : enemies) {
		enemy.update();	
		}	}
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
	
}
