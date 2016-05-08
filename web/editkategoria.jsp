<%-- 
    Document   : editkategoria
    Created on : May 8, 2016, 12:55:46 PM
    Author     : ile
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Uusi kategoria">
    <div class="row">

        <div class="col-md-5 col-centered">
            <h1>Muokkaa kategoriaa</h1>
            <form action="tallennakategoriamuutokset" method="post">
                Kategorian nimi</br><input type="text" name="otsikko" value="${kategoria.otsikko}"></br><br>
                <input type="hidden" name="id" value="${kategoria.id}">
            <button type="submit" class="btn btn-primary">Tallenna</button>
            
            </form>
        </div>
    </div>

</t:pohja>
