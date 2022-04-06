
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;
import Controller.GameWorld;
import Controller.GameFrame;
import java.awt.Graphics2D;

/**
 *
 * @author phamn
 */
public class BackgroundMap extends GameObject {

    public int[][] map;
    private int tileSize;
    
    public BackgroundMap(float x, float y, GameWorld gameWorld) {
        super(x, y, gameWorld);
        map = cacheDataLoader.getInstance().getBackgroundMap();
        tileSize = 30;
    }

    @Override
    public void Update() {}
    
    public void draw(Graphics2D g2){
        
        Camera camera = getGameWorld().camera;
        
        
        for(int i = 0;i< map.length;i++)
            for(int j = 0;j<map[0].length;j++)
                if( j*tileSize - camera.getPosX() >-30 && j*tileSize - camera.getPosX() < GameFrame.SCREEN_WIDTH
                        && i*tileSize - camera.getPosY() >-30 && i*tileSize - camera.getPosY() < GameFrame.SCREEN_HEIGHT){ 
                    g2.drawImage(cacheDataLoader.getInstance().getFrameImage("tiled"+map[i][j]).getImage(), (int) getPosX() + j*tileSize - (int) camera.getPosX(), 
                        (int) getPosY() + i*tileSize - (int) camera.getPosY(), null);
                }
        
    }
    
}
