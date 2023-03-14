<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bookings">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            Reserva para mascotas
        </h2>
        <form:form modelAttribute="booking"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${pet.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Propietario</label>
                    <div class="col-sm-10">
                        <c:out value="${owner.firstName} ${owner.lastName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Mascota</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="pet" name="pet">
                            <option value="">Selecciona una mascota</option>
                            <c:forEach var="pet" items="${pets}">
                                <option value="${pet.id}">${pet.name}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${error != null}">
                            <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                            <span style="color: red;">${error}</span>
                        </c:if>
                    </div>
                </div>
                <petclinic:inputField label="Fecha de Inicio" name="startDate"/>
                <petclinic:inputField label="Fecha de Fin" name="endDate"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <div style="color: red;">
                            <c:out value ="${Mensaje}"/>
                        </div>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Crear Nueva Reserva</button>
                </div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
