package it.msc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.msc.dao.UserDao;
import it.msc.model.User;



 
@WebServlet("/UserEditAction")
public class UserEditAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oper = request.getParameter("oper");
        UserDao dao = new UserDao();
        if(oper.equals("add")){
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String cf = request.getParameter("cf");
                       
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setCf(cf);
            
            dao.insert(user);
        }else if(oper.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String cf = request.getParameter("cf");
           
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setCf(cf);
            
            dao.update(user);
        }else if(oper.equals("del")){
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteById(id);
        }else{
            System.out.println("잘못된 접근 입니다.");
        }
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
 
}