/// Calcul du score


package JeuDeBase.Counter;


public class Scoring {
    
    public int score;
    Time timer = new Time();
    
    public void setScore(){
        score=0;
    }
    public int getScore() {
        score = (int) (score + 100*timer.timer);
        return score;
    }
    
}
