import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {
		//member variables
			Integer speed;
			
			//constructor
			public Enemy(int x, int y, int width, int height) {
				super(x, y, width, height);
				speed = 2;
				
			}
		//methods
			
			public void update() {
				super.update();
			x=x+speed;
			
			}
			void draw(Graphics g) {
				 g.drawImage(GamePanel.enemyImg, x, y, width, height, null);
				}
}
