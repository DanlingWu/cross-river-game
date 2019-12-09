package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
// will add move functions of pieces on x y
public class Sprite extends JLabel{

	ImageIcon imageIcon;
	int xOffset = 0;
	int yOffset = 0;
    public Sprite(ImageIcon icon)
    {
        super();
        this.imageIcon = icon;
        //new ImageIcon(icon.getImage().getScaledInstance(200, 200, icon.getImage().SCALE_DEFAULT));
        //this.set
    }
    public void setOffset(int x, int y){
    	this.xOffset = x;
    	this.yOffset = y;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(imageIcon.getImage().getWidth(null) > getWidth() && imageIcon.getImage().getHeight(null) > getHeight()){
        	g.drawImage(imageIcon.getImage(),this.xOffset,this.yOffset,getWidth(),getHeight(),this);
        }
        else{
        	g.drawImage(imageIcon.getImage(),this.xOffset,this.yOffset,imageIcon.getImage().getWidth(null),imageIcon.getImage().getHeight(null),this);
        }
    }
}
