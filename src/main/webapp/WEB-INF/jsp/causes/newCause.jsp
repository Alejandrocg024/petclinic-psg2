<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="newCause">
    <h2>Crear una Causa Nueva</h2>
    <form:form modelAttribute="cause" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="name"/>
            <petclinic:inputField label="Descripción" name="description"/>
            <petclinic:inputField label="Objetivo" name="budgetTarget"/>
            <petclinic:inputField label="Organización" name="organization"/>
            <div class="form-group">
                <label class="col-sm-2 control-label">Activo</label>
                <div class="col-sm-10">
                    <select class="form-control" id="active" name="active">
                        <option value="">Selecciona un valor</option>
                        <c:forEach var="a" items="${lista}">
                            <option value="${a}">${a}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Añadir Causa</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>