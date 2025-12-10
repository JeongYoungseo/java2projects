package deliverymanagement.deliveryView.storeView;

import deliverymanagement.deliveryDomain.MemberVO;
import deliverymanagement.deliveryDomain.StoreVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StoreUpdateView extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<StoreVO> storeVOList;
    String[] header = {"가게아이디", "점주아이디", "가게명", "주소"};

    JPanel panelS;
    JLabel[] labels = new JLabel[header.length];
    JTextField[] textFields = new JTextField[header.length - 1];

    JComboBox<String> ownerCombo;
    JButton btnUpdate;

    public StoreUpdateView(ArrayList<String> ownerList) {
        setLayout(new BorderLayout());
        panelS = new JPanel(new GridLayout(5, 4));
        ownerCombo = new JComboBox<>(ownerList.toArray(new String[0]));
        btnUpdate = new JButton("가게수정");

        int tfIndex = 0;
        for (int i = 0; i < header.length; i++) {
            labels[i] = new JLabel(header[i]);
            panelS.add(labels[i]);
            if (i == 1) {  // 점주아이디 위치
                panelS.add(ownerCombo);
            } else {
                textFields[tfIndex] = new JTextField();
                panelS.add(textFields[tfIndex]);
                tfIndex++;
            }
        }

        for (int i = 0; i < 3; i++)
            panelS.add(new JLabel(""));

        panelS.add(btnUpdate);
    }

    public void setTextField(int rowIndex) {

        textFields[0].setText(model.getValueAt(rowIndex, 0).toString());
        ownerCombo.setSelectedItem(model.getValueAt(rowIndex, 1));
        textFields[1].setText(model.getValueAt(rowIndex, 2).toString());
        textFields[2].setText(model.getValueAt(rowIndex, 3).toString());
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
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
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
    public void setStoreVOList(ArrayList<StoreVO> storeVOList) {
        this.storeVOList = storeVOList;
    }
    public StoreVO neededStoreUpdate() {
        StoreVO vo = new StoreVO();
        vo.setStoreId(textFields[0].getText());
        vo.setStoreOwnerId((String) ownerCombo.getSelectedItem());
        vo.setStoreName(textFields[1].getText());
        vo.setStoreAddress(textFields[2].getText());

        return vo;
    }
    public void initStoreUpdateData() {
        for(int i = 0; i < textFields.length; i++){
            textFields[i].setText("");
        }
        ownerCombo.setSelectedIndex(0);
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JTable getTable() {
        return table;
    }
}
