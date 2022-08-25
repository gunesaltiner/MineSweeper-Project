import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MineGrid {

    private static final int MINE = -1;
    private int[][] mineInformation;
    private boolean isGameOver;
    private int[][] flaggedInformation;
    private int numMines;
    private JButton[][] allButton;


    public MineGrid(int numRows, int numCols, int numMines,JButton[][] allButton){
        mineInformation= new int[numRows][numCols];
        flaggedInformation= new int[numRows][numCols];
        initializeCells();
        placeMines(numMines);
        setMineInformation();
        isGameOver=false;
        this.numMines=numMines;
        this.allButton=allButton;
    }

    private void initializeCells(){
        for (int i=0; i< mineInformation.length ;i++){
            for (int j=0; j< mineInformation[0].length ;j++){
                mineInformation[i][j] = 0;
                flaggedInformation[i][j] = 0;
            }
        }
    }

    private void placeMines(int numMines){
        Random random = new Random();
        for (int i=0; i<numMines ;i++){
            int r = random.nextInt(mineInformation.length);
            int c = random.nextInt(mineInformation[0].length);
            if (mineInformation[r][c] != MINE) {
                mineInformation[r][c] = MINE;
            } else {
                i--;
            }
        }
    }

    private void incrementMineCountAt(int i, int j){
        if(isInsideGrid(i, j) && !isMINE(i, j)) {
            mineInformation[i][j]++;
        }
    }

    private void setMineInformation(){
        for (int i=0; i< mineInformation.length ;i++){
            for (int j=0; j< mineInformation[0].length; j++){
                if (mineInformation[i][j]==MINE){
                    //previous row
                    incrementMineCountAt(i-1, j-1);
                    incrementMineCountAt(i-1, j);
                    incrementMineCountAt(i-1, j+1);

                    // left and right cells
                    incrementMineCountAt(i,j-1);
                    incrementMineCountAt(i,j+1);

                    //next row
                    incrementMineCountAt(i+1, j-1);
                    incrementMineCountAt(i+1, j);
                    incrementMineCountAt(i+1, j+1);

                }
            }
        }
    }

    private boolean isInsideGrid(int i, int j){
        return (i >=0 && i< mineInformation.length) && (j>=0 && j< mineInformation[0].length);
    }

    public boolean isMINE(int i, int j){
        return mineInformation[i][j]==MINE;
    }

    public int getCellContent(int i, int j){
        return mineInformation[i][j];
    }

    public int[][] getMineInformation(){
        return mineInformation;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;

    }

    public boolean isGameWin(){
        int count = 0;

        for(int i=0; i< mineInformation.length ;i++){
            for (int j=0; j< getMineInformation()[0].length ;j++){

                if (isMINE(i,j) && flaggedInformation[i][j]==1 ){
                    count++;
                } } }
                    if (count==numMines){
                        return true;
                    }
                    return false;
    }

    public void incrementFLag(int i, int j){
        flaggedInformation[i][j]++;

    }

    public void decrementFLag(int i, int j){
        flaggedInformation[i][j]--;
    }

    public int[][] getFlaggedInformation(){
        return flaggedInformation;
    }

    public void openingMine(int row, int col){
        if (isInsideGrid(row,col)&& getCellContent(row,col)!=0 && isGameOver==false){
            allButton[row][col].setText(String.valueOf(getCellContent(row,col)));
            allButton[row][col].setBackground(Color.white);

        }if (isInsideGrid(row,col) && getCellContent(row,col)==0 && allButton[row][col].getBackground()!=Color.white && isGameOver==false){

                allButton[row][col].setText("");
                allButton[row][col].setBackground(Color.white);
                openingMine(row-1,col);
                openingMine(row+1,col);
                openingMine(row,col-1);
                openingMine(row,col+1);
        }





    }

    public boolean isMaxFlagged(){
        int flagged=0;

        for (int i=0; i<getFlaggedInformation().length ;i++){
            for (int j=0; j<getFlaggedInformation()[0].length ;j++){
                if (getFlaggedInformation()[i][j]==1){
                    flagged++;
                }
            }
        }
        if (flagged==numMines){
            return true;
        }return false;
    }

    public int counter(){
        int flagged=0;

        for (int i=0; i<getFlaggedInformation().length ;i++){
            for (int j=0; j<getFlaggedInformation()[0].length ;j++){
                if (getFlaggedInformation()[i][j]==1){
                    flagged++;
                }
            }
        }
        return numMines-flagged;
    }
}
