<%--
  Created by IntelliJ IDEA.
  User: aleks5323
  Date: 29.02.2020
  Time: 00:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="css/toastr/toastr.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

    <title>Адаптер СМЭВ</title>
</head>
<body>
<%
    if (session.getAttribute("login") != null && session.getAttribute("pass") != null)
        System.out.println("ja");
    else
        System.out.println("nej");
%>
<div class="bs-example">
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <a href="/" class="navbar-brand">Brand</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <%
                if (session.getAttribute("login") != null && session.getAttribute("pass") != null) {
            %>  <%@include file="html/authNavbar.jsp" %> <%
                }
            %>
            <div id="userSection" class="navbar-nav ml-auto">
                <%
                    if (session.getAttribute("login") == null && session.getAttribute("pass") == null) {
                %>  <%@include file="html/loginButton.jsp" %> <%
                }
                    else {
                %> <%@include file="html/userPanel.jsp"%> <%
                    }
                %>
            </div>
        </div>
    </nav>
</div>

<div id="modalsContainer"></div>
<div id="pageContent"></div>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/lib.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        includeModalWindow("/html/modalWindows/loginForm.html");
        includeModalWindow("/html/modalWindows/conversationInfo.html");
        includeModalWindow("/html/modalWindows/createRequest.html");
    });

</script>
<%--<script type="text/javascript">--%>
<%--    var usernameLine = $.cookie("login");--%>
<%--    if (usernameLine == null) {--%>
<%--        $("#userPanel").hide();--%>
<%--        $("#navbar").hide();--%>
<%--        $("#loginButton").show();--%>
<%--    }--%>
<%--    else {--%>
<%--        $("#userString").text("Hello, " + usernameLine + "!");--%>
<%--        $("#userPanel").show();--%>
<%--        $("#navbar").show();--%>
<%--        $("#loginButton").hide();--%>
<%--    }--%>
<%--</script>--%>

<audio class="notificationSound" style="display:none;">
    <source src="" />
    <embed src="" hidden="true" loop="false"/>
</audio>
</body>
</html>
