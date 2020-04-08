
package Menu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Openning.*;
import JeuDeBase.GameState.*;


public class HomePage extends Page {
    
    private final ArrayList<Button> arrayButtons;
    MotDePasse mdp = new MotDePasse();
    ChargerPartie charge = new ChargerPartie();
    
    private int selection;
    
    
    public HomePage() {
        
        super();
        setLayout(new GridLayout(6, 1));
        MyInterface.window.setBackground(Color.black);
        Scores scoring = new Scores();
        
        arrayButtons = new ArrayList<>();
        
        // jouer
        arrayButtons.add(new Button("textures/bouton jouer", () -> {
            String file1 = "Map1.txt";
            FileReaderProject reader1x = new FileReaderProject(file1);
            String [] rowx = reader1x.getinput();
            MatrixMaker makerx=new MatrixMaker(rowx);
            int [][] matrixx = makerx.create();
            MyInterface.window.setPage(new GamePage(0, matrixx));
        }));
        
        // ordinateur
        arrayButtons.add(new Button("textures/bouton ordinateur", () -> {
            
        }));
        
        // charger partie
        arrayButtons.add(new Button("textures/bouton charger partie", () -> {
            charge.saisieMDP();
            
        }));
        
        // mot de passe
        arrayButtons.add(new Button("textures/bouton mot de passe", () -> {
            mdp.saisieMDP();
            
        }));
        
        // scores
        arrayButtons.add(new Button("textures/bouton scores", () -> {
            scoring.loadscores();
            scoring.test();
        }));
        
        // quitter
        arrayButtons.add(new Button("textures/bouton quitter", () -> {
            System.exit(0);
        }));
        
        
        add(arrayButtons.get(0));
        add(arrayButtons.get(1));
        add(arrayButtons.get(2));
        add(arrayButtons.get(3));
        add(arrayButtons.get(4));
        add(arrayButtons.get(5));
        
        // initialise la sélection sur le premier bouton
        selection = 0;
        Button b = arrayButtons.get(selection);
        b.setSelected(true);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        // désélectionne le bouton actuel
        Button b = arrayButtons.get(selection);
        b.setSelected(false);
        
        switch(e.getKeyCode()) {
            
            case KeyEvent.VK_DOWN:
                if(selection < 5) {
                    selection++;
                }
                break;
                
            case KeyEvent.VK_UP:
                if(selection > 0) {
                    selection--;
                }
                break;
                
            case KeyEvent.VK_ENTER:
                b.OnClick();
                break;
                
        }
        
        // sélectionne le nouveau bouton
        b = arrayButtons.get(selection);
        b.setSelected(true);
        
        // met à jour la texture
        validate();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
