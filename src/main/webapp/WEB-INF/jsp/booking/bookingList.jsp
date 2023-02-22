<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bookings">

    <h2>Informacion de los due&#241;os</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>Ciudad</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Telefono</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="/owners/{ownerId}" var="back">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(back)}" class="btn btn-default">Volver</a>

    <spring:url value="/booking/{ownerId}/new" var="newBooking">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <c:if test = "${esUserLogeado == true}">
        <a href="${fn:escapeXml(newBooking)}" class="btn btn-default">Nueva Reserva</a>
    </c:if>
    <br/>
    <br/>
    <br/>
    <h2>Reservas</h2>

    <table id="bookingsTable" class="table table-striped" style="border: 1px solid">
        <thead>
        <tr>
            <th>Mascota</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookings}" var="booking">
            <tr>
                <td>
                    <c:out value="${booking.pet.name}"/>
                </td>
                <td>
                    <c:out value="${booking.startDate}"/>
                </td>
                <td>
                    <c:out value="${booking.endDate}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
