/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * This is a sample taked from https://stackoverflow.com/questions/25171205/playing-sound-in-java
 * it was used to make Boom sound 
 */
public class SoundClipTest extends JFrame {
    
    public String sound;
   // Constructor
   public SoundClipTest(String sound) {  
       this.sound = sound;
      try {
         // Open an audio input stream.           
          File soundFile = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().getPath()+ "/sound/"+sound+".wav" ); //you could also get the sound file with an URL
          //System.out.print(soundFile.toString() );
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public static void main(String args) {
      new SoundClipTest(args);
   }
}