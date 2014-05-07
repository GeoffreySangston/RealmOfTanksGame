import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;


public class MiniMap {
	ArrayList<WorldObject> worldObjects;
	PlayerTank observer;
	double displayWidth;
	double displayHeight;
	double coverageWidth;
	double coverageHeight;
	
	Texture missleTex = Load.texture("res/missle.png", this);
	
	public MiniMap(ArrayList<WorldObject> wo,PlayerTank ob){
		worldObjects = wo; // might have to continuously update this
		observer = ob;
		displayWidth = 64;
		displayHeight = 64;
		coverageWidth = 2048;
		coverageHeight = 2048;
	} 
	public void displayMiniMap(double x, double y){ // top left pixel of minimap
		for(WorldObject o: worldObjects){
			if((Math.abs(observer.getXPos() - o.getXPos()) <=coverageWidth/2) && (Math.abs(observer.getYPos() - o.getYPos()) <=coverageHeight/2)){
				//add to minimap
				//System.out.println((Math.abs(observer.getXPos() - o.getXPos())) + " : " + (Math.abs(observer.getYPos() - o.getYPos())));
				//Draw.rect(o.getTexture(),(x+o.getXPos()-      .getXPos())/8,(y+o.getYPos()-observer.getYPos())/8,2,2,0.,observer);
				//Draw.rect(missleTex,o.getXPos()+50,o.getYPos()+50,2,2,0.,observer);

			}
		}
	}
	
	

}
