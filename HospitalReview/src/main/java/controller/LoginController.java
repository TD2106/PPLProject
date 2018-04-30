package controller;

import aes.AES;
import dao.AdminDAO;
import dao.HospitalDAO;
import dao.PatientDAO;
import dao.UserDAO;
import model.Admin;
import model.Hospital;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = AES.encrypt(password, "hospital_review");
        HttpSession session = request.getSession();
        try {
            if (UserDAO.isLoginInformationCorrect(email, password)) {
                int userID = UserDAO.getUserID(email, password);
                String userType = UserDAO.getUserType(userID);
                switch (userType) {
                    case "admin": {
                        Admin user = AdminDAO.getAdmin(userID);
                        session.setAttribute("user", user);
                        break;
                    }
                    case "patient": {
                        Patient user = PatientDAO.getPatient(userID);
                        session.setAttribute("user", user);
                        break;
                    }
                    case "hospital": {
                        Hospital user = HospitalDAO.getHospital(userID);
                        session.setAttribute("user", user);
                        break;
                    }
                }
                response.sendRedirect("user/index.jsp"); // Correct this when code front-end
            } else {
                response.sendRedirect("user/login.jsp?type=Patient&result=invalid"); // Correct this when code front-end
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
