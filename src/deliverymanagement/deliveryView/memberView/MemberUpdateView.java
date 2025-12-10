package deliverymanagement.deliveryView.memberView;

import deliverymanagement.deliveryDomain.MemberVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MemberUpdateView extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<MemberVO> memberVOList;
    String[] header = {"회원아이디", "비밀번호", "이름", "연락처", "주소", "역할"};
    String[] role = {"고객", "점주", "배달기사"};

    JPanel panelS;
    JLabel[] labels = new JLabel[header.length];
    JTextField[] textFields = new JTextField[header.length-1];
    JComboBox<String> roleCombo;
    JButton btnUpdate;

    public MemberUpdateView() {
        setLayout(new BorderLayout());
        roleCombo = new JComboBox(role);
        btnUpdate = new JButton("회원수정");
        panelS = new JPanel(new GridLayout(4,4));
        for (int i = 0; i < header.length; i++) {
            labels[i] = new JLabel(header[i]);
            panelS.add(labels[i]);
            if(i < header.length-1){
                textFields[i] = new JTextField();
                panelS.add(textFields[i]);
            } else{
                panelS.add(roleCombo);
            }
        }
        for (int i = 0; i < 3; i++) {
            panelS.add(new JLabel(""));
        }
        panelS.add(btnUpdate);
    }
    public void setTextField(int rowIndex){
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText(model.getValueAt(rowIndex, i).toString());
        }
        roleCombo.setSelectedItem((String)model.getValueAt(rowIndex,5 ));
    }
    public void initView() {
        model = new DefaultTableModel(header,memberVOList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(table);
        putSearchResult();
        add(scrollPane, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);

    }
    public void putSearchResult() {
        model.setRowCount(memberVOList.size());
        MemberVO memVO = new MemberVO();
        for(int i = 0; i < memberVOList.size(); i++){
            memVO = memberVOList.get(i);
            model.setValueAt(memVO.getMemberId(), i, 0);
            model.setValueAt(memVO.getMemberPassword(), i, 1);
            model.setValueAt(memVO.getMemberName(), i, 2);
            model.setValueAt(memVO.getMemberPhone(), i, 3);
            model.setValueAt(memVO.getMemberAddress(), i, 4);
            model.setValueAt(memVO.getMemberRole(), i, 5);
        }
    }
    public void setMemberVOList(ArrayList<MemberVO> memberVOList) {
        this.memberVOList = memberVOList;
    }
    public MemberVO neededMemberUpdate() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(textFields[0].getText());
        memberVO.setMemberPassword(textFields[1].getText());
        memberVO.setMemberName(textFields[2].getText());
        memberVO.setMemberPhone(textFields[3].getText());
        memberVO.setMemberAddress(textFields[4].getText());
        memberVO.setMemberRole(roleCombo.getSelectedItem().toString());

        return memberVO;
    }
    public void initMemberUpdateData() {
        for(int i = 0; i < textFields.length; i++){
            textFields[i].setText("");
        }
        roleCombo.setSelectedIndex(0);
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JTable getTable() {
        return table;
    }
}
