package videogame;

import java.awt.Color;
import java.awt.Graphics;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Esthephany Ayala Yañez 
 */
public class Brick extends Item{

    private Game game;
    private boolean power;


    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.power = false;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if(!power){
            g.setColor(Color.blue);
        }else{
        g.setColor(Color.red);
        }
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
    
    public void changeBrickColor(){
    
    
    }
    
    
     public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }
}
    