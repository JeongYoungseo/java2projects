package deliverymanagement.deliveryController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        JButton btnMember = new JButton("회원 관리");
        JButton btnStore = new JButton("가게 관리");
        JButton btnMenu = new JButton("메뉴 관리");

        btnMember.addActionListener(e -> new MemberController());
        btnStore.addActionListener(e -> new StoreController());
        btnMenu.addActionListener(e -> new MenuController());

        setLayout(new GridLayout(3, 1));
        add(btnMember);
        add(btnStore);
        add(btnMenu);

        setTitle("배달 관리 시스템 (메인 화면)");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}



