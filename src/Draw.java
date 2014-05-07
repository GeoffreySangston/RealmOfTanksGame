import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;


public class Draw {
	static double rtTwo = Math.sqrt(2);

	public static void rect(Texture tex,double x, double y, double w, double h, double rot){

		tex.bind();

		glPushMatrix();
		glBegin(GL_QUADS);
		{
			glVertex2d(x+rtTwo*Math.cos(rot + 5*Math.PI/4)*w/2,y+rtTwo*Math.sin(rot+5*Math.PI/4)*h/2);
			glTexCoord2d(1,0);
			glVertex2d(x+rtTwo*Math.cos(rot + 3*Math.PI/4)*w/2,y+rtTwo*Math.sin(rot + 3*Math.PI/4)*h/2);
			glTexCoord2d(0,0);
			glVertex2d(x+rtTwo*Math.cos(rot+Math.PI/4)*w/2,y+rtTwo*Math.sin(rot+Math.PI/4)*h/2);
			glTexCoord2d(0,1);
			glVertex2d(x+rtTwo*Math.cos(rot+7*Math.PI/4)*w/2,y+rtTwo*Math.sin(rot + 7*Math.PI/4)*h/2);
			glTexCoord2d(1,1);

		}
		glEnd();
		glPopMatrix();
	}

	public static void rect(Texture tex,double x, double y, double w, double h){

		tex.bind();

		glPushMatrix();
		glBegin(GL_QUADS);
		{
			glVertex2d(x-w/2,y-h/2);
			glTexCoord2d(1,0);
			glVertex2d(x-w/2,y+h/2);
			glTexCoord2d(0,0);
			glVertex2d(x+w/2,y+h/2);
			glTexCoord2d(0,1);
			glVertex2d(x+w/2,y-h/2);
			glTexCoord2d(1,1);

		}
		glEnd();
		glPopMatrix();

	}
	
	public static void rect(Texture tex,double x, double y, int xS, int yS, double w, double h, double rot){
		// xs/ys the part of the texture to use for drawing with
		tex.bind();
		glPushMatrix();
		glBegin(GL_QUADS);
		{
			glTexCoord2d((double)(xS+w)/tex.getImageWidth(),(double)(yS)/tex.getImageHeight()); 
			glVertex2d(x+rtTwo*Math.cos(rot+Math.PI/4)*w/2,y+rtTwo*Math.sin(rot+Math.PI/4)*h/2);
			glTexCoord2d((double)xS/tex.getImageWidth(),(double)(yS)/tex.getImageHeight());
			glVertex2d(x+rtTwo*Math.cos(rot + 3*Math.PI/4)*w/2,y+rtTwo*Math.sin(rot + 3*Math.PI/4)*h/2);
			glTexCoord2d((double)xS/tex.getImageWidth(),(double)(yS+h)/tex.getImageHeight()); 
			glVertex2d(x+rtTwo*Math.cos(rot + 5*Math.PI/4)*w/2,y+rtTwo*Math.sin(rot+5*Math.PI/4)*h/2); 
			glTexCoord2d((double)(xS+w)/tex.getImageWidth(),(double)(yS+h)/tex.getImageHeight()); 
			glVertex2d(x+rtTwo*Math.cos(rot+7*Math.PI/4)*w/2,y+rtTwo*Math.sin(rot + 7*Math.PI/4)*h/2);			

		}
		glEnd();
		glPopMatrix();
	}
}
