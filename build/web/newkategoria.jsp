<%-- 
    Document   : newtehtava
    Created on : Apr 18, 2016, 7:59:15 PM
    Author     : ile
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Uusi kategoria">
    <div class="row">

        <div class="col-md-5 col-centered">

            <h1>UUSI KATEGORIA</h1>
            <ul>
                <c:forEach items="${virheet}" var="virhe">
                    <c:out value="virhe"></c:out>
                </c:forEach>
            </ul>
            <form action="tallennakategoria" method="post">
                Kategorian nimi</br><input type="text" name="otsikko"></br><br>
                <button type="submit" class="btn btn-primary">LUO</button>

            </form>
        </div>
    </div>

</t:pohja>