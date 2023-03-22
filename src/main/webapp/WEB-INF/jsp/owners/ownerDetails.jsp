<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2>Informacion del Propietario</h2>


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

    <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Propietario</a>

    <spring:url value="{ownerId}/pets/new" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Agregar Mascota</a>

    <spring:url value="/booking/{ownerId}" var="bookings">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(bookings)}" class="btn btn-default">Hotel de mascotas</a>

    <br/>
    <br/>
    <br/>
    <h2>Mascotas y Visitas</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nombre</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Fecha de nacimiento</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Tipo</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                        <dt>Estado</dt>
                        <dd>
                            <c:if test = "${pet.inAdoption == true}">
                                <c:out value="En Adopcion"/>
                            </c:if>
                            <c:if test = "${pet.inAdoption == false}">
                                <c:out value="No esta en adopcion"/>
                            </c:if>
                        </dd>
                        <c:if test = "${owner.user.username == nombreUsuario}">
                            <dt>Borrar mascota</dt>
                            <dd>
                                <spring:url value="/owners/{ownerId}/pets/delete/{petId}" var="deleteUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(deleteUrl)}"> 
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </a>
                            </dd> 
                            <dt>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <button>
                                    <a href="${fn:escapeXml(petUrl)}">Editar Mascota</a>
                                </button>
                            </dt>
                            <dd>
                                <c:if test = "${pet.inAdoption == true}">
                                    <spring:url value="/owners/{ownerId}/pets/notInAdoption/{petId}" var="petNotInAdoption">
                                        <spring:param name="ownerId" value="${owner.id}"/>
                                        <spring:param name="petId" value="${pet.id}"/>
                                    </spring:url>
                                    <button>
                                        <a href="${fn:escapeXml(petNotInAdoption)}">Eliminar de la lista de adopcion</a>
                                    </button>
                                </c:if>
                                <c:if test = "${pet.inAdoption == false}">
                                    <spring:url value="/owners/{ownerId}/pets/inAdoption/{petId}" var="petInAdoption">
                                        <spring:param name="ownerId" value="${owner.id}"/>
                                        <spring:param name="petId" value="${pet.id}"/>
                                    </spring:url>
                                    <button>
                                        <a href="${fn:escapeXml(petInAdoption)}">Incluirla en la lista de adopciones</a>
                                    </button>
                                </c:if>
                            </dd>
                        </c:if>
                        
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Dia de visita</th>
                            <th>Descripcion</th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                                <td>
                                    <spring:url value="/owners/{ownerId}/pets/{petId}/visits/delete/{visitId}" var="deleteVisit">
                                        <spring:param name="ownerId" value="${owner.id}"/>
                                        <spring:param name="petId" value="${pet.id}"/>
                                        <spring:param name="visitId" value="${visit.id}"/>
                                    </spring:url>
                                    <c:if test = "${owner.user.username == nombreUsuario}">
                                        <a href="${fn:escapeXml(deleteVisit)}">
                                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        </a> 
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>

                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">A&#241;adir visita</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

    <br/>
    <br/>
    <br/>
    <h2>Solicitudes de adopcion</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Mascota</th>
            <th>Solicitante</th>
            <th>Fecha de solicitud</th>
            <th>Descripci&#243;n</th>
            <th>Estado de la solicitud</th>
        </tr>
        </thead>
        <tbody>
                <c:forEach items="${adoptions}" var="adoption">
                    <tr>
                        <td>
                            <c:out value="${adoption.pet.name}" />
                        </td>
                        <td>
                            <c:out value="${adoption.owner.firstName} ${adoption.owner.lastName}" />
                        </td>
                        <td>
                            <c:out value="${adoption.posting_date}" />
                        </td>
                        <td>
                            <c:out value="${adoption.description}" />
                        </td>
                        <c:if test="${adoption.status == true}">
                            <td>
                                <c:out value="Ha adoptado esta mascota" />
                            </td>
                        </c:if>
                        <c:if test="${adoption.status == false}">
                            <td>
                                <c:out value="Se ha rechazado la adopcion" />
                            </td>
                        </c:if>
                        <c:if test="${adoption.status == null}">
                            <td>
                                <c:if test="${owner.user.username == nombreUsuario}">
                                    <spring:url value="/adoptions/{ownerId}/accept/{adoptionId}" var="acceptAdoption">
                                        <spring:param name="ownerId" value="${owner.id}" />
                                        <spring:param name="adoptionId" value="${adoption.id}" />
                                    </spring:url>
                                    <a href="${fn:escapeXml(acceptAdoption)}">
                                        <span style="color: green;" class="glyphicon glyphicon-ok"
                                            aria-hidden="true">Aceptar</span>
                                    </a>
                                    <br />
                                    <spring:url value="/adoptions/{ownerId}/decline/{adoptionId}" var="declineAdoption">
                                        <spring:param name="ownerId" value="${owner.id}" />
                                        <spring:param name="adoptionId" value="${adoption.id}" />
                                    </spring:url>
                                    <a href="${fn:escapeXml(declineAdoption)}">
                                        <span style="color: red;" class="glyphicon glyphicon-remove"
                                            aria-hidden="true">Rechazar</span>
                                    </a>
                                </c:if>
                                <c:if test="${owner.user.username != nombreUsuario}">
                                    <c:out value="En espera de resolverse" />
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
        </tbody>
    </table>

</petclinic:layout>