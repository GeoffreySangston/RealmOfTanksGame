import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;



public class ScreenText {
	
	String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	Texture font;
	int xPos;
	int yPos;
	String text;
	int size;
	/**
	 * 
	 * @param f - font
	 * @param x - x Position
	 * @param y - y Position
	 * @param t - text
	 */
	

	public ScreenText(Texture f, String t){
		font = f;
		text = t.toUpperCase();
		
	}
	public void display(int xPos, int yPos){
		
		for(int i = 0; i < text.length(); i++){
			if(chars.contains(text.substring(i,i+1))){
				//System.out.println(text.substring(i,i+1) + " : " + chars.indexOf(text.substring(i,i+1)));
				Draw.rect(font, xPos + (font.getImageWidth()/chars.length())*i, yPos, (font.getImageWidth()/chars.length())*chars.indexOf(text.substring(i,i+1)), 0,(font.getImageWidth()/chars.length()), font.getImageHeight(),0);
			}
		}
	}

}
