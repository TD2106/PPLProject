<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*, dao.*, model.*, dbconnection.*, email.*, java.util.*"%>
<%
    String keyword = request.getParameter("keyword");
    List<Doctor> doctors = null;
    int specialty_id = 0, specificity_id = 0, hospital_id = 0;
    if(request.getParameter("specialty") != ""){
        specialty_id = Integer.parseInt(request.getParameter("specialty"));
    }
    if(request.getParameter("specific") != ""){
        specificity_id = Integer.parseInt(request.getParameter("specific"));
    }
    if(request.getParameter("hospital") != ""){
        hospital_id = Integer.parseInt(request.getParameter("hospital"));
    }
    if(specialty_id != 0 && specificity_id != 0){
        doctors = DoctorDAO.getAllDoctorByNameAndGeneralSpecialtyAndSpecificSpecialty(keyword.split(" ")[0], keyword.split(" ")[1], specialty_id, specificity_id);
    }else if(specialty_id != 0){
        doctors = DoctorDAO.getAllDoctorByNameAndGeneralSpecialty(keyword.split(" ")[0], keyword.split(" ")[1], specialty_id);
    }else if(hospital_id != 0){
        doctors = DoctorDAO.getAllDoctorFromHospital(hospital_id);
    }else{
        doctors = DoctorDAO.getAllDoctorByName(keyword.split(" ")[0], keyword.split(" ")[1]);
    }
%>

<jsp:include page="../include/header.jsp" flush="true" />
<%
    if(doctors.isEmpty()){
%>
<div class="page-header">
    <h1>Sorry, Your doctor will be updated later</h1>
</div>
<%
}else{
%>
<div class="page-header">
    <h1>Found <%=doctors.size()%> doctors</h1>
</div>
<div class="row text-center" style="display: flex; flex-wrap: wrap;">
    <%
        for(Doctor doctor: doctors){
    %>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <div class="thumbnail show-image">
            <img src="http://res.cloudinary.com/dqagyeboj/image/upload/v1525467616/download_tlaotz.jpg" alt="">

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
<%
    }
%>
<jsp:include page="../include/footer.jsp" flush="true" />
