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

                <th>Recaudado</th>

                <th>Objetivo</th>

                <th>Donar</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach items="${causaDonation}" var="causeMap">
                <c:if test="${causeMap.cause.budgetTarget != causeMap.amount}">
                    <tr>
                        <td>
                            <a href="/causes/${causeMap.cause.id}">
                                <c:out value="${causeMap.cause.name}" />
                            </a>
                        </td>
                        <td>
                            <c:out value="${causeMap.amount}" />
                        </td>
                        <td>
                            <c:out value="${causeMap.cause.budgetTarget}" />
                        </td>
                        <td>
                            <a href="/causes/${causeMap.cause.id}/donation/new" class="btn btn-default">Donar</a>
                        </td>

                    </tr>
                </c:if>
            </c:forEach>
    </table>
    <spring:url value="/causes/new" var="newUrl"></spring:url>
    <a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Crear una causa</a>
    </tbody>
</petclinic:layout>