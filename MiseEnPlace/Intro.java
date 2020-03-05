/// Fenêtre de démarrage avec crédits



package MiseEnPlace;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Intro extends JPanel{
    
    BufferedImage img = null;
    private File f;
    JFrame frame = new JFrame("La revanche de Snoopy");
    
    public Intro(Wall a) {
        try {
             File f = new File("C:\\Users\\hecat\\Documents\\Cours\\POO Java\\Projet Snoopy\\Images\\SnoopyGame.png");
             img = ImageIO.read(f);
             System.out.println("File " + f.toString());
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        
        BackgroundPanel background = new BackgroundPanel(img, BackgroundPanel.TILED, 0.50f, 0.5f);
         
        frame.setContentPane(background);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 640);
        frame.setResizable(false);
        frame.setVisible(true);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        frame.setVisible(false);
               
        this.validate();
        
    }
    
}
