
package Openning;

import java.awt.Container;
import java.awt.event.KeyListener;


public abstract class Page extends Container implements KeyListener {
    
    public Page() {
        
        this.addKeyListener(this);
        this.setFocusable(true);
        
    }
    
}
