
package JeuDeBase.Entity;

import java.awt.Image;


public class Snoopy {
    
    private int posX;
    private int posY;
    private boolean isMoving;
    
    private final Image img;
    
    public Snoopy(int x, int y, Image img) {
        
        this.posX = x;
        this.posY = y;
        this.isMoving = false;
        this.img = img;
        
    }
    
    public int getPosX() {
        
        return this.posX;
        
    }
    
    public int getPosY() {
        
        return this.posY;
        
    }
    
    public Image getImage() {
        
        return this.img;
        
    }
    
    public void move(int movement) {
        
        switch(movement) {
            
            case Movement.UP:
                --this.posY;
                break;
                
            case Movement.DOWN:
                ++this.posY;
                break;
                
            case Movement.RIGHT:
                ++this.posX;
                break;
                
            case Movement.LEFT:
                --this.posX;
                break;
            
        }
        
        System.out.println("PosX=" + posX + "  posY=" + posY);
        
    }
}
