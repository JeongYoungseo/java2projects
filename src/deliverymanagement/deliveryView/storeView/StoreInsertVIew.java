package deliverymanagement.deliveryView.storeView;

import deliverymanagement.deliveryDomain.StoreVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StoreInsertVIew extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<StoreVO> storeVOList;
    String[] header = {"가게아이디", "점주아이디", "가게명","주소"};
    JPanel panelS;
    JLabel[] labels = new JLabel[header.length];
    JTextField[] textFields = new JTextField[header.length-1];
    JComboBox<String> ownerCombo;
    JButton btnInsert;

    public StoreInsertVIew() {
        setLayout(new BorderLayout());
        panelS = new JPanel(new GridLayout(4, 4));
        btnInsert = new JButton("가게추가");
        ownerCombo = new JComboBox<>(new String[]{"점주선택"});

        int tfIndex = 0;
        for (int i = 0; i < header.length; i++) {
            labels[i] = new JLabel(header[i]);
            panelS.add(labels[i]);

            if (i == 1) { // 점주아이디 콤보박스
                panelS.add(ownerCombo);
            } else {
                textFields[tfIndex] = new JTextField();
                panelS.add(textFields[tfIndex]);
                tfIndex++;
            }
        }

        for (int i = 0; i < 3; i++) panelS.add(new JLabel(""));
        panelS.add(btnInsert);
    }

    public StoreInsertVIew(ArrayList<String> ownerList) {
        setLayout(new BorderLayout());
        ownerCombo = new JComboBox<>(ownerList.toArray(new String[0]));
        btnInsert = new JButton("가게추가");
        panelS = new JPanel(new GridLayout(4,4));

        int tfIndex = 0;
        for (int i = 0; i < header.length; i++) {
            labels[i] = new JLabel(header[i]);
            panelS.add(labels[i]);

            if (i == 1) {
                panelS.add(ownerCombo);
            } else {
                textFields[tfIndex] = new JTextField();
                panelS.add(textFields[tfIndex]);
                tfIndex++;
            }
        }

        for (int i = 0; i < 3; i++) panelS.add(new JLabel(""));
        panelS.add(btnInsert);
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
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);

        JScrollPane scrollPane = new JScrollPane(table);
        putSearchResult();
        add(scrollPane, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);

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

    public JButton getBtnInsert() {
        return btnInsert;
    }

    public void setStoreVOList(ArrayList<StoreVO> storeVOList) {
        this.storeVOList = storeVOList;
    }
    public StoreVO neededInsertStoreData() {
     StoreVO vo = new StoreVO();
     vo.setStoreId(textFields[0].getText());
     vo.setStoreOwnerId((String) ownerCombo.getSelectedItem());
     vo.setStoreName(textFields[1].getText());
     vo.setStoreAddress(textFields[2].getText());

     return vo;
    }
    public void initInsertStoreData() {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText("");
        }
        ownerCombo.setSelectedIndex(0);
    }
}
