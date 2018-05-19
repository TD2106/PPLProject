package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.DoctorDAO;
import dao.PatientDAO;
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
        if (!user.getUserType().equals("hospital") && !user.getUserType().equals("admin")) {
            response.sendRedirect("/not_allow"); // sua khi lam front end
            return;
        }
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                String firstName = request.getParameter("First name");
                String lastName = request.getParameter("Last name");
                String degree = request.getParameter("degree");
                int acceptInsurance = Integer.parseInt(request.getParameter("insurance"));
                String officeHour = request.getParameter("hour");
                int hospitalID = user.getUserID();
                int generalSpecialtyID = Integer.parseInt(request.getParameter("general"));
                try {
                    DoctorDAO.addDoctor(firstName, lastName, degree, acceptInsurance, officeHour, hospitalID, generalSpecialtyID);
                    response.sendRedirect("../hospital/profile.jsp?id=" + Integer.toString(hospitalID));
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
            case "edit": {
                int doctorID = Integer.parseInt(request.getParameter("id"));
                String firstName = request.getParameter("First name");
                String lastName = request.getParameter("Last name");
                String degree = request.getParameter("degree");
                int acceptInsurance = Integer.parseInt(request.getParameter("insurance"));
                String officeHour = request.getParameter("hour");
                int hospitalID = user.getUserID();
                int generalSpecialtyID = Integer.parseInt(request.getParameter("general"));
                try {
                    DoctorDAO.updateDoctor(doctorID, firstName, lastName, degree, acceptInsurance, officeHour, hospitalID, generalSpecialtyID);
                    response.sendRedirect("../doctor/profile.jsp?id=" + Integer.toString(doctorID));
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
            case "delete": {
                int hospitalID = user.getUserID();
                int doctorID = Integer.parseInt(request.getParameter("id"));
                try {
                    DoctorDAO.deleteDoctor(doctorID);
                    if (user.getUserType().equals("admin")) {
                        response.sendRedirect("../user/profile.jsp");
                    } else {
                        response.sendRedirect("../hospital/profile.jsp?id=" + Integer.toString(hospitalID));
                    }
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
            case "addlan": {
                int doctorID = Integer.parseInt(request.getParameter("id"));
                String lan = request.getParameter("lan");
                try {
                    DoctorDAO.addDoctorLanguage(doctorID, lan);
                    response.sendRedirect("../doctor/profile.jsp?id=" + Integer.toString(doctorID));
                } catch (SQLException e) {
                    response.sendRedirect("/failed");
                    e.printStackTrace();
                } finally {
                    return;
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/not_allow"); // sua khi lam front end
            return;
        }
        User user = (User) session.getAttribute("user");
        if (!user.getUserType().equals("hospital") && !user.getUserType().equals("admin")) {
            response.sendRedirect("/not_allow"); // sua khi lam front end
            return;
        }
        switch (action) {
            case "deletelan": {
                int doctorID = Integer.parseInt(request.getParameter("id"));
                String lan = request.getParameter("lan");
                try {
                    DoctorDAO.deleteDoctorLanguage(doctorID, lan);
                    response.sendRedirect("../doctor/profile.jsp?id=" + Integer.toString(doctorID));
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