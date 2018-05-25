/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Integer.max;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Esthephany Ayala Yañez 
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private boolean started;        // to start the game
    private boolean gameover;
    private Bar bar;          // to use a bar
    private Ball ball; 
    //Bar bar2;// little ball
    private int vidas ;
    private boolean pause;
    private boolean lost;
    private ArrayList<Brick> bricks; // bricks
    private KeyManager keyManager;  // to manage the keyboard
    private int score;
    private boolean win;
    private int cont;
    SoundClipTest sound;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        started = false;
        gameover = false;
        pause = false;
        keyManager = new KeyManager();
        score = 0;
        lost = false;
        vidas = 3;
        win= false;
        cont=0;
    }
    

    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         bar = new Bar(getWidth() / 2 - 50, getHeight() - 100, 100, 20, this);
         ball = new Ball(getWidth() / 2 - 10, getHeight() - 120, 20, 20, 0, 0, this);
         generateEnemies();
         display.getJframe().addKeyListener(keyManager);
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
      
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    private void tick() {
        keyManager.tick();
       if(!win){
        if(!gameover){
            if(!lost){
                //To pause the game
                pause = this.getKeyManager().p;
               //bar.setWidth(100);
                if(!(pause)){ //IF IS NOT PAUSED
                    
                    // if space and game has not started
                    if (this.getKeyManager().space && !this.isStarted()) {
                        this.setStarted(true);
                        ball.setSpeedX(3);
                        ball.setSpeedY(-3);
                    } 
                    // moving bar
                    bar.tick();
                    // if game has started
                    if (this.isStarted()) {
                        // moving the ball
                        ball.tick();
                    } else {
                        // moving the ball based on the bar
                        ball.setX(bar.getX() + bar.getWidth() / 2 - ball.getWidth() / 2);
                    }

                    // check collision bricks versus ball
                    for (int i = 0; i < bricks.size(); i++) {
                        Brick brick = (Brick) bricks.get(i);
                        if (brick != null ){
                            if (ball.intersects(brick)) {
                                 if(brick.getPower()==0){
                                ball.setSpeedY((ball.getSpeedY() *  - 1));
                                 sound = new SoundClipTest("correct");
                                }
                                 else if(brick.getPower() == 1){
                                    bar.setWidth(bar.getWidth() + bar.getWidth()/4 );
                                    ball.setSpeedY((ball.getSpeedY() *  - 1));
                                    score += 10;
                                    sound = new SoundClipTest("correct");
                                }
                                else if(brick.getPower()==2){
                                    bar.setWidth(100);
                                    score -= 10;
                                    ball.setSpeedY((ball.getSpeedY() *  - 1));
                                    sound = new SoundClipTest("boom");
                                }                      
                                bricks.remove(brick);
                                i--;
                                score += 5;
                                //cont+=1;
                            }
                        }
                    }

                    if(bar.intersects(ball)){
                         if(ball.getX() > bar.getX() && ball.getX() + 
                                 ball.getWidth() <= bar.getX() + bar.getWidth()/4){
                            if(ball.getSpeedX() > 0){
                                ball.setSpeedX(ball.getSpeedX() *-1);
                            }else{
                               ball.setSpeedX(ball.getSpeedX() *1); 
                            }
                         }else if(ball.getX() > bar.getX() + bar.getWidth()/4
                                &&   ball.getX() + ball.getWidth() <=  bar.getX()
                                 +bar.getWidth()){
                            if(ball.getSpeedX() > 0){
                                ball.setSpeedX(ball.getSpeedX() *1);
                            }else{
                               ball.setSpeedX(ball.getSpeedX() *-1); 
                            }
                        }
                        ball.setY(ball.getY()- ball.getSpeedY());
                        ball.setSpeedY(ball.getSpeedY() * -1);
                    }
                    
                    


                    // collision with walls Y
                    if(ball.getY() >= getHeight()){
                       // game.setGameover(true);
                       sound = new SoundClipTest("boom");
                       setVidas(getVidas() - 1);
                       //****GAMEOVER IF
                       if(getVidas() == 0)
                           gameover = true;
                       else if(getVidas()>=1)
                       setLost(true);
                       
                       //**** END GAMEOVER IF
                       ball.setSpeedY(0);
                       ball.setSpeedX(0);
                       ball.setY(getHeight() - 1);
                    } 
                    //when there's no brick , the player will win 
                    if(bricks.size() == 0)
                         win=true;
                }

            }else{
               //When game is LOST (live - 1), keymanager keeps listening for "J" ro init again
                if(this.getKeyManager().isJ()){
                    lost = false;
                    started = false;
                    resetBall();
                    resetBar();
                } 
            }//END LOST********
        } 
         else{
            //When GAMEOVER & WIN  keeps listening for "R" to reinit game
            if(this.getKeyManager().isR()){
                gameover = false;
                started = false;
                win = false;
                vidas = 3;
                score = 0;
                resetBall();
                resetBar();
                generateEnemies();
            }
        }   
        
       }
       else{
            //When GAMEOVER & WIN  keeps listening for "R" to reinit game
            if(this.getKeyManager().isR()){
                gameover = false;
                started = false;
                win = false;
                vidas = 3;
                score = 0;
                resetBall();
                resetBar();
                generateEnemies();
            }
        }
    }
//END TICK();********
    
    private void drawWin(Graphics g){
        //show Win
        g.drawImage(Assets.win,(this.width / 2) - 200, (this.height / 2) - 200, 400 , 400, null);
    }
    
    private void drawGameOver(Graphics g){
       // Show Game Over
        g.drawImage(Assets.gameOver,(this.width / 2) - 200, (this.height / 2) - 200, 400 , 400, null);
    }
    
    private void drawLost(Graphics g){
       // Show LOST!!
        g.drawImage(Assets.lost, (this.width / 2) - 200, (this.height / 2) - 200, 400 , 400, null);
    }
    
     private void drawPause(Graphics g){
       // Show LOST!!
        g.drawImage(Assets.pause, (this.width / 2) - 200, (this.height / 2) - 200, 400 , 400, null);
    }
    
    private void drawLives(Graphics g, int lnumber){
        if( lnumber == 3)
            g.drawImage(Assets.lives3, this.width- 160 , this.height -50 , 150, 40, null);
        else if ( lnumber == 2)
            g.drawImage(Assets.lives2, this.width- 160 , this.height -50 , 150, 40, null);
        else if ( lnumber == 1)
            g.drawImage(Assets.lives1, this.width- 160 , this.height -50 , 150, 40, null);
        else if ( lnumber <= 0)
            g.drawImage(Assets.livesNone,  this.width- 160 , this.height -50 , 150, 40, null);
    }

    
    private void drawScore(Graphics g){
        String a = Integer.toString(score);
        g.setColor(Color.BLACK);
        g.setFont(new Font ("arial",Font.PLAIN, 30));
 
        g.drawString("Puntos: " + a,20,475);
        
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }else{
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            
            
            if(!gameover){
                bar.render(g);
                //bar2.render(g);
                ball.render(g);
                for (Brick brick : bricks) {
                    brick.render(g);
                }
                drawScore(g);
                drawLives(g,vidas);
            }else if(gameover){
            drawGameOver(g);
            }
          

            if (lost && !gameover){
                drawLost(g);
            }
            if (pause && !lost && !gameover){
                drawPause(g);
            }
            if(win && !lost && !pause && !lost &&!gameover){
                
                drawWin(g);
            }
            
            bs.show();
            g.dispose();
        }
    }
    
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }


    private void sleep() {
        try        
            {
                Thread.sleep(150);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
    }

    private void generateEnemies() {
        //Generate New Enemies
        bricks = new ArrayList<Brick>();
        int width_brick = getWidth() / 10 - 6;
        int height_brick = getHeight() / 3 / 5  - 10;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                double randomNum = Math.random() * ( 3 );
                Brick brick = new Brick(i * (width_brick + 3) + 15 , 
                        j * (height_brick + 5) + 15 , width_brick, height_brick, this);
                
                if(randomNum >= 2.5) 
                    brick.setPower(1);
                
                if(randomNum >= 2 && randomNum < 2.5 ) 
                    brick.setPower(2);
                
                
                bricks.add(brick);
            }
        }
    }
    
 

    private void resetBar() {
        bar.setX(getWidth() / 2 - 50);
        bar.setY(getHeight() - 100);
        bar.setWidth(100);
    }

    private void resetBall() {
        //Reset posicion og ball and bar
        ball.setX(getWidth() / 2 - 10);
        ball.setY(getHeight() - 120);
    }
    
    
    
    public BufferStrategy getBs() {
        return bs;
    }

    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

     /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
