
package JeuDeBase.Mapping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.JPanel;
import Openning.*;
import JeuDeBase.Entity.*;


public class Stage extends JPanel {
    
    private final HashMap<Integer, Image> tiles;
    
    private final int tilemapWidth = 10;
    private final int tilemapHeight = 10;
    private final int tileWidth;
    private final int tileHeight;
    private  int [][] tilemap = {
        {0, 2, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 5, 5 ,5, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {1, 3, 5, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/}
    };
    
    private int [][] bool = {
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/},
        {0, 0, 0, 0 ,0, 0, 0, 0, 0, 0/*, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0*/}
    };
    private final Snoopy snoopy;
    
    public Stage(int [][] matrix) {
        
        super();
        this.setBounds(0, 0, 720, 720);
        this.tileWidth = this.getBounds().width / tilemapWidth;
        this.tileHeight = this.getBounds().height / tilemapHeight;
        this.snoopy = new Snoopy(5, 2, getToolkit().getImage("textures/snoopy.png"));
        
        // chargement des textures
        this.tiles = new HashMap<>();
        
            this.tiles.put(0, getToolkit().getImage("textures/tile case vide.png"));        // case vide
            this.tiles.put(1, getToolkit().getImage("textures/tile bloc cassable.png"));    // bloc cassable
            this.tiles.put(2, getToolkit().getImage("textures/tile bloc poussable.png"));   // bloc poussable
            this.tiles.put(3, getToolkit().getImage("textures/tile bloc piégé.png"));       // bloc piégé
            this.tiles.put(5, getToolkit().getImage("textures/tile bloc incassable.png"));  // bloc incassable
        tilemap=matrix;
        MyInterface.window.setBackground(Color.yellow);
        
        // affiche le nom du niveau pendant quelques secondes
        
        validate();
        
    }
    
    // retourne vrai si le niveau est chargé avec succès, retourne faux sinon
   
    
    public boolean loadFromFile(String path, String [] row ) {

        
        return true;
        
    }
    
    // retourne vrai si le niveau est chargé avec succès, retourne faux sinon
    public boolean saveIntoFile() {
        
        return true;
        
    }
    
     public Snoopy getSnoopy()
    {
    return snoopy;
    }
    
      public int[][] getTilemap()
    {
    return tilemap;
    }
    
    public void moveSnoopy(int movement) {
        
        switch(movement) {
            
            case Movement.UP:
                int p;
                if(this.snoopy.getPosY() > 0) {
                    
                    int SnoopyY=this.snoopy.getPosY();
                    int SnoopyX=this.snoopy.getPosX();
                    if((tilemap[SnoopyY-1][SnoopyX]!=5)&&(tilemap[SnoopyY-1][SnoopyX]!=1)){
                        
                        if(tilemap[SnoopyY-1][SnoopyX]==2){
                                if((tilemap[SnoopyY-2][SnoopyX]==0)&&(bool [SnoopyY-1][SnoopyX]==0)){
                                    tilemap[SnoopyY-1][SnoopyX]=0;
                                    tilemap[SnoopyY-2][SnoopyX]=2;
                         
                                    bool [SnoopyY-2][SnoopyX]=1;
                                }
                                else
                                break;
                                   
                        }
                    
                    this.snoopy.move(movement);
                    }      
                        
                    
                        
                   
                    
                    
                }
                break;
                
            case Movement.DOWN:
                if(this.snoopy.getPosY() < this.tilemapHeight - 1) {
                    
                    int SnoopyY=this.snoopy.getPosY();
                    int SnoopyX=this.snoopy.getPosX();
                    if((tilemap[SnoopyY+1][SnoopyX]!=5)&&(tilemap[SnoopyY+1][SnoopyX]!=1)){
                        
                    if((tilemap[SnoopyY+1][SnoopyX]==2)){
                                if((tilemap[SnoopyY+2][SnoopyX]==0)&&(bool [SnoopyY+1][SnoopyX]==0)){
                                    tilemap[SnoopyY+1][SnoopyX]=0;
                                    tilemap[SnoopyY+2][SnoopyX]=2;
                                    bool [SnoopyY+2][SnoopyX]=1;
                                }
                                else
                                break;
                    }
                    this.snoopy.move(movement);
                            
                    }
                    
                   
                    
                }
                break;
                
            case Movement.RIGHT:
               
                if(this.snoopy.getPosX() < this.tilemapWidth - 1) {
                    
                  int SnoopyY=this.snoopy.getPosY();
                    int SnoopyX=this.snoopy.getPosX();
                    if((tilemap[SnoopyY][SnoopyX+1]!=5)&&(tilemap[SnoopyY][SnoopyX+1]!=1)){
                       
                        if(tilemap[SnoopyY][SnoopyX+1]==2){
                                if((tilemap[SnoopyY][SnoopyX+2]==0)&&(bool [SnoopyY][SnoopyX+1]==0)){
                                    tilemap[SnoopyY][SnoopyX+1]=0;
                                    tilemap[SnoopyY][SnoopyX+2]=2;
                                    bool [SnoopyY][SnoopyX+2]=1;
                                }
                                else
                                break;
                                
                        }       
                    this.snoopy.move(movement);
                    }
                    
                    
                }
                break;
                
            case Movement.LEFT:
                if(this.snoopy.getPosX() > 0) {
                    
                    int SnoopyY=this.snoopy.getPosY();
                    int SnoopyX=this.snoopy.getPosX();
                    if((tilemap[SnoopyY][SnoopyX-1]!=5)&&(tilemap[SnoopyY][SnoopyX-1]!=1)){
                       
                     if(tilemap[SnoopyY][SnoopyX-1]==2){
                                if((tilemap[SnoopyY][SnoopyX-2]==0)&&(bool [SnoopyY][SnoopyX-1]==0)){
                                    tilemap[SnoopyY][SnoopyX-1]=0;
                                    tilemap[SnoopyY][SnoopyX-2]=2;
                                    bool [SnoopyY][SnoopyX-2]=1;
                                }
                                else
                                break;
                        }   
                    this.snoopy.move(movement);
        
                    }   
                }
                break;
            
        }
        repaint();
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        for(int y = 0; y < this.tilemapHeight; ++y) {
            
            int posY = y * (this.getBounds().height / this.tilemapHeight);
            
            for(int x = 0; x < this.tilemapWidth; ++x) {
                
                int posX = x * (this.getBounds().width / this.tilemapWidth);
                g.drawImage(tiles.get(tilemap[y][x]),
                            posX,
                            posY,
                            this.tileWidth,
                            this.tileHeight,
                            this);
                
            }
            
        }
        
        // dessine snoopy
        g.drawImage(this.snoopy.getImage(),
                    this.snoopy.getPosX() * this.tileWidth,
                    this.snoopy.getPosY() * this.tileHeight, 
                    this.tileWidth,
                    this.tileHeight,
                    this);
        
        System.out.println("dessine stage");
        
    }
    
}
