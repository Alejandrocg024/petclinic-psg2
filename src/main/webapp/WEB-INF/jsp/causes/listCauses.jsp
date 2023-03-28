<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="findCauses">
    <table id="causesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>

            <th>Presupuesto</th>

            <th>Estado</th>

            <th></th>

            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes}" var="cause">
            
                <tr>
                    <td>
                        <c:out value="${cause.name}"/>
                    </td>
                    <td>
                        <c:out value="${cause.amountDonated}"/> &euro;/
                        <c:out value="${cause.budgetTarget}"/> &euro;
                    </td>
                    <td>
                        <c:if test="${cause.active == true}">
                            <c:out value="Activa"/>
                        </c:if>
                        <c:if test="${cause.active == false}">
                            <c:out value="Recaudada"/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${cause.active == true}">
                            <a href="/causes/${cause.id}/donation/new" class="btn btn-default">Donar</a>
                        </c:if>
                    </td>
                    <td>
                        <a href="/causes/${cause.id}" class="btn btn-default">Detalles</a>
                    </td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/causes/new" var="newUrl"></spring:url>
    <a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Crear una causa</a>
    </tbody>
</petclinic:layout>