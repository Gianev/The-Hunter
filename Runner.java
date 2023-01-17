package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Runner {
	//creating variables that can be accessed throughout the class while direction can be accessed in main
	private ImageView iviewRunner;
	private Random rnd; 
	private Image imgEast,imgWest;
	public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
	private int dir; 
	private int xPos,yPos,speed, width, height; 

	//constructor giving the runner default values while letting the user pick where he spawns initially
	public Runner(int x, int y)
	{
		
		dir = EAST; 
		
		imgEast = new Image("file:runnerright.png");
		imgWest = new Image("file:runnerleft.png");
		iviewRunner = new ImageView(imgEast);
		
		width = (int)imgEast.getWidth();
		height = (int)imgEast.getHeight();
		
		xPos = x;
		yPos = y;
		
	}
	
	//returns image of the runner depending on which way he is facing
	
	public ImageView getnode() {
		
		  if(dir==EAST ) {
	            Image imgrunner = new Image("file:runnerright.png");
	            iviewRunner.setImage(imgrunner);
	        
	        }
	        else if (dir==WEST){
	            Image imgrunner = new Image("file:runnerleft.png");
	            iviewRunner.setImage(imgrunner);
	        }
		  
		  
		
		return iviewRunner;
	}
	
	//these allow runner to move in his respective direction by 1 pixel
public void moveEast() {
		
		xPos+=1;
		
	}
	
	public void moveWest() {
		
		xPos-=1;
		
	}
	
	public void moveNorth() {
		
		yPos-=1;
		
	}	
	
	public void moveSouth() {
		
		yPos+=1;
		
	}
	//allow user to set the x and y of the runner
	public void setLocation(int x, int y) {
		
		xPos = x;
		yPos = y;
		iviewRunner.setX(xPos);
		iviewRunner.setY(yPos);
				
	}
	
	//sets x pos of runner
	public void setX(int x) {
		xPos = x;
	}
	//sets yposition of runner
	public void setY(int y) {
		yPos = y;
	}
	//sets direction of runner
	public void setDirection(int dir) {

		this.dir = dir;
	}
	//returns xpos of runner
	public double getX() {

		return xPos;
	}
//returns  ypos of runner
	public double getY() {

		return yPos;
	}
	//returns width of runner
	public double getWidth()
	{
		return width;
		
	}
	//returns height of runner
	public double getHeight()
	{
		return height;
		
	}
}
