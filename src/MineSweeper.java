import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MineSweeper {
    private static final int NUM_MINES=50;
    public static final int SIZE= 20;


    public static void main(String[] args){
        JFrame frame = new JFrame("Mine Sweeper | # of mines: " + NUM_MINES);

        topPanel topPanel=new topPanel();
        frame.add(topPanel, BorderLayout.NORTH);

        try {

            Font sevendigitsfont = Font.createFont(Font.TRUETYPE_FONT, new File("Seven Segment.ttf")).deriveFont(55f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Seven Segment.ttf")));

        }
        catch (IOException | FontFormatException e){

        }



        frame.add(new MineSweeperGUI(SIZE, SIZE, NUM_MINES,topPanel), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
