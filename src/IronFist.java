import java.awt.Color;
import java.awt.Graphics;

public class IronFist extends GameObject2 {
	// member variables
	Integer speed;

	// constructor
	public IronFist(int x, int y, int width, int height,int hp) {
		super(x, y, width, height,hp);
		speed = 1;

	}

	// methods

	public void update() {
		super.update();
		x = x - speed;

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.ironbotImg, x, y, width, height, null);
	}
}