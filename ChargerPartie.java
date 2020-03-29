/// Charger une partie précédemment sauvegardée



package Menu;
import JeuDeBase.GameState.GamePage;
import Openning.FileReaderProject;
import Openning.MatrixMaker;
import Openning.MyInterface;
import Openning.Sounds;
import javax.swing.JOptionPane;


public class ChargerPartie {
    public void saisieMDP() {
    String name = JOptionPane.showInputDialog("Enter PASSWORD.");
    
      String file1 = "SaveMap.txt";
      FileReaderProject reader1 = new FileReaderProject(file1);
      String [] row1 = reader1.getinput();
      System.out.println("loading map");
      MatrixMaker maker=new MatrixMaker(row1);
      int [][] matrix = maker.create();
      
      Sounds.startmusic();
      Sounds.loadmusic("themesong");
      Sounds.play("themesong");
      
     
    MyInterface.window.setPage(new GamePage(0,matrix));
                
            
        }
    

    
}
