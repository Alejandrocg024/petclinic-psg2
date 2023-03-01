<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="specialties">

<div class="form-group">
    <label class="col-sm-2 control-label">Specialties</label>
    <div class="col-sm-10">
        <c:forEach var="specialty" items="${specialties}">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="specialties" value="${specialty.id}"
                        <c:if test="${vet.specialties.contains(specialty)}">checked</c:if>
                    > ${specialty.name}
                </label>
            </div>
        </c:forEach>
    </div>
</div>
</form:form>
</petclinic:layout>
