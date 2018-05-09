<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*, dao.*, model.*, dbconnection.*, email.*, java.util.*"%>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Patient patient = (Patient) session.getAttribute("patient");
    Hospital hospital = (Hospital) session.getAttribute("hospital");

    int doctor_id = Integer.parseInt(request.getParameter("id"));
    Doctor foundDoctor = DoctorDAO.getDoctor(doctor_id);
%>
<jsp:include page="../include/header.jsp" flush="true" />
<div class="row">
    <div class="col-md-3 col-sm-4 col-xs-0">
        <p style="text-align: center" class="lead">Dr. <%=foundDoctor.getFirstName() + " " + foundDoctor.getLastName()%></p>
        <img style="padding-left: 2em" src="http://res.cloudinary.com/dqagyeboj/image/upload/v1525467616/download_tlaotz.jpg">
        <ul class="list-group">
            <li class="list-group-item">Degree: <%=foundDoctor.getDegree()%></li>
            <li class="list-group-item">Work at: <%=HospitalDAO.getHospital(foundDoctor.getHospitalID()).getHospitalName()%> Hospital</li>
            <li class="list-group-item">Specialty: <%=GeneralSpecialtyDAO.getGeneralSpecialty(foundDoctor.getGeneralSpecialtyID()).getGeneralSpecialty()%></li>
            <li class="list-group-item">Insurance Accepted: <%=foundDoctor.isAcceptInsurance() == 1 ? "Yes": "No"%></li>
            <li class="list-group-item">Office hours: <%=foundDoctor.getOfficeHour()%></li>
            <li class="list-group-item">Average Rating: <%=foundDoctor.getAverageRating()%></li>
        </ul>
        <ul style="text-align: center;" class="list-group">
            <li class="list-group-item">Language</li>
<%
    for(String doctorLanguage: DoctorDAO.getAllDoctorLanguages(doctor_id)){
%>
            <li class="list-group-item"><%=doctorLanguage.substring(0, 1).toUpperCase() + doctorLanguage.substring(1)%></li>
<%
    }
%>
        </ul>
<%
    if(patient != null){
        if(!PatientDAO.isInFavoriteDoctor(patient.getPatientID(), doctor_id)){
%>
        <form action="../rating?action=add&doctorid=<%=doctor_id%>" method="post">
            <button type="submit" class="btn btn-primary btn-block">Add to favorite</button>
        </form>


<%
        }else{
%>
        <form action="../rating?action=remove&doctorid=<%=doctor_id%>" method="post">
            <button type="submit" class="btn btn-primary btn-block">Remove to favorite</button>
        </form>
<%
        }
%>
        <ul class="list-group">
            <li style="text-align: center;" class="list-group-item">Comment</li>
            <li class="list-group-item">
                <form action="../rating?action=comment&doctorid=<%=request.getParameter("id")%>" method="post">
                    <textarea name="comment" class="form-control custom-control" rows="3" style="resize:none"></textarea>
                    <br>
                    <button type="submit" class="btn btn-primary btn-block">Submit</button>
                </form>
            </li>
        </ul>
        <ul class="list-group">
            <li style="text-align: center;" class="list-group-item">Rating</li>
            <li class="list-group-item">
                <form action="../rating?action=update&doctorid=<%=request.getParameter("id")%>" method="post">
                    <select name="rating" class="form-control">
                        <option value="1">So bad</option>
                        <option value="2">Bad</option>
                        <option value="3">Quite Good</option>
                        <option value="4">Good</option>
                        <option value="5">Excellent</option>
                    </select>
                    <br>
                    <button type="submit" class="btn btn-primary btn-block">Submit</button>
                </form>
            </li>
        </ul>
<%
    }
%>

    </div>
    <div class="col-md-9 col-sm-8 col-xs-12">
        <div class="media">
<%
    for(Comment comment: CommentDAO.getAllCommentOfADoctor(doctor_id)){
%>

            <div class="media-left">
                <img style="width: 64px; height: 64px;" src="https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100" alt="">
            </div>
            <div class="media-body">
                <h4 class="media-heading"><%=PatientDAO.getPatient(comment.getPatientID()).getFirstName() + " " + PatientDAO.getPatient(comment.getPatientID()).getLastName()%></h4>
                <p><%=comment.getContent()%></p>
            </div>
            <br>

<%
    }
%>
        </div>
    </div>
</div>


<jsp:include page="../include/footer.jsp" flush="true" />