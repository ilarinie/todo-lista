<%-- 
    Document   : newtehtava
    Created on : Apr 18, 2016, 7:59:15 PM
    Author     : ile
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Uusi tehtävä">
    <div class="row">

        <div class="col-md-5 col-centered">
           
            <h1>UUSI TEHTÄVÄ</h1>
             <ul>
                <c:forEach items="${virheet}" var="virhe">
                    <li style="color:red;">${virhe}</li>
                </c:forEach>
            </ul>
            <form action="tallennatehtava" method="post">
            OTSIKKO</br><input type="text" name="otsikko" value="${tehtava.otsikko}"></br>
            PRIORITEETTI</br><input type="number" name="prioriteetti" value="${tehtava.prioriteetti}"></br>
            KUVAUS</br>
            <textarea name="kuvaus">${tehtava.kuvaus}</textarea></br>
            <button type="submit" class="btn btn-primary">LUO</button>
            
            </form>
        </div>
    </div>

</t:pohja>