import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;


public class FollowTank extends NPCTank {
	public FollowTank(Texture tex, double x, double y, double th, Timer t,PlayerTank pt){
		super(tex,x,y,th,t,pt);
		width = 16;
		height = 16;
		shotWaitTime = 1000;
		health = 1;
		speed = 8;

	}
	
	@Override
	public void updatePos(){
		/*if(this.distanceTo(playerTank) <= 200){
			speed = 4;
			theta = angleTo(playerTank);
			xPos += speed*Math.cos(theta);
			yPos += speed*Math.sin(theta);
		} else {*/
			theta = angleTo(playerTank);
			xPos += speed*Math.cos(theta);
			yPos += speed*Math.sin(theta);
		//}
	}
	
	public ArrayList<NPCMissle> shoot(){
		ArrayList<NPCMissle> tempList = new ArrayList<NPCMissle>();
		//tempList.add(new NPCMissle(missleTex2,this.xPos,this.yPos,angleTo(playerTank),this.getTimer()));
		return tempList;
	}
}
