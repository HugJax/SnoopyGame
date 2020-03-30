
package Openning;

import JeuDeBase.GameState.*;
import JeuDeBase.Mapping.*;
import JeuDeBase.Entity.*;
import Menu.*;


public class SnoopyGame {

    public static void main(String[] args) {
      String file1 = "Map.txt";
      FileReaderProject reader1 = new FileReaderProject(file1);
      String [] row = reader1.getinput();
      System.out.println("loading map");
      MatrixMaker maker=new MatrixMaker(row);
      int [][] matrix = maker.create();
      
      Sounds.startmusic();
      Sounds.loadmusic("themesong", "sounds/themesong.wav");
      Sounds.loadmusic("echec", "sounds/echeclevel.wav");
      Sounds.loadmusic("objet", "sounds/objet.wav");
      Sounds.loadmusic("succes", "sounds/reussitelevel.wav");
      Sounds.loadmusic("teleportation", "sounds/teleportation.wav");
      Sounds.loadmusic("start", "sounds/startlevel.wav");
      Sounds.play("themesong");
      
     
    MyInterface.window.setPage(new GamePage(0,matrix));
     //MyInterface.sauvegardeMap();
    
        
        
    }
    
}

