package it.msc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import it.msc.model.Event;
 
public class EventDao {
	
    public void update(Event event){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE event SET date=?,description=?,engine=?,hour=?,employees=? where id=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getDate());
            pstmt.setString(2, event.getDescription());
            pstmt.setString(3, event.getEngine());
            pstmt.setInt(4, event.getId());
            pstmt.setInt(5, event.getHour());
            pstmt.setString(6, event.getEmployees());
            
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
   
    public void insert(Event event){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO event(date,description,engine,hour,employees) values(?,?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getDate());
            pstmt.setString(2, event.getDescription());
            pstmt.setString(3, event.getEngine());
            pstmt.setInt(4, event.getHour());
            pstmt.setString(5, event.getEmployees());
       
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
        String sql = "DELETE FROM event WHERE id=?";
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
        String sql="SELECT count(*) FROM event";
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
    public List<Event> selectUsers(int page, int perPageRow) {
        int beginRow = perPageRow * page - perPageRow;
        List<Event> list = new ArrayList<Event>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="SELECT id,date,description,engine,hour,employees FROM event ORDER BY id desc LIMIT ?,?";
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
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setDate(rs.getString("date"));
                event.setDescription(rs.getString("description"));
                event.setEngine(rs.getString("engine"));
                event.setHour(rs.getInt("hour"));
                event.setEmployees(rs.getString("employees"));
                list.add(event);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // db관련  커넥션 해제
        }
        return list;
    }
}