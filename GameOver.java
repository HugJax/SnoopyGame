/// Fin du niveau, affichage du score 


package JeuDeBase.GameState;

import JeuDeBase.Counter.*;
import Menu.*;
import Openning.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;


public class GameOver extends Page{
    private int timer;
    private int nbBirds;
    private int score;
    Time tim = new Time();
    Scoring sco = new Scoring();
    Birds bir = new Birds();
    GamePage gp = new GamePage();

    public void gameover() {
        timer = (int) tim.getTimer();
        nbBirds = bir.getNbBirds();
        score = sco.getScore();
        
        if(nbBirds==4) {
            gp.level ++;
        } else {
            
        }
        
        setLayout(new GridLayout(6, 1));
        MyInterface.window.setBackground(Color.black);
        new Button("textures/bouton suivant", () -> {
            String file1 = "Map" + gp.level + ".txt";
            FileReaderProject reader1x = new FileReaderProject(file1);
            String [] rowx = reader1x.getinput();
            MatrixMaker makerx=new MatrixMaker(rowx);
            int [][] matrixx = makerx.create();
            MyInterface.window.setPage(new GamePage(0, matrixx));
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
