import org.newdawn.slick.opengl.Texture;


public class Tank extends WorldObject{
	
	long lastShotTime;
	int health;
	double speed;
	public Tank(Texture tex, double x, double y, double th, Timer t){
		super(tex,x,y,th,t);
		width = 8;
		height = 8;
		
		lastShotTime = -1;
	}
	
	public void setLastShotTime(long t){
		lastShotTime = t;
	}
	public long getLastShotTime(){
		return lastShotTime;
	}
	public int getHealth(){
		return health;
	}
	public void damageTank(){
		health -=1;
	}
	public double getSpeed(){
		return speed;
	}
	
}
