/*ICS4U1 CPT: The Hunter
 *Giancarlo Evacula,Kevin Perera
 *Conway K
 *Program Description: The Hunter is a 2D shooter game where you play 
 *the Hunter. Your goal is to eliminate every target and then killed
 *Excalibur their leader. As you advance through the levels then
 *your task will get a bit more challenging. Avoid any obstacles along 
 *the way as you may lose lives. Also avoid shooting innocent villagers
 * as you will be penaltied for that. WASD and spacebar are your controls
 * , keep that in mind. Now i ask you, Can you beat Excalibur? Even better,
 * can you beat Excalibur the fastest and take top spot on the leaderboard?
 * 
 *Program Details: The Hunter makes use of a variety of classes for the different unique 
 *objects and people you will find while playing. The game also uses arraylists/arrays to 
 *have multiple objects with less code, makes use of animationtimers to smoothly 
 *move and check the previously mentioned objects, makes use of keyframes and Timelines 
 *to add in the objects at certain intervals and also to keep track of time, makes use of 
 *alerts so the user can decide what they want to do after they have lost or won, makes use
 *of textfields so the user can input their username/name which is then stored,
 *makes use of labels to input instructions and information to the user, makes use of 2d
 *arrays to store names and their respective scores and lastly uses the file class to 
 *permanently store user's names and scores
 */
package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	//making global variables and objects and arrays and lists
	private Pane root;
	private boolean goRight, goLeft, goUp, goDown, gameStart=false,levelOne=false,levelTwo=false,levelThree=false;
	private boolean tutorial=false, ttlInst1=false,ttlInst2=false, ttlInst3=false;
	private Timeline levelTimer,villagerTimer, guardTimer, fireTimer, scoreTimer;
	private AnimationTimer animation, ttlAnimation, fireAnimation, moveAnimation;
	private int [] rnddir , rnddir2;
	private Random rnd;
	private Scene scene;
	private Label level1,level2,level3,lbl2,lbllives, lblInstructions, lblVillager, lblTime, lblTitle, lblName;
	private Label lblHighScore;
	private Label[] highscores,namehighscores;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int numBullets=-1,seconds,seconds2,counter=0,counter1=0,counter2=0,counter3=0,scoreSeconds=0, minutes=0, scorelength;
	private Image imgStart;
	private ImageView iviewStart, iviewBackground, iviewBoss;
	private Player player;
	private Villager[] villagerslvl1,villagerslvl2;
	private Villager ttlVillager;
	private ShapeShifter shapeshifter;
	private Runner runner, ttlRunner;
	private ArrayList<String> names, names2,names3;
	private ArrayList<String> scorename,scorename1;
	private String name, finalS,finalN;
	private TextField txtfield;
	private int lives;
	private Boss boss;
	private boolean gameOver = false;
	private Button btnStart, btnTtl, btnBack;
	private ArrayList<SwordGuard> guard = new ArrayList<SwordGuard>();
	private int guardcount = -1;
	private Mage[] mage;
	private Fireball[] fireball0,fireball1,fireball2,fireball3,fireball4,fireball5,fireball6,fireball7,fireball8,fireball9;
	private String[][] scoreboard;
	private ArrayList<String> scores,scores1;
	private DecimalFormat df; 
	private String[] scores2;
	private File dataFile;
	private FileReader in;
	private BufferedReader readFile;
	private FileWriter filewriter;
	private BufferedWriter output;



	public void start(Stage primaryStage) {


		try {
			//initialize random lives and rnd numbers for our villagers (this will be explained later on)
			rnd = new Random();
			lives = 3;
			df = new DecimalFormat("00");

			highscores = new Label[5];
			namehighscores =new Label[5];
			names2 = new ArrayList<String>();
			names3 = new ArrayList<String>();
			scorename = new ArrayList<String>();
			scorename1 = new ArrayList<String>();
			scores = new ArrayList<String>();
			scores1 = new ArrayList<String>();

			scoreboard = new String[5][2];
			rnddir = new int[10];
			rnddir2 = new int[15];
			//creating startscreen
			imgStart = new Image("file:startscreen.png");
			iviewStart = new ImageView(imgStart);
			iviewStart.setFitWidth(1157);
			iviewStart.setFitHeight(800);

			//creating background
			Image imgBack = new Image("file:background2.png");
			iviewBackground = new ImageView(imgBack);

			//creating boss level background 
			Image imgBoss= new Image("file:bosslevel.png");
			iviewBoss = new ImageView(imgBoss);

			root = new Pane();
			scene = new Scene(root, imgBack.getWidth(), imgBack.getHeight());

			//making textfield for username
			txtfield = new TextField();
			txtfield.setAlignment(Pos.CENTER);
			txtfield.setPrefWidth(300);
			txtfield.setPrefHeight(35);
			txtfield.setLayoutX(100);
			txtfield.setLayoutY(300);
			txtfield.setFont(Font.font("New Time Roman", FontPosture.REGULAR, 15));

			//create the start button
			btnStart = new Button("Start");
			btnStart.setPrefSize(300, 125);
			btnStart.setLayoutX(200);
			btnStart.setLayoutY(600);
			btnStart.setFont(Font.font("Verdana",FontWeight.BOLD, 30));
			btnStart.setFocusTraversable(false);
			btnStart.setOnAction(e -> btnStart_Click());
			btnStart.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

			//create the tutorial button
			btnTtl = new Button("Tutorial");
			btnTtl.setPrefSize(300, 125);
			btnTtl.setLayoutX(600);
			btnTtl.setLayoutY(600);
			btnTtl.setFont(Font.font("Verdana",FontWeight.BOLD, 30));
			btnTtl.setFocusTraversable(false);
			btnTtl.setOnAction(e -> btnTtl_Click());
			btnTtl.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

			//create back button to leave tutorial
			btnBack = new Button("<- BACK");
			btnBack.setPrefSize(200,50);
			btnBack.setLayoutX(0);
			btnBack.setLayoutY(0);
			btnBack.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
			btnBack.setFocusTraversable(false);
			btnBack.setOnAction(e -> btnBack_Click());
			btnBack.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
			//add these to the window


			//label that displays the player's time
			lblTime = new Label("Time: " + df.format(minutes)+":"+df.format(scoreSeconds));
			lblTime.setTextFill(Color.BLACK);
			lblTime.setPrefSize(400, 60);
			lblTime.setFont(Font.font("Comic Sans",FontWeight.BOLD,30));
			lblTime.setAlignment(Pos.CENTER);
			lblTime.setLayoutX(400);
			lblTime.setLayoutY(0);

			//label for title
			lblTitle = new Label("THE HUNTER");
			lblTitle.setTextFill(Color.BLACK);
			lblTitle.setPrefSize(400, 60);
			lblTitle.setFont(Font.font("Comic Sans",FontWeight.BOLD,50));
			lblTitle.setAlignment(Pos.CENTER);
			lblTitle.setLayoutX(root.getWidth() / 2 - lblTitle.getPrefWidth() / 2);
			lblTitle.setLayoutY(50);

			//label for name enter
			lblName = new Label("Enter a name and then press start");
			lblName.setTextFill(Color.BLACK);
			lblName.setPrefSize(300, 60);
			lblName.setFont(Font.font("Comic Sans",FontWeight.BOLD,15));
			lblName.setAlignment(Pos.CENTER);
			lblName.setLayoutX(100);
			lblName.setLayoutY(250);

			//label for highscores
			lblHighScore = new Label("Top 5 Scores");
			lblHighScore.setTextFill(Color.BLACK);
			lblHighScore.setPrefSize(300, 60);
			lblHighScore.setFont(Font.font("Comic Sans",FontWeight.BOLD,30));
			lblHighScore.setAlignment(Pos.CENTER);
			lblHighScore.setLayoutX(700);
			lblHighScore.setLayoutY(250);

			finalS = null;
			finalN = null;
			//SCORE CHECKING
			try 
			{
				dataFile = new File("scores.txt"); 
				in = new FileReader (dataFile);
				readFile = new BufferedReader(in);

				filewriter = new FileWriter(dataFile, true) ;
				output = new BufferedWriter(filewriter);

				//text will represent the line we are reading
				String text;
				while((text = readFile.readLine()) != null) {
					//add every line to scorename which will store all namesand scores
					scorename1.add(text);
				}

				//cycles throuigh and sperates the scores and the names
				for( int f = 0; f < scorename1.size(); f++) {
					if(f % 2 == 0) {
						//every even line which is a name then add the line to the array of names
						names3.add(scorename1.get(f));
					}
					else {
						//every odd line which is a score then add the line to the array of scores
						scores1.add(scorename1.get(f));
					}
				}
			}
			//indicates if there was any errors with writing or reading file
			catch (IOException e) 
			{ 
				System.out.println("Problem writing to file."); 
				System.err.println("IOException: "+ e.getMessage()); 
			} 
			//add scores to a seperate array so it can be sorted in sort method
			scores2 = new String[scores1.size()];

			for(int i = 0; i < scores1.size(); i++) {

				scores2[i] = scores1.get(i);
			}
			//call to sort method to sort the scores
			BubbleSort(scores2);

			//all vacant spots will be represented as dashes
			scorelength = scores2.length;
			for(int rows =0;rows<5;rows++) {
				if(rows>=scorelength) {
					scoreboard[rows][1] = "--";
					scoreboard[rows][0] = "--";
				}
				//if there is a score put it onto the 2d array
				else {
					scoreboard[rows][1] = scores2[rows];
					scoreboard[rows][0] = names3.get(scores1.indexOf(scores2[rows]));
					scores1.set(scores1.indexOf(scores2[rows]), "done");

				}
			}
			//this helps with spacing the scores out
			int spacing = 40;

			//makes all the labels for the highscores and creates the spacing for them and their contents
			for(int rows =0;rows<scoreboard.length;rows++) {
				finalN = (rows+1)+"."+scoreboard[rows][0];
				finalS = scoreboard[rows][1];
				namehighscores[rows] = new Label(finalN);
				namehighscores[rows].setTextFill(Color.BLACK);
				namehighscores[rows].setPrefSize(300, 40);
				namehighscores[rows].setFont(Font.font("Comic Sans",FontWeight.BOLD,20));
				namehighscores[rows].setAlignment(Pos.CENTER_LEFT);
				namehighscores[rows].setLayoutX(650);
				namehighscores[rows].setLayoutY(260+spacing);
				highscores[rows] = new Label(finalS);
				highscores[rows].setTextFill(Color.BLACK);
				highscores[rows].setPrefSize(300, 40);
				highscores[rows].setFont(Font.font("Comic Sans",FontWeight.BOLD,20));
				highscores[rows].setAlignment(Pos.CENTER_RIGHT);
				highscores[rows].setLayoutX(750);
				highscores[rows].setLayoutY(260+spacing);
				//increase spacing for the next cycle
				spacing+=40;
			}

			//finaln and finals are for strings for the names and scores so that they cant be put onto labels individually
			//
			for(int rows =0;rows<scoreboard.length;rows++) {
				finalN = (rows+1)+"."+scoreboard[rows][0];
				finalS = scoreboard[rows][1];
				//put the name and score in the same row
				namehighscores[rows].setText(finalN);
				highscores[rows].setText(finalS);
			}


			//add all labels for start menu 
			root.getChildren().addAll(iviewStart,btnStart,btnTtl, txtfield, lblTitle, lblName,lblHighScore);
			for(int i =0;i<scoreboard.length;i++) {
				root.getChildren().addAll(highscores[i],namehighscores[i]);
			}
			//tutorial setup 
			ttlVillager = new Villager();
			ttlRunner = new Runner(0,0);
			//Player set up
			player = new Player(100,100);

			//creating an array of villagers for level one
			villagerslvl1 = new Villager[10];
			for(int i =0;i<villagerslvl1.length;i++) {
				villagerslvl1[i]=new Villager((int)root.getWidth(), (int)root.getHeight());
			}

			//creating an array of villagers for level two
			villagerslvl2 = new Villager[15];
			for(int i =0;i<villagerslvl2.length;i++) {
				villagerslvl2[i]=new Villager((int)root.getWidth(), (int)root.getHeight());
			}

			//creating an array of mages
			mage = new Mage[10];
			int move = 0;
			for(int i = 0; i < mage.length; i++) {
				move += 100;
				if(i % 2 == 0) {
					mage[i] = new Mage(move, 0);
				}
				else {
					mage[i] = new Mage(move, (int)root.getHeight() - 50);
				}
			}
			//creat arrays of fireballs for each mage
			fireball0 = new Fireball [3];
			fireball1 = new Fireball [3];
			fireball2 = new Fireball [3];
			fireball3 = new Fireball [3];
			fireball4 = new Fireball [3];
			fireball5 = new Fireball [3];
			fireball6 = new Fireball [3];
			fireball7 = new Fireball [3];
			fireball8 = new Fireball [3];
			fireball9 = new Fireball [3];
			//initilaize their locations on top of the mages
			for(int i =0; i<fireball0.length;i++) {
				fireball0[i] =new Fireball(100,0,1);
				fireball1[i] =new Fireball(200,(int)root.getHeight() - 50,0);
				fireball2[i] =new Fireball(300,0,1);
				fireball3[i] =new Fireball(400,(int)root.getHeight() - 50,0);
				fireball4[i] =new Fireball(500,0,1);
				fireball5[i] =new Fireball(600,(int)root.getHeight() - 50,0);
				fireball6[i] =new Fireball(700,0,1);
				fireball7[i] =new Fireball(800, (int)root.getHeight() - 50,0);
				fireball8[i] =new Fireball(900,0,1);
				fireball9[i] =new Fireball(1000 ,(int)root.getHeight() - 50,0);
			}

			//initializing runner sword gaurd and the shapeshifter
			runner = new Runner(400,200);
			shapeshifter= new ShapeShifter((int)root.getWidth(), (int)root.getHeight());
			//			swordguard = new SwordGuard(0,0);

			//set up the boss
			boss = new Boss();
			boss.setLocation(1000,((int)(root.getHeight()/2-boss.getHeight()/2)));

			//checks for key press events to trigger booleans for movement
			scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				public void handle (KeyEvent e)
				{
					if (e.getCode() == KeyCode.D)
					{
						goRight = true; 
						//direwction to indicate change in sprite facing picture
						player.setDirection(player.EAST);


					}
					else if (e.getCode() == KeyCode.A)
					{
						goLeft = true;
						//direction to indicate change in sprite facing picture
						player.setDirection(player.WEST);

					}
					else if (e.getCode() == KeyCode.W)
					{
						goUp = true;

					}
					else if (e.getCode() == KeyCode.S)
					{
						goDown = true;

					}
					if(e.getCode() == KeyCode.SPACE) {

					}
				}
			});

			//once the key is let go of then the booleans are now false which stops player movement, also checks if the key being pressed was pressed during the tutorial
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				public void handle (KeyEvent e)
				{
					if (e.getCode() == KeyCode.D)
					{
						goRight = false; 
						if(tutorial==true)
							//if it was during tutorial then their first instruction is true meaning they did their first task
							ttlInst1=true;

					}
					else if (e.getCode() == KeyCode.A)
					{
						goLeft = false;
						if(tutorial==true)
							//if it was during tutorial then their first instruction is true meaning they did their first task
							ttlInst1=true;
					}
					else if (e.getCode() == KeyCode.W)
					{
						goUp = false;
						if(tutorial==true)//if it was during tutorial then their first instruction is true meaning they did their first task
							ttlInst1=true;
					}
					else if (e.getCode() == KeyCode.S)
					{
						goDown = false;
						if(tutorial==true)//if it was during tutorial then their first instruction is true meaning they did their first task
							ttlInst1=true;
					}
					//if they hit space during the tutorial or the game then shoot
					if(gameStart ==true || tutorial ==true) {
						if(e.getCode()==KeyCode.SPACE) {
							//if u have less than 3 bullets then add (numbullets counts index 0 as one bullet)
							if(numBullets<2) {
								numBullets++;
								bullets.add(numBullets, new Bullet());
								//set the bullet's position to the players gun relative to their direction and positioin
								if(player.getDir()==0) {
									bullets.get(numBullets).setPosition(player.getX()-50, player.getY()-15, player.getDir());

								}
								else {
									bullets.get(numBullets).setPosition(player.getX(), player.getY()-15, player.getDir());

								}
								//add to room
								root.getChildren().add(bullets.get(numBullets).getNode());
								//if theyre in tutorial then tell the game that they completed their second task but not their first one
								if(tutorial==true) {
									if(ttlInst1==true) {
										ttlInst1=false;
										ttlInst2=true;
									}
								}
							}
						}
					}
				}
			});

			//Villager movement so that they move in random directions
			KeyFrame kfVillager = new KeyFrame(Duration.millis(1000), new 
					EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					//if it is level one then create the villagers and give them a random number that will represent their direction
					if(levelOne==true) {
						for(int i =0;i<villagerslvl1.length;i++) {
							rnddir[i] = rnd.nextInt(4);

							if(rnddir[i]==0) {
								villagerslvl1[i].setDirection(0);
							}
							else if(rnddir[i]==1) {
								villagerslvl1[i].setDirection(180);
							}
							else if(rnddir[i]==2) {
								villagerslvl1[i].setDirection(90);
							}
							else {
								villagerslvl1[i].setDirection(270);
							}
						}
					}
					//if it it is level 2 then make the shapeshifter move in random directions by assigning it a number that will rep direction
					if(levelTwo==true) {
						int rando = rnd.nextInt(4);

						if(rando == 0) {
							shapeshifter.setDirection(90);
						}

						else if(rando == 1) {
							shapeshifter.setDirection(270);
						}

						else if(rando == 2) {
							shapeshifter.setDirection(0);
						}

						else {
							shapeshifter.setDirection(180);
						}

						//since the level is 2 then create the set of the level 2 villagers and give them each a random number that will give them their direction
						for(int i =0;i<villagerslvl2.length;i++) {
							rnddir2[i] = rnd.nextInt(4);

							if(rnddir2[i]==0) {
								villagerslvl2[i].setDirection(0);
							}
							else if(rnddir2[i]==1) {
								villagerslvl2[i].setDirection(180);
							}
							else if(rnddir2[i]==2) {
								villagerslvl2[i].setDirection(90);
							}
							else {
								villagerslvl2[i].setDirection(270);
							}
						}
					}
				}
			});

			//start the timer for villager
			villagerTimer = new Timeline(kfVillager);
			villagerTimer.setCycleCount(Timeline.INDEFINITE);

			moveAnimation = new AnimationTimer() {
				public void handle(long val) {
					//only start moving everything after the timer starts
					if(gameStart==true) {
						//if the key is being pressed, so when the boolean is true, move in the respective direction, also checks boundaries in if statements and make sure they cannot leave the room
						if (goUp == true)
						{
							player.moveNorth();
							if (player.getY() < 0)
							{
								player.setY(0);
							}
						}
						else if (goDown == true)
						{
							player.moveSouth();
							if (player.getY() + player.getHeight() > scene.getHeight())
							{
								player.setY((int)scene.getHeight() -(int)player.getHeight());
							}
						}
						else if (goLeft == true)
						{
							player.moveWest();
							if (player.getX() < 0)
							{
								player.setX(0);
							}
						}
						else if (goRight == true)
						{
							player.moveEast();
							if (player.getX() + player.getWidth() > scene.getWidth())
							{
								player.setX((int)scene.getWidth() - (int)player.getWidth());
							}
						}

						//update the location and draw it out
						player.getnode();

						if(levelOne==true) {
							//make villager move and also make sure the villager stays in the room
							for(int i =0;i<villagerslvl1.length;i++) {
								villagerslvl1[i].move();

								if (villagerslvl1[i].getY() < 0)
								{
									villagerslvl1[i].setY(0);
								}

								if (villagerslvl1[i].getY() + villagerslvl1[i].getHeight() > scene.getHeight())
								{
									villagerslvl1[i].setY((int)scene.getHeight() -(int)villagerslvl1[i].getHeight());
								}

								if (villagerslvl1[i].getX() < 0)
								{
									villagerslvl1[i].setX(0);
								}

								if (villagerslvl1[i].getX() + villagerslvl1[i].getWidth() > scene.getWidth())
								{
									villagerslvl1[i].setX((int)scene.getWidth() - (int)villagerslvl1[i].getWidth());
								}

								villagerslvl1[i].getnode();
							}
							//Runner run away from player, if player is to the left of runner then runner moves right
							//if player is more right of player runner moves left, if player higher than runner runner moves down
							//and if player is under runner than runner moves uyp
							if(runner.getX() + (runner.getWidth() / 2) < player.getX() + (player.getWidth() / 2)) {
								runner.setX((int)runner.getX() - 1);
								runner.setDirection(180);
							}
							else {
								runner.setX((int)runner.getX()+1); 
								runner.setDirection(0);
							}

							if(runner.getY() + (runner.getHeight() / 2) < player.getY() + (player.getHeight() / 2)) {
								runner.setY((int)runner.getY() - 1);
							}
							else {
								runner.setY((int)runner.getY()+1); 
							}

							//checks room boundaries to make sure runner doesnt leave
							if (runner.getY() < 0)
							{
								runner.setY(0);
							}

							if (runner.getY() + runner.getHeight() > scene.getHeight())
							{
								runner.setY((int)scene.getHeight() -(int)runner.getHeight());
							}

							if (runner.getX() < 0)
							{
								runner.setX(0);
							}

							if (runner.getX() + runner.getWidth() > scene.getWidth())
							{
								runner.setX((int)scene.getWidth() - (int)runner.getWidth());
							}

							runner.setLocation((int)runner.getX(), (int)runner.getY());
							runner.getnode();
						}

						//if its level two then make the villagers of level 2 move and also make sure they do not leave the room
						if(levelTwo==true) {
							for(int i =0;i<villagerslvl2.length;i++) {
								villagerslvl2[i].move();

								if (villagerslvl2[i].getY() < 0)
								{
									villagerslvl2[i].setY(0);
								}

								if (villagerslvl2[i].getY() + villagerslvl2[i].getHeight() > scene.getHeight())
								{
									villagerslvl2[i].setY((int)scene.getHeight() -(int)villagerslvl2[i].getHeight());
								}

								if (villagerslvl2[i].getX() < 0)
								{
									villagerslvl2[i].setX(0);
								}

								if (villagerslvl2[i].getX() + villagerslvl2[i].getWidth() > scene.getWidth())
								{
									villagerslvl2[i].setX((int)scene.getWidth() - (int)villagerslvl2[i].getWidth());
								}

								villagerslvl2[i].getnode();
							}
							//Shapeshifter movement and boundary check, shapeshiftmoves like villager
							shapeshifter.move();

							if (shapeshifter.getY() < 0)
							{
								shapeshifter.setY(0);
							}

							if (shapeshifter.getY() + shapeshifter.getHeight() > scene.getHeight())
							{
								shapeshifter.setY((int)scene.getHeight() -(int)shapeshifter.getHeight());
							}

							if (shapeshifter.getX() < 0)
							{
								shapeshifter.setX(0);
							}

							if (shapeshifter.getX() + shapeshifter.getWidth() > scene.getWidth())
							{
								shapeshifter.setX((int)scene.getWidth() - (int)shapeshifter.getWidth());
							}

							shapeshifter.getnode();

						}

						//if the level is 3 then make the boss move and also make sure it bounces off the walls, it will only bounce of top and bottom wall
						if(levelThree == true) {

							boss.move();

							if (boss.getY() < 0)
							{
								boss.setDirection(270);
							}

							else if(boss.getY() + boss.getHeight() > root.getHeight()) {
								boss.setDirection(90);
							}
							//update boss
							boss.getnode();

							//similar to the runner but its opposite, this makes the guard chase the player
							//if player is to the right of the guard then guard moves right, more left guard moves left
							// more higher up then guard moves up and if underneath then guard moves down
							//also set direction to make guard face direction of player
							for (int i = 0; i < guard.size(); i++) {

								if(guard.get(i).getX() + (guard.get(i).getWidth() / 2) < player.getX() + (player.getWidth() / 2)) {
									guard.get(i).setX(guard.get(i).getX() + 2);
									guard.get(i).setDirection(0);
								}
								else {
									guard.get(i).setX(guard.get(i).getX() - 2); 
									guard.get(i).setDirection(180);
								}

								if(guard.get(i).getY() + (guard.get(i).getHeight() / 2) < player.getY() + (player.getHeight() / 2)) {
									guard.get(i).setY(guard.get(i).getY() +2);
								}
								else {
									guard.get(i).setY(guard.get(i).getY() - 2);
								}

								//update image and location
								guard.get(i).setLocation(guard.get(i).getX(),guard.get(i).getY());
								guard.get(i).getnode();


							}

						}
					}
				}
			};
			animation = new AnimationTimer() {
				public void handle(long val) {


					//update lives		

					//only start moving everything after the timer starts
					if(gameStart==true) {

						//move the bullets
						if(numBullets>=0) {
							for(int i =0;i<bullets.size();i++) {
								bullets.get(i).move();
								bullets.get(i).getNode();


								//check if bullet leaves left side
								if (bullets.get(i).getX() + bullets.get(i).getWidth()<= 0){
									root.getChildren().remove(bullets.get(i).getNode());
									bullets.remove(i);
									numBullets--;
								}
								//if bullet leaves right side
								else if(bullets.get(i).getX() > (int)root.getWidth()) {
									root.getChildren().remove(bullets.get(i).getNode());
									bullets.remove(i);
									numBullets--;
								}
							}
							for(int i =0;i<bullets.size();i++) {
								//check if bullet hit runner and removes bullet and indicates that its time for level 2
								if(levelOne==true) {
									if(bullets.get(i).getNode().getBoundsInParent().intersects
											(runner.getnode().getBoundsInParent())) {
										root.getChildren().remove(bullets.get(i).getNode());
										bullets.remove(i);
										numBullets--;
										root.getChildren().removeAll(player.getnode(),runner.getnode(),lbl2);
										for(int index =0;index<villagerslvl1.length;index++) {
											root.getChildren().remove(villagerslvl1[index].getnode());
										}
										//let player know its level 2 with this label that will play unfer level timer
										levelOne=false;
										levelTwo=true;
										level2 = new Label("LEVEL TWO");
										level2.setTextFill(Color.BLACK);
										level2.setPrefSize(300, 50);
										level2.setAlignment(Pos.CENTER);
										level2.setFont(Font.font("Comic Sans",FontWeight.BOLD,40));
										level2.setLayoutX((scene.getWidth()/2-150));
										level2.setLayoutY(scene.getHeight()/2-25);
										root.getChildren().add(level2);
										scoreTimer.pause();
										levelTimer.play();
									}
								}
								//check if bullet hits shapeshifter once hes revealed
								else if (levelTwo==true) {
									if(bullets.get(i).getNode().getBoundsInParent().intersects
											(shapeshifter.getnode().getBoundsInParent())) {
										if(shapeshifter.isfound()==true) {
											root.getChildren().removeAll(player.getnode(),shapeshifter.getnode());
											for(int index =0;index<villagerslvl2.length;index++) {
												root.getChildren().remove(villagerslvl2[index].getnode());
											}
											//tells game that level2 is over and we are at boss stage and make a label telling the player that and then 
											//add boss stage elements and start the timer for the level timer which pops up a label indicating that ist boss time
											levelTwo=false;
											levelThree=true;
											level3= new Label("\tFINAL LEVEL: \nKILL EXCALIBUR TO WIN");
											level3.setTextFill(Color.BLACK);
											level3.setPrefSize(600, 100);
											level3.setAlignment(Pos.CENTER_LEFT);
											level3.setFont(Font.font("Comic Sans",FontWeight.BOLD,40));
											level3.setLayoutX((scene.getWidth()/2-150));
											level3.setLayoutY(scene.getHeight()/2-25);
											root.getChildren().removeAll(iviewBackground,lbllives,lbl2,lblTime);
											root.getChildren().addAll(iviewBoss,lbllives,lblTime);
											root.getChildren().add(level3);
											scoreTimer.pause();
											levelTimer.play();
										}

										root.getChildren().remove(bullets.get(i).getNode());
										bullets.remove(i);
										numBullets--;

									}
								}
								//if it is level 3 then check if any bullets hit any of the guards then remove them from the room
								else if(levelThree==true) {
									//if the bullet hit the boss then gameover is true
									if(bullets.get(i).getNode().getBoundsInParent().intersects
											(boss.getnode().getBoundsInParent())) {
										gameOver = true;
										break;
									}
								}
							}
							//check if bullet hit villager of level 1
							if(levelOne==true) {
								for(int i =0;i<villagerslvl1.length;i++) {
									for(int index =0;index<bullets.size();index++) {
										if(bullets.get(index).getNode().getBoundsInParent().intersects(
												villagerslvl1[i].getnode().getBoundsInParent())) {

											//remove the bullet also remove a life
											root.getChildren().remove(bullets.get(index).getNode());
											bullets.remove(index);
											numBullets--;
											lives --;
											if(lives == 0) {

												gameOver = true;

											}
											lbllives.setText("Your lives:" + lives);
										}
									}
								}
							}
							//if its level 2 then check if any bullets hit the vilagers of level 2
							if (levelTwo==true) {
								for(int i =0;i<villagerslvl2.length;i++) {
									for(int index =0;index<bullets.size();index++) {
										if(bullets.get(index).getNode().getBoundsInParent().intersects(
												villagerslvl2[i].getnode().getBoundsInParent())) {

											//remove the bullet
											root.getChildren().remove(bullets.get(index).getNode());
											bullets.remove(index);
											numBullets--;
											lives --;
											if(lives == 0) {
												gameOver = true;

											}
											lbllives.setText("Your lives:" + lives);
										}
									}
								}
							}
						}
						//if it is level 3 then 
						if(levelThree==true) {
							//checks the x of the player and then will face the way the player is so if player is more right then set the direction of the mage right
							//vice versa with left then update the node so he faces the player
							for(int i =0;i<mage.length;i++) {

								if(player.getX() + player.getWidth() /2 > mage[i].getX() + mage[i].getWidth()) {
									mage[i].setDirection(0);

								}
								else {
									mage[i].setDirection(180);
								}
								mage[i].getnode();
							}
							for (int i2 = 0; i2 < guard.size(); i2++)
							{
								for(int i = 0;i<bullets.size();i++) {
									if (bullets.get(i).getNode().getBoundsInParent().intersects
											(guard.get(i2).getnode().getBoundsInParent())) {
										root.getChildren().remove(guard.get(i2).getnode());
										guard.remove(i2);
										guardcount--;
										root.getChildren().remove(bullets.get(i).getNode());
										bullets.remove(i);
										numBullets--;
										break;
									}
								}
							}
							//checks if the player ever collides with any guard and if they do then the guard is removed form the list and they lose a life
							for (int i2 = 0; i2 < guard.size(); i2++)
							{
								if (player.getnode().getBoundsInParent().intersects(guard.get(i2).getnode().getBoundsInParent())) {


									root.getChildren().remove(guard.get(i2).getnode());
									guard.remove(i2);
									guardcount--;

									lives--;
									//if lives are 0 then game ends and also update lives on label
									if(lives == 0) {
										gameOver = true;
									}
									lbllives.setText("Your lives:" + lives);
								}
							}



						}

						//check collision with shapeshifter and player

						if (player.getnode().getBoundsInParent().intersects(shapeshifter.getnode().getBoundsInParent())) {

							shapeshifter.found();

						}
					}

					Platform.runLater(new Runnable() {
						public void run()
						{//since game over is true that means they either dieed or beat the game then stop the timers to prevent further animation/spawning
							if (gameOver == true)

							{
								scoreTimer.pause();
								villagerTimer.stop();
								animation.stop();
								moveAnimation.stop();
								guardTimer.stop();
								fireAnimation.stop();
								//create and show and alert telling the user they have died
								//asked if they want to leave, if yes program ends if not thenthey are returned to hoime screen

								//if they have less than or equal to 0  lives and game ended  then they are told they died
								if(lives <= 0) {


									Alert alert = new Alert(AlertType.CONFIRMATION);

									ButtonType bt1 = new ButtonType("Menu");
									ButtonType bt2 = new ButtonType("Quit");

									alert.getButtonTypes().clear();
									alert.getButtonTypes().addAll(bt1,bt2);
									alert.setHeaderText(null);
									alert.setTitle("Game Over");
									alert.setGraphic(player.getnode());
									alert.setContentText("You died\nDo you want to quit?");
									Optional<ButtonType> result = alert.showAndWait();
									//if they click yes
									if(result.get() == bt1) {
										restart();
									}
									else {
										System.exit(0);		
									}
								}

								//if they win meaning they have more than 0 lives when game ends then they are congratualted
								else if (lives > 0) {
									//tells them they beat the game flawlessly
									try {
										File dataFile = new File("scores.txt"); 
										BufferedWriter out = new BufferedWriter(new FileWriter(dataFile, 
												true));
										BufferedWriter writeFile = new BufferedWriter(out);
										//										BufferedWriter out = new BufferedWriter(new FileWriter(datafile, 
										//												true));

										//add the name to the file
										writeFile.write(name); 
										writeFile.newLine();

										//close writer
										writeFile.close(); 
										out.close(); 

										FileReader in = new FileReader(dataFile); 
										BufferedReader readFile = new BufferedReader(in);

										//make string that represents the text on the line in the file
										String lineOfText;

										try 
										{ 
											// Will read each line until the end of the file. 
											while ((lineOfText = readFile.readLine())!= null) 
											{ 
												// Outputs the line that was read.
												names.add(lineOfText.toString());

											} 
											readFile.close(); //Close BufferedReader
											in.close(); // Close the FileReader
										} 

										catch(Exception e) {
											e.printStackTrace();
										}

									}

									catch(Exception e) {
										e.printStackTrace();
									}

									System.out.println("Data written to file.");


									try {
										//access file
										File dataFile = new File("scores.txt"); 
										BufferedWriter out = new BufferedWriter(new FileWriter(dataFile, 
												true));
										BufferedWriter writeFile = new BufferedWriter(out);
										//									BufferedWriter out = new BufferedWriter(new FileWriter(datafile, 
										//											true));

										//add the name to the file
										writeFile.write(df.format(minutes) + ":" + df.format(scoreSeconds)); 
										writeFile.newLine();

										//close writer
										writeFile.close(); 
										out.close(); 
									}
									catch(Exception e) {
										e.printStackTrace();
									}

									//SCORE CHECKING
									try 
									{
										//clear the arrays for score and name so that we can make sure we dont read stuff from before
										scores.clear();
										names2.clear();
										File dataFile = new File("scores.txt"); 
										FileReader in = new FileReader (dataFile);
										BufferedReader readFile = new BufferedReader(in);

										FileWriter filewriter = new FileWriter(dataFile, true) ;
										BufferedWriter output = new BufferedWriter(filewriter);

										//text will represent the line its reading in the file
										String text;
										//scorename will store everything in the file and then read thorugh the file and add the line it is at
										while((text = readFile.readLine()) != null) {
											scorename.add(text);
										}

										//seperastes scores from the names
										for( int f = 0; f < scorename.size(); f++) {
											if(f % 2 == 0) {
												//every even line which is a name is added to the name array list
												names2.add(scorename.get(f));
											}
											else {
												//any of the odd lines are put onto the scores because it is scores
												scores.add(scorename.get(f));
											}
										}
									}

									catch (IOException e) 
									{ 
										System.out.println("Problem writing to file."); 
										System.err.println("IOException: "+ e.getMessage()); 
									} 



									//scores2 will store the content of the scores so that we cna sort it later
									scores2 = new String[scores.size()];

									for(int i = 0; i < scores.size(); i++) {

										scores2[i] = scores.get(i);
									}

									//sort the scores
									BubbleSort(scores2);

									//any vacant spots for the leaderboards are shown as dashes
									scorelength = scores2.length;
									for(int rows =0;rows<5;rows++) {
										if(rows>=scorelength) {
											scoreboard[rows][1] = "--";
											scoreboard[rows][0] = "--";
										}
										else {
											//if there is a score there then check which name the score corresponds to
											scoreboard[rows][1] = scores2[rows];
											scoreboard[rows][0] = names2.get(scores.indexOf(scores2[rows]));
											scores.set(scores.indexOf(scores2[rows]), "done");

										}
									}
									//finaln and finals are to seperately get the score and name of each place so 1st place will have an finaln finals n = name s = scoret
									for(int rows =0;rows<scoreboard.length;rows++) {
										finalN = (rows+1)+"."+scoreboard[rows][0];
										finalS = scoreboard[rows][1];
										namehighscores[rows].setText(finalN);
										highscores[rows].setText(finalS);
									}
									//alert for gameover

									Alert alert = new Alert(AlertType.CONFIRMATION);
									//custom button names
									ButtonType bt1 = new ButtonType("Menu");
									ButtonType bt2 = new ButtonType("Quit");
									
									//set up for the alert
									alert.getButtonTypes().clear();
									alert.getButtonTypes().addAll(bt1, bt2);
									alert.setHeaderText(null);
									alert.setTitle("Game Over");
									alert.setGraphic(player.getnode());
									//they are congratulated cause they lost no lives
									if(lives == 3) {
										alert.setContentText("Wow you beat the game without dying once!\nDo you want to quit?");
									}
									else {
										//regular congratulations
										alert.setContentText("Wow you beat the game\nDo you want to quit?");
									}

									Optional<ButtonType> result = alert.showAndWait();
									//if they click yes
									if(result.get() == bt1) {

										restart();

									}

									else{
										System.exit(0);
									}
								}
							}

						}
					});
				}
			};
			fireAnimation = new AnimationTimer() {

				//moves and updates every even fireball and checks to see if they leave the room or if they hit the player. it updates lives if they do hit player
				//also fireballs are moved back to their mage upon collision
				public void handle(long val) {
					if(levelThree==true) {
						for(int i = 0;i<fireball0.length;i++) {
							fireball0[i].move();
							fireball6[i].move();
							fireball4[i].move();
							fireball8[i].move();
							fireball2[i].move();

							fireball0[i].getNode();
							fireball6[i].getNode();
							fireball4[i].getNode();
							fireball8[i].getNode();
							fireball2[i].getNode();
							if(fireball0[i].getY() > root.getHeight()) {
								fireball0[i].setY(0);
							}
							else if(fireball0[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball0[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}
							if(fireball6[i].getY() > root.getHeight()) {
								fireball6[i].setY(0);
							}
							else if(fireball6[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball6[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}
							if(fireball4[i].getY() > root.getHeight()) {
								fireball4[i].setY(0);

							}
							else if(fireball4[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball4[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}

							if(fireball8[i].getY() > root.getHeight()) {
								fireball8[i].setY(0);
							}
							else if(fireball8[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball8[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}

							if(fireball2[i].getY() > root.getHeight()) {
								fireball2[i].setY(0);
							}
							else if(fireball2[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball2[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}
						}

						//moves and updates every fireball that is odd
						for(int i = 0;i<fireball3.length;i++) {
							fireball3[i].move();
							fireball1[i].move();
							fireball5[i].move();
							fireball7[i].move();
							fireball9[i].move();

							fireball3[i].getNode();
							fireball1[i].getNode();
							fireball5[i].getNode();
							fireball7[i].getNode();
							fireball9[i].getNode();

							//checks if the fireballs leaves the room and also checks if the fireball hits the player and updates lives if they do hit
							//if they are hit game is now over if lives == 0
							if(fireball3[i].getY() < 0) {
								fireball3[i].setY((int)(root.getHeight()-50));
							}
							else if(fireball3[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball3[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}

							if(fireball1[i].getY() < 0) {
								fireball1[i].setY((int)(root.getHeight()-50));
							}
							else if(fireball1[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball1[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}

							if(fireball5[i].getY() < 0) {
								fireball5[i].setY((int)(root.getHeight()-50));
							}
							else if(fireball5[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball5[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}

							if(fireball7[i].getY() < 0) {
								fireball7[i].setY((int)(root.getHeight()-50));
							}
							else if(fireball7[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball7[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}

							if(fireball9[i].getY() < 0) {
								fireball9[i].setY((int)(root.getHeight()-50));
							}
							else if(fireball9[i].getNode().getBoundsInParent().intersects(player.getnode().getBoundsInParent()))  {
								fireball9[i].setY(0);
								lives--;
								if(lives == 0) {
									gameOver = true;
								}
								lbllives.setText("Your lives:" + lives);
							}
						}

					}
				}
			};
			//movement for player which moves them in their respective direction while also boundary checking and making sure they dont leave the room
			ttlAnimation = new AnimationTimer() {
				public void handle(long val) {

					if (goUp == true)
					{
						player.moveNorth();
						if (player.getY() < 0)
						{
							player.setY(0);
						}
					}
					else if (goDown == true)
					{
						player.moveSouth();
						if (player.getY() + player.getHeight() > scene.getHeight())
						{
							player.setY((int)scene.getHeight() -(int)player.getHeight());
						}
					}
					else if (goLeft == true)
					{
						player.moveWest();
						if (player.getX() < 0)
						{
							player.setX(0);
						}
					}
					else if (goRight == true)
					{
						player.moveEast();
						if (player.getX() + player.getWidth() > scene.getWidth())
						{
							player.setX((int)scene.getWidth() - (int)player.getWidth());
						}
					}
					player.getnode();

					//move the bullets
					if(numBullets>=0) {
						for(int i =0;i<bullets.size();i++) {
							bullets.get(i).move();
							bullets.get(i).getNode();


							//check if bullet leaves left side
							if (bullets.get(i).getX() + bullets.get(i).getWidth()<= 0){
								root.getChildren().remove(bullets.get(i).getNode());
								bullets.remove(i);
								numBullets--;
							}
							//if bullet leaves right side
							else if(bullets.get(i).getX() > (int)root.getWidth()) {
								root.getChildren().remove(bullets.get(i).getNode());
								bullets.remove(i);
								numBullets--;
							}
						}
						if(ttlInst2==true) {
							//if bullet in tutorial hits runner 
							for(int i =0;i<bullets.size();i++) {
								if(bullets.get(i).getNode().getBoundsInParent().intersects
										(ttlRunner.getnode().getBoundsInParent())) {
									root.getChildren().remove(bullets.get(i).getNode());
									bullets.remove(i);
									numBullets--;
									root.getChildren().remove(ttlRunner.getnode());

									ttlInst2=false;
									ttlInst3=true;
								}
							}
						}
					}
					//if we are at the third instruction then set the text to the next instruction regading shoot villager
					if(ttlInst3==true) {
						lblInstructions.setText("Make sure not to shoot the Villagers or you will lose one of\nyour lives!\n"
								+ " You only have 3 and if you lose them all you lose the game!");
						lblVillager.setText("<- Villager");

						levelTimer.play();
					}
					//if we are at the second instruction then set the text to the next instruction regarding shooting the enemy
					else if(ttlInst2==true) {
						lblInstructions.setLayoutX(400);
						lblInstructions.setText("Shoot the enemy to go to the next level. (each level has a\ndifferent enemy that does something different.");
					}	
					//if we are at the first instruction then tell suer to use space to shoot
					else if(ttlInst1==true) {
						lblInstructions.setText("Press Space Bar to shoot");
					}


				}

			};


			primaryStage.setScene(scene);
			primaryStage.show();

			//timer for tutorial which will display for 5 seconds before disappearing it also ends tutorial at 5 seconds
			KeyFrame kfTimer = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					seconds++;
					if(tutorial==true) {
						if(seconds%5==0) {
							ttlInst3=false;

							lblInstructions.setText("You have now finished the tutorial press\nthe 'SPACE' bar to go back to the menu");
							btnBack.setFocusTraversable(true);
							tutorial=false;
							//stop animtion and timers for the tutorial because it is now over
							levelTimer.stop();
							ttlAnimation.stop();
						}
					}				
					//if we are at 2 seconds then
					else if(seconds%2==0) {
						//start the game w this boolean
						gameStart=true;
						if(levelOne==true) {
							//remove the label that said level 1 then show the label with the hint
							root.getChildren().remove(level1);
							lbl2 = new Label();
							lbl2.setTextFill(Color.BLACK);
							lbl2.setText("Your Target will run away, corner him");
							lbl2.setPrefSize(400, 60);
							lbl2.setFont(Font.font("Comic Sans",FontWeight.BOLD,20));
							lbl2.setAlignment(Pos.CENTER);
							//lbl2.setStyle("-fx-background-color: AQUA");
							lbl2.setLayoutX(0);
							lbl2.setLayoutY(0);

							//label to display player's lives
							lbllives = new Label();
							lbllives.setTextFill(Color.BLACK);
							lbllives.setText("Your lives: " + lives);
							lbllives.setPrefSize(400, 60);
							lbllives.setFont(Font.font("Comic Sans",FontWeight.BOLD,30));
							lbllives.setAlignment(Pos.CENTER);
							lbllives.setLayoutX(800);
							lbllives.setLayoutY(0);
							//this is for next respawn when level advances to make sure player spawns at 100,100
							player.setLocation(100,100);

							//add player and runner and labels stop currents timers and start next ones for the next stage
							root.getChildren().addAll(player.getnode(),runner.getnode());
							for(int i =0;i<villagerslvl1.length;i++) {
								root.getChildren().add(villagerslvl1[i].getnode());
							}
							root.getChildren().addAll(lbl2, lbllives,lblTime);
							scoreTimer.playFromStart();
							levelTimer.stop();
						}
						//if we are at level 2
						else if(levelTwo==true) {
							//get rid of level2 label 
							root.getChildren().remove(level2);
							player.setLocation(0, 0);
							root.getChildren().addAll(player.getnode(),shapeshifter.getnode());
							for(int i =0;i<villagerslvl2.length;i++) {
								root.getChildren().add(villagerslvl2[i].getnode());
							}
							shapeshifter.notfound();
							lbl2.setText("Your target is blending in\nMake contact with the fake to undo their spell");
							lbl2.setPrefSize(600, 60);
							root.getChildren().add(lbl2);
							scoreTimer.play();
							levelTimer.stop();
						}
						else if (levelThree==true) {
							for(int i =0;i<mage.length;i++) {
								root.getChildren().add(mage[i].getnode());
							}
							root.getChildren().remove(level3);
							player.setLocation(0, (int)(root.getHeight()/2-player.getHeight()/2));
							root.getChildren().addAll(player.getnode(),boss.getnode());
							guardTimer.play();
							fireTimer.play();
							scoreTimer.play();
							fireAnimation.start();
							levelTimer.stop();
						}

					}

				}
			});
			levelTimer = new Timeline(kfTimer);
			levelTimer.setCycleCount(Timeline.INDEFINITE);

			//keyframe timer that is responsible for the shootingof the fireballs,
			KeyFrame kffireball = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if(levelThree==true) {

						//if the seconds is divisible of 3 then fire fireball for mages 0 3 and 6
						//this makes it so they dont all shoot the same way so the game is harder
						if(seconds2%3==0) {
							if(counter<3) {
								fireball0[counter].fire();
								fireball3[counter].fire();
								fireball6[counter].fire();
								root.getChildren().addAll(fireball0[counter].getNode(),fireball3[counter].getNode(),fireball6[counter].getNode());
								counter++;
							}
						}
						//if its divisible by 4 then fire fireball for mages 1 4 and 7
						else if(seconds2%4==0) {
							if(counter1<3) {
								fireball1[counter1].fire();
								fireball4[counter1].fire();
								fireball7[counter1].fire();
								root.getChildren().addAll(fireball1[counter1].getNode(),fireball4[counter1].getNode(),fireball7[counter1].getNode());
								counter1++;
							}
						}
						//if divisible by 5 then fire for mages 2 5 and 8
						else if(seconds2%5==0) {
							if(counter2<3) {
								fireball2[counter2].fire();
								fireball5[counter2].fire();
								fireball8[counter2].fire();
								root.getChildren().addAll(fireball2[counter2].getNode(),fireball5[counter2].getNode(),fireball8[counter2].getNode());
								counter2++;
							}
						}
						//if div by 86 then fire for mage 9
						else if(seconds2%6==0) {
							if(counter3<3) {
								fireball9[counter3].fire();
								root.getChildren().add(fireball9[counter].getNode());
							}
						}
						seconds2++;
					}
				}
			});
			fireTimer = new Timeline(kffireball);
			fireTimer.setCycleCount(Timeline.INDEFINITE);

			//spawner for the guards which only happens when we are at level 3 and spawns one every 3 secs or 3000 ms
			KeyFrame kfGuard = new KeyFrame(Duration.millis(3000), new 
					EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if(levelThree==true) {
						guardcount++;
						guard.add(guardcount, new SwordGuard((int)boss.getX() + (int)boss.getWidth()/2, (int)boss.getY() + (int)boss.getHeight()/2));
						//guard.get(guardcount).setPosition((int)boss.getWidth() /2, (int)boss.getHeight()/2);
						root.getChildren().add(guard.get(guardcount).getnode());
					}
				}
			});

			//start the timer for the spawner
			guardTimer = new Timeline(kfGuard);
			guardTimer.setCycleCount(Timeline.INDEFINITE);

			//keep track of the amount of time the user takes
			KeyFrame kfScore = new KeyFrame(Duration.millis(1000), new 
					EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					scoreSeconds++;
					//update time
					lblTime.setText("Time: " + df.format(minutes)+":"+df.format(scoreSeconds));
					if(scoreSeconds==60) {
						scoreSeconds=0;
						minutes++;
					}
				}
			});			
			scoreTimer = new Timeline(kfScore);
			scoreTimer.setCycleCount(Timeline.INDEFINITE);






		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void btnStart_Click() {
		//make an alert if the user enters nothing
		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		if (txtfield.getText().equals(""))
		{
			//user enters nothing show them the alert and ask them to enter a word or sentence
			alert.setContentText("You need to enter a name");
			alert.setTitle("Error");
			alert.showAndWait();
		}
		else {
			//initialize the name array list and also get name from user in textfield
			names = new ArrayList<String>();
			name = txtfield.getText();

			//this triggers the timers and also lets the game know we have started the game at level one and not the tutorial with the boolean levelOne
			root.getChildren().removeAll(btnStart,btnTtl,txtfield, iviewStart, lblTitle, lblName,lblHighScore);
			for(int i =0;i<scoreboard.length;i++) {
				root.getChildren().removeAll(highscores[i],namehighscores[i]);
			}
			root.getChildren().add(iviewBackground);
			levelOne=true;
			//indicating the level
			level1 = new Label("LEVEL ONE");
			level1.setTextFill(Color.BLACK);
			level1.setPrefSize(300, 50);
			level1.setAlignment(Pos.CENTER);
			level1.setFont(Font.font("Comic Sans",FontWeight.BOLD,40));
			level1.setLayoutX((scene.getWidth()/2-150));
			level1.setLayoutY(scene.getHeight()/2-25);
			root.getChildren().add(level1);
			levelTimer.play();
			moveAnimation.start();
			animation.start();
			villagerTimer.play();
		}
	}
	private void btnTtl_Click() {
		//removes the stuff from start screen and lets the game know we are doing tutoriasl  by using tutorial boolean
		root.getChildren().removeAll(btnStart,btnTtl,txtfield,lblTitle,lblName,lblHighScore);
		for(int i =0;i<scoreboard.length;i++) {
			root.getChildren().removeAll(highscores[i],namehighscores[i]);
		}
		tutorial=true;

		lblInstructions = new Label("Use the WASD Keys to move");
		lblInstructions.setTextFill(Color.BLACK);
		lblInstructions.setPrefSize(600,60);
		lblInstructions.setAlignment(Pos.CENTER);
		lblInstructions.setFont(Font.font("Comic Sans",FontWeight.BOLD,20));
		lblInstructions.setLayoutX(200);
		lblInstructions.setLayoutY(250);

		//make label and set up items for the tutorial
		lblVillager = new Label ("");
		lblVillager.setTextFill(Color.BLACK);
		lblVillager.setPrefSize(200,30);
		lblVillager.setAlignment(Pos.CENTER);
		lblVillager.setFont(Font.font("Comic Sans",FontWeight.BOLD,20));
		lblVillager.setLayoutY(500);
		lblVillager.setLayoutX(530);

		player.setLocation(150, 250);
		ttlRunner.setLocation(500, 400);
		ttlRunner.setDirection(180);
		ttlVillager.setLocation(500, 500);

		//add them to the room
		root.getChildren().addAll(player.getnode(), ttlVillager.getnode(), ttlRunner.getnode(),btnBack,lblInstructions,lblVillager);
		ttlAnimation.start();
	}
	private void btnBack_Click() {
		//ends the tutorial and removes everything in the room and stops the timers for the tutorial
		ttlAnimation.stop();
		levelTimer.stop();
		ttlInst1=false;
		ttlInst2=false;
		ttlInst3=false;
		tutorial=false;
		btnBack.setFocusTraversable(false);
		root.getChildren().removeAll(player.getnode(), ttlVillager.getnode(), ttlRunner.getnode(),btnBack, lblInstructions,lblVillager);
		root.getChildren().addAll(btnStart,btnTtl,txtfield,lblTitle,lblName,lblHighScore);
		for(int i =0;i<scoreboard.length;i++) {
			root.getChildren().addAll(highscores[i],namehighscores[i]);
		}
	}
	private void restart() {
		//check what level the game ended in and remove whatever was in the room at the time and also 
		//make the boolean flase for the level indicating it is no longer that level
		if(levelOne==true) {
			levelOne=false;
		}
		if(levelTwo==true) {
			levelTwo=false;
		}
		if(levelThree==true) {
			//if we are in level 3 when its restart time thn we remove eveyrhting that was in the room ex boss and lavbels
			root.getChildren().removeAll(player.getnode(),boss.getnode(),iviewBoss,lbllives,lblTime);
			//remove all guards
			for(int i =0; i<guard.size();i++) {
				root.getChildren().remove(guard.get(i).getnode());
				guard.remove(i);
				guardcount--;
			}
			//remove bullets
			for(int i =0; i<bullets.size();i++) {
				root.getChildren().remove(bullets.get(i).getNode());
				bullets.remove(i);
				numBullets--;
			}
			//we are no longer level 3 so set i false and reset counters
			levelThree=false;
			counter=0;
			counter1=0;
			counter2=0;
			counter3=0;


		}
		root.getChildren().clear();
		//reset the booleans timers and array list
		gameStart=false;
		gameOver=false;
		goRight=false;
		goLeft=false;
		goUp=false;
		goDown=false;
		minutes =0;
		scoreSeconds=0;
		scorename.clear();
		scores.clear();
		names2.clear();

		//update time to 0
		lblTime.setText(df.format(minutes)+":"+df.format(scoreSeconds));

		//remove any remiaining bullets 
		if(numBullets>0) {
			for(int i=0;i<numBullets;i++) {
				root.getChildren().remove(bullets.get(i).getNode());
				bullets.remove(i);
				numBullets--;
			}
		}
		//reset all the people...
		player= new Player(100,100);
		//creating an array of villagers for level one
		villagerslvl1 = new Villager[10];
		for(int i =0;i<villagerslvl1.length;i++) {
			villagerslvl1[i]=new Villager((int)root.getWidth(), (int)root.getHeight());
		}

		//creating an array of villagers for level two
		villagerslvl2 = new Villager[15];
		for(int i =0;i<villagerslvl2.length;i++) {
			villagerslvl2[i]=new Villager((int)root.getWidth(), (int)root.getHeight());
		}

		//creating an array of mages
		mage = new Mage[10];
		int move = 0;
		for(int i = 0; i < mage.length; i++) {

			move += 100;


			if(i % 2 == 0) {
				mage[i] = new Mage(move, 0);
			}

			else {
				mage[i] = new Mage(move, (int)root.getHeight() - 50);
			}

		}
		//creating all the fireballs
		for(int i =0; i<fireball0.length;i++) {
			fireball0[i] =new Fireball(100,0,1);
			fireball1[i] =new Fireball(200,(int)root.getHeight() - 50,0);
			fireball2[i] =new Fireball(300,0,1);
			fireball3[i] =new Fireball(400,(int)root.getHeight() - 50,0);
			fireball4[i] =new Fireball(500,0,1);
			fireball5[i] =new Fireball(600,(int)root.getHeight() - 50,0);
			fireball6[i] =new Fireball(700,0,1);
			fireball7[i] =new Fireball(800, (int)root.getHeight() - 50,0);
			fireball8[i] =new Fireball(900,0,1);
			fireball9[i] =new Fireball(1000 ,(int)root.getHeight() - 50,0);
		}

		//initializing runner sword gaurd and the shapeshifter
		runner = new Runner(400,200);
		shapeshifter= new ShapeShifter((int)root.getWidth(), (int)root.getHeight());
		shapeshifter.notfound();

		//set up the boss
		boss = new Boss();
		boss.setLocation(1000,((int)(root.getHeight()/2-boss.getHeight()/2)));
		//giving player 3 lives again
		lives =3;
		//clear textfield
		txtfield.clear();
		//home screen buttons
		root.getChildren().addAll(iviewStart,btnStart,btnTtl, txtfield, lblTitle, lblName,lblHighScore);
		for(int i =0;i<scoreboard.length;i++) {
			root.getChildren().addAll(highscores[i],namehighscores[i]);
		}
	}

	//for sorting from smallest to biggest
	public static void BubbleSort(String[] data) {
		boolean done = false;
		for(int end = data.length - 1; end > 0 && !done; end-- ) {

			done = true;
			for(int i = 0; i < end; i++) {
				if (data[i].compareTo(data[i+1]) > 0) {
					done = false;
					String temp = data[i];
					data[i]= data[i+1];
					data[i+1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
