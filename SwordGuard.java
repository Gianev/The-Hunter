package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SwordGuard {
	//setting up variables to be used everywhere in the class while direction can be accessed in main
	
	private ImageView iviewRunner;
	private Random rnd; 
	private Image imgEast,imgWest;
	public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
	private int dir; 
	private int speed, width, height; 
	private double xPos,yPos;
	//constructor that gives default values and allows user to set the xpos and ypos 
	public SwordGuard(int x, int y)
	{
		
		dir = EAST; 
		
		imgEast = new Image("file:skeletonright.png");
		imgWest = new Image("file:skeletonleft.png");
		iviewRunner = new ImageView(imgEast);
		
		width = (int)imgEast.getWidth();
		height = (int)imgEast.getHeight();
		
		xPos = x;
		yPos = y;
		
		iviewRunner.setX(xPos);
		iviewRunner.setY(yPos);
	}
	//returns image of the swordguard depending on which way it is facing
	public ImageView getnode() {
		
		  if(dir==EAST ) {
	            Image imgrunner = new Image("file:skeletonright.png");
	            iviewRunner.setImage(imgrunner);
	        
	        }
	        else if (dir==WEST){
	            Image imgrunner = new Image("file:skeletonleft.png");
	            iviewRunner.setImage(imgrunner);
	        }
		  
		  
		
		return iviewRunner;
	}
	//these move the guard in the respective direction by thevariable speeed
public void moveEast() {
		
		xPos+=5;
		
	}
	
	public void moveWest() {
		
		xPos-=5;
		
	}
	
	public void moveNorth() {
		
		yPos-=5;
		
	}	
	
	public void moveSouth() {
		
		yPos+=5;
		
	}
	
	//sets location xpos and ypos of guard
	public void setLocation(double x, double y) {
		
		xPos = x;
		yPos = y;
		iviewRunner.setX(xPos);
		iviewRunner.setY(yPos);
				
	}
	//sets x of guard
	public void setX(double x) {
		xPos = x;
	}
	//sets yposition of guard
	public void setY(double y) {
		yPos = y;
	}
	//sets direction of guard
	public void setDirection(int dir) {

		this.dir = dir;
	}
	//returns xpos of guard
	public double getX() {

		return xPos;
	}
//returns  ypos of guard
	public double getY() {

		return yPos;
	}
	//returns width of guard
	public double getWidth()
	{
		return width;
		
	}
	//returns height of guard
	public double getHeight()
	{
		return height;
		
	}

	//sets position of guard
	public void setPosition(int width2, int height2) {
		xPos = width2;
		yPos = height2;
		iviewRunner.setX(xPos);
		iviewRunner.setY(yPos);;
		
	}
}


