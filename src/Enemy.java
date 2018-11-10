import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {
		//member variables
			Integer speed;
			//constructor
			public Enemy(int x, int y, int width, int height) {
				super(x, y, width, height);
				speed = 7;
			}
		//methods
			
			public void update() {
				super.update();
			y++;
				
			}
			void draw(Graphics g) {
				g.setColor(Color.red);
				g.drawRect(x, y, width, height);
				}
}
