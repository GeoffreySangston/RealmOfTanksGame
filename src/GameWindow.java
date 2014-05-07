import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;




public class GameWindow{
	final static int windowWidth = 700; // plan to make these smaller than the "RealmOfTanks
	final static int windowHeight = 700;// class SCREENWIDTH in order to provide space for a menu or gui or something
	final static int colosseumWidth = 960;
	final static int colosseumHeight = 960;
	final int MENU = 0;
	final int GAME = 1;

	int curRoundNum;
	int curRoundSpawn;
	int numRounds;
	int roundMonstersKilled;
	long lastDamageTime;
	
	AudioClip audioClip;
	
	ScreenText roundText;
	
	Round curRound;

	Timer gameTimer;
	//MiniMap miniMap;
	HealthDisplay retroHealthDisp;

	boolean exit;
	boolean userDead;
	boolean keyDownW;
	boolean keyDownS;
	boolean keyDownA;
	boolean keyDownD;
	boolean keyDownQ;
	boolean keyDownE;
	boolean keyDownSpace;
	boolean mouseDownLeft;

	boolean enoccurred = false;

	ArrayList<WorldObject> worldObjects;
	ArrayList<Integer> removeList;
	double cameraRad;
	int category;
	double userSpeedX;
	double userSpeedY;
	double userTheta;
	double[] nullArr = {-1.0};

	PlayerTank playerTank;

	Texture aimer = Load.texture("res/crossHair.png", this);
	Texture playerTex = Load.texture("res/tankPlaceholder.png", this);
	Texture missleTex = Load.texture("res/missle.png", this);
	Texture missleTex2 = Load.texture("res/missle2.png", this);
	Texture followTankTex = Load.texture("res/enemyTank.png", this);
	Texture barrierTex = Load.texture("res/barrier.png", this);
	Texture spinTankTex = Load.texture("res/spinTank.png", this);
	Texture shootFollowTex = Load.texture("res/enemyTank2.png", this);
	Texture courierFont = Load.texture("res/courierFont.png", this);
	Texture splitTest = Load.texture("res/splitTest.png", this);

	public GameWindow(){
		
		
		gameTimer = new Timer();
		exit = false;
		keyDownW = false;
		keyDownS = false;
		keyDownA = false;
		keyDownD = false;
		keyDownSpace = false;
		mouseDownLeft = false;
		
		try {
			audioClip = Applet.newAudioClip(new URL("file:res/Skipsalot_Soundcloud.wav"));
			audioClip.loop();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		cameraRad = windowWidth/2;

		curRoundSpawn = 0; // position in the spawnPacks array
		curRoundNum = 0;
		numRounds = 3; 
		category = 0;
		roundMonstersKilled = 0;
		userDead = false;
		
		lastDamageTime = 0;

		playerTank = new PlayerTank(playerTex,0,0,0,gameTimer);


		worldObjects = new ArrayList<WorldObject>();
		removeList = new ArrayList<Integer>();
		worldObjects.add(playerTank);
		
		createWalls();

		//miniMap = new MiniMap(worldObjects,playerTank);
		retroHealthDisp = new HealthDisplay(0);

		loadNextRound(); // loads the first round

	}

	public void loadNextRound(){
		// going to change this to loading from a file but now just going to manually declare stuff
		ArrayList<Integer> spawnTimes = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> spawnPacks = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> spawnPacksX = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> spawnPacksY = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> placeholderPack = new ArrayList<Integer>();
		placeholderPack.add(1);
		placeholderPack.add(0);
		placeholderPack.add(1);
		placeholderPack.add(0);
		placeholderPack.add(1);
		placeholderPack.add(0);
		placeholderPack.add(1);
		placeholderPack.add(0);


		ArrayList<Integer> placeholderPackX = new ArrayList<Integer>();
		placeholderPackX.add(-485);
		placeholderPackX.add(-245);
		placeholderPackX.add(-470);
		placeholderPackX.add(-235);
		placeholderPackX.add(485);
		placeholderPackX.add(245);
		placeholderPackX.add(475);
		placeholderPackX.add(235);

		ArrayList<Integer> placeholderPackY = new ArrayList<Integer>();
		placeholderPackY.add(-485);
		placeholderPackY.add(-245);
		placeholderPackY.add(-470);
		placeholderPackY.add(-235);
		placeholderPackY.add(485);
		placeholderPackY.add(245);
		placeholderPackY.add(475);
		placeholderPackY.add(235);
		
		ArrayList<Integer> placeholderPackTwo = new ArrayList<Integer>();
		placeholderPackTwo.add(0);
		placeholderPackTwo.add(0);
		
		ArrayList<Integer> placeholderPackTwoX = new ArrayList<Integer>();
		placeholderPackTwoX.add(-245);
		placeholderPackTwoX.add(245);


		ArrayList<Integer> placeholderPackTwoY = new ArrayList<Integer>();
		placeholderPackTwoY.add(245);
		placeholderPackTwoY.add(-245);
		
		ArrayList<Integer> placeholderPackThree = new ArrayList<Integer>();
		placeholderPackThree.add(2);

		
		ArrayList<Integer> placeholderPackThreeX = new ArrayList<Integer>();
		placeholderPackThreeX.add(0);

		ArrayList<Integer> placeholderPackThreeY = new ArrayList<Integer>();
		placeholderPackThreeY.add(0);
		
		ArrayList<Integer> placeholderPackFour = new ArrayList<Integer>();
		placeholderPackFour.add(2);
		placeholderPackFour.add(2);
		placeholderPackFour.add(2);
		placeholderPackFour.add(2);
		
		ArrayList<Integer> placeholderPackFourX = new ArrayList<Integer>();
		placeholderPackFourX.add(-400);
		placeholderPackFourX.add(-400);
		placeholderPackFourX.add(400);
		placeholderPackFourX.add(400);

		ArrayList<Integer> placeholderPackFourY = new ArrayList<Integer>();
		placeholderPackFourY.add(-400);
		placeholderPackFourY.add(400);
		placeholderPackFourY.add(-400);
		placeholderPackFourY.add(400);


	
		for(int i = 0; i < 1; i++){
			spawnTimes.add(i*1000);
			spawnPacks.add(placeholderPack);
			spawnPacksX.add(placeholderPackX);
			spawnPacksY.add(placeholderPackY);
		}
		spawnTimes.add(3000);
		spawnPacks.add(placeholderPackTwo);
		spawnPacksX.add(placeholderPackTwoX);
		spawnPacksY.add(placeholderPackTwoY);
		
		spawnTimes.add(4000);
		spawnPacks.add(placeholderPackThree);
		spawnPacksX.add(placeholderPackThreeX);
		spawnPacksY.add(placeholderPackThreeY);
		
		spawnTimes.add(5000);
		spawnPacks.add(placeholderPackTwo);
		spawnPacksX.add(placeholderPackTwoX);
		spawnPacksY.add(placeholderPackTwoY);
		
		spawnTimes.add(7000);
		spawnPacks.add(placeholderPackTwo);
		spawnPacksX.add(placeholderPackTwoX);
		spawnPacksY.add(placeholderPackTwoY);
		
		spawnTimes.add(9000);
		spawnPacks.add(placeholderPack);
		spawnPacksX.add(placeholderPackX);
		spawnPacksY.add(placeholderPackY);
	
		/*spawnTimes.add(0);
		spawnPacks.add(placeholderPackFour);
		spawnPacksX.add(placeholderPackFourX);
		spawnPacksY.add(placeholderPackFourY);
		*/
		curRoundNum++;
		System.out.println(curRoundNum);
		roundMonstersKilled = 0;
		curRoundSpawn = 0;
		curRound = new Round(curRoundNum,spawnTimes,spawnPacks,spawnPacksX,spawnPacksY,60);
		roundText = new ScreenText(courierFont, "ABCDEF");

	}

	public Timer getTimer(){
		return gameTimer;
	}

	public void updateGame(){
		//System.out.println(roundMonstersKilled + " : " + curRound.getTotRoundMonsters());
	
		if(roundMonstersKilled == curRound.getTotRoundMonsters()){
			curRound.setRoundOverTrue();
		}
		
		if(curRound.getRoundStarted() && !curRound.getRoundOver()){
			recalculateValues();
			act();	
			
			if(curRoundSpawn != curRound.getSpawnTimes().size()){
				if(curRound.getSpawnTimes().get(curRoundSpawn) <= curRound.getRoundTimer().getLifeTime() && !enoccurred){
					System.out.println("spawning: " + curRoundSpawn);
					spawnEnemies(curRoundSpawn); 
					curRoundSpawn++;
					enoccurred = true;
				} else {
					enoccurred = false;
				}
			}

			//miniMap.displayMiniMap(0, 0);
		} else if(!curRound.getRoundStarted()){
			curRound.start();
			System.out.println("Starting round " + curRound.getRoundNumber() + " which has " + curRound.getTotRoundMonsters() + " monsters.");
		
		} else if(curRound.getRoundOver()){
			System.out.println("Loading");
			loadNextRound();
		}



	}

	public boolean gameOver(){ // restarts game or brings to menu screen
		if (playerTank.getHealth() <= 0){
			audioClip.stop();
		}
		return (playerTank.getHealth() <= 0);
	}

	public boolean exit(){ // exits entire window
		return exit;
	}

	public void setCategory(int c){
		category = c;
	}
	public int getCategory(){
		return category;
	}

	public void getInput(){ // System call to get keydowns
		keyDownW = Keyboard.isKeyDown(Keyboard.KEY_W);
		keyDownS = Keyboard.isKeyDown(Keyboard.KEY_S);
		keyDownA = Keyboard.isKeyDown(Keyboard.KEY_A);
		keyDownD = Keyboard.isKeyDown(Keyboard.KEY_D);
		keyDownQ = Keyboard.isKeyDown(Keyboard.KEY_Q);
		keyDownE = Keyboard.isKeyDown(Keyboard.KEY_E);
		keyDownSpace = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		mouseDownLeft = Mouse.isButtonDown(0);

	}
	private void recalculateValues(){ //non cumulative measurements
		userSpeedX = Math.cos(userTheta);
		userSpeedY = Math.sin(userTheta);

	}
	private void act(){ // cumulative measurements
		playerTank.enableAllMovements(); // part of the process that prevents playerTank from moving through walls
		

		for(int i = worldObjects.size()-1; i >= 0; i--){
			worldObjects.get(i).updatePos();


			if((worldObjects.get(i) instanceof NPCTank)){
				if(gameTimer.getLifeTime() - ((NPCTank)worldObjects.get(i)).getLastShotTime() > ((NPCTank)worldObjects.get(i)).getShotWaitTime()){
					for(NPCMissle m: ((NPCTank)worldObjects.get(i)).shoot()){
						worldObjects.add(m);
					}
					((NPCTank)worldObjects.get(i)).setLastShotTime(gameTimer.getLifeTime());
				}
			}
			
			
			// Remove upon time out
			/*if(worldObjects.get(i).getClass().toString().equals("class PlayerMissle") || worldObjects.get(i).getClass().toString().equals("class NPCMissle")){
				if(gameTimer.getLifeTime() - worldObjects.get(i).getBirthTime() >1000){
					removeList.add(i);
				}
			}*/

			// Remove upon collison
			for(int j = i-1; j >= 0; j--){

				if(worldObjects.get(i).collidesWith(worldObjects.get(j))){
					//System.out.println("i: " + worldObjects.get(i).getClass().toString() + " j: " + worldObjects.get(j).getClass().toString() );
					if((worldObjects.get(i).getClass().toString().equals("class PlayerMissle") && worldObjects.get(j) instanceof NPCTank)){
						removeList.add(i);
						((Tank) worldObjects.get(j)).damageTank();
						if(((Tank) worldObjects.get(j)).getHealth() <= 0){
							roundMonstersKilled++;
							removeList.add(j);
						}
					} else if((worldObjects.get(i) instanceof NPCTank && worldObjects.get(j).getClass().toString().equals("class PlayerMissle"))){
						((Tank) worldObjects.get(i)).damageTank();
						if(((Tank) worldObjects.get(i)).getHealth() <= 0){
							roundMonstersKilled++;
							removeList.add(i);
						}
						removeList.add(j);
					}
					else if((worldObjects.get(i).getClass().toString().equals("class NPCMissle") && worldObjects.get(j).getClass().toString().equals("class PlayerTank"))){
						removeList.add(i);
						playerTank.damageTank();
						if(playerTank.getHealth() <= 0){
							removeList.add(j);
						}
					} else if((worldObjects.get(i).getClass().toString().equals("class PlayerTank") && worldObjects.get(j).getClass().toString().equals("class NPCMissle"))){
						playerTank.damageTank();
						if(playerTank.getHealth() <= 0){
							removeList.add(i);
						}
						removeList.add(j);
					} else if(worldObjects.get(i).getClass().toString().equals("class PlayerTank") && worldObjects.get(j) instanceof NPCTank){
						if(gameTimer.getLifeTime() - lastDamageTime > 1000){
							playerTank.damageTank();
							lastDamageTime = gameTimer.getLifeTime(); // Need to make a flashing animation for this
						}
						if(playerTank.getHealth() <= 0){
							removeList.add(i);
						}
					} else if(worldObjects.get(i) instanceof NPCTank && worldObjects.get(j).getClass().toString().equals("class PlayerTank")){
						if(gameTimer.getLifeTime() - lastDamageTime > 1000){
							playerTank.damageTank();
							lastDamageTime = gameTimer.getLifeTime();
						}
						if(playerTank.getHealth() <= 0){
							removeList.add(i);
						}
					} else if(worldObjects.get(i).getClass().toString().equals("class Barrier") && ((worldObjects.get(j).getClass().toString().equals("class PlayerMissle")))){
						((Missle)worldObjects.get(j)).explode();
						removeList.add(j);
					} else if(((worldObjects.get(i).getClass().toString().equals("class PlayerMissle")) && worldObjects.get(j).getClass().toString().equals("class Barrier"))){
						((Missle)worldObjects.get(i)).explode();
						removeList.add(i);
					} else if(worldObjects.get(i).getClass().toString().equals("class Barrier") && ((worldObjects.get(j).getClass().toString().equals("class NPCMissle")))){
						((Missle)worldObjects.get(j)).explode();
						removeList.add(j);
					} else if(((worldObjects.get(i).getClass().toString().equals("class NPCMissle")) && worldObjects.get(j).getClass().toString().equals("class Barrier"))){
						((Missle)worldObjects.get(i)).explode();
						removeList.add(i);
					} else if(worldObjects.get(i).getClass().toString().equals("class Barrier") && ((worldObjects.get(j).getClass().toString().equals("class PlayerTank")))){
						switch(((Barrier)worldObjects.get(i)).getSide()){
						case 0: playerTank.disableLeft(); break;
						case 1: playerTank.disableUp(); break;
						case 2: playerTank.disableRight(); break;
						case 3: playerTank.disableDown(); break;
						}
						//System.out.println(((Barrier)worldObjects.get(i)).getSide()+ ": " + playerTank.canMoveLeft + "," + playerTank.canMoveUp + "," + playerTank.canMoveRight + "," + playerTank.canMoveDown);
					
						//System.out.println(worldObjects.get(j).angleTo(worldObjects.get(i)));
					} else if(((worldObjects.get(i).getClass().toString().equals("class PlayerTank")) && worldObjects.get(j).getClass().toString().equals("class Barrier"))){
						//System.out.println(worldObjects.get(i).angleTo(worldObjects.get(j)));
						switch(((Barrier)worldObjects.get(i)).getSide()){
						case 0: playerTank.disableLeft(); break;
						case 1: playerTank.disableUp(); break;
						case 2: playerTank.disableRight(); break;
						case 3: playerTank.disableDown(); break;
						}
					}
					
				}

			}

		}
		//System.out.println(removeList + " : " + removeDuplicates(removeList));
		Collections.sort(removeList);
		removeList = (ArrayList<Integer>) removeDuplicates(removeList);
		for(int i = removeList.size()-1; i >= 0; i--){
			worldObjects.remove(worldObjects.get(removeList.get(i)));
		}
		removeList.clear();
		
		if(keyDownW){ // Intention is just that down goes reverse from up
			playerTank.moveForward();
		}
		if(keyDownS){
			playerTank.moveBackward();
		}
		if(keyDownA){
			playerTank.addTheta(.2);
		}
		if(keyDownD){
			playerTank.addTheta(-.2);
		}
		if(mouseDownLeft){
			if(gameTimer.getLifeTime() - playerTank.getLastShotTime() > 100){
				worldObjects.add(new PlayerMissle(missleTex,playerTank.getXPos(),playerTank.getYPos(),Math.atan2(-(windowHeight/2-Mouse.getY()),-(windowWidth/2 - Mouse.getX())), gameTimer));
				playerTank.setLastShotTime(gameTimer.getLifeTime());
			}
		}
	}

	public List removeDuplicates(List list)  
	{  
		Set uniqueEntries = new HashSet();  
		for (Iterator iter = list.iterator(); iter.hasNext(); ) {  
			Object element = iter.next();  
			if (!uniqueEntries.add(element)) // if current element is a duplicate,  
				iter.remove();                 // remove it  
		}
		return list;
	}  

	

	private void spawnEnemies(int crs){ // currentRoundSpawn
		for(int i = 0; i < curRound.getSpawnPacks().get(crs).size(); i++){
			switch(curRound.getSpawnPacks().get(crs).get(i)){
			case 0:worldObjects.add(new FollowTank(followTankTex,curRound.getSpawnPacksX().get(crs).get(i),curRound.getSpawnPacksY().get(crs).get(i),(Math.random()-.5)*2*Math.PI,gameTimer,playerTank)); break;
			case 1:worldObjects.add(new ShootingFollowTank(shootFollowTex,curRound.getSpawnPacksX().get(crs).get(i),curRound.getSpawnPacksY().get(crs).get(i),(Math.random()-.5)*2*Math.PI,gameTimer,playerTank)); break;
			case 2:worldObjects.add(new SpinTank(spinTankTex,curRound.getSpawnPacksX().get(crs).get(i),curRound.getSpawnPacksY().get(crs).get(i),0,gameTimer,playerTank)); break;
			}

		}
	}
	
	private void createWalls(){
		// top and bottom wall (will also contain all the corners
		for(int i = -1 + -(colosseumWidth/Barrier.barrierWidth)/2; i < (colosseumWidth/Barrier.barrierWidth)/2 + 1;i++){
			worldObjects.add(new Barrier(barrierTex,i*Barrier.barrierWidth,-colosseumHeight/2 + Barrier.barrierHeight/2,0,gameTimer,3));
			worldObjects.add(new Barrier(barrierTex,i*Barrier.barrierWidth,colosseumHeight/2 - Barrier.barrierHeight/2,0,gameTimer,1));
		}
		// left and right wall
		for(int i = -1 + -(colosseumHeight/Barrier.barrierHeight)/2; i < (colosseumHeight/Barrier.barrierHeight)/2 + 2;i++){
			worldObjects.add(new Barrier(barrierTex,-colosseumWidth/2 + Barrier.barrierWidth/2,i*Barrier.barrierWidth,0,gameTimer,0));
			worldObjects.add(new Barrier(barrierTex,colosseumWidth/2 - 3*Barrier.barrierWidth/2,i*Barrier.barrierWidth,0,gameTimer,2));
		}
		
		
	}


	//public boolean onScreen(WorldObject o){ // was planning on implementing this to only draw objects which were on the screen in order to optimize but then realized lwjgl probably handles this
	//	return(o.getXPos() < (playerTank.getXPos() + windowWidth/2) && o.getXPos());
	//}

	public void render(){
		for(WorldObject o : worldObjects){
			//System.out.println(o.getClass() + " x: " + o.getXPos() + " y: " + o.getYPos() + " rad: " + o.getTheta());
			//if(onScreen(o)){
			Draw.rect(o.getTexture(),(int)(o.getXPos()-playerTank.getXPos() + windowWidth/2),(int)(o.getYPos()-playerTank.getYPos() + windowHeight/2), 0, 0,o.getWidth(),o.getHeight(),o.getTheta());
			//}
		}
		Draw.rect(aimer,Mouse.getX(),Mouse.getY(),16,16,0);
		retroHealthDisp.displayHealth(windowWidth - 200, windowHeight  - 50, playerTank.getHealth());
		roundText.display( 300, windowHeight - 50);
		Draw.rect(splitTest, 50, 50, 0, 0,32, 32,0);
		//Draw.rect(courierFont,50,50,32,32,0);

	}
	
	public AudioClip getMusic(){
		return audioClip;
	}
	
	public double getDurationOfWavInSeconds(File file)
	{   
	    AudioInputStream stream = null;

	    try 
	    {
	        stream = AudioSystem.getAudioInputStream(file);

	        AudioFormat format = stream.getFormat();

	        return file.length() / format.getSampleRate() / (format.getSampleSizeInBits() / 8.0) / format.getChannels();
	    }
	    catch (Exception e) 
	    {
	        // log an error
	        return -1;
	    }
	    finally
	    {
	        try { stream.close(); } catch (Exception ex) { }
	    }
	}



}