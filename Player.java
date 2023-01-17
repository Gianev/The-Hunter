package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	//create the variables so they can be accessed in every method, while direction can be accessed in other classes
	private ImageView iviewPlayer;
	private Image imgEast, imgWest;
//	private boolean dead;
	public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
	private int dir; 
	private int xPos,yPos,speed, width, height;  

//constructor where user can pass in where they want player to spawn
	
	public Player(int x, int y)
	{
		
		dir = EAST; 
		
		imgEast = new Image("file:shooter.right.png");
		iviewPlayer = new ImageView(imgEast);
		
		width = (int)imgEast.getWidth();
		height = (int)imgEast.getHeight();
		
		xPos = x;
		yPos = y;
		
	}
	
	//gives back player img based on their direction 
	
	public ImageView getnode() {
		
		  if(dir==EAST ) {
	            Image imgkoop = new Image("file:shooter.right.png");
	            iviewPlayer.setImage(imgkoop);
	        
	        }
	        else if (dir==WEST){
	            Image imgkoop = new Image("file:shooter.left.png");
	            iviewPlayer.setImage(imgkoop);
	        }
		  
		  
		
		return iviewPlayer;
	}
	
	//user can set location of player by passing in their desires xpos and ypos
	public void setLocation(int x, int y) {
		
		xPos = x;
		yPos = y;
		iviewPlayer.setX(xPos);
		iviewPlayer.setY(yPos);
				
	}
	
	//MOVEMENT these all move player 5 pixels in the user's  desired direction
	public void moveEast() {
		
		xPos+=5;
		iviewPlayer.setX(xPos);
	}
	
	public void moveWest() {
		
		xPos-=5;
		iviewPlayer.setX(xPos);
	}
	
	public void moveNorth() {
		
		yPos-=5;
		iviewPlayer.setY(yPos);
	}	
	
	public void moveSouth() {
		
		yPos+=5;
		iviewPlayer.setY(yPos);
	}
	
	//sets xposition of player
	public void setX(int x) {
		xPos = x;
	}
	//sets yposition of player
	public void setY(int y) {
		yPos = y;
	}
	//sets direction of plaeyr
	public void setDirection(int dir) {

		this.dir = dir;
	}
	//returns xpos of player
	public double getX() {

		return xPos;
	}
//returns  ypos ofplayer
	public double getY() {

		return yPos;
	}
	//returns width of plaeyr
	public double getWidth()
	{
		return width;
		
	}
	//returns height of plaeyr
	public double getHeight()
	{
		return height;
		
	}
	//return direction of the player
	public int getDir() {
		return dir;
	}
	//makes dead variable true
//	public void Kill() {
//		
//		dead = true;
//		
//	}
}