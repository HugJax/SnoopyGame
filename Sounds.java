/// Music du jeu et effets sonores


package Openning;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sounds {
    
    private static HashMap<String, Clip> music;
    private static int gap;
    
    
    // Creation of clip of music type hashmap
    public static void startmusic() {
        music = new HashMap<String, Clip>();
        gap = 0;
    }
    
    // Load theme song of the game
    public static void loadmusic(String store) {
        if(music.get(store) != null) return;
        Clip clip;
        try {
            InputStream load = Sounds.class.getResourceAsStream("sounds/themesong.wav");
            if(load == null) {
                System.out.println("bande son manquante absente");
            }
            InputStream buff = new BufferedInputStream(load);
            AudioInputStream aud = AudioSystem.getAudioInputStream(buff);
            AudioFormat base = aud.getFormat();
            AudioFormat decode = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, base.getSampleRate(), 16, base.getChannels(), base.getChannels()*2, base.getSampleRate(), false);
            AudioInputStream stream = AudioSystem.getAudioInputStream(decode, aud);
            clip = AudioSystem.getClip();
            clip.open(stream);
            music.put(store, clip);
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        }
    }
    
    public static void play(String s) {
        play(s, gap);
    }
    
    public static void play(String s, int i) {
        Clip c = music.get(s);
        if(c == null) return;
        if(c.isRunning()) c.stop();
        c.setFramePosition(i);
        while(!c.isRunning()) c.start();
    }
    
    public static void loop(String s) {
        loop(s, gap, gap, music.get(s).getFrameLength() - 1);
    }
	
    public static void loop(String s, int frame) {
	loop(s, frame, gap, music.get(s).getFrameLength() - 1);
    }
	
    public static void loop(String s, int start, int end) {
	loop(s, gap, start, end);
    }
	
    public static void loop(String s, int frame, int start, int end) {
        Clip c = music.get(s);
        if(c == null) return;
        if(c.isRunning()) c.stop();
        c.setLoopPoints(start, end);
        c.setFramePosition(frame);
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public static void stop(String s) {
        if(music.get(s) == null) return;
        if(music.get(s).isRunning()) music.get(s).stop();
    }
    
    public static void resume(String s) {
		if(music.get(s).isRunning()) return;
		music.get(s).start();
	}
	
	public static void resumeLoop(String s) {
		Clip c = music.get(s);
		if(c == null) return;
		c.loop(Clip.LOOP_CONTINUOUSLY);
	}
    
}
