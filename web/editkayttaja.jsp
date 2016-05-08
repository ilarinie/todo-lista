<%-- 
    Document   : editkayttaja
    Created on : May 8, 2016, 12:12:14 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja>
 <div class="row">

        <div class="col-md-5 col-centered">
            <h1>Muokkaa käyttäjää</h1>
            <form action="tallennakayttajamuutokset" method="post">
            TUNNUS</br><input type="text" name="nimi" value="${kayttaja.nimi}"></br>
            SALASANA</br><input type="password" name="salasana"}></br>
            <input type="hidden" name="id" value="${kayttaja.id}">
            <button type="submit" class="btn btn-primary">Tallenna</button>
            
            </form>
        </div>
    </div>
</t:pohja>