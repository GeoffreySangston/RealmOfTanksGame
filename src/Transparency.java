import java.awt.image.*;
import java.awt.*;


import javax.swing.ImageIcon;


// TRANSPARENCY /////////////////////////////////////////////////////////////////
public class Transparency { // should change name to image processing class
 static int imageWidth;
 static int imageHeight;
 static ImageObserver observer;
 public Transparency(ImageObserver ob){
  observer = ob;

 }
 public static BufferedImage makeColorTransparent(ImageIcon im, final Color color) {
  
  ImageFilter filter = new RGBImageFilter() {
   // the color we are looking for... Alpha bits are set to opaque
   public int markerRGB = color.getRGB() | 0xFF000000; // chagne back to 0xFF000000

   public final int filterRGB(int x, int y, int rgb) {
    if ( ( rgb | 0xFF000000 ) == markerRGB ) {
     // Mark the alpha bits as zero - transparent
     return 0x000000FF & rgb;

    }
    else {
     // nothing to do
     return rgb;
    }
   }
  }; 

  ImageProducer ip = new FilteredImageSource(im.getImage().getSource(), filter);
  return myCreateImage(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ip)));
 }

 public static BufferedImage myCreateImage(ImageIcon image) { 
  observer = image.getImageObserver();
  //System.out.println(observer + " Here's the problem?");
  imageWidth = image.getIconWidth();
  imageHeight = image.getIconHeight();
  // Create a buffered image in which to draw  
  BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight,  BufferedImage.TYPE_INT_ARGB);  
  // Draw image into bufferedImage.  
  Graphics2D g2 = bufferedImage.createGraphics();  
  g2.drawImage(image.getImage(), 0, 0, observer);  
  g2.dispose();  

  return bufferedImage;  
 } 
}


// TRANSPARENCY /////////////////////////////////////////////////////////////////