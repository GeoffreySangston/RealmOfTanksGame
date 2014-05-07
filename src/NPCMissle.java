import org.newdawn.slick.opengl.Texture;


public class NPCMissle extends Missle {
	public NPCMissle(Texture tex, double x, double y, double th, Timer t){
		super(tex,x,y,th,t);
	}
	@Override
	public void updatePos(){
		xPos+=5*Math.cos(theta);
		yPos+=5*Math.sin(theta);
	}
}
