
package Openning;

import javax.swing.JFrame;


public class Window extends JFrame {
    
    private Page page;
    
    public Window() {
        
        setSize(1280, 720 + 37);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    public void setPage(Page page) {
        
        this.page = page;
        this.setContentPane(page);
        this.setVisible(true);
        
    }
    
}
