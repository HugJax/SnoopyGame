
package Menu;


import JeuDeBase.GameState.GamePage;
import Openning.FileReaderProject;
import Openning.MatrixMaker;
import Openning.MyInterface;
import Openning.Sounds;
import javax.swing.JOptionPane;


public class MotDePasse {
    

    
    
    
    public void saisieMDP() {
    String name = JOptionPane.showInputDialog("Enter PASSWORD.");
    switch(name) {
            
            case "1":
                String file1 = "Map1.txt";
      FileReaderProject reader1 = new FileReaderProject(file1);
      String [] row1 = reader1.getinput();
      System.out.println("loading map");
      MatrixMaker maker=new MatrixMaker(row1);
      int [][] matrix = maker.create();
      
      Sounds.startmusic();
      Sounds.loadmusic("themesong");
      Sounds.play("themesong");
      
     
    MyInterface.window.setPage(new GamePage(0,matrix));
                break;
                
            case "2":
                   String file2 = "Map2.txt";
      FileReaderProject reader2 = new FileReaderProject(file2);
      String [] row2 = reader2.getinput();
      System.out.println("loading map");
      MatrixMaker maker2=new MatrixMaker(row2);
      int [][] matrix2 = maker2.create();
      
      Sounds.startmusic();
      Sounds.loadmusic("themesong");
      Sounds.play("themesong");
      
     
    MyInterface.window.setPage(new GamePage(0,matrix2));
                break;
                
            case "3":
                   String file3 = "Map3.txt";
      FileReaderProject reader3 = new FileReaderProject(file3);
      String [] row3 = reader3.getinput();
      System.out.println("loading map3");
      MatrixMaker maker3=new MatrixMaker(row3);
      int [][] matrix3 = maker3.create();
      
      Sounds.startmusic();
      Sounds.loadmusic("themesong");
      Sounds.play("themesong");
      
     
    MyInterface.window.setPage(new GamePage(0,matrix3));
                
                break;
                
            
            
        }
    
}
    
}
