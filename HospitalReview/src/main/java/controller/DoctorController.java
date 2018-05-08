package controller;

import dao.DoctorDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DoctorController", urlPatterns = "/doctor_controller")
public class DoctorController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/not_allow"); // sua khi lam front end
            return;
        }
        User user = (User) session.getAttribute("user");
        if (!user.getUserType().equals("hospital")) {
            response.sendRedirect("/not_allow"); // sua khi lam front end
            return;
        }
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String degree = request.getParameter("degree");
                int acceptInsurance = Integer.parseInt(request.getParameter("accept_insurance"));
                String officeHour = request.getParameter("office_hour");
                int hospitalID = user.getUserID();
                int generalSpecialtyID = Integer.parseInt(request.getParameter("general_specialty_id"));
                try {
                    DoctorDAO.addDoctor(firstName, lastName, degree, acceptInsurance, officeHour, hospitalID, generalSpecialtyID);
                    response.sendRedirect("/success"); // change when front-end coding
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
            case "update": {
                int doctorID = Integer.parseInt(request.getParameter("doctor_id"));
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String degree = request.getParameter("degree");
                int acceptInsurance = Integer.parseInt(request.getParameter("accept_insurance"));
                String officeHour = request.getParameter("office_hour");
                int hospitalID = user.getUserID();
                int generalSpecialtyID = Integer.parseInt(request.getParameter("general_specialty_id"));
                try {
                    DoctorDAO.updateDoctor(doctorID, firstName, lastName, degree, acceptInsurance, officeHour, hospitalID, generalSpecialtyID);
                    response.sendRedirect("/success"); // change when front-end coding
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
            case "delete": {
                int doctorID = Integer.parseInt(request.getParameter("doctor_id"));
                try {
                    DoctorDAO.deleteDoctor(doctorID);
                    response.sendRedirect("/success"); // change when front-end coding
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
        }
    }
}
