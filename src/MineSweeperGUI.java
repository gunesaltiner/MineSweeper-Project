import javax.swing.*;
import java.awt.*;


public class MineSweeperGUI extends JPanel {
    private MineGrid grid;
    private topPanel topPanel;

    public MineSweeperGUI(int numRows, int numCols, int numMines,topPanel topPanel){
        JButton[][] allButton=new JButton[numRows][numCols];
        grid= new MineGrid(numRows, numCols, numMines,allButton);
        setLayout(new GridLayout(numRows, numCols));

        this.topPanel=topPanel;
        this.topPanel.setCounter(numMines);

        for (int i=0 ; i < numRows ;i++){
            for (int j=0; j < numCols ;j++){
                JButton button = new JButton();
                button.setBackground(Color.lightGray);
                allButton[i][j]=button;
                add(button);
                button.addActionListener(new ButtonHandler(i,j,grid,allButton,numMines,this.topPanel));
                button.addMouseListener(new ButtonHandler(i,j,grid,allButton,numMines,this.topPanel));
            }
        }
    }
}
