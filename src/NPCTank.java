import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;


public class NPCTank extends Tank{
	Texture missleTex2 = Load.texture("res/missle2.png", this);
	int curNode;
	int shotWaitTime; // shoots once every "shotTime" milliseconds
	PlayerTank playerTank;
	
	public NPCTank(Texture tex, double x, double y, double th, Timer t,PlayerTank pt){
		super(tex,x,y,th,t);
		playerTank = pt;
		speed = 3;
	}
	
	@Override
	public void updatePos(){
	}
	
	public ArrayList<NPCMissle> shoot(){
		ArrayList<NPCMissle> tempList = new ArrayList<NPCMissle>();
		return tempList;
	}
	
	public int getShotWaitTime(){
		return shotWaitTime;
	}
	
}
