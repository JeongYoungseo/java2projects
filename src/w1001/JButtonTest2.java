package w1001;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonTest2 extends JFrame {
    JTextField tf;
    public JButtonTest2() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int fw = 500;
        int fh = 200;
        int x = screenWidth/2 - fw/2;
        int y = screenHeight/2 - fh/2;

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setTitle("JButtonTest2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tf = new JTextField(20);
        JButton btn = new JButton("확인");
        add(tf);
        add(btn);
        btn.addActionListener(btnListener);
        setBounds(x, y, fw, fh);
        setVisible(true);
    }
    ActionListener btnListener = new ActionListener() {
       //callBack Method
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,tf.getText() + "가(이) 입력되었습니다." );
        }
    };
    public static void main(String[] args) {
        new JButtonTest2();
    }
}
