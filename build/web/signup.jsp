<%-- 
    Document   : signup
    Created on : Apr 25, 2016, 9:00:42 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:login>
 <div class="row">

        <div class="col-md-5 col-centered">
            <h1>REKISTERÖIDY</h1>
            <form action="newkayttaja" method="post">
            TUNNUS</br><input type="text" name="nimi"></br>
            SALASANA</br><input type="password" name="salasana"></br>

            <button type="submit" class="btn btn-primary">LUO</button>
            
            </form>
        </div>
    </div>
</t:login>