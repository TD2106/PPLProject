package controller;

import aes.AES;
import dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditProfileController", urlPatterns = "/edit_profile")
public class EditProfileController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String password = request.getParameter("password");
        password = AES.encrypt(password, "hospital_review");
        switch (action) {
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String email = request.getParameter("email");
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String gender = request.getParameter("gender");
                String address = request.getParameter("address");
                try {
                    PatientDAO.updatePatientProfile(id, email, password, firstName, lastName, gender, address);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("/user/profile.jsp"); // sua lai khi code front end
                return;
            }
        }
    }
}
