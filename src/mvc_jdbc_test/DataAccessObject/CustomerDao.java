package mvc_jdbc_test.DataAccessObject;

import java.sql.*;
import java.util.*;
import mvc_jdbc_test.entity.Customer;

public class CustomerDao {
    public Customer getCustomerId (Connection conn, String id) {
        Customer c = null;
        String sql = "select * from 고객 where 고객아이디 = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
           ps.setString(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()) {
               c = new Customer(
                       rs.getString("고객아이디"),
                       rs.getString("고객이름"),
                       rs.getInt("나이"),
                       rs.getString("등급"),
                       rs.getString("직업"),
                       rs.getInt("적립금")
               );
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public void updateCustomer(Connection con, Customer c){
        String sql = "update 고객 set 고객이름=?, 나이=?,등급=?,직업=?,적립금=? where 고객아이디=?";
        try (PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1, c.getCustomername());
            ps.setInt(2, c.getAge());
            ps.setString(3,c.getLevel());
            ps.setString(4, c.getJob());
            ps.setInt(5,c.getReward());
            ps.setString(6,c.getCustomerid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteCustomer(Connection con, String id) {
        String sql = "delete from 고객 where 고객아이디=?";
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


