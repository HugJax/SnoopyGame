/// 


package JeuDeBase.Counter;


public class Birds {
    private int nbbirds = 0;
    
    public void addBird() {
        nbbirds++;
    }
    
    public boolean isThatAll() {
        if (nbbirds!=4) {
            return false;
        } else {return true;}
    }
    
}
