
package JeuDeBase.GameState;

import java.awt.event.KeyEvent;
import Openning.*;
import JeuDeBase.Entity.*;
import JeuDeBase.Mapping.*;
import JeuDeBase.Counter.*;

import java.io.PrintWriter;


public class GamePage extends Page {
    
    private Stage stage;
    public int level = 1;
    Time timer = new Time();
    
    public GamePage(){
        
    }
    
    public GamePage(int idxStage,int [] [] matrix) {
        
        super();
        this.stage = new Stage(matrix);
        this.add(this.stage);
        //this.setStage("text");
        
    }

    public GamePage(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setStage(String stagePath) {
        
       // this.stage = new Stage();
        this.remove(this.stage);
        this.add(this.stage);
        timer.setTime();
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
       
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()) {
            
            case KeyEvent.VK_UP:
                this.stage.moveSnoopy(Movement.UP);
                break;
                
            case KeyEvent.VK_DOWN:
                this.stage.moveSnoopy(Movement.DOWN);
                break;
                
            case KeyEvent.VK_LEFT:
                this.stage.moveSnoopy(Movement.LEFT);
                break;
                
            case KeyEvent.VK_RIGHT:
                this.stage.moveSnoopy(Movement.RIGHT);
                break;
                
         case KeyEvent.VK_S:
                JOptionPane.showMessageDialog(null, "Sauvegarde effectue");
                FileReaderProject saveF= new FileReaderProject("save");
                saveF.cleanInputmap("save","" ); 
                saveF.setInput("save", String.valueOf(stage.getSnoopy().getPosX()));
                saveF.setInput("save", String.valueOf(stage.getSnoopy().getPosY()));
                
                 FileReaderProject saveM= new FileReaderProject("savemap");
                 
                saveM.cleanInputmap("savemap","" ); 
                 int[][] map = stage.getTilemap();
                 int i,j;
                   for (i=0;i<10;i++)
        {       
    
                for (j=0;j<10;j++)
                {
                    if(j!=9)
                        saveF.setInputmap("savemap",String.valueOf(map[i][j]) );  
                    else
                        saveF.setInput("savemap",String.valueOf(map[i][j]) );
                }
          }    
                
                break;
            
            case KeyEvent.VK_C:
                int map2[][]=stage.getTilemap();
                int x=stage.getSnoopy().getPosX();
                int y=stage.getSnoopy().getPosY();
                if(map2[y+1][x]==1)
                    map2[y+1][x]=0;
                if(map2[y-1][x]==1)
                    map2[y-1][x]=0;
                if(map2[y][x+1]==1)
                    map2[y][x+1]=0;
                if(map2[y][x-1]==1)
                    map2[y][x-1]=0;
                break;
                
            case KeyEvent.VK_ESCAPE:
                break;
                
            case KeyEvent.VK_ESCAPE:
                break;
            
        }
        
        // actualise la texture
        validate();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
