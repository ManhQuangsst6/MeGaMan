package gameObject;

import Controller.GameWorld;
import View.Animation;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Megaman extends Human {

    public static final int RUNSPEED = 3;
    
    private Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
    private Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
    private Animation dickForwardAnim, dickBackAnim;
    private Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
    private Animation landingForwardAnim, landingBackAnim;
    
   // private Animation climWallForward, climWallBack;
    
    private long lastShootingTime;
    private boolean isShooting = false;
    
    private AudioClip hurtingSound;
    private AudioClip shooting1;
   
    public Megaman(float x, float y, GameWorld gameWorld) {
        super(x, y, 70, 90, 0.1f, 100, gameWorld);
        
        shooting1 = cacheDataLoader.getInstance().getSound("bluefireshooting");
        hurtingSound = cacheDataLoader.getInstance().getSound("megamanhurt");
        
        setTeamType(LEAGUE_TEAM);

        setTimeForNoBehurt(2000*1000000);
        
        runForwardAnim = cacheDataLoader.getInstance().getAnimation("run");
        runBackAnim = cacheDataLoader.getInstance().getAnimation("run");
        runBackAnim.flipAllImage();   
        
        idleForwardAnim = cacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim = cacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim.flipAllImage();
        
        dickForwardAnim = cacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim = cacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim.flipAllImage();
        
        flyForwardAnim = cacheDataLoader.getInstance().getAnimation("flyingup");
        flyForwardAnim.setIsRepeated(false);
        flyBackAnim = cacheDataLoader.getInstance().getAnimation("flyingup");
        flyBackAnim.setIsRepeated(false);
        flyBackAnim.flipAllImage();
        
        landingForwardAnim = cacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim = cacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim.flipAllImage();
        
        behurtForwardAnim = cacheDataLoader.getInstance().getAnimation("hurting");
        behurtBackAnim = cacheDataLoader.getInstance().getAnimation("hurting");
        behurtBackAnim.flipAllImage();
        
        idleShootingForwardAnim = cacheDataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim = cacheDataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim.flipAllImage();
        
        runShootingForwarAnim = cacheDataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim = cacheDataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim.flipAllImage();
        
        flyShootingForwardAnim = cacheDataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim = cacheDataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim.flipAllImage();
        
    }

    @Override
    public void Update() {

        super.Update();
        
        if(isShooting){
            if(System.nanoTime() - lastShootingTime > 500*1000000){
                isShooting = false;
            }
        }
        
        if(getIsLanding()){
            landingBackAnim.Update(System.nanoTime());
            if(landingBackAnim.isLastFrame()) {
                setIsLanding(false);
                landingBackAnim.reset();
                runForwardAnim.reset();
                runBackAnim.reset();
            }
        }
        
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        
        // TODO Auto-generated method stub
        Rectangle rect = getBoundForCollisionWithMap();
        
        if(getIsDicking()){
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 20;
            rect.width = 44;
            rect.height = 65;
        }else{
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 40;
            rect.width = 44;
            rect.height = 80;
        }
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        
        switch(getState()){
        
            case ALIVE:
            case NOBEHURT:
               
                if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
                {
                    System.out.println("Plash...");
                }else{
                    
                    if(getIsLanding()){

                        if(getDirection() == RIGHT_DIR){
                            landingForwardAnim.setCurrentFrame(landingBackAnim.getCurrentFrame());
                            landingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - landingForwardAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }else{
                            landingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - landingBackAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }

                    }else if(getIsJumping()){

                        if(getDirection() == RIGHT_DIR){
                            flyForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                               
                                flyShootingForwardAnim.setCurrentFrame(flyForwardAnim.getCurrentFrame());
                                flyShootingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) +12, (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                                flyForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        }else{
                            flyBackAnim.Update(System.nanoTime());
                            if(isShooting){
                                flyShootingBackAnim.setCurrentFrame(flyBackAnim.getCurrentFrame());
                                flyShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) - 12, (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                            flyBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        }

                    }else if(getIsDicking()){

                        if(getDirection() == RIGHT_DIR){
                            dickForwardAnim.Update(System.nanoTime());
                            dickForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - dickForwardAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }else{
                            dickBackAnim.Update(System.nanoTime());
                            dickBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - dickBackAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }

                    }else{
                        if(getSpeedX() > 0){
                            runForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                                runShootingForwarAnim.setCurrentFrame(runForwardAnim.getCurrentFrame() - 1);
                                runShootingForwarAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                                runForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(runForwardAnim.getCurrentFrame() == 1) runForwardAnim.setIgnoreFrame(0);
                        }else if(getSpeedX() < 0){
                            runBackAnim.Update(System.nanoTime());
                            if(isShooting){
                                runShootingBackAnim.setCurrentFrame(runBackAnim.getCurrentFrame() - 1);
                                runShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                                runBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(runBackAnim.getCurrentFrame() == 1) runBackAnim.setIgnoreFrame(0);
                        }else{
                            if(getDirection() == RIGHT_DIR){
                                if(isShooting){
                                    idleShootingForwardAnim.Update(System.nanoTime());
                                    idleShootingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }else{
                                    idleForwardAnim.Update(System.nanoTime());
                                    idleForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }
                            }else{
                                if(isShooting){
                                    idleShootingBackAnim.Update(System.nanoTime());
                                    idleShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }else{
                                    idleBackAnim.Update(System.nanoTime());
                                    idleBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }
                            }
                        }            
                    }
                }
                
                break;
            
            case BEHURT:
                if(getDirection() == RIGHT_DIR){
                    behurtForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }else{
                    behurtBackAnim.setCurrentFrame(behurtForwardAnim.getCurrentFrame());
                    behurtBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }
                
                break;
             
            case FEY:
                
                break;

        }
        
        //drawBoundForCollisionWithMap(g2);
       //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void run() {
        if(getDirection() == LEFT_DIR)
            setSpeedX(-5);
        else setSpeedX(5);
    }

    @Override
    public void jump() {

        if(!getIsJumping()){
            setIsJumping(true);
            setSpeedY(-5.0f);           
            flyBackAnim.reset();
            flyForwardAnim.reset();
        }
        // for clim wall
        else{
            Rectangle rectRightWall = getBoundForCollisionWithMap();
            rectRightWall.x += 1;
            Rectangle rectLeftWall = getBoundForCollisionWithMap();
            rectLeftWall.x -= 1;
            
            if(getGameWorld().physicalMap.haveCollisionWithRightWall(rectRightWall)!=null && getSpeedX() > 0){
                setSpeedY(-5.0f);
              
                flyBackAnim.reset();
                flyForwardAnim.reset();
                
            }else if(getGameWorld().physicalMap.haveCollisionWithLeftWall(rectLeftWall)!=null && getSpeedX() < 0){
                setSpeedY(-5.0f);
                //setSpeedX(1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                //setDirection(RIGHT_DIR);
            }
                
        }
    }

    @Override
    public void dick() {
        if(!getIsJumping())
            setIsDicking(true);
    }

    @Override
    public void standUp() {
        setIsDicking(false);
        idleForwardAnim.reset();
        idleBackAnim.reset();
        dickForwardAnim.reset();
        dickBackAnim.reset();
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        runForwardAnim.reset();
        runBackAnim.reset();
        runForwardAnim.unIgnoreFrame(0);
        runBackAnim.unIgnoreFrame(0);
    }

    @Override
    public void attack() {
    
        if(!isShooting && !getIsDicking()){
            
            shooting1.play();
            
            Bullet bullet = new BlueFire(getPosX(), getPosY(), getGameWorld());
            if(getDirection() == LEFT_DIR) {
                bullet.setSpeedX(-10);
                bullet.setPosX(bullet.getPosX() -40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() - 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }else {
                bullet.setSpeedX(10);
                bullet.setPosX(bullet.getPosX() + 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() + 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }
            if(getIsJumping())
                bullet.setPosY(bullet.getPosY() - 20);
            
            bullet.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(bullet);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            
        }
    
    }
    @Override
    public void hurtingCallback(){
        System.out.println("Call back hurting");
        hurtingSound.play();
    }

}
