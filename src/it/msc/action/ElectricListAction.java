package it.msc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
import it.msc.dao.ElectricDao;
import it.msc.model.Electric;
import it.msc.model.ElectricJson;
 
@WebServlet("/ElectricListAction")
public class ElectricListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
       
        ElectricDao dao = new ElectricDao();
        List<Electric> list = dao.selectUsers(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
       
        ElectricJson userJson = new ElectricJson();
        userJson.setTotal(total);
        userJson.setRecords(records);
        userJson.setPage(page);
        userJson.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(userJson);
       
        response.setContentType("json");
        response.setCharacterEncoding("utf-8");
       
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
 
}