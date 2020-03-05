/// Contient tous les panels dont nous avons besoin



package MiseEnPlace;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class Wall extends JPanel{
    public ArrayList<JPanel> panneaux;
    public Wall()
            {
                this.setBackground(new Color(59,93,170));

                this.setLayout(new BorderLayout());
                JPanel buffer= new Intro(this);
                add(buffer,BorderLayout.CENTER);

                this.validate();
                setVisible(true);
            }

     public void rset()
    {
         int i;
            for (i=0;i<this.getComponentCount();i++)
            {
                getComponent(i).setVisible(false);
                getComponent(i).setEnabled(false);
            }

    }
}
