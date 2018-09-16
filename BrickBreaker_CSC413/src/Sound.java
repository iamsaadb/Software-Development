
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saad
 */
public class Sound {
    
   public static void playMusic (String filepath){
   InputStream music;
   try{
    music = new FileInputStream(new File(filepath));
    AudioStream audio = new AudioStream(music);
    AudioPlayer.player.start(audio);
    }
   catch(Exception e){
   System.out.println("CANNOT FIND AUDIO FILE");}
}
}