package it.msc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.msc.dao.ElectricDao;
import it.msc.model.Electric;



 
@WebServlet("/ElectricEditAction")
public class ElectricEditAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oper = request.getParameter("oper");
        ElectricDao dao = new ElectricDao();
        if(oper.equals("add")){
            String date = request.getParameter("date");
            String generator = request.getParameter("generator");
            int volt = Integer.parseInt(request.getParameter("volt"));
            int kw = Integer.parseInt(request.getParameter("kw"));
            int ampere = Integer.parseInt(request.getParameter("ampere"));
                       
            Electric electric = new Electric();
            electric.setDate(date);
            electric.setGenerator(generator);
            electric.setVolt(volt);
            electric.setKw(kw);
            electric.setAmpere(ampere);
            
            dao.insert(electric);
        }else if(oper.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            String date = request.getParameter("date");
            String generator = request.getParameter("generator");
            int volt = Integer.parseInt(request.getParameter("volt"));
            int kw = Integer.parseInt(request.getParameter("kw"));
            int ampere = Integer.parseInt(request.getParameter("ampere"));
                       
            Electric electric = new Electric();
            electric.setId(id);
            electric.setDate(date);
            electric.setGenerator(generator);
            electric.setVolt(volt);
            electric.setKw(kw);
            electric.setAmpere(ampere);
            
            dao.update(electric);
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