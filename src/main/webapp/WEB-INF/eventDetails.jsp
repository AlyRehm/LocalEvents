<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css"/>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Local Events</title>
</head>
<body>
    <div class = "container">
        <div class="d-flex justify-content-between align-items-center">
            <h2><c:out value="${event.eventName}"></c:out></h2>
            <div>	
            	<a href="/event/new">New Event</a>
            	<a href="/dashboard">Dashboard</a>
            	<a href="/logout">Logout</a>
        	</div>
        </div>
        <div>
            <p><span class="text-uppercase fw-bold">Host:</span> <c:out value="${event.user.username}"/> </p>
            <p><span class="text-uppercase fw-bold">Location: </span><c:out value="${event.location}"/></p>
			<p> <span class="text-uppercase fw-bold">Date: 	</span>
				<fmt:formatDate type = "date" 
         			value = "${event.eventDate}" />
			</p>
			
			
			<p><span class="text-uppercase fw-bold">Price: </span> $<c:out value="${event.price}"/></p>
			<p><span class="text-uppercase fw-bold">Description: </span><c:out value="${event.description}"/></p>
        </div>
        <div class="row align-items-center">
			<div class="col-sm">
			<c:if test="${event.user.id==user.id}">
				<div class="col-sm">
					<a class="btn btn-secondary" href="/event/${event.id}/editEvent" role="button">Edit</a>
				</div>
					<a class="btn btn-danger" href="/events/delete/${event.id}" role="button">Delete</a>
			</c:if>
			</div>
		</div>
		
		
    </div>
</body>
</html>