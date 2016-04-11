<%-- 
    Document   : kayttaja
    Created on : Apr 11, 2016, 9:46:14 PM
    Author     : ile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Käyttäjälista">

     <h2>Käyttäjien lista</h2>
    
    <c:forEach items="${lista}" var="kayttaja">
        <c:out value="${kayttaja.nimi}" />
    </c:forEach>
    
    
</t:pohja>

