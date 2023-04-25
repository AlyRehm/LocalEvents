<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- Search icon CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Local Events</title>
</head>
<body>
	<div class="container">
		<h1>Welcome <c:out value="${user.username}"/></h1>
		<p><a href="/event/new">New Event</a></p>
		<p><a href="/account">Account</a></p>
		<p><a href="/logout">Logout</a></p>
		<h3>All Events</h3>
		
		<form action="/search" method="GET">
			<div class="d-flex inline">
				<input type="text" class="form-control" name="keyword" placeholder="Search events">
					<button type="submit" class="btn btn-search" type="button"> <i class="fa fa-search"></i>
					</button>
				</input>
			</div>
		</form>
				
		<table>
			<thead>
				<th>Event</th>
				<th>Date</th>
				<th>Location</th>
				<th>Price</th>
				<th>Actions</th>
			</thead>
			<tbody>
				<c:forEach var="event" items="${events}">
					<tr>
						<td>
							<a href="/event/${event.id}">${event.eventName}</a>
						</td>
						<td>
							<fmt:formatDate type = "date" 
         						value = "${event.eventDate}" />
						</td>
						<td>
							<c:out value="${event.location}"></c:out>
						</td>
						<td>
							<c:out value="${event.price}"></c:out>
						</td>
						<td>
							<c:if test="${event.user.id==user.id}">
								<a href="/event/${event.id}/editEvent">Edit</a>
							</c:if>
							<c:if test="${event.user.id!=user.id}">
								<a href="/events/attend/${event.id}">Attend Event</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>