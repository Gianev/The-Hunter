package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet
{
	//declare variables and objects
	private Image imgEast, imgWest;
	private ImageView iviewBullet;
	public boolean fired;
	private double xPos, yPos, width, height; 
	public int dir; 
	public final static int EAST = 0, WEST = 1;
	//constructor for bullet where variables and objects are initilaized
	public Bullet()
	{
		imgEast = new Image("file:bulletEast.png");
		imgWest = new Image("file:bulletWest.png");
		
		fired = false;
		
		xPos = -40;
		yPos = -40; 
		width = imgEast.getWidth();
		height = imgEast.getHeight();
		
		dir = EAST;
		
		iviewBullet = new ImageView(imgEast);
		iviewBullet.setX(xPos);
		iviewBullet.setY(yPos);
	}
	//constructor for bullet for a set location 
	public Bullet(double x, double y)
	{
		imgEast = new Image("file:bulletEast.png");
		imgWest = new Image("file:bulletWest.png");
		
		fired = false;
		
		xPos = x;
		yPos = y; 
		width = imgEast.getWidth();
		height = imgEast.getHeight();
		
		dir = EAST;
		
		iviewBullet = new ImageView(imgEast);
		iviewBullet.setX(xPos);
		iviewBullet.setY(yPos);
	}
	//return direction
	public int getDirection()
	{
		return dir;
	}
	//return height
	public double getHeight()
	{
		return height;
	}
	//return width
	public double getWidth()
	{
		return width;
	}
	//return x position
	public double getX()
	{
		return xPos;
	}
	//return y position
	public double getY()
	{
		return yPos;
	}
	//return if the bullet was fired
	public boolean isFired()
	{
		return fired;
	}
	//move the bullet in the right direction
	public void move()
	{
		if (dir == EAST)
		{
			xPos += 10;
				
		}
		else
		{
			xPos -= 10;
		}
		
		iviewBullet.setX(xPos);
	}
	//set the position of the bullet
	public void setPosition(double playerX, double playerY, int dir)
	{
		this.dir = dir;
			
			
		if (this.dir == EAST)
		{
			xPos = playerX + 75;
		}
		else
		{
			xPos = playerX;
		}
		
		yPos = playerY + 40;
		fired = true;
		iviewBullet.setX(xPos);
		iviewBullet.setY(yPos);
	}
	//set the x position
	public void setX(int x)
	{
		xPos = x;
		//iviewBullet.setX(xPos);
	}
	//set the y position
	public void setY(int y)
	{
		yPos = y;
		//iviewBullet.setY(yPos);
	}
	//stop the bullet from moving
	public void stopBullet() {
		
		fired = false;
	}
	//check if the bullet left the screen
	public boolean isOffScreen(double edge) {
		
		boolean offScreen = false;
		
		if (xPos >= edge || xPos + width <= 0)
		{
			offScreen = true;
			fired = false;
		}
		else
		{
			offScreen = false;
		}
		
		return offScreen;
	}
	//return the imageview object
	public ImageView getNode() {
		
		if (dir == EAST)
		{
			iviewBullet.setImage(imgEast);
		}
		else
		{
			iviewBullet.setImage(imgWest);
			
		}
		return iviewBullet;
	}

}
