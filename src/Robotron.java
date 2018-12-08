import java.awt.Color;
import java.awt.Graphics;

public class Robotron extends GameObject {
//member variables
	Integer speed;
	Integer damage;
	Integer price;
	//constructor
	public Robotron(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 3;
		damage=5;
		price=15;
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
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		}
}
