
package snoopy.game.Pages.homepage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import snoopy.game.MyInterface;
import snoopy.game.Page;
import snoopy.game.Pages.gamepage.GamePage;
import snoopy.game.Pages.scorepage.ScorePage;


public class HomePage extends Page {
    
    private final ArrayList<Button> arrayButtons;
    private int selection;
    
    public HomePage() {
        
        super();
        setLayout(new GridLayout(6, 1));
        MyInterface.window.setBackground(Color.black);
        
        arrayButtons = new ArrayList<>();
        
        // jouer
        arrayButtons.add(new Button("textures/bouton jouer", () -> {
            try {
                MyInterface.window.setPage(new GamePage(1));
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        
        // ordinateur
        arrayButtons.add(new Button("textures/bouton ordinateur", () -> {
            
        }));
        
        // charger partie
        arrayButtons.add(new Button("textures/bouton charger partie", () -> {
            
        }));
        
        // mot de passe
        arrayButtons.add(new Button("textures/bouton mot de passe", () -> {
            
        }));
        
        // scores
        arrayButtons.add(new Button("textures/bouton scores", () -> {
            MyInterface.window.setPage(new ScorePage());
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
    public void clear() {
        
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

}
