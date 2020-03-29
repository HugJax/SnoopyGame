
package Openning;

import Menu.*;
import JeuDeBase.GameState.*;


public class SnoopyGame {

    public static void main(String[] args) {
        Sounds.startmusic();
        Sounds.loadmusic("theme_song");
        Sounds.play("theme_song");
        MyInterface.window.setPage(new GamePage(0));
        
    }
    
}
