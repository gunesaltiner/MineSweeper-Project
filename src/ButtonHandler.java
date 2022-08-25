import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonHandler extends MouseAdapter implements ActionListener {
    private int row, col;
    private MineGrid grid;
    private JButton[][] allButton;
    private int numMines;
    private topPanel topPanel;
    private ImageIcon sad = new ImageIcon("sad.png");

    public ButtonHandler(int x, int y, MineGrid g, JButton[][] allButton, int numMines, topPanel topPanel) {
        row = x;
        col = y;
        grid = g;
        this.allButton = allButton;
        this.numMines = numMines;
        this.topPanel = topPanel;

    }

    public void actionPerformed(ActionEvent event) {
        if (grid.isMINE(row, col) && grid.isGameOver() == false) {
            topPanel.getTimer().stop();
            topPanel.getLabel().setIcon(sad);

            JButton button = (JButton) event.getSource();
            button.setBackground(Color.red);
            Icon mine = new ImageIcon("mine.png");
            button.setIcon(mine);
            for (int i = 0; i <= grid.getMineInformation().length - 1; i++) {
                for (int j = 0; j <= grid.getMineInformation()[0].length - 1; j++) {
                    if (grid.isMINE(i, j)) {
                        allButton[i][j].setIcon(mine);
                    }
                }
            }
            grid.setGameOver(true);

        } else {
            grid.openingMine(row, col);
        }
    }

    public void mouseClicked(MouseEvent event) {
        JButton button = (JButton) event.getSource();

        if (grid.isGameOver() == false) {
            if (event.getButton() == 3) {
                if (grid.getFlaggedInformation()[row][col] == 0 && allButton[row][col].getBackground() != Color.white && grid.isMaxFlagged() == false) {
                    Icon flag = new ImageIcon("flag.png");
                    button.setIcon(flag);
                    grid.incrementFLag(row, col);
                    topPanel.setCounter(grid.counter());
                    if (grid.isGameWin()) {
                        JOptionPane.showMessageDialog(null, "You are genius!!");
                        System.exit(0);
                    }
                } else {
                    button.setIcon(null);
                    grid.decrementFLag(row, col);
                    topPanel.setCounter(grid.counter());
                }
            }
        }
    }
}
