<%--
  Created by IntelliJ IDEA.
  User: aleks5323
  Date: 14.05.2020
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("/");
%>