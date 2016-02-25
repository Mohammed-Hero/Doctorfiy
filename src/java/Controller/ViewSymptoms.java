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
public class ViewSymptoms extends HttpServlet {

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

        String sym_list = "";

        for (Integer sym : Guest.CurrentSymptomsID) {
            sym_list += sym + ",";
        }
        sym_list = sym_list.substring(0, sym_list.length() - 1);

        DBconnection DB = new DBconnection();
        DB.Query = "SELECT symptom.nameSymptom\n"
                + "FROM symptom\n"
                + "WHERE symptom.idSymptom IN (" + sym_list + ");";
        DB.Make_Connection(true);
        DB.Execute_Query(1);
        sym_list = "";
        try {
            while (DB.Rs.next()) {
                sym_list += DB.Rs.getString(1) + "/";

            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewSymptoms.class.getName()).log(Level.SEVERE, null, ex);
        }
        DB.Close_Connection_Of(3);
        DB.Close_Connection_Of(2);
        DB.Close_Connection_Of(1);
        System.out.print("Symptoms :"+sym_list);
        response.getWriter().write(sym_list);
    }
}
