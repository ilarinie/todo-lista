<%-- 
    Document   : index
    Created on : Mar 20, 2016, 12:37:22 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Todo-lista">

    <h2>Todo-lista</h2>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Tehtävä</th>
                <th>Käyttäjä</th>
                <th>Prioriteetti</th>
                <th>Suorita</th>
                <th>Poista</th>
            </tr>

        </thead>
        <tbody>
            <c:forEach items="${tehtavalista}" var="tehtava">
                <tr>
                    <td><a href="tehtava?id=${tehtava.id}">${tehtava.otsikko}</a></td>
                    <td><a href="kayttaja?id=${tehtava.kayttaja.id}">${tehtava.kayttaja.nimi}</a></td>
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
