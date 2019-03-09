import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject2 {
	boolean isAlive = true;
	int x;
	int y;
	int width;
	int height;
	Rectangle collisionBox;
int hp;

	// constructor
	public GameObject2(int x, int y, int width, int height, int hp) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.hp=hp;
		collisionBox = new Rectangle();
		collisionBox.setBounds(x, y, width, height);
	}

	// methods
	void draw(Graphics g) {

	}

	public void update() {
		collisionBox.setBounds(x, y, width, height);

	}

}
