package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mage {
	
	//create the variables so they can be accessed in every method, while direction can be accessed in other classes
		private ImageView iviewMage, iviewFire;
		private Image imgEast,imgWest, imgFire;
//		private boolean dead;
		public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
		private int dir; 
		private int xPos,yPos,speed, width, height;  

		//constructor for the mage giving it default values and letting the user pick where it spawns (xpos and ypos)
		public Mage(int x, int y)
		{
			
			dir = EAST; 
			
			imgEast = new Image("file:mageright.png");
			imgWest = new Image("file:mageleft.png");
			iviewMage = new ImageView(imgEast);
			
			
			width = (int)imgEast.getWidth();
			height = (int)imgEast.getHeight();
			
			xPos = x;
			yPos = y;
			iviewMage.setX(xPos);
			iviewMage.setY(yPos);
			
		}
		
		//return the mage image depending on which way it is facing
		public ImageView getnode() {
			
			 if(dir==EAST ) {
		            iviewMage.setImage(imgEast);
		        
		        }
		        else if (dir==WEST){
		            iviewMage.setImage(imgWest);
		        }
			 
			return iviewMage;
		}
		//set x and y of the mage
		
		public void setLocation(int x, int y) {
			
			xPos = x;
			yPos = y;
			iviewMage.setX(xPos);
			iviewMage.setY(yPos);
					
		}
		
		
		//set the direction of the mage
		public void setDirection(int dir) {

			this.dir = dir;
		}
		//returns xpos of mage
		public double getX() {

			return xPos;
		}
		//returns  ypos of mage
		public double getY() {

			return yPos;
		}
		//returns width of mage
		public double getWidth()
		{
			return width;

		}
		//returns height of mage
		public double getHeight()
		{
			return height;

		}
		
		
		
}