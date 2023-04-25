<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<!--previously I used <meta charset="UTF-8">-->
<meta charset="ISO-8859-1">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css"/>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Create a Local Event</title>
</head>
<body>
    <div class="container">
        <div class="d-flex flex-row-reverse align-items-center">
            <a href="/dashboard">Dashboard</a>
            <a href="/logout">Logout</a>
        </div>
        <div>
            <h2>New Event</h2>
        </div>
        <!--finish form-->
        <form:form action="/events/new" method="post" modelAttribute="event" class="form">
            <form:input type="hidden" value="${user.id}" path="user"/>
            <div class="form-row">
                <form:label class="form-label" path="eventName">Event Name:</form:label>
                <p><form:errors class="text-danger" path="eventName"/></p>
                <form:input class="form-control" path="eventName" type="text"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="location">Area:</form:label>
                <p><form:errors class="text-danger" path="location"/></p>
                <form:input class="form-control" path="location" type="text"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="type">Event Type:</form:label>
                <p><form:errors class="text-danger" path="type"/></p>
                <form:input class="form-control" path="type" type="text"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="description">Description:</form:label>
                <p><form:errors class="text-danger" path="description"/></p>
                <form:textarea class="form-control" path="description" type="text"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="price">Price:</form:label>
                <p><form:errors class="text-danger" path="price"/></p>
                <form:input class="form-control" type="number" path="price"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="eventDate">Date:</form:label>
                <p><form:errors class="text-danger" path="eventDate"/></p>
                <form:input class="date" path="eventDate" type="date" value='<fmt:formatDate value="${cForm.date}" pattern="MM-dd-yyyy" />'/>
            </div>
            <div>
                <input type="submit" value="Submit" class="button"  class="btn btn-primary"/>
            </div>
        </form:form>
    </div>
</body>
</html>