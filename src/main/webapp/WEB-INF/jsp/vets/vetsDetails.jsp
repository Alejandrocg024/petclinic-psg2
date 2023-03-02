<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <h2>Informacion del veterinario</h2>

    <table id="vetsTable" class="table table-striped">
            <tr>
                <th>Nombre</th>
                <td>
                    <b><c:out value="${vets.firstName} ${vets.lastName}"/></b>
                </td>
            </tr>
            <tr>
                <th>Especialidades</th>
                <td>
                    <c:if test="${vets.nrOfSpecialties > 0}">
                        <c:forEach var="specialty" items="${vets.specialties}" varStatus = "loop">
                        <c:out value="${specialty.name}"/> 
                        <c:if test = "${not loop.last}">, </c:if>
                    </c:forEach>
                    </c:if>
                    <c:if test="${vets.nrOfSpecialties == 0}">Ninguno</c:if>
                </td>
            </tr>
    </table>

    <spring:url value="/vets/{vetId}/edit" var="editUrl">
        <spring:param name="vetId" value="${vets.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Veterinario</a>

</petclinic:layout>
