<%--
  Created by IntelliJ IDEA.
  User: aleks5323
  Date: 14.05.2020
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="navbar" class="navbar-nav">
    <li class="nav-item">
        <a href="#" onclick="includePage();" class="nav-link">Мой профиль</a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="conversationsNavButton" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Запросы
        </a>
        <div class="dropdown-menu" aria-labelledby="conversationsNavButton">
            <a class="dropdown-item" onclick="showModalWindow('createRequestModal')" href="#">Новый запрос</a>
            <a class="dropdown-item" onclick="includePage('html/conversations.html')" href="#">Отобразить запросы</a>
            <!--                            <div class="dropdown-divider"></div>-->
            <!--                            <a class="dropdown-item" href="#">Something else here</a>-->
        </div>
    </li>
</div>