package deliverymanagement.deliveryReporsitory;

import deliverymanagement.deliveryDomain.StoreVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreReporsitory {
    ArrayList<StoreVO> storeVOList;

    public ArrayList<StoreVO> select(String search, int selected) {
        Connection con = JDBCConnectorDelivery.getConnection();
        storeVOList = new ArrayList<StoreVO>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String[] columnName = {"가게아이디", "점주아이디","가게명","주소"};
        String sql = "select 가게아이디, 점주아이디, 가게명, 주소 from 가게 where  " + columnName[selected] + " like ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                StoreVO storeVO = new StoreVO();
                storeVO.setStoreId(rs.getString("가게아이디"));
                storeVO.setStoreOwnerId(rs.getString("점주아이디"));
                storeVO.setStoreName(rs.getString("가게명"));
                storeVO.setStoreAddress(rs.getString("주소"));
                storeVOList.add(storeVO);
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
        return storeVOList;
    }

    public void insert(StoreVO storeVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "insert into 가게 values(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, storeVO.getStoreId());
            ps.setString(2, storeVO.getStoreOwnerId());
            ps.setString(3, storeVO.getStoreName());
            ps.setString(4, storeVO.getStoreAddress());
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

    public void update(StoreVO storeVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "update 가게 set 점주아이디=?, 가게명=?, 주소=? where 가게아이디=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, storeVO.getStoreOwnerId());
            ps.setString(2, storeVO.getStoreName());
            ps.setString(3, storeVO.getStoreAddress());
            ps.setString(4, storeVO.getStoreId());
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
    public void delete(StoreVO storeVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "delete from 가게 where 가게아이디=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, storeVO.getStoreId());
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
