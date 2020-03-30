/// Calcul du score


package JeuDeBase.Counter;


public class Scoring {
    
    public static int score;
    
    public void setScore(){
        score=0;
    }
    public int getScore() {
        score = (int) (score + 100*Time.timer);
        return score;
    }
    
}
