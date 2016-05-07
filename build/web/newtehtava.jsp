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
            <form action="newtehtava" method="post">
            OTSIKKO</br><input type="text" name="otsikko"></br>
            PRIORITEETTI</br><input type="number" name="prioriteetti"></br>
            KUVAUS</br>
            <textarea name="kuvaus"></textarea></br>
            <button type="submit" class="btn btn-primary">LUO</button>
            
            </form>
        </div>
    </div>

</t:pohja>