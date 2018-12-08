import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	boolean isAlive = true;
int x;
int y;
int width;
int height;
Rectangle collisionBox;
Rectangle enemyBox;
//constructor
public GameObject(int x, int y, int width, int height) {
	this.x=x;
	this.y=y;
	this.height=height;
	this.width=width;
	collisionBox = new Rectangle();
	collisionBox.setBounds(x, y, width, height);
	enemyBox = new Rectangle();
	enemyBox.setBounds(x, y, width, height);
}

//methods
	void draw(Graphics g) {
	
	}

	public void update() {
		collisionBox.setBounds(x, y, width, height);
		enemyBox.setBounds(x, y, width, height);
	}



}
