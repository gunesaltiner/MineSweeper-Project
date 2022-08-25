import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.*;


public class topPanel extends JPanel {

    private int seconds,minutes,elapsedTime;
    private JLabel timeLabel;
    private Timer timer;
    private Icon happy= new ImageIcon("happy.png");
    private JLabel label,label2;

    public topPanel(){

        timer = new Timer(1000,actListener);
        timeLabel= new JLabel();
        seconds=0;
        minutes=0;


        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        label = new JLabel(happy);
        add(label, BorderLayout.CENTER);

        label2 = new JLabel();
        add(label2,BorderLayout.WEST);

        String sec= String.format("%02d", seconds);
        String min= String.format("%02d", seconds);
        timeLabel.setText(min +":"+ sec);

        timeLabel.setForeground(Color.red);
        timer.start();
        add(timeLabel, BorderLayout.EAST);

    }


    ActionListener actListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            minutes = (elapsedTime/60000)%60;
            seconds = (elapsedTime/1000)%60;
            String second_string =String.format("%02d",seconds);
            String minutes_string = String.format("%02d", minutes);
            timeLabel.setText(minutes_string + ":" + second_string);
        }
    };

    public JLabel getLabel() {
        return label;
    }


    public Timer getTimer() {
        return timer;
    }



    public void setCounter(int count){
        label2.setText(String.valueOf(count));
        label2.setForeground(Color.red);

    }
}
