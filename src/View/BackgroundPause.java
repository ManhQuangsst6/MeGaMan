/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class BackgroundPause {
     ImageIcon icon;
    public BackgroundPause(Graphics g){
        icon=new ImageIcon("data\\nen2.jpg");
        g.drawImage(icon.getImage(), 0, 0, 1000,600,null);
    }
}
