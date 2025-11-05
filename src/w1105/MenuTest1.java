package w1105;

import center_frame.CenterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTest1 extends JFrame {
    JLabel lbl = new JLabel("메뉴를 선택하면 문자열이 변경됩니다.", JLabel.CENTER);
    JMenuItem miNew = new JMenuItem("새문서");
    JMenuItem miOpen = new JMenuItem("열기");
    JMenuItem miClose = new JMenuItem("닫기");

    public MenuTest1() {
        add(lbl);
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu mFile = new JMenu("파일");
        JMenu mEdit = new JMenu("편집");
        mb.add(mFile);
        mb.add(mEdit);

        mFile.add(miNew);
        mFile.add(miOpen);
        mFile.add(miClose);

        miNew.addActionListener(menuListenr);
        miOpen.addActionListener(menuListenr);
        miClose.addActionListener(menuListenr);

        setTitle("메뉴 작성");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(500, 500);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);
    }

    ActionListener menuListenr = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JMenuItem mi = (JMenuItem) e.getSource();
            if(mi == miNew) {
                lbl.setText("[새문서] 메뉴 항목을 선택했습니다.");
            } else if (mi == miOpen) {
                lbl.setText("[열기] 메뉴 항목을 선택했습니다.");
            } else {
                System.exit(0);
            }
        }
    };

    public static void main(String[] args) {
        new MenuTest1();
    }
}

