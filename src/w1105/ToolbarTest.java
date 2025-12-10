package w1105;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolbarTest extends JFrame {
    JLabel label = new JLabel("툴바의 항목을 선택하세요");
    JButton bNew = new JButton(new ImageIcon("D:/java2projects/javaproject2/src/toolbaricon/new.png"));
    JButton bOpen = new JButton(new ImageIcon("D:/java2projects/javaproject2/src/toolbaricon/open.png"));
    JButton bClose = new JButton(new ImageIcon("D:/java2projects/javaproject2/src/toolbaricon/close.jpg"));
    public ToolbarTest() {
        JToolBar tb = new JToolBar();
        add(tb, BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);

        tb.add(bNew);
        tb.add(bOpen);
        tb.addSeparator();
        tb.add(bClose);

        bNew.addActionListener(btnListener);
        bOpen.addActionListener(btnListener);
        bClose.addActionListener(btnListener);

        setTitle("Toolbar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500, 500);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if(button == bNew){
                label.setText("[새문서]선택");
            }
            else if(button == bOpen){
                label.setText("[열기]선택");
            }
            else{
                System.exit(0);
            }
        }
    };

    public static void main(String[] args) {
        new ToolbarTest();
    }
}
