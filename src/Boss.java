import java.awt.Color;
import java.awt.Graphics;

public class Boss extends GameObject {
	Integer speed;

	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 1;
	}

	public void update() {
		super.update();
		x = x - speed;
	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

}
