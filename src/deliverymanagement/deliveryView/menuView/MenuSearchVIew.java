package deliverymanagement.deliveryView.menuView;

import deliverymanagement.deliveryDomain.MenuVO;
import deliverymanagement.deliveryDomain.StoreVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MenuSearchVIew extends JPanel {
    JTable table;
    DefaultTableModel model;

    ArrayList<MenuVO> menuVOList;
    String[] header = {"메뉴아이디", "가게아이디", "메뉴명", "가격", "설명"};

    JLabel lbl;
    JTextField txtSearch;
    JButton btnSearch;
    JPanel panel;
    JComboBox<String> cmb;
    String[] comboStr = {"메뉴아이디", "가게아이디", "메뉴명", "가격", "설명"};

    public MenuSearchVIew() {
        setLayout(new BorderLayout());

        menuVOList = new ArrayList<>();

        cmb = new JComboBox<>(comboStr);
        lbl = new JLabel("검색어: ");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("검색");

        panel = new JPanel();
        panel.add(cmb);
        panel.add(lbl);
        panel.add(txtSearch);
        panel.add(btnSearch);

        add(panel, BorderLayout.NORTH);
    }

    public void initView() {
        model = new DefaultTableModel(header, menuVOList.size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        putSearchResult();

        add(scrollPane, BorderLayout.CENTER);
    }

    public void putSearchResult() {
        model.setRowCount(menuVOList.size());

        for (int i = 0; i < menuVOList.size(); i++) {
            MenuVO vo = menuVOList.get(i);
            model.setValueAt(vo.getMenuID(), i, 0);
            model.setValueAt(vo.getMenuStoreID(), i, 1);
            model.setValueAt(vo.getMenuName(), i, 2);
            model.setValueAt(vo.getMenuPrice(), i, 3);
            model.setValueAt(vo.getMenuDescription(), i, 4);
        }
    }

    public void setMenuVOList(ArrayList<MenuVO> menuVOList) {
        this.menuVOList = menuVOList;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JComboBox<String> getCmb() {
        return cmb;
    }

    public String getMenuSearch() {
        return txtSearch.getText();
    }
}
