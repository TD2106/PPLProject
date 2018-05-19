package controller;

import dao.GeneralSpecialtyDAO;
import dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GeneralSpecialty", urlPatterns = "/general")
public class GeneralSpecialty extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try{
            switch (action){
                case "add":{
                    String name = request.getParameter("name");
                    GeneralSpecialtyDAO.addGeneralSpecialty(name);
                    response.sendRedirect("../admin/editGeneralSpecialty.jsp");
                }
            }

        }catch (Exception e){

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try{
            switch (action){
                case "delete":{
                    int id = Integer.parseInt(request.getParameter("id"));
                    GeneralSpecialtyDAO.deleteGeneralSpecialty(id);
                    response.sendRedirect("../admin/editGeneralSpecialty.jsp");
                }
            }

        }catch (Exception e){

        }
    }
}
