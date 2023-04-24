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
<title>Edit Your Event</title>
</head>
<body>
    <div class="container">
        <div class="d-flex flex-row-reverse align-items-center">
            <a href="/dashboard">Dashboard</a>
            <a href="/logout">Logout</a>
        </div>
        <div>
            <h2>Edit Event</h2>
        </div>
        <!--finish form-->
        <form:form action="/events/${event.id}/edit" method="post" modelAttribute="event" class="form">
      	<form:input type="hidden" value="${event.user.id}" path="user"/>
            <div class="form-row">
                <form:label class="form-label" path="eventName">Event Name:</form:label>
                <p><form:errors class="text-danger" path="eventName"/></p>
                <form:input class="form-control" path="eventName" type="text"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="location">Area:</form:label>
                <form:errors class="text-danger" path="location"/>
                <form:input class="form-control" path="location"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="type">Event Type:</form:label>
                <form:errors class="text-danger" path="type"/>
                <form:input class="form-control" path="type"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="description">Description:</form:label>
                <form:errors class="text-danger" path="description"/>
                <form:textarea class="form-control" path="description"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="price">Price:</form:label>
                <form:errors class="text-danger" path="price"/>
                <form:input class="form-control" type="number" path="price"/>
            </div>
            <div class="form-row">
                <form:label class="form-label" path="eventDate">Date:</form:label>
                <form:errors class="text-danger" path="eventDate"/>
                <form:input class="date" path="eventDate" type ="date" value='<fmt:formatDate value="${cForm.date}" pattern="MM-dd-yyyy" />'/>
            </div>
            <div>
                <input type="submit" value="Submit" class="btn btn-primary"/>
            </div>
        </form:form>
    </div>
</body>
</html>