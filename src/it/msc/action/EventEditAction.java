package it.msc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.msc.dao.EventDao;
import it.msc.model.Event;



 
@WebServlet("/EventEditAction")
public class EventEditAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oper = request.getParameter("oper");
        EventDao dao = new EventDao();
        if(oper.equals("add")){
            String date = request.getParameter("date");
            String description = request.getParameter("description");
            String engine = request.getParameter("engine");
            int hour = Integer.parseInt(request.getParameter("hour"));
            String employees = request.getParameter("employees");
                       
            Event event = new Event();
            event.setDate(date);
            event.setDescription(description);
            event.setEngine(engine);
            event.setHour(hour);
            event.setEmployees(employees);
            
            dao.insert(event);
        }else if(oper.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            String date = request.getParameter("date");
            String description = request.getParameter("description");
            String engine = request.getParameter("engine");
            int hour = Integer.parseInt(request.getParameter("hour"));
            String employees = request.getParameter("employees");
                       
            Event event = new Event();
            event.setId(id);
            event.setDate(date);
            event.setDescription(description);
            event.setEngine(engine);
            event.setHour(hour);
            event.setEmployees(employees);
            
            dao.update(event);
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