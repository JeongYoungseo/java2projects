package deliverymanagement.deliveryReporsitory;

import deliverymanagement.deliveryDomain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberReporsitory {
    ArrayList<MemberVO> memberVOList;

    public ArrayList<MemberVO> select(String search, int selected) {
        Connection con = JDBCConnectorDelivery.getConnection();
        memberVOList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        // 콤보박스 순서와 정확히 맞춰야 함!!!
        String[] columnName = {"이름", "연락처"};

        String sql = "SELECT 회원아이디, 비밀번호, 이름, 연락처, 주소, 역할 "
                + "FROM 배달회원 "
                + "WHERE " + columnName[selected] + " LIKE ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                MemberVO vo = new MemberVO();
                vo.setMemberId(rs.getString("회원아이디"));
                vo.setMemberPassword(rs.getString("비밀번호"));
                vo.setMemberName(rs.getString("이름"));
                vo.setMemberPhone(rs.getString("연락처"));
                vo.setMemberAddress(rs.getString("주소"));
                vo.setMemberRole(rs.getString("역할"));
                memberVOList.add(vo);
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
        return memberVOList;
    }

    public ArrayList<String> selectOwnerId(){
        ArrayList<String> ownerList = new ArrayList<>();
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "select 회원아이디 from 배달회원 where 역할 = '점주'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ownerList.add(rs.getString("회원아이디"));
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
        return ownerList;
    }
    public void insert(MemberVO memberVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "insert into 배달회원 values(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, memberVO.getMemberId());
            ps.setString(2, memberVO.getMemberPassword());
            ps.setString(3, memberVO.getMemberName());
            ps.setString(4, memberVO.getMemberPhone());
            ps.setString(5, memberVO.getMemberAddress());
            ps.setString(6, memberVO.getMemberRole());
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

    public void update(MemberVO memberVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "update 배달회원 set 비밀번호=?, 이름=?, 연락처=?, 주소=?, 역할=? where 회원아이디=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, memberVO.getMemberPassword());
            ps.setString(2, memberVO.getMemberName());
            ps.setString(3, memberVO.getMemberPhone());
            ps.setString(4, memberVO.getMemberAddress());
            ps.setString(5, memberVO.getMemberRole());
            ps.setString(6, memberVO.getMemberId());
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
    public void delete(MemberVO memberVO) {
        Connection con = JDBCConnectorDelivery.getConnection();
        String sql = "delete from 배달회원 where 회원아이디=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, memberVO.getMemberId());
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
