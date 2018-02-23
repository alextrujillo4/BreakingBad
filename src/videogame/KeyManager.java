/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {
    
    public boolean up;      // flag to move up the bar
    public boolean down;    // flag to move down the bar
    public boolean left;    // flag to move left the bar
    public boolean right;   // flag to move right the bar
    public boolean space;   // flag to space
    public boolean j;
    public boolean p;
    public boolean r;
    private boolean keys[];  // to store all the flags for every key

    public boolean[] getKeys() {
        return keys;
    }

    public void setKeys(boolean[] keys) {
        this.keys = keys;
    }
    
    
    public KeyManager() {
        keys = new boolean[256];
        p = true;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        if (e.getKeyCode()!= KeyEvent.VK_P){
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         // set true to every key is UP
        switch (e.getKeyCode()) {
             case KeyEvent.VK_J:
                keys[KeyEvent.VK_J] = false ;//keys[KeyEvent.VK_P];
                break;
            case KeyEvent.VK_R:
                keys[KeyEvent.VK_R] = false;
                break;
                case KeyEvent.VK_P:
                    keys[KeyEvent.VK_P] = !keys[KeyEvent.VK_P];
                break;
            default:
                keys[e.getKeyCode()] = false;
                break;
        }
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        j = keys[KeyEvent.VK_J]; 
        p = keys[KeyEvent.VK_P]; 
        r = keys[KeyEvent.VK_R]; 
    }
    
    
    
   
    
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isSpace() {
        return space;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }

    public boolean isJ() {
        return j;
    }

    public void setJ(boolean j) {
        this.j = j;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }
    
      public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }
}
