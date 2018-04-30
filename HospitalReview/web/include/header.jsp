<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*, dao.*, model.*, dbconnection.*, email.*, java.util.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hospital Review Website</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script id="jqVer" type="text/javascript" src="https://code.jquery.com/jquery-1.8.0.min.js"></script>
    <script id="btVer" type="text/javascript" src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/js/bootstrap.js'></script>

    <script type="text/javascript" src="../javascript/jquery.autocomplete.js"></script>

    <script language="javascript" type="text/javascript">
        jQuery(function () {
            $("#search").autocomplete("../user/list.jsp");
        });
    </script>

    <script language="javascript" type="text/javascript">
        jQuery(function () {
            $("#advSearch").autocomplete("list.jsp");
        });
    </script>

    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../javascript/script.js"></script>
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-nav-demo" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="../user/index.jsp"><span class="glyphicon glyphicon-film" aria-hidden="true"></span>HR</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-nav-demo">
                <form action="../user/search.jsp" method="POST" class="navbar-form navbar-left">
                    <div class="form-group">
                        <input name="keyword" type="text" class="form-control" id="search" placeholder="Search Movie">


                    </div>
                    <button type="submit" id="searchButton" class="btn btn-default">Search</button>
                </form>
                <div class="nav navbar-nav navbar-right">
<%
                        User user = (User)session.getAttribute("user");
%>
                    <li id="adSearch"><a href="../user/advanceSearch.jsp">Advance Search</a></li>
<%
                        if(user == null){
%>
                    <ul class="nav navbar-nav">
                        <li id="login" class="dropdown">
                            <a id="login_type" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li id="login_admin"><a href="../user/login.jsp?type=admin&result=">For Admin</a></li>
                                <li id="login_patient"><a href="../user/login.jsp?type=patient&result=">For Patient</a></li>
                                <li id="login_hospital"><a href="../user/login.jsp?type=hospital&result=">For Hospital</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav">
                        <li id="register" class="dropdown">
                            <a id="regType" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Register<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li id="reg_admin"><a href="../user/register.jsp?action=add_admin">For Admin</a></li>
                                <li id="reg_patient"><a href="../user/register.jsp?action=add_patient">For Patient</a></li>
                                <li id="reg_hospital"><a href="../user/register.jsp?action=add_hospital">For Hospital</a></li>
                            </ul>
                        </li>
                    </ul>
<%
                    }else{
%>
                        <li id="profile"><a href="../member/member.jsp?id=<%=user.getUserID()%>" title="View your profile">Hello <%=user.getUserID()%></a></li>
                        <li><a href="../MemberController?action=logout">Logout</a></li>
<%
                    }
%>
                </div>
            </div>
        </div>

    </nav>
    <div class="container-fluid">