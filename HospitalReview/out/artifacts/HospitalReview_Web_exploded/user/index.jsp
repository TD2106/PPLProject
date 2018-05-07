<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*, dao.*, model.*, dbconnection.*, email.*, java.util.*"%>
<jsp:include page="../include/header.jsp" flush="true" />
<div class="row text-center" style="display: flex; flex-wrap: wrap;">
<%
    List<Doctor> doctors = DoctorDAO.getAllDoctor();

    for(Doctor doctor: doctors){
%>
        <div class="col-md-3 col-sm-6 col-xs-12">
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
<jsp:include page="../include/footer.jsp" flush="true" />