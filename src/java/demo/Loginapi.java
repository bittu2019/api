
package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Loginapi extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       String email=request.getParameter("key_myemail");
       String password=request.getParameter("key_mypwd");
       String name1="",gender1="",city1="";
        JSONObject jsono=new JSONObject();
        JSONArray array=new JSONArray();
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db", "root", "root");
            PreparedStatement ps = con.prepareStatement("select * from register where email=? and password=?");
             ps.setString(1, email);
             ps.setString(2, password);
             ResultSet rs=ps.executeQuery();
             
             while(rs.next())
             {
                 JSONObject record=new JSONObject();
                   name1=rs.getString("name");
                   record.put("db_name", name1);
                   
                   gender1=rs.getString("gender");
                   record.put("db_gender", gender1);
                   
                   city1=rs.getString("city");
                   record.put("db_city", city1);
                   
                   array.add(record);
             }
             jsono.put("my_data", array);
             response.getWriter().write(jsono.toString());
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
