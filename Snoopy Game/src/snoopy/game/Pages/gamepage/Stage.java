
package snoopy.game.Pages.gamepage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import snoopy.game.MyInterface;
import snoopy.game.Pages.homepage.HomePage;


public final class Stage extends JPanel {
    
    private GamePage gamePage;
    
    private final HashMap<Integer, Image> tiles;
    
    private final int tilemapWidth = 20;
    private final int tilemapHeight = 10;
    private final int tileWidth;
    private final int tileHeight;
    private int [][] tilemap;
    
    private Snoopy snoopy;
    private Timer snoopyMovementsTimer;
    
    private Ball ball;
    private Timer ballMovementsTimer;
    
    private int capturedBirds;
    
    private int stageIdx;
    
    public Stage(GamePage gamePage, String stagePath, int width, int height) {
        
        super();
        this.setBounds(0, 0, width, height);
        this.gamePage = gamePage;
        this.capturedBirds = 0;
        this.tileWidth = this.getBounds().width / tilemapWidth;
        this.tileHeight = this.getBounds().height / tilemapHeight;
        
        // chargement de la tilemap
        loadFromFile(stagePath);
        
        // chargement des textures
        this.tiles = new HashMap<>();
        
            this.tiles.put(0, getToolkit().getImage("textures/tile case vide.png"));        // case vide
            this.tiles.put(1, getToolkit().getImage("textures/tile bloc cassable.png"));    // bloc cassable
            this.tiles.put(2, getToolkit().getImage("textures/tile bloc poussable.png"));   // bloc poussable
            this.tiles.put(3, getToolkit().getImage("textures/tile bloc piégé.png"));       // bloc piégé
            this.tiles.put(4, getToolkit().getImage("textures/tile bloc incassable.png"));  // bloc incassable
            this.tiles.put(6, getToolkit().getImage("textures/tapis.png"));                 // case de tapis roulant
            this.tiles.put(9, getToolkit().getImage("textures/tile oiseau.png"));           // oiseau à capturer
        
        // affiche le nom du niveau pendant quelques secondes
        
        // Ces timers servent à rythmer les déplacements de snoopy et la balle
        initSnoopyMovementsTimer();
        initBallMovementsTimer();
        snoopyMovementsTimer.start();
        ballMovementsTimer.start();
        
        validate();
        
    }
    
    public void clear() {
        
        this.ball = null;
        this.snoopy = null;
        this.ballMovementsTimer = null;
        this.snoopyMovementsTimer = null;
        this.gamePage = null;
        
    }
    
    // retourne vrai si le niveau est chargé avec succès, retourne faux sinon
    public boolean loadFromFile(String stagePath) {
        
        boolean ret = false;
        
        // remplissage de la tilemap
        {
            FileReaderProject reader = new FileReaderProject(stagePath + ".txt");
            String[] row = reader.getinput();
            MatrixMaker maker = new MatrixMaker(row);
            tilemap = maker.create();
        }
        
        InputStream stream = null;
        InputStreamReader reader = null;
        BufferedReader buff = null;
        
        try {
            
            stream = new FileInputStream(stagePath + ".txt");
            reader = new InputStreamReader(stream);
            buff = new BufferedReader(reader);
            
            String line;
            String[] Sline;
            
            for(int row = 0; row < tilemapHeight; row++) {
                buff.readLine();
            }
            
            // lecture de l'index du niveau
            stageIdx = Integer.parseInt(buff.readLine());
            
            // initialisation de Snoopy
            line = buff.readLine();
            Sline = line.split(" ");
            int snoopyPosX = Integer.parseInt(Sline[0]);
            int snoopyPosY = Integer.parseInt(Sline[1]);
            snoopy = new Snoopy(snoopyPosX, snoopyPosY, getToolkit().getImage("textures/snoopy.png"));
            
            // initialisation de la balle
            line = buff.readLine();
            Sline = line.split(" ");
            double ballPosX = Double.parseDouble(Sline[0]);
            double ballPosY = Double.parseDouble(Sline[1]);
            ball = new Ball(ballPosX, ballPosY, getToolkit().getImage("textures/balle.png"));
            ball.direction = Integer.parseInt(Sline[2]);
            
            ret = true;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
            gamePage.gameRunning = false;
        } catch (IOException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ret;
        
    }
    
    // retourne vrai si le niveau est chargé avec succès, retourne faux sinon
    public boolean saveIntoFile() {
        
        boolean ret = false;
        File file = new File("saves/save0.txt");
        
        try {
            
            FileWriter writer = new FileWriter(file);
            
            // sauvegarde de la tilemap
            for(int row = 0; row < tilemapHeight; row++) {
                
                for(int col = 0; col < tilemapWidth; col++) {
                    writer.write(tilemap[row][col]);
                }
                
                writer.write("\n");
                
            }
            
            // sauvegarde de l'index du niveau
            writer.write(stageIdx + "\n");
            
            // sauvegarde de la position de snoopy
            writer.write(snoopy.getPosX() + " " + snoopy.getPosY());
            
            writer.close();
            
        } catch(IOException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
        
    }
    
    private void initBallMovementsTimer() {
        
        ActionListener tacheCountdown = (ActionEvent e1) -> {
            
            if((int) ball.getPosX() == snoopy.getPosX() && (int) ball.getPosY() == snoopy.getPosY() &&
                    (int) (ball.getPosX() - 0.5) == snoopy.getPosX() && (int) (ball.getPosY() - 0.5) == snoopy.getPosY()) {
                
                lose();
                
            }
            
            moveBall();
            repaint();
                    
        };
        
        ballMovementsTimer = new Timer(80, tacheCountdown);
        
    }
    
    private void initSnoopyMovementsTimer() {
        
        ActionListener tacheCountdown = (ActionEvent e1) -> {
            
            snoopy.isMoving = false;
            
            if(tilemap[snoopy.getPosY()][snoopy.getPosX()] == 3) {
                
                lose();
                
            }
            
            if(tilemap[snoopy.getPosY()][snoopy.getPosX()] == 6) {
                
                snoopy.isMoving = moveSnoopy(snoopy.direction);
            }
            
            if(capturedBirds == 4) {
                
                if(stageIdx == gamePage.nbStage) {
                    
                    MyInterface.window.setPage(new HomePage());
                    
                }
                
                capturedBirds = 0;
                gamePage.computeScore();
                gamePage.restartCountdown();
                loadFromFile("levels/niveau" + (stageIdx + 1));
                repaint();
                
            }
                    
        };
        
        snoopyMovementsTimer = new Timer(200, tacheCountdown);
        
    }
    
    public boolean moveBloc(int movement, int posX, int posY) {
        
        boolean ret = false;
        
        HashSet<Integer> obstacles = new HashSet<>();
        obstacles.add(1);
        obstacles.add(2);
        obstacles.add(4);
        obstacles.add(5);
        obstacles.add(9);

        switch(movement) {

            case Movement.UP:
                if(posY > 0 && !obstacles.contains(tilemap[posY - 1][posX])) {

                    tilemap[posY][posX] = 0;
                    tilemap[posY - 1][posX] = 4;
                    ret = true;

                }
                break;

            case Movement.DOWN:
                if(posY < this.tilemapHeight - 1 && !obstacles.contains(tilemap[posY + 1][posX])) {

                    tilemap[posY][posX] = 0;
                    tilemap[posY + 1][posX] = 4;
                    ret = true;

                }
                break;

            case Movement.RIGHT:
                if(posX < this.tilemapWidth - 1  && !obstacles.contains(tilemap[posY][posX + 1])) {

                    tilemap[posY][posX] = 0;
                    tilemap[posY][posX + 1] = 4;
                    ret = true;

                }
                break;

            case Movement.LEFT:
                if(posX > 0 && !obstacles.contains(tilemap[posY][posX - 1])) {

                    tilemap[posY][posX] = 0;
                    tilemap[posY][posX - 1] = 4;
                    ret = true;

                }
                break;

        }
        
        return ret;
        
    }
    
    public void moveBall() {
        
        HashSet<Integer> obstacles = new HashSet<>();
        obstacles.add(1);
        obstacles.add(2);
        obstacles.add(4);
        obstacles.add(5);
        
        double posX = ball.getPosX();
        double posY = ball.getPosY();
        
        switch(ball.direction) {
            
            case Movement.TOP_LEFT:
                if(posX > 0) {
                    
                    if(posY > 0) {
                    
                        if(obstacles.contains(tilemap[(int) (posY - 0.1)][(int) (posX - 0.1)])) {

                            boolean top = !obstacles.contains(tilemap[(int) (posY - 0.5)][(int) (posX)]);
                            boolean left = !obstacles.contains(tilemap[(int) (posY)][(int) (posX - 0.5)]);

                            if(top == false && left == false || (top == true && left == true)) {
                                ball.direction = Movement.BOT_RIGHT;
                            }

                            if(top == false && left == true) {
                                ball.direction = Movement.BOT_LEFT;
                            }

                            if(top == true && left == false) {
                                ball.direction = Movement.TOP_RIGHT;
                            }

                        }
                        
                    }
                    else {
                        
                        ball.direction = Movement.BOT_LEFT;
                        
                    }
                    
                }
                else {
                    
                    ball.direction = Movement.TOP_RIGHT;
                    
                }
                break;
                
            case Movement.TOP_RIGHT:
                if(posX <= tilemapWidth - 0.5) {
                    
                    if(posY > 0) {
                    
                        if(obstacles.contains(tilemap[(int) (posY - 0.1)][(int) (posX + 0.1)])) {

                            boolean top = !obstacles.contains(tilemap[(int) (posY - 0.5)][(int) (posX - 1)]);
                            boolean right = !obstacles.contains(tilemap[(int) (posY)][(int) (posX + 0.5)]);

                            if(top == false && right == false || (top == true && right == true)) {
                                ball.direction = Movement.BOT_LEFT;
                            }

                            if(top == false && right == true) {
                                ball.direction = Movement.BOT_RIGHT;
                            }

                            if(top == true && right == false) {
                                ball.direction = Movement.TOP_LEFT;
                            }

                        }
                    
                    }
                    else {
                        
                        ball.direction = Movement.BOT_RIGHT;
                        
                    }
                    
                }
                else {
                    
                    ball.direction = Movement.TOP_LEFT;
                    
                }
                break;
                
            case Movement.BOT_LEFT:
                if(posX > 0) {
                    
                    if(posY <= tilemapHeight - 0.5) {
                    
                        if(obstacles.contains(tilemap[(int) (posY + 0.1)][(int) (posX - 0.1)])) {

                            boolean bot = !obstacles.contains(tilemap[(int) (posY + 0.5)][(int) (posX)]);
                            boolean left = !obstacles.contains(tilemap[(int) (posY - 1)][(int) (posX - 0.5)]);

                            if((bot == false && left == false) || (bot == true && left == true)) {
                                ball.direction = Movement.TOP_RIGHT;
                            }

                            if(bot == false && left == true) {
                                ball.direction = Movement.TOP_LEFT;
                            }

                            if(bot == true && left == false) {
                                ball.direction = Movement.BOT_RIGHT;
                            }

                        }
                    
                    }
                    else {
                        
                        ball.direction = Movement.TOP_LEFT;
                        
                    }
                    
                }
                else {
                    
                    ball.direction = Movement.BOT_RIGHT;
                    
                }
                break;
                
            case Movement.BOT_RIGHT:
                if(posX <= tilemapWidth - 0.5) {
                    
                    if(posY <= tilemapHeight - 0.5) {
                    
                        if(obstacles.contains(tilemap[(int) (posY + 0.1)][(int) (posX + 0.1)])) {

                            boolean bot = !obstacles.contains(tilemap[(int) (posY + 0.5)][(int) (posX - 1)]);
                            boolean right = !obstacles.contains(tilemap[(int) (posY - 1)][(int) (posX + 0.5)]);

                            if((bot == false && right == false) || (bot == true && right == true)) {
                                ball.direction = Movement.TOP_LEFT;
                            }

                            if(bot == false && right == true) {
                                ball.direction = Movement.TOP_RIGHT;
                            }

                            if(bot == true && right == false) {
                                ball.direction = Movement.BOT_LEFT;
                            }

                        }
                    
                    }
                    else {
                        
                        ball.direction = Movement.TOP_RIGHT;
                        
                    }
                    
                }
                else {
                    
                    ball.direction = Movement.BOT_LEFT;
                    
                }
                break;
                
            default:
                break;
            
        }
        
        ball.move(ball.direction);
        
    }
    
    public boolean moveSnoopy(int movement) {
        
        boolean ret = false;
        
        if(snoopy.isMoving == false) {
        
            HashSet<Integer> obstacles = new HashSet<>();
            obstacles.add(1);
            obstacles.add(2);
            obstacles.add(4);
            obstacles.add(5);
            
            int posX = snoopy.getPosX();
            int posY = snoopy.getPosY();
            
            snoopy.direction = movement;
            
            switch(movement) {

                case Movement.UP:
                    if(posY > 0 && !obstacles.contains(tilemap[posY - 1][posX])) {
                        
                        this.snoopy.move(movement);
                        ret = true;

                    }
                    break;

                case Movement.DOWN:
                    if(posY < this.tilemapHeight - 1 && !obstacles.contains(tilemap[posY + 1][posX])) {

                        this.snoopy.move(movement);
                        ret = true;

                    }
                    break;

                case Movement.RIGHT:
                    if(posX < this.tilemapWidth - 1  && !obstacles.contains(tilemap[posY][posX + 1])) {

                        this.snoopy.move(movement);
                        ret = true;

                    }
                    break;

                case Movement.LEFT:
                    if(posX > 0 && !obstacles.contains(tilemap[posY][posX - 1])) {

                        this.snoopy.move(movement);
                        ret = true;

                    }
                    break;

            }
            
            if(tilemap[snoopy.getPosY()][snoopy.getPosX()] == 9) {
                
                birdCaptured();
                
            }
            
            snoopyMovementsTimer.restart();
            repaint();
        
        }
        
        return ret;
        
    }
    
    public void actionSnoopy() {
        
        int posX = snoopy.getPosX();
        int posY = snoopy.getPosY();
        
        switch(snoopy.direction) {

            case Movement.UP:
                if(posY > 0) {

                    if(tilemap[posY - 1][posX] == 1) {
                        tilemap[posY - 1][posX] = 0;
                    }
                    
                    if(tilemap[posY - 1][posX] == 2) {
                        moveBloc(snoopy.direction, posX, posY - 1);
                    }

                }
                break;

            case Movement.DOWN:
                if(posY < this.tilemapHeight - 1) {

                    if(tilemap[posY + 1][posX] == 1) {
                        tilemap[posY + 1][posX] = 0;
                    }
                    
                    if(tilemap[posY + 1][posX] == 2) {
                        moveBloc(snoopy.direction, posX, posY + 1);
                    }

                }
                break;

            case Movement.RIGHT:
                if(posX < this.tilemapWidth - 1) {

                    if(tilemap[posY][posX + 1] == 1) {
                        tilemap[posY][posX + 1] = 0;
                    }
                    
                    if(tilemap[posY][posX + 1] == 2) {
                        moveBloc(snoopy.direction, posX + 1, posY);
                    }

                }
                break;

            case Movement.LEFT:
                if(posX > 0) {

                    if(tilemap[posY][posX - 1] == 1) {
                        tilemap[posY][posX - 1] = 0;
                    }
                    
                    if(tilemap[posY][posX - 1] == 2) {
                        moveBloc(snoopy.direction, posX - 1, posY);
                    }

                }
                break;

        }
        
        repaint();
        
    }
    
    public void lose() {
        
        snoopyMovementsTimer.stop();
        
        if(gamePage.lifes > 1) {
                    
            try {
                
                gamePage.lifes--;
                gamePage.updateLifesDisplay();
                
                Thread.sleep(1000);
                
                gamePage.restartCountdown();
                capturedBirds = 0;
                loadFromFile("levels/niveau" + stageIdx);
                repaint();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else {
            
            MyInterface.window.setPage(new HomePage());

        }
        
    }
    
    private void birdCaptured() {
        
        capturedBirds++;
        tilemap[snoopy.getPosY()][snoopy.getPosX()] = 0;
        repaint();
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        for(int y = 0; y < this.tilemapHeight; ++y) {
            
            int posY = y * (this.getBounds().height / this.tilemapHeight);
            
            for(int x = 0; x < this.tilemapWidth; ++x) {
                
                int posX = x * (this.getBounds().width / this.tilemapWidth);
                Image tile = tiles.get(tilemap[y][x]);
                
                if(tile == null) {
                    tile = tiles.get(0);
                }
                
                g.drawImage(tile,
                            posX,
                            posY,
                            this.tileWidth,
                            this.tileHeight,
                            this);
                
            }
            
        }
        
        // dessine snoopy
        g.drawImage(this.snoopy.getImage(),
                    (int) (this.snoopy.getPosX() * this.tileWidth),
                    (int) (this.snoopy.getPosY() * this.tileHeight), 
                    this.tileWidth,
                    this.tileHeight,
                    this);
        
        // dessine la balle
        g.drawImage(this.ball.getImage(),
                    (int) (this.ball.getPosX() * this.tileWidth) - this.tileWidth / 2,
                    (int) (this.ball.getPosY() * this.tileHeight) - this.tileHeight / 2, 
                    this.tileWidth,
                    this.tileHeight,
                    this);
        
    }
    
}
