import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;



public class RealmOfTanks {

	final static int SCREENWIDTH = 700; // iphone5 is 320x588
	final static int SCREENHEIGHT = 700;
	final static int FPS = 60;


	static boolean exitWindow;
	
	final static int MENUSCREEN = 0;
	final static int PLAYING = 1;
	final static int WIN = 2;
	final static int LOSE = 3;
	final static int LOADINGGAMEFROMMENU = 4;

	static int gameState = PLAYING;
	
	static GameWindow game;
	
	static Cursor emptyCursor;
	

	public static void main(String[] args){
		
		exitWindow = false;
		
		initDisplay();
		
		initGL();
		while(!exitWindow){
		System.out.println("called");
		initGame();
		gameLoop();
		}
		System.out.println("exited");
		
		cleanUp();
	}

	private static void initGame(){
		game = new GameWindow();
	}
	private static void getGameInput(){
		game.getInput();
	}
	private static void updateGame(){

		game.updateGame();
	}

	private static void renderGame(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();

		game.render();

		Display.update();
		Display.sync(FPS);
	}

	private static void gameLoop(){
		boolean occurred = false;
		while(!game.gameOver() && !exitWindow){
			exitWindow = Display.isCloseRequested();
			if(game.getTimer().getLifeTime()%(32) == 0 && !occurred){ //int 1 sec/32 frame = int 1000 millis/32 frames = 32 millis/frame, this represents how long to wait before updating the frames

				getGameInput();
				updateGame();
				renderGame();
			} else if(game.getTimer().getLifeTime()%(32) != 0 && occurred) {
				occurred = false;
			}
			
		}
	}




	private static void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
		glMatrixMode(GL_MODELVIEW);

		glClearColor(0,0,0,1);


		glDisable(GL_DEPTH_TEST);
		glEnable(GL_BLEND); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//glColor4f(1.0f,1.0f,1.0f,1.0f);
	}


	private static void initDisplay(){
		try
		{

			Display.setDisplayMode(new DisplayMode(SCREENWIDTH,SCREENHEIGHT));

			Display.create();

			Display.setVSyncEnabled(true);
			
			emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
			Mouse.setNativeCursor(emptyCursor);
			
		} catch (LWJGLException ex){}
	}

	private static void cleanUp(){
		game.getMusic().stop();
		Display.destroy();
	}
}