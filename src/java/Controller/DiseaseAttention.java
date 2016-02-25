/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hero
 */
public class DiseaseAttention extends HttpServlet {

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

        
        GuestDiagnose Guest = (GuestDiagnose) request.getSession().getAttribute("Guest");
        
        
        
        String disease_name = Guest.RecommendedDiseaseName.get(0);
        String disease_severity = "none";

        DBconnection DB = new DBconnection();
        DB.Make_Connection(true);
        DB.Query = "SELECT disease.severtyDisease\n"
                + "FROM disease\n"
                + "WHERE disease.nameDisease = '"+disease_name+"';";
        DB.Execute_Query(1);
        try {
            while(DB.Rs.next())
            disease_severity = DB.Rs.getString(1);
            System.out.println(disease_severity);
        } catch (SQLException ex) {
        }
        
        switch (disease_severity) {
            case "LOW":
                disease_severity = "Medical attention is unnecessary !";
                break;
            case "HIGH":
                disease_severity = "Medical attention is necessary !\nPlease call 911!";
                break;
            case "MED":
                disease_severity = "Medical attention is required !\n";
                break;
        }
        DB.Close_Connection_Of(3);
        DB.Close_Connection_Of(2);
        DB.Close_Connection_Of(1);
        response.getWriter().write(disease_severity);
    }

}
