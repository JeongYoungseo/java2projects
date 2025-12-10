package mvc_jdbc_test.dataAccessObject;

import java.sql.*;

import mvc_jdbc_test.entity.Customer;

// Data Access Object (DAO)
// ==> DB 접근 전용 클래스 (데이터베이스와 직접 통신하는 역할)
// ==> 데이터베이스와 직접 연결되어 데이터를 읽고, 쓰고, 수정하고, 삭제하는 기능 수행
// ==> 그 중 '수정(Update)'과 '삭제(Delete)' 기능을 구현함
// [DAO 사용 이유]
// 1. MainController의 코드가 간결해지고 가독성이 높아짐
// 2. DB 코드를 직접 다루면 복잡해져 유지보수가 어려워지므로 분리함
// 3. 데이터베이스 구조가 변경되어도 DAO 파일만 수정하면 되어 유지보수와 테스트가 편리함
// 4. 각 클래스의 역할이 명확히 구분되어, 오류 발생 시 해당 부분만 수정하면 됨
// 5. 실제 DB 처리는 DAO가 담당하여 구조적으로 안정적임
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


