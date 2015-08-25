package it.msc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import it.msc.model.Electric;
 
public class ElectricDao {
	
    public void update(Electric electric){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE electric SET date=?,generator=?,volt=?,kw=?,ampere=? where id=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, electric.getDate());
            pstmt.setString(2, electric.getGenerator());
            pstmt.setInt(3, electric.getVolt());
            pstmt.setInt(4, electric.getId());
            pstmt.setInt(5, electric.getKw());
            pstmt.setInt(6, electric.getAmpere());
            
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
   
    public void insert(Electric electric){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO electric(date,generator,volt,kw,ampere) values(?,?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/msc","root","claudio");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, electric.getDate());
            pstmt.setString(2, electric.getGenerator());
            pstmt.setInt(3, electric.getVolt());
            pstmt.setInt(4, electric.getKw());
            pstmt.setInt(5, electric.getAmpere());
       
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
        String sql = "DELETE FROM electric WHERE id=?";
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
        String sql="SELECT count(*) FROM electric";
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
   
    
    public List<Electric> selectUsers(int page, int perPageRow) {
        int beginRow = perPageRow * page - perPageRow;
        List<Electric> list = new ArrayList<Electric>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="SELECT id,date,generator,volt,kw,ampere FROM electric ORDER BY id desc LIMIT ?,?";
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
                Electric electric = new Electric();
                electric.setId(rs.getInt("id"));
                electric.setDate(rs.getString("date"));
                electric.setGenerator(rs.getString("generator"));
                electric.setKw(rs.getInt("kw"));
                electric.setVolt(rs.getInt("volt"));
                electric.setAmpere(rs.getInt("ampere"));
                list.add(electric);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            // db관련  커넥션 해제
        }
        return list;
    }
}