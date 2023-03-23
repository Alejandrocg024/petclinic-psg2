<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="causeDetails">
    <h2>Causa</h2>

    <p>Nombre: <c:out value="${cause.name}"/></p>
    <p>Descripción: <c:out value="${cause.description}"/></p>
    <p>Objetivo: <c:out value="${cause.targetBudget}"/></p>
    <p>Organización: <c:out value="${cause.organization}"/></p>

    <h2>Donaciones</h2>

    <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Cliente</th>

            <th>Cantidad</th>

            <th>Fecha</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donationList}" var="donation">
            <tr>
                <td>
                    <c:out value="${donation.user.username}"/> 
                </td>
                <td>
                    <c:out value="${donation.amount} "/>
                </td>
                <td> 
                    <c:out value="${donation.donationDate}"/>   
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>