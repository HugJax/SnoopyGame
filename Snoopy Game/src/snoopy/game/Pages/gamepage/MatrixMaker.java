package snoopy.game.Pages.gamepage;


public class MatrixMaker {
    
    private final int [][] matrix;
    private final String [] row;
    
    public MatrixMaker( String [] row){
        this.row=row;
        matrix=new int[10][20];
    
    }
    
    public int [][] create(){
        
        for (int i=0;i<10;i++) {
            
            for (int j=0;j<20;j++) {
                matrix[i][j]= (row[i].charAt(j)-48);
            }
            
        }
        
        return matrix;
        
    }
    
}
