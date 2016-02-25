package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import Model.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hero
 */
public class DiseaseIdentifier extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String sym_list[] = request.getParameterValues("sym_list[]");
      
        
        
        
         GuestDiagnose Guest = (GuestDiagnose)request.getSession().getAttribute("Guest");
        
        DBconnection DB = new DBconnection();
        
        for (String sym : sym_list) {
            Guest.CurrentSymptomsID.add(Integer.parseInt(sym));
        }
        
//        DB.Make_Connection(true);
//        DB.Query = "";
//        DB.Execute_Query(1);
        
        
       
        
        
        
     request.getSession(true).setAttribute("Guest", Guest);
     
    }


}
