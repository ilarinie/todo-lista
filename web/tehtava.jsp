<%-- 
    Document   : tehtava
    Created on : Apr 25, 2016, 7:39:09 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Tehtava">
    <h1>${tehtava.otsikko}</h1>
    <p>${tehtava.kayttaja}</p>
    <div class="col-md-5">
        <h3>Kuvaus</h3>
        <p><c:out value="${tehtava.kuvaus}"></c:out></p>
        <h3>Kategoriat:</h3>
        <c:forEach items="${tehtava.kategoriat}" var="kategoria">
            <a href="kategoria?id=${kategoria.id}"><span class="label label-default"><c:out value="${kategoria.otsikko}"></c:out></span></a>
            </c:forEach>

        <h3>Suorita:</h3>
        <p>
            <c:choose>
                <c:when test="${tehtava.suoritettu}">
                    Suoritettu!
                </c:when>    
                <c:otherwise>
                <form action="SuoritaTehtava" method="post">
                    <input type="hidden" name="id" value="${tehtava.id}">
                    <button type="submit" class="btn btn-primary">suorita</button>
                </form>
            </c:otherwise>
        </c:choose>
    </p>

    <table class="table">
        <thead>
            <tr>
                <th>Lisää kategoria</th>
                <th>Muokkaa</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Lisää Kategoria
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <c:forEach items="${lisattavat}" var="kat">
                                <li><a href="lisaakategoria?tid=${tehtava.id}&kid=${kat.id}">${kat.otsikko}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </td>
                <td>

                    <a href="muokkaatehtava?id=${tehtava.id}" class="btn btn-warning">Muokkaa</a>
                </td>
            </tr>
        </tbody>
    </table>
</div>


</t:pohja>