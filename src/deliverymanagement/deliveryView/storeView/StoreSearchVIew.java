package deliverymanagement.deliveryView.storeView;

import deliverymanagement.deliveryDomain.StoreVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StoreSearchVIew extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<StoreVO> storeVOList;
    String[] header = {"가게아이디", "점주아이디", "가게명", "주소"};

    JLabel lbl;
    JTextField txtSearch;
    JButton btnSearch;
    String search;
    JPanel panel;
    JComboBox<String> cmb;
    String[] comboStr = {"가게아이디", "점주아이디", "가게명"};

    public StoreSearchVIew() {
        setLayout(new BorderLayout());
        cmb = new JComboBox<>(comboStr);
        lbl = new JLabel("검색어: ");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        panel = new JPanel();
        panel.add(cmb);
        panel.add(lbl);
        panel.add(txtSearch);
        panel.add(btnSearch);
    }

    public void initView() {
        model = new DefaultTableModel(header,storeVOList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);

        JScrollPane scrollPane = new JScrollPane(table);
        putSearchResult();

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void putSearchResult() {
        model.setRowCount(storeVOList.size());
        StoreVO vo = new StoreVO();
        for(int i = 0; i < storeVOList.size(); i++){
            vo = storeVOList.get(i);
            model.setValueAt(vo.getStoreId(), i, 0);
            model.setValueAt(vo.getStoreOwnerId(), i, 1);
            model.setValueAt(vo.getStoreName(), i, 2);
            model.setValueAt(vo.getStoreAddress(), i, 3);
        }
    }
    public String getStoreSearch() {
        search = txtSearch.getText();
        return search;
    }
    public void setStoreVOList(ArrayList<StoreVO> storeVOList) {
        this.storeVOList = storeVOList;
    }
    public JButton getBtnSearch() {
        return btnSearch;
    }
    public JComboBox<String> getCmb() {
        return cmb;
    }

}
