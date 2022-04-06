/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class FrameImage {
    private String name;
    BufferedImage image;

    public FrameImage(){
        name=null;
        image=null;
    }
    public FrameImage(String nameString,BufferedImage img) {
        this.name=nameString;
        image=img;
    }
    public FrameImage(FrameImage frameImage) {
        this.image=new BufferedImage(frameImage.getImageWidth(), frameImage.getImageHeight(),frameImage.image.getType() );
        Graphics g=image.getGraphics();
        g.drawImage(frameImage.getImage(), 0, 0, null);
        name = frameImage.name;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public int getImageWidth(){
        return image.getWidth();
    }
    
     public int getImageHeight(){
        return image.getHeight();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void draw(Graphics2D g,int x,int y){
        g.drawImage(image, x-image.getWidth()/2, y-image.getHeight()/2,null);
    }
}
