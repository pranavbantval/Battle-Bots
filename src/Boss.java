import java.awt.Color;
import java.awt.Graphics;

public class Boss extends GameObject {
	Integer speed;
	int avoid = 0;

	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 1;
	}

	public void update() {
		super.update();
		if (avoid % 2 == 0) {

			x = x + speed;
		}
		avoid++;
	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

}
