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
public class SymptomsRecommender extends HttpServlet {

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
        DBconnection DB = new DBconnection();
        
        String recommendedSymptoms = "";
        String sym_list_String = "";
        String disease_list_String = "";

        for (Integer sym : Guest.CurrentSymptomsID) {
            sym_list_String += sym + ",";
            

        }
        sym_list_String = sym_list_String.substring(0, sym_list_String.length() - 1);

        for (Integer disease : Guest.RecommendedDiseaseID) {
            disease_list_String += disease + ",";

        }
        disease_list_String = disease_list_String.substring(0, disease_list_String.length() - 1);

        DB.Make_Connection(true);
        DB.Query = "SELECT DISTINCT symptom.nameSymptom,symptom.idSymptom\n"
                + "FROM disease\n"
                + "JOIN disease_has_symptoms\n"
                + "ON  disease_has_symptoms.Disease_idDisease = disease.idDisease\n"
                + "JOIN symptom\n"
                + "ON symptom.idSymptom = disease_has_symptoms.Symptom_idSymptom\n"
                + "WHERE symptom.areaSymptom = '"+Guest.SelectedArea+"'\n"
                + "AND   disease_has_symptoms.Disease_idDisease IN("+disease_list_String+")\n"
                + "AND   disease_has_symptoms.Symptom_idSymptom NOT IN("+sym_list_String+")";
        DB.Execute_Query(1);
        
        try {
            while(DB.Rs.next()){
                recommendedSymptoms+=DB.Rs.getString(1)+"/"+DB.Rs.getInt(2)+"/";
            }
        } catch (SQLException ex) {}
        
        System.out.println(recommendedSymptoms);
        
        response.getWriter().write(recommendedSymptoms);
        
        DB.Close_Connection_Of(3);
        DB.Close_Connection_Of(2);
        DB.Close_Connection_Of(1);

    }

}
