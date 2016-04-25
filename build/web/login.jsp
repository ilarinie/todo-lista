<%-- 
    Document   : login.jsp
    Created on : Apr 18, 2016, 9:10:41 PM
    Author     : ile
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:login>
    <div class="col-md-5 col-centered">
        <h1>KIRJAUDU SISÄÄN</h1>
        <form action="login" method="post">
            NIMI</br><input type="text" name="nimi"></br>
            SALASANA</br><input type="password" name="salasana"></br>
            <button type="submit" class="btn btn-primary">KIRJAUDU</button>
        </form>
    </div>

</t:login>