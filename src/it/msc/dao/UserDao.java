package it.msc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import it.msc.model.User;
 
public class UserDao {
	
    public void update(User user){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE user SET name=?,email=?,username=?,password=?,cf=? where id=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUsername());
            pstmt.setInt(5, user.getId());
            pstmt.setString(6, user.getPassword());
            pstmt.setString(4, user.getCf());
            
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
   
    public void insert(User user){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO user(name,email,username,password,cf) values(?,?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getCf());
       
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if(e instanceof ClassNotFoundException){
                System.out.println("드라이버 로딩 실패!");
            }else if (e instanceof SQLException){
                System.out.println("sql 에러!");
            }
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
   
   
    public void deleteById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM user WHERE id=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            if(e instanceof ClassNotFoundException){
                System.out.println("드라이버 로딩 실패!");
            }else if (e instanceof SQLException){
                System.out.println("sql 에러!");
            }
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
   
   
    // 전체 행의 수를 리턴하는 메서드
    public int getCountRow() {
       
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="SELECT count(*) FROM user";
        try{
            // DB 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");
            // DB 접속
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            // 쿼리 명령어 설정, 보내기, 결과물 받기
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 결과물 편집, 리턴
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // db관련  커넥션 해제
        }
        return 0;
    }
   
    // 전체 행의 데이터 리스트를 리턴하는 메서드
    public List<User> selectUsers(int page, int perPageRow) {
        int beginRow = perPageRow * page - perPageRow;
        List<User> list = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="SELECT id,name,email,username,password,cf FROM user ORDER BY id desc LIMIT ?,?";
        // DB 드라이버 로딩
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // DB 접속
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            // 쿼리 명령어 설정, 보내기, 결과물 받기
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, beginRow);
            pstmt.setInt(2, perPageRow);
            rs = pstmt.executeQuery();
            // 결과물 편집, 리턴
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCf(rs.getString("cf"));
                list.add(user);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // db관련  커넥션 해제
        }
        return list;
    }
}