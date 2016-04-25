<%-- 
    Document   : kayttaja
    Created on : Apr 11, 2016, 9:46:14 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Käyttäjälista">

     <h2>Käyttäjien lista</h2>
     <ul>
    <c:forEach items="${lista}" var="kayttaja">
        <li><a href="kayttaja?id=${kayttaja.id}">${kayttaja.nimi}</a></li>
    </c:forEach>
     </ul>
    
</t:pohja>

