package controller;

import aes.AES;
import dao.AdminDAO;
import dao.HospitalDAO;
import dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = AES.encrypt(password, "hospital_review");
        switch (action) {
            case "add_patient": {
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String gender = request.getParameter("gender");
                String address = request.getParameter("address");
                System.out.println(action + ' ' + email + ' ' + password + ' ' + firstName + ' ' + lastName + ' ' + gender + ' ' + address);
                try {
                    PatientDAO.addPatient(email, password, firstName, lastName, gender, address);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("/user"); // sua lai khi code front end
                return;
            }
            case "add_hospital": {
                String hospitalName = request.getParameter("hospital_name");
                String address = request.getParameter("address");
                String website = request.getParameter("website");
                String hospitalAdminName = request.getParameter("hospital_admin_name");
                String hospitalAdminEmail = request.getParameter("hospital_admin_email");
                try {
                    HospitalDAO.addHospital(email, password, hospitalName, address, website, hospitalAdminName, hospitalAdminEmail);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("/user"); // sua lai khi code front end
                return;
            }
            case "add_admin": {
                try {
                    AdminDAO.addAdmin(email, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("resultAddAdmin", "success");
                request.getRequestDispatcher("admin_index.jsp").forward(request, response);
                return;
            }
        }
    }
}
