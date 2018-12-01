import java.awt.Color;
import java.awt.Graphics;

public class IronFist extends GameObject {
//member variables
	Integer speed;
	//constructor
	public IronFist(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}
//methods
	
	public void update() {
		super.update();
	
		
	}
	void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		}
}