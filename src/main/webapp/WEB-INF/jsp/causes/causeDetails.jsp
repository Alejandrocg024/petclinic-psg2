<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="causeDetails">

    <h2>Detalles de la causa</h2>
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause1.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripcion</th>
            <td><c:out value="${cause1.description}"/></td>
        </tr>
        <tr>
            <th>Presupuesto</th>
            <td><c:out value="${cause1.amountDonated}"/> &euro;/
                <c:out value="${cause1.budgetTarget}"/> &euro;
            </td> 
        </tr>
        <tr>
            <th>Organizacion</th>
            <td><c:out value="${cause1.organization}"/></td>
        </tr>
    </table>

    <c:if test="${cause1.active == true}">
        <a href="/causes/${cause1.id}/donation/new" class="btn btn-default">Donar</a>
    </c:if>

    <br/>
    <br/>
    <br/>
    <h2>Donaciones a la causa</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Cliente</th>
            <th>Cantidad</th>
            <th>Fecha de donacion</th>
        </tr>
        </thead>
        <tbody>
                <c:forEach items="${donations}" var="donation">
                    <tr>
                        <td>
                            <c:out value="${donation.owner.user.username}"/> 
                        </td>
                        <td>
                            <c:out value="${donation.amount} "/>
                        </td>
                        <td>
                            <c:out value="${donation.date} "/>
                        </td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>

</petclinic:layout>