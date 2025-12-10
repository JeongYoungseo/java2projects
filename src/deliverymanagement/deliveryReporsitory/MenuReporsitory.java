package deliverymanagement.deliveryReporsitory;

import deliverymanagement.deliveryDomain.MenuVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuReporsitory {
    ArrayList<MenuVO> menuVOList;

    public ArrayList<MenuVO> select(String search, int selected) {
        Connection con = JDBCConnectorDelivery.getConnection();
        menuVOList = new ArrayList<MenuVO>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String[] columnName = {"메뉴아이디", "가게아이디","메뉴명","가격","설명"};
        String sql = "select 메뉴아이디, 가게아이디, 메뉴명, 가격, 설명 from 메뉴 where  " + columnName[selected] + " like ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                MenuVO menuVO = new MenuVO();
                menuVO.setMenuID(rs.getString("메뉴아이디"));
                menuVO.setMenuStoreID(rs.getString("가게아이디"));
                menuVO.setMenuName(rs.getString("메뉴명"));
                menuVO.setMenuPrice(rs.getInt("가격"));
                menuVO.setMenuDescription(rs.getString("설명"));
                menuVOList.add(menuVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return menuVOList;
    }
    public ArrayList<String> selectStoreId(){
        ArrayList<String> storeList = new ArrayList<>();
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "select 가게아이디 from 가게 ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                storeList.add(rs.getString("가게아이디"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return storeList;
    }


    public void insert(MenuVO menuVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "insert into 메뉴 values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, menuVO.getMenuID());
            ps.setString(2, menuVO.getMenuStoreID());
            ps.setString(3, menuVO.getMenuName());
            ps.setInt(4, menuVO.getMenuPrice());
            ps.setString(5, menuVO.getMenuDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println("insert close failed");
                e.printStackTrace();
            }
        }
    }

    public void update(MenuVO menuVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "update 메뉴 set 가게아이디=?, 메뉴명=?, 가격=?, 설명=? where 메뉴아이디=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, menuVO.getMenuStoreID());
            ps.setString(2, menuVO.getMenuName());
            ps.setInt(3, menuVO.getMenuPrice());
            ps.setString(4, menuVO.getMenuDescription());
            ps.setString(5, menuVO.getMenuID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println("insert close failed");
                e.printStackTrace();
            }
        }
    }
    public void delete(MenuVO menuVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "delete from 메뉴 where 메뉴아이디=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, menuVO.getMenuID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                System.out.println("insert close failed");
                e.printStackTrace();
            }
        }
    }
}
