/// Affichage des scores précédants


package Menu;

import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.CharBuffer;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;


public class Scores {
    
    private String[][] scorelist = new String[10][2];
    private ArrayList<String> fichier = new ArrayList<>();
    private File file;
    private FileReader reader;
    private char[] chars;
    private String score ="";
    int i=0;
    
    public void loadscores() {
        String fichier = "scoreboard.txt";
        String tableau[] = new String[2];
        int j=0;
        try {
          FileReader fr = new FileReader(fichier);
          BufferedReader br = new BufferedReader(fr);
          String str;
          int nbligne = 0;
          while((str=br.readLine()) != null)
          {
            String[] splits = str.split(";");
            int taille = splits.length;
            for(int i = 0; i < taille ;i++)
            {
              tableau[i] = splits[i];
            }
            scorelist[j][0] = tableau[0];
            scorelist[j][1] = tableau[1];
            j++;
          }br.close();
        }
        catch(IOException ioe){System.out.println("erreur:"+ioe);}
    }
    
    void displayscore() {
        
    }
    
    public void test() {
        for(int i=0; i<4; i++) {
            System.out.println(scorelist[i][0] + " - " + scorelist[i][1]);
        }
    }
    
}
