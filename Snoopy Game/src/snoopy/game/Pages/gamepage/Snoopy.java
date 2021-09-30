
package snoopy.game.Pages.gamepage;

import java.awt.Image;
import java.text.DecimalFormat;


public class Snoopy {
    
    private int posX;
    private int posY;
    public int direction;
    public boolean isMoving;
    
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
        
        isMoving = true;
        
        switch(movement) {
            
            case Movement.UP:
                posY--;
                break;
                
            case Movement.DOWN:
                posY++;
                break;
                
            case Movement.RIGHT:
                posX++;
                break;
                
            case Movement.LEFT:
                posX--;
                break;
                
            default:
                isMoving = false;
                break;
            
        }
        
    }
}
