<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>
        Nueva Adopcion
    </h2>
    <form:form modelAttribute="adoption" class="form-horizontal" id="add-adoption-form">
        <div class="form-group has-feedback">
            <div class="form-group">
                <label class="col-sm-2 control-label">Mascota</label>
                <div class="col-sm-10">
                    <c:out value="${pet.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Propietario</label>
                <div class="col-sm-10">
                    <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
                </div>
            </div>
            <div>
                <petclinic:inputField label="Descripcion" name="description"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-10">
                <div style="color: red;">
                    <c:out value ="${Mensaje}"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Solicitar la adopcion</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>