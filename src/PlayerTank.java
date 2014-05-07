import org.newdawn.slick.opengl.Texture;


public class PlayerTank extends Tank{
	boolean canMoveUp;
	boolean canMoveLeft;
	boolean canMoveDown;
	boolean canMoveRight;
	
	double xMove;
	double yMove;

	public PlayerTank(Texture tex, double x, double y, double th, Timer t){
		super(tex,x,y,th,t);
		health = 5;
		speed = 10;

		canMoveUp = true;
		canMoveLeft = true;
		canMoveDown = true;
		canMoveRight = true;
		
		xMove = 0;
		yMove = 0;

	}

	public void moveForward(){
		//if(Math.cos(theta) < 0)
		
		xMove = speed*Math.cos(theta);
		yMove = speed*Math.sin(theta);
		
		if(canMoveLeft && canMoveRight){
			xPos += xMove;
		} else if(canMoveLeft){
			if(Math.cos(theta) <= 0){
				xPos += xMove;
			} else {
				xPos += 0;
			}
		} else if(canMoveRight){
			if(Math.cos(theta) >= 0){
				xPos += xMove;
			} else {
				xPos += 0;
			}
		}
		
		if(canMoveUp && canMoveDown){
			yPos += yMove;
		} else if(canMoveUp){
			if(Math.sin(theta) >= 0){
				yPos += yMove;
			} else {
				yPos += 0;
			}
		} else if(canMoveDown){
			if(Math.sin(theta) <= 0){
				yPos += yMove;
			} else {
				yPos += 0;
			}
		}
	}

	public void moveBackward(){
		xMove = speed*Math.cos(theta);
		yMove = speed*Math.sin(theta);
		xPos -= xMove;
		yPos -= yMove;
	}
	
	public void moveLeft(){
		xMove = speed*Math.cos(theta + Math.PI/2);
		yMove = speed*Math.sin(theta + Math.PI/2);
		xPos += xMove;
		yPos += yMove;
	}
	public void moveRight(){
		xMove = speed*Math.cos(theta + Math.PI/2);
		yMove = speed*Math.sin(theta + Math.PI/2);
		xPos -= xMove;
		yPos -= yMove;
	}

	public void enableAllMovements(){
		canMoveUp = true;
		canMoveLeft = true;
		canMoveDown = true;
		canMoveRight = true;
	}

	public void disableLeft(){
		canMoveLeft = false;
		canMoveRight = true;
	}
	public void disableUp(){
		canMoveUp = false;
		canMoveDown = true;
	}
	public void disableRight(){
		canMoveRight = false;
		canMoveLeft = true;
	}
	public void disableDown(){
		canMoveDown = false;
		canMoveUp = true;
	}


}
