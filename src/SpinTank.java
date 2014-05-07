import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;


public class SpinTank extends NPCTank {
	
	public SpinTank(Texture tex, double x, double y, double th, Timer t,PlayerTank pt){
		super(tex,x,y,th,t,pt);
		width = 32;
		height = 32;
		shotWaitTime = 150;
		health = 5;
		speed = 0;
	}
	public void updatePos(){
		theta = (gameTimer.getLifeTime() - birthTime)/100;
	}
	public ArrayList<NPCMissle> shoot(){
		ArrayList<NPCMissle> tempList = new ArrayList<NPCMissle>();
		tempList.add(new NPCMissle(missleTex2,this.xPos,this.yPos,theta,this.getTimer()));
		tempList.add(new NPCMissle(missleTex2,this.xPos,this.yPos,theta + Math.PI/2,this.getTimer()));
		tempList.add(new NPCMissle(missleTex2,this.xPos,this.yPos,theta + Math.PI,this.getTimer()));
		tempList.add(new NPCMissle(missleTex2,this.xPos,this.yPos,theta + 3*Math.PI/2,this.getTimer()));
		return tempList;
	}
}
