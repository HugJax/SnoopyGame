
package Menu;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


public final class Button extends JPanel{
    
    private final Runnable customFunction;
    private final Image img1; // texture "non-sélectionnée"
    private final Image img2; // texture "sélectionée"
    private Image CurrentImg; // texture actuelle
    
    public Button(String texturePath, Runnable customFunction) {
        
        super();
        this.img1 = getToolkit().getImage(texturePath + ".png");
        this.img2 = getToolkit().getImage(texturePath + " select.png");
        this.CurrentImg = this.img1;
        
        this.customFunction = customFunction;
        
        setOpaque(false);
        
    }
    
    public final void OnClick() {
        
        customFunction.run();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        int w = this.CurrentImg.getWidth(this);
        int h = this.CurrentImg.getHeight(this);
        int x = ((this.getWidth() - w) / 2);
        int y = ((this.getHeight() - h) / 2);

        g.drawImage(this.CurrentImg, x, y, w, h, this);
        
        System.out.println("dessine bouton");
        
    }
    
    public void setSelected(boolean isSelected) {
        
        this.CurrentImg = (isSelected == false) ? this.img1 : this.img2;
        this.setBounds(0, 0, this.CurrentImg.getWidth(this), this.CurrentImg.getHeight(this));
        
    }
    
}
