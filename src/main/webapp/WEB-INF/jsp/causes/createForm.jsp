<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="donationForm">
    <h2>Donar a la causa</h2>
    <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Cantidad a donar" name="amount"/>
            <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <div style="color: red;">
                        <c:out value ="${Mensaje}"/>
                        <c:out value ="${error}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Añadir donación</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>