package controller;

import dao.DoctorDAO;
import dao.GeneralSpecialtyDAO;
import dao.HospitalDAO;
import dao.PatientDAO;
import model.Hospital;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HospitalController", urlPatterns = "/hospital")
public class HospitalController extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int hospital_id = Integer.parseInt(request.getParameter("id"));
        Patient patient = (Patient) session.getAttribute("patient");
        String action = request.getParameter("action");

        try{
            switch (action){
                case "add":{
                    String fname = request.getParameter("First name");
                    String lname = request.getParameter("Last name");
                    String degree = request.getParameter("degree");
                    String hour = request.getParameter("office hour");
                    int insurance = Integer.parseInt(request.getParameter("insurance"));
                    int general = Integer.parseInt(request.getParameter("general"));
                    DoctorDAO.addDoctor(fname,lname,degree,insurance,hour,hospital_id,general);
                }
                case "remove":{
                    PatientDAO.removeFavoriteHospital(patient.getPatientID(), hospital_id);
                }
                case "edit":{
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String hosName = request.getParameter("hospital_name");
                    String address = request.getParameter("address");
                    String website = request.getParameter("website");
                    String adname = request.getParameter("hospital_admin_name");
                    String adEmail = request.getParameter("hospital_admin_email");
                    HospitalDAO.updateHospital(hospital_id, email, password, hosName, address, website, adname, adEmail );
                }
            }
            response.sendRedirect("/hospital/profile.jsp?id="+hospital_id);
        }catch (Exception e){

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
