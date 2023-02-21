<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Book Pet Hotel</title>
</head>
<body>
    <h1>Book Pet Hotel</h1>
    <form action="${pageContext.request.contextPath}/pet-hotel/book" method="post">
        <label for="pet">Pet:</label>
        <select id="pet" name="pet">
            <option value="">Select a pet</option>
            <c:forEach var="pet" items="${pets}">
                <option value="${pet.id}">${pet.name}</option>
            </c:forEach>
        </select>

        <label for="start-date">Start Date:</label>
        <input type="date" id="start-date" name="startDate">

        <label for="end-date">End Date:</label>
        <input type="date" id="end-date" name="endDate">


        <button type="submit">Book Pet Hotel</button>
    </form>
</body>
</html>