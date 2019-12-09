package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private BufferedImage image;

    public ImagePanel(String file) {
       try {                
          image = ImageIO.read(new File(file));
       } catch (IOException ex) {
            // handle exception...
    	   System.out.println("Image not found: " + file);
       }
       this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
