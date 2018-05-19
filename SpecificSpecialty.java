package controller;

import dao.GeneralSpecialtyDAO;
import dao.SpecificSpecialtyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SpecificSpecialty", urlPatterns = "/specific")
public class SpecificSpecialty extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try{
            switch (action){
                case "add":{
                    String name = request.getParameter("name");
                    int genid = Integer.parseInt(request.getParameter("genid"));
                    SpecificSpecialtyDAO.addSpecificSpecialty(name, genid);
                    response.sendRedirect("../admin/editSpecialtyCategory.jsp");
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
                    SpecificSpecialtyDAO.deleteSpecificSpecialty(id);
                    response.sendRedirect("../admin/editSpecialtyCategory.jsp");
                }
            }

        }catch (Exception e){

        }
    }
}
