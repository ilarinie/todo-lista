<%-- 
    Document   : kayttaja
    Created on : Apr 25, 2016, 8:48:46 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Käyttäjälista">
        <h1>${kayttaja.nimi}</h1>
        
        <h2>Käyttäjän todot:</h2>
        <table class="table table-hover">
        <thead>
            <tr>
                <th>Tehtävä</th>
                <th>Prioriteetti</th>
                <th>Suorita</th>
                <th>Poista</th>
            </tr>

        </thead>
        <tbody>
            <c:forEach items="${tehtavat}" var="tehtava">
                <tr>
                    <td><a href="tehtava?id=${tehtava.id}">${tehtava.otsikko}</a></td>
                    <td>${tehtava.prioriteetti}</td>
                    <c:choose>
                        <c:when test="${tehtava.suoritettu}">
                            <td>Suoritettu!</td> 
                        </c:when>    
                        <c:otherwise>
                    <form action="SuoritaTehtava" method="post">
                        <input type="hidden" name="id" value="${tehtava.id}">
                        <td><button type="submit" class="btn btn-primary">suorita</button></td>
                    </form>
                </c:otherwise>
            </c:choose>
            <form action="DestroyTehtava" method="post">
                <input type="hidden" name="id" value="${tehtava.id}">
                <td><button type="submit" class="btn btn-danger">poista</button></td>
            </form>
        </tr>
    </c:forEach>
</tbody>
</table>
</t:pohja>