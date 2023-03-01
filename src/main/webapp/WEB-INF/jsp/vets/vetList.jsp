<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>Veterinarios</h2>

    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Especialidades</th>
            <security:authorize access="hasAnyRole('admin', 'veterinarian')">
                <th>
                    Eliminar
                </th>
            </security:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <spring:url value="/vets/{vetId}" var="vetUrl">
                        <spring:param name="vetId" value="${vet.id}"/>
                    </spring:url>
                    
                    <a href="${fn:escapeXml(vetUrl)}"><c:out value="${vet.firstName} ${vet.lastName}"/></a>
                    <div>
                        
                    </div>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}" varStatus = "loop">
                        <c:out value="${specialty.name}"/> 
                        <c:if test = "${not loop.last}">, </c:if>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">ninguno</c:if>
                </td>
                <sec:authorize access="hasAnyRole('admin', 'veterinarian')" > 
                    <td> 
                        <a href="/vets/delete/${vet.id}"> 
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </a>      
                    </td>
                </sec:authorize>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <spring:url value="/vets.xml" var="xml"></spring:url>
    <a href= "${fn:escapeXml(xml)}" class="btn btn-default">Ver como XML</a>
            
    <sec:authorize access="hasAuthority('admin')">
    <spring:url value="/vets/new" var="newUrl"></spring:url>
	<a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Add Vet</a>
	</sec:authorize>

</petclinic:layout>
