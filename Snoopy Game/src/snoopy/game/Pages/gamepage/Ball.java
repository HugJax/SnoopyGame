
package snoopy.game.Pages.gamepage;

import java.awt.Image;

public class Ball {
    
    private double posX;
    private double posY;
    public int direction;
    
    private final Image img;
    
    Ball(double x, double y, Image img) {
        
        this.posX = x;
        this.posY = y;
        this.img = img;
        
    }
    
    public double getPosX() {
        
        return posX;
        
    }
    
    public double getPosY() {
        
        return posY;
        
    }
    
    public Image getImage() {
        
        return img;
        
    }
    
    public void move(int movement) {
        
        switch(movement) {
            
            case Movement.TOP_LEFT:
                posY = posY - 0.5;
                posX = posX - 0.5;
                break;
                
            case Movement.TOP_RIGHT:
                posY = posY - 0.5;
                posX = posX + 0.5;
                break;
                
            case Movement.BOT_LEFT:
                posY = posY + 0.5;
                posX = posX - 0.5;
                break;
                
            case Movement.BOT_RIGHT:
                posY = posY + 0.5;
                posX = posX + 0.5;
                break;
                
            default:
                break;
            
        }
        
    }
    
}
