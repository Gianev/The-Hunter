package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Villager {
//sets variables and objects of tyhe villager to be used in all methods while main can access direction
	private ImageView iviewVillager;
	private Random rnd; 
	private Image imgVillager;
	private int dir,direction; 
	private int xPos,yPos,speed, width, height; 
	public final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;

//constructor of villager which allows user to set an x and y for spawn
	public Villager(int x, int y)
	{

		rnd = new Random();

		//gives villager a roandom direction and also default values for all variabels
		direction = rnd.nextInt(4)+1;
		if(direction ==1) 
			dir=EAST;
		else if(direction==2)
			dir=WEST;
		else if(direction==3)
			dir=NORTH;
		else
			dir=SOUTH;
		
		imgVillager = new Image("file:villager.png");
		iviewVillager = new ImageView(imgVillager);
		speed = 1;

		xPos = rnd.nextInt(x - (int)imgVillager.getWidth());
		yPos = rnd.nextInt(y - (int)imgVillager.getHeight());
		iviewVillager.setX(xPos);
		iviewVillager.setY(yPos);

		width = (int)imgVillager.getWidth();
		height = (int)imgVillager.getHeight();

	}
	//constructor with default and preset values
	public Villager()
	{

		rnd = new Random();

		direction = rnd.nextInt(4)+1;
		if(direction ==1) 
			dir=EAST;
		else if(direction==2)
			dir=WEST;
		else if(direction==3)
			dir=NORTH;
		else
			dir=SOUTH;
		
		imgVillager = new Image("file:villager.png");
		iviewVillager = new ImageView(imgVillager);
		speed = 1;

		xPos = 0;
		yPos = 0;
		iviewVillager.setX(xPos);
		iviewVillager.setY(yPos);

		width = (int)imgVillager.getWidth();
		height = (int)imgVillager.getHeight();

	}

	//sets ypos
	public void setY(int y) {

		this.yPos = y;
	}
//sets xpos
	public void setX(int x) {

		this.xPos = x;
	}
//moves villager in respective direction and uses speed to determine how fast/slow it moves
	public void move() {

		//		int rando = rnd.nextInt(4);
		//		
		//		if(rando == 0) {
		//			dir = EAST;
		//		}
		//		
		//		else if(rando == 1) {
		//			dir = WEST;
		//		}
		//		
		//		else if(rando == 2) {
		//			dir = NORTH;
		//		}
		//		
		//		else {
		//			dir = SOUTH;
		//		}

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
//update location
		iviewVillager.setX(xPos);
		iviewVillager.setY(yPos);
	}

//set direction of villager
	public void setDirection(int dir) {

		this.dir = dir;
	}
	//returns xpos of villager
	public double getX() {

		return xPos;
	}
	//returns  ypos of vill;ager
	public double getY() {

		return yPos;
	}
	//returns width of villager
	public double getWidth()
	{
		return width;

	}
	//returns height of villager
	public double getHeight()
	{
		return height;

	}

	//returns imageview of villager
	public ImageView getnode() {


		return iviewVillager;
	}
	//sets x and y of villager
public void setLocation(int x, int y) {
		
		xPos = x;
		yPos = y;
		iviewVillager.setX(xPos);
		iviewVillager.setY(yPos);
				
	}




}


