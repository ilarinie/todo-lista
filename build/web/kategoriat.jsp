<%-- 
    Document   : kategoriat
    Created on : May 7, 2016, 2:14:27 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Kategoriat">
    <h1>Kategoriat</h1>
    <ul>
        <c:forEach items="${kategoriat}" var="kategoria">

            <li><a href="kategoria?id=${kategoria.id}">${kategoria.otsikko}</a> - <a href="muokkaakategoria?id=${kategoria.id}" class="btn btn-warning">Muokkaa</a></li>
            </c:forEach>

    </ul>




</t:pohja>
