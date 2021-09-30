
package snoopy.game;

import java.awt.Container;
import java.awt.event.KeyEvent;


public abstract class Page extends Container {
    
    public Page() {
        
    }
    
    public abstract void clear();
    public abstract void keyPressed(KeyEvent e);
    
}
