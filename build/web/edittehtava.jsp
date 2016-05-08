<%-- 
    Document   : edittehtava
    Created on : May 8, 2016, 11:23:41 AM
    Author     : ile
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Muokkaa tehtävää">
    <div class="row">

        <div class="col-md-5 col-centered">
            <h1>MUOKKAA TEHTÄVÄÄ</h1>
             <ul>
                <c:forEach items="${virheet}" var="virhe">
                    <li style="color:red;">${virhe}</li>
                </c:forEach>
            </ul>
            <form action="tallennatehtavamuutokset" method="post">
            OTSIKKO</br><input type="text" name="otsikko" value="${tehtava.otsikko}"></br>
            PRIORITEETTI</br><input type="number" name="prioriteetti" value="${tehtava.prioriteetti}"></br>
            KUVAUS</br>
            <textarea name="kuvaus">${tehtava.kuvaus}</textarea></br>
            <input type="hidden" name="id" value="${tehtava.id}">
            <button type="submit" class="btn btn-primary">Tallenna</button>
            
            
            
            
            </form>
             <a href="javascript:history.back()" class="btn btn-default">Takaisin</a>
        </div>
           
    </div>

</t:pohja>
