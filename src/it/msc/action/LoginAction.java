package it.msc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import it.msc.model.Login;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;


public class LoginAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Login loginData;

	
	private Map<String, Object> session;
	public static final String LOGIN_SUCCESS = "success";
	public static final String LOGIN_FAILED = "error";	
	

	public String home() {
		return SUCCESS;
	}

	// ---------------------------- Log Out register user

	
	public String logOut() {
		session.remove("loginId");
		addActionMessage("You Have Been Successfully Logged Out");
		return SUCCESS;
	}

	// ---------------------------- Login register user
	
	
	public String loginRegisterUser() {   
	  
		
	 	Connection conn = null; 
	 	String username;
	 	String password;
	 	username = loginData.getUsername;
	 	password = loginData.password;  
	 	
  
       try {   
          
          Class.forName("com.mysql.jdbc.Driver");   
          conn = DriverManager.getConnection("jdbc:mysql://localhost/msc", "root", "claudio");   
          String sql = "SELECT * FROM user WHERE username = ? AND password = ?";      
          PreparedStatement ps = conn.prepareStatement(sql);   
          ps.setString(1, username);   
          ps.setString(2, password);   
          ResultSet rs = ps.executeQuery();  
          System.out.println();

         
        while (rs.next()) { 
        	if ( username == null)
        		{
        		addActionError("Please Enter Valid emailId or Password");
    			return LOGIN;
        		 
      		} else {
      			 session.put("loginId", username);
       			return SUCCESS;
    			
      		}
        }
       }
        catch (Exception e) { 
        	        	
       } finally {   
          if (conn != null) {   
             try {   
                conn.close();   
             } catch (Exception e) {   
             }   
          }   
       }   
       addActionError("Please Enter Valid emailId or Password");
       return LOGIN;   
    }   
	 	
    public Login getLoginData(){
    	return this.loginData;
    }

    public void setLoginData(Login loginData){
    	this.loginData = loginData;
    }
	

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

}
