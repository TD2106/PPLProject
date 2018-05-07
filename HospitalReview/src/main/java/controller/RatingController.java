package controller;

import dao.CommentDAO;
import dao.PatientDAO;
import dao.RatingDAO;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RatingController", urlPatterns = "/rating")
public class RatingController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int doctor_id = Integer.parseInt(request.getParameter("doctorid"));
        Patient patient = (Patient) session.getAttribute("patient");
        String action = request.getParameter("action");

        try{
            switch (action){
                case "rate":{

                }
                case "update":{
                    RatingDAO.updateRating(patient.getPatientID(), doctor_id, Integer.parseInt(request.getParameter("rating")));
                    break;
                }
                case "comment":{
                    CommentDAO.addComment(patient.getPatientID(), doctor_id, request.getParameter("comment"));
                }
                case "add":{
                    PatientDAO.addFavoriteDoctor(patient.getPatientID(), doctor_id);
                }
                case "remove":{
                    PatientDAO.removeFavoriteDoctor(patient.getPatientID(), doctor_id);
                }

            }
            response.sendRedirect("/doctor/index.jsp?id="+doctor_id);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
