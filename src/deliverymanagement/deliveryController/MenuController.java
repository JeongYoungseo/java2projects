package deliverymanagement.deliveryController;

import center_frame.CenterFrame;
import deliverymanagement.deliveryDomain.MenuVO;
import deliverymanagement.deliveryReporsitory.MenuReporsitory;
import deliverymanagement.deliveryView.menuView.MenuInsertVIew;
import deliverymanagement.deliveryView.menuView.MenuSearchVIew;
import deliverymanagement.deliveryView.menuView.MenuUpdateView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MenuController extends JFrame {
    MenuSearchVIew searchPan;
    MenuInsertVIew insertPan;
    MenuUpdateView updatePan;
    MenuReporsitory menuRepo;
    ArrayList<MenuVO> menuVOList;
    JComboBox<String> combo;
    JButton btnSearch, btnInsert, btnUpdate;
    JTable tableUpdate;
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    public MenuController() {
        // 메뉴 검색
        searchPan = new MenuSearchVIew();
        combo = searchPan.getCmb();
        menuRepo = new MenuReporsitory();
        menuVOList = menuRepo.select("", combo.getSelectedIndex());
        searchPan.setMenuVOList(menuVOList);
        searchPan.initView();
        btnSearch = searchPan.getBtnSearch();
        btnSearch.addActionListener(btnSearchL);
        tabbedPane.add("메뉴검색", searchPan);

        // 메뉴 추가
        ArrayList<String> storeList = menuRepo.selectStoreId(); // FK 가져오기
        insertPan = new MenuInsertVIew(storeList);
        menuVOList = menuRepo.select("", 0);
        insertPan.setMenuVOList(menuVOList);
        insertPan.initView();
        btnInsert = insertPan.getBtnInsert();
        btnInsert.addActionListener(btnInsertL);
        tabbedPane.add("메뉴추가", insertPan);

        // 메뉴 수정 및 삭제
        storeList = menuRepo.selectStoreId();
        updatePan = new MenuUpdateView(storeList);
        menuVOList = menuRepo.select("", 0);
        updatePan.setMenuVOList(menuVOList);
        updatePan.initView();
        btnUpdate = updatePan.getBtnUpdate();
        btnUpdate.addActionListener(btnUpdateL);
        tableUpdate = updatePan.getTable();
        tableUpdate.addMouseListener(tableUpdateL);
        tabbedPane.add("메뉴수정 및 삭제", updatePan);

        add(tabbedPane);
        tabbedPane.addMouseListener(tabL);
        setTitle("메뉴관리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CenterFrame cf = new CenterFrame(600, 500);
        cf.centerXY();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());
        setVisible(true);

        JButton btnBack = new JButton("뒤로가기");
        btnBack.addActionListener(e -> {
            dispose();
            new MainFrame();
        });
        add(btnBack, BorderLayout.NORTH);

    }

    ActionListener btnSearchL = e -> {
        menuVOList = menuRepo.select(searchPan.getMenuSearch(), combo.getSelectedIndex());
        searchPan.setMenuVOList(menuVOList);
        searchPan.putSearchResult();
    };


    ActionListener btnInsertL = e -> {
        MenuVO vo = insertPan.neededInsertMenuData();
        menuRepo.insert(vo);

        menuVOList = menuRepo.select("", 0);
        insertPan.setMenuVOList(menuVOList);
        insertPan.putSearchResult();
        insertPan.initInsertMenuData();
    };


    MouseAdapter tableUpdateL = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getClickCount() == 1) {
                int row = tableUpdate.getSelectedRow();
                updatePan.setTextField(row);
            }

            if (e.getClickCount() == 2) {
                int chk = JOptionPane.showConfirmDialog(MenuController.this,
                        "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);

                if (chk == JOptionPane.YES_OPTION) {
                    MenuVO vo = updatePan.neededMenuUpdate();
                    menuRepo.delete(vo);

                    menuVOList = menuRepo.select("", 0);
                    updatePan.setMenuVOList(menuVOList);
                    updatePan.putSearchResult();
                }
            }
        }
    };


    ActionListener btnUpdateL = e -> {
        MenuVO vo = updatePan.neededMenuUpdate();
        menuRepo.update(vo);

        menuVOList = menuRepo.select("", 0);
        updatePan.setMenuVOList(menuVOList);
        updatePan.putSearchResult();
        updatePan.initMenuUpdateData();
    };


    MouseAdapter tabL = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int idx = tabbedPane.getSelectedIndex();

            menuVOList = menuRepo.select("", 0);

            if (idx == 0) {
                searchPan.setMenuVOList(menuVOList);
                searchPan.putSearchResult();
            } else if (idx == 1) {
                insertPan.setMenuVOList(menuVOList);
                insertPan.putSearchResult();
            } else {
                updatePan.setMenuVOList(menuVOList);
                updatePan.putSearchResult();
            }
        }
    };


    public static void main(String[] args) {
        new MenuController();
    }
}
