import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;


public class Load {
 static ImageIcon holder;
 static BufferedImage bufferedHolder;
 static Texture texHolder;
 static URL url;
 public static Texture texture(String key, Object cls){

  holder = bufferedImage(key,cls);
  bufferedHolder = Transparency.makeColorTransparent(holder, Color.BLUE);
 
  
  try {
   texHolder = BufferedImageUtil.getTexture(null,bufferedHolder);
 
  } catch (IOException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
  /*try {
   
   return TextureLoader.getTexture("png", new FileInputStream(new File(key)));
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }*/
  
  return texHolder;
  //return null;
 }
 public static ImageIcon bufferedImage(String key, Object cls){
  holder = new ImageIcon(key);
  return holder;
  /*try{
   //return ImageIO.read(url);
  } catch(IOException ex){System.out.println("Could not load the image");};*/
  //return Transparency.myCreateImage(imageHolder);
  
  //return null; // Maybe make this return a blank image
 }
 
 
}