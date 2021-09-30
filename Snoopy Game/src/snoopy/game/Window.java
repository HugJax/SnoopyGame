
package snoopy.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Window extends JFrame implements KeyListener {
    
    private Page page;
    
    public Window() {
        
        setSize(1280, 720 + 37);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setFocusable(true);
        
    }
    
    public void setPage(Page page) {
        
        if(this.page != null) {
            this.page.clear();
            this.page = null;
        }
        
        this.page = page;
        this.setContentPane(page);
        this.setVisible(true);
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        
        page.keyPressed(arg0);
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }
    
}
