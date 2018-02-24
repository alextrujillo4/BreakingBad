/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Esthephany Ayala Yañez 
 */
public class Ball extends Item{

    private Game game;
    private int speedX;     // speed x
    private int speedY;     // speed y
    
    public Ball(int x, int y, int width, int height, int speedX, int speedY, 
            Game game) {
        super(x, y, width, height);
        this.speedX = speedX;
        this.speedY = speedY;
        this.game = game;
    }


    
    @Override
    public void tick() {
        // moving bar depending on keys <-  ->
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
       
        // collision with walls  X
        if (getX() + 20 >= game.getWidth()) {
            setX(game.getWidth() - 20);
            setSpeedX(getSpeedX() * -1);
        }else if (getX() <= 0) {
            setX(0);
            setSpeedX(getSpeedX() * -1);
        }
        
        //collision with wall Y up
        if(getY() <= -20){
            setY(0);
            setSpeedY(getSpeedY()*-1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
    
    
    
        public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    
    private void increaseSpeed(){
        if(speedY <= 8){
            speedY = speedY --;
            speedX = speedX ++;
        }

    
    }
    
    private void resetSpeed(){
        speedY = -3;
        speedX = 3;
    }

}
