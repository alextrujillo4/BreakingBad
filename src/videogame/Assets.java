/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Esthephany Ayala Yañez 
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage bar;     // to store the bar image
    
     public static BufferedImage brick0; 
     public static BufferedImage brick1; 
     public static BufferedImage brick2; 
       
    public static BufferedImage gameOver;
    public static BufferedImage pause;
    public static BufferedImage win;
    
    public static BufferedImage lives3;
    public static BufferedImage lives2;
    public static BufferedImage lives1;
    public static BufferedImage livesNone;
    
    
    public static BufferedImage lost;
    

    /**
     * initializing the images of the game
     */
    public static void init() {

        background = ImageLoader.loadImage("/images/Background.png");
        bar = ImageLoader.loadImage("/images/player.png");
        
        brick0 = ImageLoader.loadImage("/images/brick0.png");
        brick1 = ImageLoader.loadImage("/images/brick1.png");
        brick2 = ImageLoader.loadImage("/images/brick2.png");
        

        gameOver= ImageLoader.loadImage("/images/GameOver.png");
        win = ImageLoader.loadImage("/images/ganar.png");
        
        lives3 = ImageLoader.loadImage("/images/vidas3.png");
        lives2 = ImageLoader.loadImage("/images/vidas2.png");
        lives1= ImageLoader.loadImage("/images/vidas1.png");
        livesNone= ImageLoader.loadImage("/images/novidas.png");
        
        lost= ImageLoader.loadImage("/images/Lost.png");
        pause= ImageLoader.loadImage("/images/pause.png");
    }
    
}
