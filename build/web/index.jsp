<%-- 
    Document   : index
    Created on : Mar 20, 2016, 12:37:22 PM
    Author     : ile
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Todo-lista">

    <h2>Todo-lista</h2>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Tehtävä</th>
                <th>Prioriteetti</th>
                <th>Suorita</th>
            </tr>

        </thead>
        <tbody>
            <c:forEach items="${lista}" var="tehtava">
            <tr>
                <td>${tehtava.otsikko}</td>
                <td>${tehtava.prioriteetti}</td>
                <td><button class="btn btn-primary">suorita</button></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
       
            
    

</t:pohja>
