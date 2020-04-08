/// Timer du jeu


package JeuDeBase.Counter;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Time {
    public Date time;
    public Date nextTime;
    public long timer;
	
    public void setTime() { time = new Date(); }
    public void nextTime() { nextTime = new Date(); }
    
    public long getTimer() {
        timer = 60 - (nextTime.getTime()-time.getTime());
        return timer;
    }
}
