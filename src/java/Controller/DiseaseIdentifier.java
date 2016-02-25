package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import Model.*;
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
public class DiseaseIdentifier extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sym_list[] = request.getParameterValues("sym_list[]");
        String sym_list_String = "";

        GuestDiagnose Guest = (GuestDiagnose) request.getSession().getAttribute("Guest");

        DBconnection DB = new DBconnection();

        for(Integer sym : Guest.CurrentSymptomsID){
            sym_list_String += sym + ",";
        }
       
        for (String sym : sym_list) {
            sym_list_String += sym + ",";
            Guest.CurrentSymptomsID.add(Integer.parseInt(sym));
        }
        sym_list_String = sym_list_String.substring(0, sym_list_String.length() - 1);

        DB.Make_Connection(true);
        DB.Query = "SELECT disease.nameDisease, disease.idDisease\n"
                + "FROM disease\n"
                + "JOIN disease_has_symptoms\n"
                + "ON  disease_has_symptoms.Disease_idDisease = disease.idDisease\n"
                + "JOIN symptom\n"
                + "ON symptom.idSymptom = disease_has_symptoms.Symptom_idSymptom\n"
                + "WHERE symptom.areaSymptom = '"+Guest.SelectedArea+"'\n"
                + "AND   disease_has_symptoms.Symptom_idSymptom IN("+sym_list_String+") \n"
                + "GROUP BY(disease.nameDisease)\n"
                + "HAVING COUNT(disease.nameDisease) = "+Guest.CurrentSymptomsID.size()+";";
        DB.Execute_Query(1);

        Guest.RecommendedDiseaseID.clear();
        Guest.RecommendedDiseaseName.clear();
        try {
            while(DB.Rs.next()){
               Guest.RecommendedDiseaseName.add( DB.Rs.getString(1) );
               Guest.RecommendedDiseaseID.add( DB.Rs.getInt(2) );
            }
        } catch (SQLException ex) {}
                
        DB.Close_Connection_Of(3);
        DB.Close_Connection_Of(2);
        DB.Close_Connection_Of(1);
        
        if(Guest.RecommendedDiseaseName.size() == 1){
            request.getSession(true).setAttribute("Guest", Guest);
            response.getWriter().write(Guest.RecommendedDiseaseName.get(0));
        }
        else if(Guest.RecommendedDiseaseName.size() == 0){
            request.getSession(true).setAttribute("Guest", Guest);
            response.getWriter().write("0");
        }
        else{
            request.getSession(true).setAttribute("Guest", Guest);
            response.getWriter().write("1");
        }
       
        

    }

}
