package deliverymanagement.deliveryView.memberView;

import deliverymanagement.deliveryDomain.MemberVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MemberSearchVIew extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<MemberVO> memberVOList;
    String[] header = {"회원아이디", "비밀번호", "이름", "연락처", "주소", "역할"};

    JLabel lbl;
    JTextField txtSearch;
    JButton btnSearch;
    String search;
    JPanel panel;
    JComboBox<String> cmb;
    String[] comboStr = { "이름", "연락처"};

    public MemberSearchVIew() {

        setLayout(new BorderLayout());
        memberVOList = new ArrayList<>();

        cmb = new JComboBox<>(comboStr);
        lbl = new JLabel("검색어: ");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");

        panel = new JPanel();
        panel.add(cmb);
        panel.add(lbl);
        panel.add(txtSearch);
        panel.add(btnSearch);

        add(panel,BorderLayout.NORTH);
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
    public String getSearch() {
        search = txtSearch.getText();
        return search;
    }
    public void setMemberVOList(ArrayList<MemberVO> memberVOList) {
        this.memberVOList = memberVOList;
    }
    public JButton getBtnSearch() {
        return btnSearch;
    }
    public JComboBox<String> getCmb() {
        return cmb;
    }

}
