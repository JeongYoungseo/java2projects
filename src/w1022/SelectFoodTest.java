package w1022;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFoodTest extends JFrame {
    JComboBox comboBox;
    JLabel lbl;
    String[] items = {"비빔밥","불고기", "파스타", "피자", "스테이크"};
//JComboBox -> getSelected
    public SelectFoodTest() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Food: ");
        comboBox = new JComboBox(items);
        JButton button = new JButton("선택: ");
        panel.add(label); panel.add(comboBox); panel.add(button);
        add(panel,"North");
        lbl = new JLabel(new ImageIcon("./imgs_root/food_imgs/food"));
        add(lbl,"Center");


        // EventProgramimg
        button.addActionListener(buttonListener);

        setTitle("항목선택 가능 컴포넌트 JList and JComboBox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500,400);
        cf.centerXY();
        setBounds(cf.getX(),cf.getY(), cf.getFh(), cf.getFw());
        setVisible(true);

    }
    ActionListener buttonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = comboBox.getSelectedIndex();
            ImageIcon imageIcon = new ImageIcon("./imgs_root/food_imgs/food" +selectedIndex + ".jpg");
            lbl.setIcon(imageIcon);
        }
    };

    public static void main(String[] args) {
        new SelectFoodTest();
    }
}
