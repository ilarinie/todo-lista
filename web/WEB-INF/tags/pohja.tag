<%-- 
    Document   : pohja
    Created on : Apr 8, 2016, 9:24:02 PM
    Author     : ile
--%>

<%@tag description="Pohja TODO-LISTA sivuille" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO-Lista</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li><a href="/todo-lista">Etusivu</a></li>
                    <li><a href="newtehtava">Uusi Tehtävä</a></li>
                    <li><a href="uusikategoria.html">Uusi Kategoria</a></li>
                    <li><a href="kayttajat">Käyttäjälista</a></li>
                    <li><a href="login">Login</a></li>
                </ul>
            </div>
        </nav>
        <c:if test="${virheViesti != null}">
            <div class="alert alert-danger">Virhe! ${virheViesti}</div>
        </c:if>
        <div class="container">
            <jsp:doBody/>
        </div>
    </body>
</html>
