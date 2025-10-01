package w1001;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonTest extends JFrame {
    public JButtonTest() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setTitle("JButtonTest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] buttonTexts = {"클릭","버튼","집","쫑이","메렁"};
        JButton[] btns = new JButton[buttonTexts.length];
        for (int i = 0; i < buttonTexts.length; i++) {
            btns[i] = new JButton(buttonTexts[i]);
            btns[i].addActionListener(btnListener);
            add(btns[i]);
        }

        setBounds(200, 200, 300, 200);
        setVisible(true);
    }
    ActionListener btnListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,e.getActionCommand() + "버튼" );
        }
    };
    public static void main(String[] args) {
        new JButtonTest();
    }
}
