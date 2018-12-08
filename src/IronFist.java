import java.awt.Color;
import java.awt.Graphics;

public class IronFist extends GameObject {
//member variables
	Integer speed;
	Integer damage;
	Integer price;
	//constructor
	public IronFist(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		damage=10;
		price = 20;
	}
//methods
	
	public void update() {
		super.update();
	x=x-speed;
	if(collisionBox.intersects(enemyBox)) {
		isAlive=false;
	}
		
	}
	void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		}
}