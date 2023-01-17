package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boss {
	
	//create the variables so they can be accessed in every method, while direction can be accessed in other classes
		private ImageView iviewBoss;
		private Image imgBoss;
//		private boolean dead;
		public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
		private int dir; 
		private int xPos,yPos,speed, width, height;  

		//constructor whcih givs default values for width height xpos yp9s and image
		public Boss() {
			speed = 2;
			dir = EAST; 
			
			imgBoss = new Image("file:boss.png");
			iviewBoss = new ImageView(imgBoss);
			
			width = (int)imgBoss.getWidth();
			height = (int)imgBoss.getHeight();
			
			xPos = 0;
			yPos =0;
			iviewBoss.setX(xPos);
			iviewBoss.setY(yPos);
		}
		//same constructor except user can give it an xpos and y pos
		public Boss(int x, int y)
		{
			speed = 2;
			dir = EAST; 
			
			imgBoss = new Image("file:boss.png");
			iviewBoss = new ImageView(imgBoss);
			
			width = (int)imgBoss.getWidth();
			height = (int)imgBoss.getHeight();
			
			xPos = x;
			yPos = y;
			
		}
		//returns image of boss
		
		public ImageView getnode() {
			 
			return iviewBoss;
		}
		
		//set xpos and ypos of trhe boss
		public void setLocation(int x, int y) {
			
			xPos = x;
			yPos = y;
			iviewBoss.setX(xPos);
			iviewBoss.setY(yPos);
					
		}
		
		//move the boss in direction it is headed and set x and y to xpos and ypos
		public void move() {
			
			if(dir == NORTH) {
				yPos-= speed;
			}
			else {
				yPos +=speed;
			}

			iviewBoss.setX(xPos);
			iviewBoss.setY(yPos);
			
		}
		
		//set directiion of the boss
		public void setDirection(int dir) {

			this.dir = dir;
		}
		//returns xpos of boss
		public double getX() {

			return xPos;
		}
		//returns  ypos of boss
		public double getY() {

			return yPos;
		}
		//returns width of boss
		public double getWidth()
		{
			return width;

		}
		//returns height of boss
		public double getHeight()
		{
			return height;

		}
		
		
}
