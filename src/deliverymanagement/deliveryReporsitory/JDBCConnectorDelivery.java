package deliverymanagement.deliveryReporsitory;

import java.sql.*;

public class JDBCConnectorDelivery {
    private static final String DRIVER_PATH = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private static final String USER_NAME = "C##JYS";
    private static final String PASSWORD = "1234";

    private static Connection con;

    public static Connection getConnection() {

        try {
            // 1. JDBC Driver Memory Loading
            Class.forName(DRIVER_PATH);
            System.out.println("JDBC Driver loaded");
            // 2. Connection
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection Successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection error");
        }
        return con;
    }

    public static void resultSetTest(){


        try {
            // 3. SQL문을 실행할 수 있는 Statement 객체 생성
            String sql = "select * from 배달회원";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString("회원아이디") +" || ");
                System.out.print(rs.getString("비밀번호") +" || ");
                System.out.print(rs.getString("이름") +" || ");
                System.out.println(rs.getString("연락처")+" || ");
                System.out.println(rs.getString("주소")+" || ");
                System.out.println(rs.getString("역할"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
       Connection con = getConnection();
        resultSetTest();
    }
}
