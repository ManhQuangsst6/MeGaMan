/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObject;

import Controller.GameWorld;

/**
 *
 * @author Admin
 */
public abstract class GameObject {
    private float PosX;
    private float PosY;
    private GameWorld gameWorld;

    public GameObject(float PosX, float PosY, GameWorld gameWorld) {
        this.PosX = PosX;
        this.PosY = PosY;
        this.gameWorld = gameWorld;
    }

    public float getPosX() {
        return PosX;
    }

    public void setPosX(float PosX) {
        this.PosX = PosX; 
      }

    public float getPosY() {
        return PosY;
    }

    public void setPosY(float PosY) {
        this.PosY = PosY;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }
    public abstract void Update();
}
