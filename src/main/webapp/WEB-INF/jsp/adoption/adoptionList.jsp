<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

<petclinic:layout pageName="adoptions">
    <h2>Mascotas de adopcion</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Mascota</th>
            <th>Propietario</th>
            <th>Fecha de nacimiento</th>
            <th>Tipo</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${petsInAdoption}" var="pet">
            <tr>
                <td>
                    <c:out value="${pet.name}"/>
                </td>
                <td>
                    <c:out value="${pet.owner.firstName}"/>
                </td>
                <td>
                    <petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                    <c:out value="${pet.type.name}"/>
                </td>
                <td>
                    <spring:url value="adoptions/{petId}/new" var="adoption">
                        <spring:param name="petId" value="${pet.id}"/>
                    </spring:url>
                    <c:if test = "${pet.owner.user.username != nombreUsuario}">
                        <a href="${fn:escapeXml(adoption)}">
                            Solicitar adopcion 
                        </a>
                    </c:if>
                    <c:if test = "${pet.owner.user.username == nombreUsuario}">
                        <spring:url value="/owners/{ownerId}/pets/notInAdoption/{petId}" var="petNotInAdoption">
                            <spring:param name="ownerId" value="${pet.owner.id}"/>
                            <spring:param name="petId" value="${pet.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(petNotInAdoption)}">Eliminar de la lista de adopcion</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>