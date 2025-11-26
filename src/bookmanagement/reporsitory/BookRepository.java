package bookmanagement.reporsitory;

import bookmanagement.domain.BookVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 데이터베이스 연동
public class BookRepository {
    ArrayList<BookVO> bookVOList;

    public ArrayList<BookVO> select(String searchWord, int selectedIndex ) {
        Connection con = JDBCConnector.getConnection();
        bookVOList = new ArrayList<BookVO>();
        ResultSet rs = null;
        PreparedStatement psmt = null;
        String[] columnName = {"name", "publish", "author"};
        String sql = "select isbn, name, publish, author, price, category_name from book, category where book.category = category.category_id and " + columnName[selectedIndex] + " like ?";
        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, "%"+searchWord+"%");
            rs = psmt.executeQuery();
            while (rs.next()) {
                BookVO bookVO = new BookVO();
                bookVO.setIsbn(rs.getInt("isbn"));
                bookVO.setName(rs.getString("name"));
                bookVO.setPublish(rs.getString("publish"));
                bookVO.setAuthor(rs.getString("author"));
                bookVO.setPrice(rs.getInt("price"));
                bookVO.setCategoryName(rs.getString("category_name"));
                bookVOList.add(bookVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null)
                    rs.close();

                if(psmt!=null)
                    psmt.close();

                if(con!=null)
                    con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookVOList;
    }

    public void insert(BookVO bookVO) {
        Connection con = JDBCConnector.getConnection();
        String sql = "insert into book values(?,?,?,?,?,?)";
        PreparedStatement psmt = null;

        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, bookVO.getIsbn());
            psmt.setString(2, bookVO.getName());
            psmt.setString(3, bookVO.getPublish());
            psmt.setString(4, bookVO.getAuthor());
            psmt.setInt(5, bookVO.getPrice());

            int categoryID = 0;
            switch(bookVO.getCategoryName()){
                case "IT도서":
                    categoryID = 10;
                    break;
                case "소설":
                    categoryID = 20;
                    break;
                case "비소설":
                    categoryID = 30;
                    break;
                case "경제":
                    categoryID = 40;
                    break;
                case "사회":
                    categoryID = 50;
                    break;
            }
            psmt.setInt(6, categoryID);
            psmt.executeUpdate(); //--> insert때 사용
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if(psmt!=null)
                    psmt.close();

                if(con!=null)
                    con.close();

            } catch (SQLException e) {
                System.out.println("insert close failed");
                e.printStackTrace();
            }
        }
    }
}
