package w1105;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.*;

public class DrawPolygonTest extends JFrame {
    public DrawPolygonTest() {
        DrawPannel dp = new DrawPannel();
        add(dp);

        setTitle("DrawPolygonTest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500, 400);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawPolygonTest();
    }
    class DrawPannel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            g.drawLine(10, 20, 500,20);
            g.setColor(Color.green);
            g.drawOval(20, 30, 150, 100);
            g.setColor(Color.blue);
            g.fillOval(200, 30, 150, 100);
            g.setColor(Color.yellow);
            g.fillOval(370, 30, 100, 100);
            g.setColor(Color.orange);
            g.drawRect(10, 150, 200, 150);
            g.setColor(Color.pink);
            g.fillRect(220, 150, 100, 100);
            g.setColor(Color.cyan);
            g.fillRoundRect(250, 150, 70, 70, 30, 30);
            g.setColor(Color.darkGray);
            int[] xPoints = {110,50,200};
            int[] yPoints = {200,250,250};
            g.drawPolygon(xPoints, yPoints, xPoints.length);
        }
    }
}
