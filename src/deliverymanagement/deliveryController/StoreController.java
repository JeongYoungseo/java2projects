package deliverymanagement.deliveryController;

import center_frame.CenterFrame;
import deliverymanagement.deliveryDomain.StoreVO;
import deliverymanagement.deliveryReporsitory.MemberReporsitory;
import deliverymanagement.deliveryReporsitory.StoreReporsitory;
import deliverymanagement.deliveryView.storeView.StoreInsertVIew;
import deliverymanagement.deliveryView.storeView.StoreSearchVIew;
import deliverymanagement.deliveryView.storeView.StoreUpdateView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StoreController extends JFrame {
    StoreSearchVIew searchPan;
    StoreInsertVIew insertPan;
    StoreUpdateView updatePan;
    StoreReporsitory storeReporsitory;
    ArrayList<StoreVO> storeVOList;
    JComboBox<String> combo;
    JButton btnSearch;
    JButton btnInsert;
    JButton btnUpdate;
    JTable tableUpdate;
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    public StoreController() {
        //도서 검색
        searchPan = new StoreSearchVIew();
        combo = searchPan.getCmb();
        storeReporsitory = new StoreReporsitory();
        storeVOList = storeReporsitory.select("",combo.getSelectedIndex());
        searchPan.setStoreVOList(storeVOList);
        searchPan.initView();
        btnSearch = searchPan.getBtnSearch();
        btnSearch.addActionListener(btnSearchL);
        tabbedPane.add("가게검색",searchPan);


        // 도서 추가
        MemberReporsitory memberRepo = new MemberReporsitory();
        ArrayList<String> ownerList = memberRepo.selectOwnerId();
        insertPan = new StoreInsertVIew(ownerList);      // 점주 목록 포함된 생성자 사용
        storeVOList = storeReporsitory.select("",0);
        insertPan.setStoreVOList(storeVOList);
        insertPan.initView();

        btnInsert = insertPan.getBtnInsert();
        btnInsert.addActionListener(btnInsertL);
        tabbedPane.add("가게추가", insertPan);


        // 도서 수정 및 삭제
        memberRepo = new MemberReporsitory();   // ← 이미 선언된 멤버 변수에 할당 (추천)
        ownerList = memberRepo.selectOwnerId();
        updatePan = new StoreUpdateView(ownerList);
        storeVOList = storeReporsitory.select("",0);
        updatePan.setStoreVOList(storeVOList);
        updatePan.initView();
        btnUpdate = updatePan.getBtnUpdate();
        btnUpdate.addActionListener(btnUpdateL);
        tableUpdate = updatePan.getTable();
        tableUpdate.addMouseListener(tableUpdateL);
        tabbedPane.add("가게수정 및 삭제", updatePan);


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
        add(btnBack, BorderLayout.NORTH); //

    }
    ActionListener btnSearchL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            storeVOList = storeReporsitory.select(searchPan.getStoreSearch(),combo.getSelectedIndex());
            searchPan.setStoreVOList(storeVOList);
            searchPan.putSearchResult();
        }
    };

    ActionListener btnInsertL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreVO vo = insertPan.neededInsertStoreData();
            storeReporsitory.insert(vo);
            storeVOList = storeReporsitory.select("",0);
            insertPan.setStoreVOList(storeVOList);
            insertPan.putSearchResult();
            insertPan.initInsertStoreData();
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
                int result = JOptionPane.showConfirmDialog(StoreController.this,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    StoreVO vo = updatePan.neededStoreUpdate();
                    storeReporsitory.delete(vo);
                    storeVOList = storeReporsitory.select("",0);
                    updatePan.setStoreVOList(storeVOList);
                    updatePan.putSearchResult();
                }

            }
        }
    };

    ActionListener btnUpdateL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            StoreVO vo = updatePan.neededStoreUpdate();
            storeReporsitory.update(vo);
            storeVOList = storeReporsitory.select("",0);
            updatePan.setStoreVOList(storeVOList);
            updatePan.putSearchResult();
            updatePan.initStoreUpdateData();
        }
    };
    MouseAdapter tabL = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int tabIndex = tabbedPane.getSelectedIndex();
            if(tabIndex == 0) {
                storeVOList = storeReporsitory.select("",0);
                searchPan.setStoreVOList(storeVOList);
                searchPan.putSearchResult();
            } else if(tabIndex == 1) {
                storeVOList = storeReporsitory.select("",0);
                insertPan.setStoreVOList(storeVOList);
                insertPan.putSearchResult();
            } else{
                storeVOList = storeReporsitory.select("",0);
                updatePan.setStoreVOList(storeVOList);
                updatePan.putSearchResult();
            }
        }
    };
    public static void main(String[] args) {new StoreController();}
}
