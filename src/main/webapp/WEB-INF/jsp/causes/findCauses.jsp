<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<style>
    #causes {
        margin-bottom: 20px;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
    }

    #donation {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
    }
</style>

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
        <c:forEach items="${causesMap}" var="causeMap">
            <tr>
                <td>
                    <a href="/causes/details/${causeMap.id}"> 
                        <c:out value="${causeMap.name}"/>
                    </a>   
                </td>
                <td>
                    <c:out value="${donacionesTotal} "/>
                </td>
                <td> 
                    <c:out value="${causeMap.targetBudget}"/>   
                </td>
                <td>
                    <form:form 
                        modelAttribute="donation" 
                        class="form-horizontal" 
                        id="add-owner-form"
                        method="POST"
                        action="donation/new"
                        >   
                        <input type="hidden" name="cause" value="${causeMap.id}"/>
                        <div id="donation">
                            <div class="form-group has-feedback">
                                <form:input id="donation-input" path="amount" required="true" placeholder="Añade cantidad..." autocomplete="off" type="number"/>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button class="btn btn-default" type="submit">Donar</button>
                                </div>
                            </div>
                        </div>                        
                    </form:form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>