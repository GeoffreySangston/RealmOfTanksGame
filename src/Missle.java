import org.newdawn.slick.opengl.Texture;


public class Missle extends WorldObject{
	long birthTime;
	public Missle(Texture tex, double x, double y, double th, Timer t){
		super(tex,x,y,th,t);
		width = 8;
		height = 8;
		
	}
	@Override
	public void updatePos(){
		xPos+=15*Math.cos(theta);
		yPos+=15*Math.sin(theta);
	}
	
	public void explode(){
		
	}
	
}
