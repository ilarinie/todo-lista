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
        <h3>Kuvaus<h3>
                <p>${tehtava.kuvaus}</p>
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
                </div>


            </t:pohja>