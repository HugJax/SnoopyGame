/// Affichage des scores précédants


package snoopy.game.Pages.scorepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.util.*;
import java.io.*;
import javax.swing.*;
import snoopy.game.Page;
import javax.swing.JLabel;



public class ScorePage extends Page{
    
    private String[][] scorelist = new String[10][2];
    private ArrayList<String> fichier = new ArrayList<>();
    private File file;
    private FileReader reader;
    private char[] chars;
    private String score ="";
    int i=0;
    private JLabel scoring1;
    private JLabel scoring2;
    private JLabel scoring3;
    private JLabel scoring4;
    private JLabel scoring5;
    private JLabel scoring6;
    private JLabel scoring7;
    private JLabel scoring8;
    private JLabel scoring9;
    private JLabel scoring10;
        
    public ScorePage() throws IOException {
        super();
        loadscores();
        test();
        
        Font font = new Font("BRLNSB.TTF", Font.BOLD, 24);
        Font fonttitle = new Font("BRLNSB.TTF", Font.BOLD, 48);
        JLabel title = new JLabel("Top Score");
        title.setFont(fonttitle);
        title.setForeground(Color.yellow);
        title.setBounds(500, 20, 270, 100);
        add(title);
        
        //à refaire
        scoring1 = new JLabel(scorelist[0][0] + " - " + scorelist[0][1]);
        scoring1.setFont(font);
        scoring1.setForeground(Color.yellow);
        scoring1.setBounds(550, 100, 270, 100);
        add(scoring1);
        
        scoring2 = new JLabel(scorelist[1][0] + " - " + scorelist[1][1]);
        scoring2.setFont(font);
        scoring2.setForeground(Color.yellow);
        scoring2.setBounds(550, 150, 270, 100);
        add(scoring2);
        
        scoring3 = new JLabel(scorelist[2][0] + " - " + scorelist[2][1]);
        scoring3.setFont(font);
        scoring3.setForeground(Color.yellow);
        scoring3.setBounds(550, 200, 270, 100);
        add(scoring3);
        
        scoring4 = new JLabel(scorelist[3][0] + " - " + scorelist[3][1]);
        scoring4.setFont(font);
        scoring4.setForeground(Color.yellow);
        scoring4.setBounds(550, 250, 270, 100);
        add(scoring4);
        
        scoring5 = new JLabel(scorelist[4][0] + " - " + scorelist[4][1]);
        scoring5.setFont(font);
        scoring5.setForeground(Color.yellow);
        scoring5.setBounds(550, 300, 270, 100);
        add(scoring5);
        
        scoring6 = new JLabel(scorelist[5][0] + " - " + scorelist[5][1]);
        scoring6.setFont(font);
        scoring6.setForeground(Color.yellow);
        scoring6.setBounds(550, 350, 270, 100);
        add(scoring6);
        
        scoring7 = new JLabel(scorelist[6][0] + " - " + scorelist[6][1]);
        scoring7.setFont(font);
        scoring7.setForeground(Color.yellow);
        scoring7.setBounds(550, 400, 270, 100);
        add(scoring7);
        
        scoring8 = new JLabel(scorelist[7][0] + " - " + scorelist[7][1]);
        scoring8.setFont(font);
        scoring8.setForeground(Color.yellow);
        scoring8.setBounds(550, 450, 270, 100);
        add(scoring8);
        
        scoring9 = new JLabel(scorelist[8][0] + " - " + scorelist[8][1]);
        scoring9.setFont(font);
        scoring9.setForeground(Color.yellow);
        scoring9.setBounds(550, 500, 270, 100);
        add(scoring9);
        
        scoring10 = new JLabel(scorelist[9][0] + " - " + scorelist[9][1]);
        scoring10.setFont(font);
        scoring10.setForeground(Color.yellow);
        scoring10.setBounds(550, 550, 270, 100);
        add(scoring10);
    }
    
    @Override
    public void clear() {
        
        this.fichier = null;
        this.file = null;
        this.reader = null;
        
    }
    
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
    /*
    public void displayscore() {
        
    }*/
    
    public void test() {
        for(int i=0; i<4; i++) {
            System.out.println(scorelist[i][0] + " - " + scorelist[i][1]);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
