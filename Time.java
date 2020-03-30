/// Timer du jeu


package JeuDeBase.Counter;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Time {
    public static Date time;
    public static Date nextTime;
    public static long timer;
	
    public static void setTime() { time = new Date(); }
    public static void nextTime() { nextTime = new Date(); }
    
    public long getTimer() {
        timer = 60 - (nextTime.getTime()-time.getTime());
        return timer;
    }
}
