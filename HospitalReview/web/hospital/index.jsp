<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*, dao.*, model.*, dbconnection.*, email.*, java.util.*"%>
<%@ page import="javax.print.Doc" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Patient patient = (Patient) session.getAttribute("patient");
    Hospital hospital = (Hospital) session.getAttribute("hospital");

    int hospital_id = Integer.parseInt(request.getParameter("id"));
    Hospital foundHospital = HospitalDAO.getHospital(hospital_id);
%>
<jsp:include page="../include/header.jsp" flush="true" />
<div class="row">
    <div class="col-md-3 col-sm-4 col-xs-0">
        <p style="text-align: center" class="lead"><%=foundHospital.getHospitalName()%> Hospital</p>
        <img style="padding-left: 2em" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ39iVq1-lbylXKvt9XfgHtuXU7t-I60bOOVRhji9eGU5Qi0lh">
        <ul class="list-group">
            <li class="list-group-item">Address: <%=foundHospital.getAddress()%></li>
            <li class="list-group-item">Website: <%=foundHospital.getWebsite()%></li>
            <li class="list-group-item">Hospital Administrator: <%=foundHospital.getAdminName()%></li>
            <li class="list-group-item">Hospital Email: <%=foundHospital.getAdminEmail()%></li>
        </ul>
<%
    if(patient != null){
        if(PatientDAO.isInFavoriteHospital(patient.getPatientID(), hospital_id)){
%>
        <a href="../favHospital?action=remove&id=<%=hospital_id%>" class="btn list-group-item" >Remove to favorite</a>
<%
        }else{
%>
        <a href="../favHospital?action=add&id=<%=hospital_id%>" class="btn list-group-item" >Add to favorite</a>
<%
        }
    }
%>
    </div>
    <div class="col-md-9 col-sm-8 col-xs-12">
        <div class="text-center" style="display: flex; flex-wrap: wrap;">
<%
    List<Doctor> doctors = DoctorDAO.getAllDoctorFromHospital(hospital_id);
    for(Doctor doctor: doctors){
%>
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="thumbnail show-image">
                    <img src="http://res.cloudinary.com/dqagyeboj/image/upload/v1525467616/download_tlaotz.jpg">
                    <div class="caption">
                        <h3>Dr. <%=doctor.getFirstName() + " " + doctor.getLastName()%></h3>
                    </div>
                    <p>
                        <a href="../doctor/index.jsp?id=<%=doctor.getDoctorID()%>" class="btn btn-default btn-block detailButton">Details</a>
                    </p>
                </div>
            </div>
<%
    }
%>

        </div>
    </div>
</div>