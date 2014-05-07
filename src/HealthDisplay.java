import org.newdawn.slick.opengl.Texture;


public class HealthDisplay {
	int displayType; // 0 for arcade style discrete hits, 1 for health bar, just working with arcade style for now
	int numHitsTot;
	int numHitsCur;
	
	Texture discreteHit;
	int discreteHitWidth;
	int discreteHitHeight;
	
	public HealthDisplay(int dt){
		displayType = dt;
		
		switch(displayType){
		case 0:
			discreteHit = Load.texture("res/heart.png", this);
			discreteHitWidth = discreteHit.getImageWidth();
			discreteHitHeight = discreteHit.getImageHeight();
		}
	}
	public void displayHealth(double x, double y, int nhc){
		numHitsCur = nhc;
		switch(displayType){
		case 0: 
			for(int i = 0; i < numHitsCur; i++){
				Draw.rect(discreteHit, x + i*(discreteHitWidth+2), y, discreteHitWidth, discreteHitHeight);
			}
			
			
			break;
		case 1: 
			
			break;
		}
	}
	

}
