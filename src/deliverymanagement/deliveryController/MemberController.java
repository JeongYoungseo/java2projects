package deliverymanagement.deliveryController;

import bookmanagement.domain.BookVO;
import bookmanagement.reporsitory.BookRepository;
import bookmanagement.view.BookInsertView;
import bookmanagement.view.BookSearchView;
import bookmanagement.view.BookUpdateView;
import center_frame.CenterFrame;
import deliverymanagement.deliveryDomain.MemberVO;
import deliverymanagement.deliveryReporsitory.MemberReporsitory;
import deliverymanagement.deliveryView.memberView.MemberInsertVIew;
import deliverymanagement.deliveryView.memberView.MemberSearchVIew;
import deliverymanagement.deliveryView.memberView.MemberUpdateView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MemberController extends JFrame {
    MemberSearchVIew searchPan;
    MemberInsertVIew insertPan;
    MemberUpdateView updatePan;
    MemberReporsitory memberReporsitory;
    ArrayList<MemberVO> memberVOList;
    JComboBox<String> combo;
    JButton btnSearch;
    JButton btnInsert;
    JButton btnUpdate;
    JTable tableUpdate;
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    public MemberController() {
        //도서 검색
        searchPan = new MemberSearchVIew();
        combo = searchPan.getCmb();
        memberReporsitory = new MemberReporsitory();
        memberVOList = memberReporsitory.select("",combo.getSelectedIndex());
        searchPan.setMemberVOList(memberVOList);
        searchPan.initView();
        btnSearch = searchPan.getBtnSearch();
        btnSearch.addActionListener(btnSearchL);
        tabbedPane.add("회원검색",searchPan);


        // 도서 추가
        insertPan = new MemberInsertVIew();
        memberVOList = memberReporsitory.select("",0);
        insertPan.setMemberVOList(memberVOList);
        insertPan.initView();
        btnInsert = insertPan.getBtnInsert();
        btnInsert.addActionListener(btnInsertL);
        tabbedPane.add("회원추가",insertPan);

        // 도서 수정 및 삭제
        updatePan = new MemberUpdateView();
        memberVOList = memberReporsitory.select("",0);
        updatePan.setMemberVOList(memberVOList);
        updatePan.initView();
        btnUpdate = updatePan.getBtnUpdate();
        btnUpdate.addActionListener(btnUpdateL);
        tableUpdate = updatePan.getTable();
        tableUpdate.addMouseListener(tableUpdateL);
        tabbedPane.add("회원수정 및 삭제",updatePan);

        //탭추가
        add(tabbedPane);
        tabbedPane.addMouseListener(tabL);
        setTitle("배달관리시스템");
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
    ActionListener btnSearchL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            memberVOList = memberReporsitory.select(searchPan.getSearch(),combo.getSelectedIndex());
            searchPan.setMemberVOList(memberVOList);
            searchPan.putSearchResult();
        }
    };

    ActionListener btnInsertL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MemberVO vo = insertPan.neededInsertMemberData();
            memberReporsitory.insert(vo);
            memberVOList = memberReporsitory.select("",0);
            insertPan.setMemberVOList(memberVOList);
            insertPan.putSearchResult();
            insertPan.initInsertMemberData();
        }
    };
    MouseAdapter tableUpdateL = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {

            if(e.getClickCount() == 1) {
                int rowIndex = tableUpdate.getSelectedRow();
                updatePan.setTextField(rowIndex);

            }
            if(e.getClickCount() == 2) {
                int result = JOptionPane.showConfirmDialog(MemberController.this,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    MemberVO vo = updatePan.neededMemberUpdate();
                    memberReporsitory.delete(vo);
                    memberVOList = memberReporsitory.select("",0);
                    updatePan.setMemberVOList(memberVOList);
                    updatePan.putSearchResult();
                }

            }
        }
    };

    ActionListener btnUpdateL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MemberVO vo = updatePan.neededMemberUpdate();
            memberReporsitory.update(vo);
            memberVOList = memberReporsitory.select("",0);
            updatePan.setMemberVOList(memberVOList);
            updatePan.putSearchResult();
            updatePan.initMemberUpdateData();
        }
    };
    MouseAdapter tabL = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int tabIndex = tabbedPane.getSelectedIndex();
            if(tabIndex == 0) {
                memberVOList = memberReporsitory.select("",0);
                searchPan.setMemberVOList(memberVOList);
                searchPan.putSearchResult();
            } else if(tabIndex == 1) {
                memberVOList = memberReporsitory.select("",0);
                insertPan.setMemberVOList(memberVOList);
                insertPan.putSearchResult();
            } else{
                memberVOList = memberReporsitory.select("",0);
                updatePan.setMemberVOList(memberVOList);
                updatePan.putSearchResult();
            }
        }
    };
    public static void main(String[] args) {new MemberController();}
}
