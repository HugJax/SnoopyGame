/// Affichage d'image



package MiseEnPlace;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {
    ImagePanel(String a)
    {
        this.setBackground(new Color(146,177,229));
        ImageIcon vis = new ImageIcon(a);
         if(vis==null)
        {
            System.out.println("image at "+   "not found");
        }
         else
         {
             JLabel arnold = new JLabel(vis);
             add(arnold);
             setVisible(true);
             this.validate();

         }

    }

}