package w1015;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonTest extends JFrame {
    String[] rbStrArr = {"아이브","블랙핑크","에스파"};
    String[] imgNameArr = {"totoro", "images", "chihiro"};
    JRadioButton[] rbArr = new JRadioButton[rbStrArr.length];
    JLabel lbl = new JLabel("",JLabel.CENTER);

    public RadioButtonTest() {
        JPanel panel = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        int i = 0;
        for (String str : rbStrArr) {
            rbArr[i] = new JRadioButton(str);
            rbArr[i].addActionListener(radioBtnListener);
            bg.add(rbArr[i]);
            panel.add(rbArr[i]);
            i++;
        }
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);
        add(panel,"North");
        add(lbl,"Center");

//        for(int i = 0; i < rbStrArr.length; i++) {
//            rbArr[i] = new JRadioButton(rbStrArr[i]);
//        }
        setTitle("RadioButtonTest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500, 300);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    ActionListener radioBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;
            for (JRadioButton rb : rbArr) {
                if (rb.isSelected()) {
                    ImageIcon imageIcon = new ImageIcon("D:/java2projects/javaproject2/src/w1015/imgs/" + imgNameArr[i] + ".jpg");
                    lbl.setIcon(imageIcon);
                    JOptionPane.showMessageDialog(null, rb.getText() + "가 선택되었습니다.");
                }
                i++;
            }
        }
    };

    public static void main(String[] args) {
        new RadioButtonTest();
    }
}
