<%--
  Created by IntelliJ IDEA.
  User: aleks5323
  Date: 14.05.2020
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userPanel">
    <h7 id="userString">Приветствую <% out.println(session.getAttribute("login")); %>!</h7>
    <a href="scripts/logout.jsp" class="ion-log-out"></a>
</div>