<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../include/header.jsp" flush="true" />
<center>
    <h3>Please log in</h3>
</center>
<img style="margin-left: 35%; width: 30%;" src="https://myslu.slu.edu/res/images/cas-padlock-icon.png" class="img-rounded">
<jsp:include page="../include/footer.jsp" flush="true" />
<script>
    setTimeout(function() {
        window.location.href = "/user/login.jsp?type=patient&result=";
    }, 3000);
</script>