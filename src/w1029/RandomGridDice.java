package w1029;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomGridDice extends JFrame {
    public RandomGridDice() {
        Random rand = new Random();
        int rows = rand.nextInt(10)+1; //0~bound-1
        int cols = rand.nextInt(10)+1;
        setLayout(new GridLayout(rows, cols));


        Color[] colors = {Color.blue,Color.green,Color.cyan,Color.gray,Color.orange,Color.red};
        for (int i = 0; i < rows*cols; i++) {
            int diceNum = rand.nextInt(6)+1;
            JButton jb = new JButton(diceNum+"");
            jb.setBackground(colors[diceNum-1]);
            add(jb);
        }

        setTitle("RandomGridDice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500, 500);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    public static void main(String[] args) {
        new RandomGridDice();
    }
}
