/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObject;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import View.Animation;
import View.FrameImage;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

public class cacheDataLoader {
    private static cacheDataLoader Instance;
    private Hashtable<String,FrameImage> frameImages;
    private Hashtable<String,Animation> animations;
    private String frameFile ="data/frame.txt";
    private String animationFile ="data/animation.txt";
    private String physMapFile="data/phys_map.txt";
    private String backgroundmapfile = "data/background_map.txt";
    private String soundfile = "data/sounds.txt";
    
    
    private Hashtable<String, AudioClip> sounds;
    private int phys_map[][];
    private int[][] background_map;

    
    public static cacheDataLoader getInstance(){
         if(Instance==null)
            Instance=new cacheDataLoader();
         return Instance;
    }
    
    public void LoadSounds() throws IOException{
        sounds = new Hashtable<String, AudioClip>();
        
        FileReader fr = new FileReader(soundfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) { // no line = "" or something like that
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(soundfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i ++){
                
                AudioClip audioClip=null ;
                while((line = br.readLine()).equals(""));

                String[] str = line.split(" ");
                String name = str[0];
                
                String path = str[1];

                try {
                   audioClip =  Applet.newAudioClip(new URL("file","",str[1]));

                } catch (MalformedURLException ex) {}
                
                Instance.sounds.put(name, audioClip);
            }
            
        }
        
        br.close();
        
    }
    public  void LoadFrame() throws IOException{
        frameImages=new Hashtable<String,FrameImage>();
        FileReader fr=new FileReader(frameFile);
        BufferedReader br= new BufferedReader(fr);
        String line=null;
        if(br.readLine()==null){
            System.out.println("not data");
            throw new IOException();
        }
        else{
            fr=new FileReader(frameFile);
            br= new BufferedReader(fr);
            while((line=br.readLine()).equals(""));
            int n=Integer.parseInt(line);
            for(int i=0;i<n;i++){
                FrameImage frame=new FrameImage();
                while((line=br.readLine()).equals(""));
                frame.setName(line);
                while((line=br.readLine()).equals(""));
                String str[] = line.split(" ");
                String path=str[1];
                
                while((line=br.readLine()).equals(""));
                str=line.split(" ");
                int x=Integer.parseInt(str[1]);
                
                while((line=br.readLine()).equals(""));
                str=line.split(" ");
                int y=Integer.parseInt(str[1]);
                
                while((line=br.readLine()).equals(""));
                str=line.split(" ");
                int w=Integer.parseInt(str[1]);
                
                while((line=br.readLine()).equals(""));
                str=line.split(" ");
                int h=Integer.parseInt(str[1]);
                BufferedImage imageData= ImageIO.read(new File(path));
                BufferedImage image=imageData.getSubimage(x, y, w, h);
                frame.setImage(image);
                
                Instance.frameImages.put(frame.getName(), frame);
            }
        }
        br.close();
    }

    
     public void LoadAnimation() throws IOException {
        
        animations = new Hashtable<String, Animation>();
        
        FileReader fr = new FileReader(animationFile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) {
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(animationFile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i ++){
                
                Animation animation = new Animation();
                
                while((line = br.readLine()).equals(""));
                animation.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                
                for(int j = 0;j<str.length;j+=2)
                    animation.add(getFrameImage(str[j]), Double.parseDouble(str[j+1]));
                
                Instance.animations.put(animation.getName(), animation);
                
            }
            
        }
        
        br.close();
    }
     public  FrameImage getFrameImage(String name){
         FrameImage frameImage=new FrameImage(Instance.frameImages.get(name));
         return frameImage;
     }
     public Animation getAnimation(String name ){
         Animation animation=new Animation(Instance.animations.get(name));
         return animation;
     }
    public int[][] getPhysicalMap(){
        return Instance.phys_map;
    }
     public void LoadData() throws IOException{
         LoadFrame();
         LoadAnimation();
         LoadPhysMap();
         LoadBackgroundMap();
         LoadSounds();
     }
     public void LoadPhysMap() throws IOException{
        
        FileReader fr = new FileReader(physMapFile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        Instance.phys_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            String [] str = line.split(" ");
            for(int j = 0;j<numberOfColumns;j++)
                Instance.phys_map[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i = 0;i < numberOfRows;i++){
            
            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+Instance.phys_map[i][j]);
            
            System.out.println();
        }
        
        br.close();
        
    }

    public int[][] getBackgroundMap() {
       return Instance.background_map;
    }
     public void LoadBackgroundMap() throws IOException{
        
        FileReader fr = new FileReader(backgroundmapfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        Instance.background_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            String [] str = line.split(" |  ");
            for(int j = 0;j<numberOfColumns;j++)
                Instance.background_map[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i = 0;i < numberOfRows;i++){
            
            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+Instance.background_map[i][j]);
            
            System.out.println();
        }
        
        br.close();
        
    }
     public AudioClip getSound(String name){
        return Instance.sounds.get(name);
    }
}
