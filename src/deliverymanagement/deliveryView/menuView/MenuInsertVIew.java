package deliverymanagement.deliveryView.menuView;

import deliverymanagement.deliveryDomain.MenuVO;
import deliverymanagement.deliveryDomain.StoreVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MenuInsertVIew extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<MenuVO> menuVOList;
    String[] header = {"메뉴아이디", "가게아이디", "메뉴명","가격", "설명"};
    JPanel panelS;
    JLabel[] labels = new JLabel[header.length];
    JTextField[] textFields = new JTextField[header.length-1];
    JComboBox<String> storeCombo;
    JButton btnInsert;

    public MenuInsertVIew(ArrayList<String> storeList) {
        setLayout(new BorderLayout());
        storeCombo = new JComboBox<>(storeList.toArray(new String[0]));
        btnInsert = new JButton("메뉴추가");
        panelS = new JPanel(new GridLayout(4,4));

        int tfIndex = 0;
        for (int i = 0; i < header.length; i++) {
            labels[i] = new JLabel(header[i]);
            panelS.add(labels[i]);
            if(i ==1) {
                panelS.add(storeCombo);
            } else {
                textFields[tfIndex] = new JTextField();
                panelS.add(textFields[tfIndex]);
                tfIndex++;
            }
        }

        for (int i = 0; i < 3; i++) {
            panelS.add(new JLabel(""));
        }
        panelS.add(btnInsert);
    }
    public void initView() {
        model = new DefaultTableModel(header,menuVOList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);

        JScrollPane scrollPane = new JScrollPane(table);
        putSearchResult();
        add(scrollPane, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);

    }
    public void putSearchResult() {
        model.setRowCount(menuVOList.size());
        MenuVO vo = new MenuVO();
        for(int i = 0; i < menuVOList.size(); i++){
            vo = menuVOList.get(i);
            model.setValueAt(vo.getMenuID(), i, 0);
            model.setValueAt(vo.getMenuStoreID(), i, 1);
            model.setValueAt(vo.getMenuName(), i, 2);
            model.setValueAt(vo.getMenuPrice(), i, 3);
            model.setValueAt(vo.getMenuDescription(), i, 4);

        }
    }

    public JButton getBtnInsert() {
        return btnInsert;
    }

    public void setMenuVOList(ArrayList<MenuVO> menuVOList) {
        this.menuVOList = menuVOList;
    }
    public MenuVO neededInsertMenuData() {
     MenuVO vo = new MenuVO();
     vo.setMenuID(textFields[0].getText());
     vo.setMenuStoreID((String) storeCombo.getSelectedItem());
     vo.setMenuName(textFields[1].getText());
     vo.setMenuPrice(Integer.parseInt(textFields[2].getText()));
     vo.setMenuDescription(textFields[3].getText());

     return vo;
    }
    public void initInsertMenuData() {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText("");
        }
        storeCombo.setSelectedIndex(0);
    }
}
