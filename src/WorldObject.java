import org.newdawn.slick.opengl.Texture;


public class WorldObject {
	int id;
	
	double xPos;
	double yPos;
	double width;
	double height;
	
	double theta;
	
	Texture objectTexture;
	
	protected long birthTime;
	
	Timer gameTimer;
	
	public WorldObject(Texture tex, double x, double y, double th, Timer t){
		id = this.hashCode();
		
		objectTexture = tex;
		
		xPos = x;
		yPos = y;
		
		theta = th;
		
		gameTimer = t;
		birthTime = t.getLifeTime();
	}
	Timer getTimer(){
		return gameTimer;
	}
	
	public boolean collidesWith(WorldObject oo){//other object
		/*
		 * 
		 * boolean rectangle_collision(float x_1, float y_1, float width_1, float height_1, float x_2, float y_2, float width_2, float height_2)
			{
  				return !(x_1 > x_2+width_2 || x_1+width_1 < x_2 || y_1 > y_2+height_2 || y_1+height_1 < y_2);
			}
		 * 
		 */
		return !(oo.getXPos() > this.getXPos()+this.getWidth() || oo.getXPos()+oo.getWidth() < this.getXPos() || oo.getYPos() > this.getYPos()+this.getHeight() || oo.getYPos()+oo.getHeight() < this.getYPos());
	}
	public double distanceTo(WorldObject oo){
		return Math.sqrt(Math.pow(this.getXPos()-oo.getXPos(),2) + Math.pow(this.getYPos()-oo.getYPos(),2));
	}
	public double angleTo(WorldObject oo){
		return(Math.atan2(oo.getYPos() - this.getYPos(), oo.getXPos() - this.getXPos()));
	}
	
	long getBirthTime(){
		return birthTime;
	}
	
	public Texture getTexture(){
		return objectTexture;
	}
	
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
	public double getXPos(){
		return xPos;
	}
	public void setXPos(int x){
		xPos = x;
	}
	public double getYPos(){
		return yPos;
	}
	public void setYPos(int y){
		yPos = y;
	}
	public double getTheta(){
		return theta;
	}
	public void addTheta(double n){
		theta += n;
	}
	public void updatePos(){
	}
	
}
