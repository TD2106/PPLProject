<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*, dao.*, model.*, dbconnection.*, email.*, java.util.*"%>
<%
    String query = (String)request.getParameter("q");
    List<Doctor> doctors = DoctorDAO.getAllDoctor();

    for(Doctor doctor: doctors){
        if (doctor.getFirstName().toUpperCase().startsWith(query.toUpperCase())){
            out.print(doctor.getFirstName() + " " + doctor.getLastName() + "\n");
        }
    }
%>