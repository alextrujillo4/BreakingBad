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
 * @author antoniomejorado
 */
public class Bar extends Item{

    private Game game;
    
    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        // moving bar depending on keys <-  ->
        if (game.getKeyManager().left) {
           setX(getX() - 6);
        }
        if (game.getKeyManager().right) {
           setX(getX() + 6);
        }
        // collision with walls
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= 0) {
            setX(0);
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
