package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fireball
{
	//declare variables and objects
	private Image imgFireball;
	private ImageView iviewFireball;
	public boolean fired;
	private double xPos, yPos, width, height; 
	public int dir; 
	public final static int NORTH = 0, SOUTH = 1;

	//constructor which gives default values
	public Fireball()
	{

		fired = false;
		imgFireball = new Image("file:fireball.gif");

		xPos = 0;
		yPos = 0; 
		width = imgFireball.getWidth();
		height = imgFireball.getHeight();

		dir = NORTH;

		iviewFireball = new ImageView(imgFireball);
		iviewFireball.setX(xPos);
		iviewFireball.setY(yPos);
	}
	//constructor that allows user to decide their default location and direction
	public Fireball(double x, double y, int dir2)
	{
		imgFireball = new Image("file:fireball.gif");

		fired = false;

		xPos = x;
		yPos = y; 
		width = imgFireball.getWidth();
		height = imgFireball.getHeight();

		dir = dir2;

		iviewFireball = new ImageView(imgFireball);
		iviewFireball.setX(xPos);
		iviewFireball.setY(yPos);
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
	//return height
	public double getWidth()
	{
		return width;
	}
	//return X
	public double getX()
	{
		return xPos;
	}
	//return Y
	public double getY()
	{
		return yPos;
	}
	//indicates if fireball was fired
	public boolean isFired()
	{
		return fired;
	}
	//moves the fireball in the respective directon by 3
	public void move()
	{
		if(fired==true) {
			if (dir == SOUTH)
			{
				yPos += 3;

			}
			else
			{
				yPos -= 3;
			}
			iviewFireball.setY(yPos);
		}
		
	}
	//sets location of x and y of fireball
	public void setPosition(double playerX, double playerY, int dir)
	{
		this.dir = dir;


		if (this.dir == NORTH)
		{
			xPos = playerY - 75;
		}
		else
		{
			xPos = playerX;
		}


		fired = true;
		iviewFireball.setX(xPos);
		iviewFireball.setY(yPos);
	}
	//set x pos of the fireball
	public void setX(int x)
	{
		xPos = x;
		//iviewBullet.setX(xPos);
	}
	//set y pos of fireball
	public void setY(int y)
	{
		yPos = y;
		//iviewBullet.setY(yPos);
	}
	//makes fired false
	public void stopBullet() {

		fired = false;
	}
//checks if fireball is beyond boundaries
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
	//returns image of fireball

	public ImageView getNode() {
		return iviewFireball;
	}
	//indicates that the fireball has been shot
	public void fire() {
		fired=true;
	}
	//stops firing
	public void stopFire(){
		fired=false;
		if(dir==0) {
			
		}
	}
	

}