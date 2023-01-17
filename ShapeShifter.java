package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShapeShifter {
	//create all variables to be accessed throughout the the class while direction can be used in main

	private ImageView iviewShapeShifter;
	private Random rnd; 
	private boolean revealed = false;
	private Image imgShapeShifter,imgVillager;
	public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
	private int dir; 
	private int xPos,yPos,speed, width, height; 

	//constructor that gives default values while letting the user choose the initial xpos and ypos
	public ShapeShifter(int x, int y)
	{
		speed = 1;
		dir = EAST; 

		rnd = new Random();
		imgVillager = new Image("file:villager.png");
		iviewShapeShifter = new ImageView(imgVillager);

		width = (int)imgVillager.getWidth();
		height = (int)imgVillager.getHeight();

		xPos = rnd.nextInt(x - (int)imgVillager.getWidth());
		yPos = rnd.nextInt(y - (int)imgVillager.getHeight());
		iviewShapeShifter.setX(xPos);
		iviewShapeShifter.setY(yPos);

	}
//returns image of shapeshifter based on if it was found and revealed and the direction it is facing 
	public ImageView getnode() {


		if(revealed == true) {
			Image imgSS = new Image("file:wizardright.png");
			iviewShapeShifter.setImage(imgSS);
		}

		if(revealed == true && dir == EAST) {
			Image imgSS = new Image("file:wizardright.png");
			iviewShapeShifter.setImage(imgSS);

		}
		else if (revealed == true && dir== WEST){
			Image imgSS = new Image("file:wizardleft.png");
			iviewShapeShifter.setImage(imgSS);
		}


		else if(revealed == false){
			
			iviewShapeShifter.setImage(imgVillager);
		}


		return iviewShapeShifter;
	}

	//moves shapeshifter by the speeed variable and in the respective direction and set the x and y to the xpos and ypos
	public void move() {


		if(dir==EAST) {
			xPos+=speed;
		}
		else if(dir == WEST){
			xPos-=speed;
		}

		else if(dir == NORTH) {
			yPos-= speed;
		}
		else {
			yPos +=speed;
		}

		iviewShapeShifter.setX(xPos);
		iviewShapeShifter.setY(yPos);
	}

	//set y position of the shapeshifter 
	public void setY(int y) {

		this.yPos = y;
	}

	//set x position of the shapeshfter
	public void setX(int x) {

		this.xPos = x;
	}

	//set direction of the shapeshifter
	public void setDirection(int dir) {

		this.dir = dir;
	}

	//returns the xposition of the shapeshifter
	public double getX() {

		return xPos;
	}

	//returns the ypositionof the shapeshfiter
	public double getY() {

		return yPos;
	}

	//returns width of shapeshifter
	public double getWidth()
	{
		return width;

	}
//returns the height of the shapeshifter
	public double getHeight()
	{
		return height;

	}
//use this to indicate if the shapeshifter was found
	public void found() {
		revealed = true;
	}
	
	public void notfound() {
		revealed = false;
	}
	//returns if the shapeshifter was found
	public boolean isfound() {
		return revealed;
	}




}
