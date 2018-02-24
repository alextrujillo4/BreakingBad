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
    private int power;

    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.power = 0;//0 significa Normal..... 1 = bueno ... 2 = malo
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        switch (power) {
            case 0:
                g.drawImage(Assets.brick0, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 1:
                g.drawImage(Assets.brick1, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:
                g.drawImage(Assets.brick2, getX(), getY(), getWidth(), getHeight(), null);
                break;
            default:
                break;
        }
    }
    
    
    
    
     public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
    