/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DBconnection;
import Model.GuestDiagnose;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hero
 */
public class IntitalSymptoms extends HttpServlet {

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
        
        // passed body part
        String bodypart = request.getParameter("bodypart");
        // to be passed to view
        String Result = "";
        
        GuestDiagnose newGuest = new GuestDiagnose();
        newGuest.SelectedArea = bodypart;
        request.getSession(true).setAttribute("Guest", newGuest);

        DBconnection DB = new DBconnection();
        DB.Make_Connection(true);
        DB.Query = "SELECT  COUNT(disease_has_symptoms.Symptom_idSymptom) AS SymOccurance ,\n"
                + "              symptom.nameSymptom AS SymptomName, symptom.idSymptom \n"
                + "              \n"
                + "FROM symptom \n"
                + "JOIN disease_has_symptoms \n"
                + "ON   symptom.idSymptom = disease_has_symptoms.Symptom_idSymptom\n"
                + "WHERE   symptom.areaSymptom = '"+bodypart+"'\n"
                + "GROUP BY(symptom.nameSymptom)\n"
                + "ORDER BY SymOccurance DESC;";
        DB.Execute_Query(1);
        
        try {
            while(DB.Rs.next()){
                Result+=DB.Rs.getString(2)+"/"+DB.Rs.getInt(3)+"/";
               }
            
        } catch (SQLException ex) {
            Logger.getLogger(IntitalSymptoms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.getWriter().write(Result);
        
        DB.Close_Connection_Of(3);
        DB.Close_Connection_Of(2);
        DB.Close_Connection_Of(1);

    }

}
