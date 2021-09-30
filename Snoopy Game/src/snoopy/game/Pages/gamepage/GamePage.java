
package snoopy.game.Pages.gamepage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import snoopy.game.Page;


public class GamePage extends Page {
    
    public final int nbStage;
    private Stage stage;
    public boolean gameRunning; // true= la partie est en cours, false= la partie est terminé
    
    public int seconds;
    public Timer countdown;
    private final JLabel countdownDisplay;
    
    public int score;
    private final JLabel scoreDisplay;
    
    public int lifes;
    private final JLabel lifesDisplay;
    
    
    public GamePage(int idxStage) throws IOException {
        
        super();
        this.nbStage = 3;
        
        // ajout du chronomètre
        seconds = 60;
        countdownDisplay = new JLabel("TIME LEFT: " + seconds);
        
        Font font = new Font("BRLNSB.TTF", Font.BOLD, 24);
        countdownDisplay.setFont(font);
        countdownDisplay.setForeground(Color.yellow);
        countdownDisplay.setBounds(1050, 50, 270, 100);
        add(countdownDisplay);
        initCountdown();
        
        // ajout du score
        score = 0;
        scoreDisplay = new JLabel("SCORE: " + score);
        scoreDisplay.setFont(font);
        scoreDisplay.setForeground(Color.yellow);
        scoreDisplay.setBounds(1050, 200, 270, 100);
        add(scoreDisplay);
        
        // ajout du compteur de vies
        lifes = 3;
        lifesDisplay = new JLabel("LIFES: " + lifes);
        lifesDisplay.setFont(font);
        lifesDisplay.setForeground(Color.yellow);
        lifesDisplay.setBounds(1050, 350, 270, 100);
        add(lifesDisplay);
        
        // ajout de l'interface
        JLabel interface1 = new JLabel();
        JLabel interface2 = new JLabel();
        interface1.setBounds(0, 500, 1270, 220);
        interface2.setBounds(1000, 0, 270, 500);
        
        BufferedImage img1 = ImageIO.read(new File("textures/interface1.jpg"));
        BufferedImage img2 = ImageIO.read(new File("textures/interface2.jpg"));
        Image dimg1 = img1.getScaledInstance(interface1.getWidth(), interface1.getHeight(), Image.SCALE_SMOOTH);
        Image dimg2 = img2.getScaledInstance(interface2.getWidth(), interface2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(dimg1);
        ImageIcon icon2 = new ImageIcon(dimg2);
        
        interface1.setIcon(icon1);
        interface2.setIcon(icon2);
        add(interface1);
        add(interface2);
        
        
        this.setStage("levels/niveau" + idxStage, 1000, 500);
        this.add(this.stage);
        
    }
    
    public void clear() {
        
        this.countdown = null;
        this.stage.clear();
        this.stage = null;
        
    }
    
    private void initCountdown() {
        
        ActionListener tacheCountdown = (ActionEvent e1) -> {
            if(seconds > 0) {
                
                seconds--;
                countdownDisplay.setText("TIME LEFT: " + seconds);
                countdownDisplay.repaint();
                
            }
            else {
                
                stage.lose();
                
            }
            
            if(gameRunning == false) {
                
                System.exit(0);
                
            }
        };
        
        countdown = new Timer(1000, tacheCountdown);
        
    }
    
    public void restartCountdown() {
        
        seconds = 60;
        countdown.restart();
        countdownDisplay.setText("TIME LEFT: " + seconds);
        countdownDisplay.repaint();
        
    }
    
    public void updateLifesDisplay() {
        
        lifesDisplay.setText("LIFES: " + lifes);
        lifesDisplay.repaint();
        
    }
    
    public void computeScore() {
        
        score = score + 100 * seconds;
        scoreDisplay.setText("SCORE: " + score);
        scoreDisplay.repaint();
        
    }
    
    public void setStage(String stagePath, int width, int height) {
        
        this.stage = new Stage(this, stagePath, width, height);
        this.gameRunning = true;
        
        this.seconds = 60;
        this.countdown.start();
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()) {

            case KeyEvent.VK_UP:
                this.stage.moveSnoopy(Movement.UP);
                break;

            case KeyEvent.VK_DOWN:
                this.stage.moveSnoopy(Movement.DOWN);
                break;

            case KeyEvent.VK_LEFT:
                this.stage.moveSnoopy(Movement.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                this.stage.moveSnoopy(Movement.RIGHT);
                break;

            case KeyEvent.VK_A:
                this.stage.actionSnoopy();
                break;

            case KeyEvent.VK_ESCAPE:
                break;

        }

        // actualise la texture
        validate();
        
    }

}
