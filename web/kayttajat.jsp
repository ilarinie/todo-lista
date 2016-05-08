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
    <table class="table">
        <thead>
            <tr>
                <th>Käyttäjä</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${lista}" var="kayttaja">
                <tr>
                    <td>${kayttaja.nimi}<td>
                    <td> <c:if test="${kirjautunut.admin}">
                            <a href="destroykayttaja?id=${kayttaja.id}" class="btn btn-danger">Poista</a> <a href="muokkaakayttaja?id=${kayttaja.id}" class="btn btn-warning">Muokkaa</a>
                        </c:if></td>
                    <td> <c:if test="${kirjautunut.id}==${kayttaja.id}">
                            <a href="muokkaakayttaja?id=${kayttaja.id}" class="btn btn-warning">Muokkaa</a>
                        </c:if></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</ul>

</t:pohja>

