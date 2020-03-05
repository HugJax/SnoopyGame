/// Création de la fenêtre du jeu



package MiseEnPlace;
import java.awt.Color;
import javax.swing.*;


public class Fenetre extends JFrame{
        private Wall wall;
        
        public Fenetre()
        {
            wall= new Wall();
            add(wall);
            
            this.setBackground(new Color(146,177,229));
            setTitle("La revanche de Snoopy");
            setSize(640, 640);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            this.validate();

        }
}
