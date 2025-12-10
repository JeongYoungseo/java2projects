package bookmanagement.view;

import bookmanagement.domain.BookVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookSearchView extends JPanel {
    JTable table;
    DefaultTableModel model;
    ArrayList<BookVO> bookVOList;
    String[] header = {"도서번호", "도서명", "출판사", "저자명", "도서가격", "카테고리"};
    JLabel lbl;
    JTextField txtSearch;
    JButton btnSearch;
    String searchWord = "";
    JPanel panN;
    JComboBox<String> combo;
    String[] comboStr = {"도서명", "출판사", "저자명"};
    public BookSearchView() {
        setLayout(new BorderLayout());
        combo = new JComboBox<>(comboStr);
        lbl = new JLabel("검색어: ");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("검색");
        panN = new JPanel();
        panN.add(combo);
        panN.add(lbl);
        panN.add(txtSearch);
        panN.add(btnSearch);
    }

    // JTable과 DefaultTableModel을 연결하고 테이블과 관련된 내용을 초기화
    public void initView() {
        model = new DefaultTableModel(header,bookVOList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        // 테이블 컬럼너비
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);

        // 테이블의 행의 개수가 많을 경우, 스크롤바가 필요
        JScrollPane scrollPane = new JScrollPane(table);

        // 각 셀에 리스트에 저장된 BookVO객체가 가지고 있는 값들을 설정
        putSearchResult();
        // 현재 panel에 North에는 panN, center에는 scrollBar가 있는 테이블
        add(panN, BorderLayout.NORTH); // "North"(문자열) 넣는 것도 가능
        add(scrollPane, BorderLayout.CENTER);
    }
    // DefaultTableModel에 도서정보들을 하나씩 반환받아서 설정하는 메소드
    // 셀에 접근하기 위해
    public void putSearchResult(){
        // DefaultTableModel에 행 개수를 설정
        model.setRowCount(bookVOList.size());
        BookVO bVO = null;
        for(int i = 0; i < bookVOList.size(); i++){
            bVO = bookVOList.get(i);
            model.setValueAt(bVO.getIsbn(), i, 0); //행번호, 열번호
            model.setValueAt(bVO.getName(), i, 1);
            model.setValueAt(bVO.getPublish(), i, 2);
            model.setValueAt(bVO.getAuthor(), i, 3);
            model.setValueAt(bVO.getPrice(), i, 4);
            model.setValueAt(bVO.getCategoryName(), i, 5);
        }
    }
    public String getSearchWord(){
        searchWord = txtSearch.getText();
        return searchWord;
    }

    public void setBookVOList(ArrayList<BookVO> bookVOList){
        this.bookVOList = bookVOList;

    }
    public JButton getBtnSearch() {
        return btnSearch;
    }
    public JComboBox<String> getCombo() {
        return combo;
    }
}
