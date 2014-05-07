import org.newdawn.slick.opengl.Texture;


public class Barrier extends WorldObject{
	public final static int barrierWidth = 16;
	public final static int barrierHeight = 16;
	
	private int side;
	/*   1
	 * 0   2
	 *   3
	 */ 
	
	public Barrier(Texture tex, double x, double y, double th, Timer t, int ltrb){
		super(tex,x,y,th,t);
		width = barrierWidth;
		height = barrierHeight;
		side = ltrb;
	}
	
	public int getSide(){
		return side;
	}
}
