package controller;

import dao.PatientDAO;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PatientController", urlPatterns = "/favHospital")
public class PatientController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int hospital_id = Integer.parseInt(request.getParameter("id"));
        Patient patient = (Patient) session.getAttribute("patient");
        String action = request.getParameter("action");

        try{
            switch (action){
                case "add":{
                    PatientDAO.addFavoriteHospital(patient.getPatientID(), hospital_id);
                }
                case "remove":{
                    PatientDAO.removeFavoriteHospital(patient.getPatientID(), hospital_id);
                }
            }
            response.sendRedirect("/hospital/profile.jsp?id="+hospital_id);
        }catch (Exception e){

        }

    }
}
