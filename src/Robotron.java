import java.awt.Color;
import java.awt.Graphics;

public class Robotron extends GameObject {
//member variables
	Integer speed;
	
	//constructor
	public Robotron(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 3;
		
	}
//methods
	
	public void update() {
		super.update();
	x=x-speed;
	
		
	}
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
		}
}
