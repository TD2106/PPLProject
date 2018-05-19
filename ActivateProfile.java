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

@WebServlet(name = "ActivateProfile",  urlPatterns = "/activate")
public class ActivateProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int patient_id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        try{
            switch (action){
                case "activate":{
                    PatientDAO.activatePatient(patient_id);
                }
                case "remove":{

                }
            }
            response.sendRedirect("../admin/activatePatient.jsp");
        }catch (Exception e){

        }

    }
}
