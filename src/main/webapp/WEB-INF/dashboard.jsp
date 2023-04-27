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
	<title>Edit Your Event</title>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<div>
				<h1>Welcome <c:out value="${user.username}"/></h1>
			</div>
			<div class="row align-items-center">
				<a href="/event/new">New Event</a>
				<a href="/account">Account</a>
				<a href="/logout">Logout</a>
			</div>
		</div>
		<div class="d-flex justify-content-center">
			<h3>All Local Events</h3>
		</div>
		<form action="/search" method="GET">
			<div class="d-flex inline">
				<input type="text" class="form-control" name="keyword" placeholder="Search events">
					<button type="submit" class="btn btn-search" type="button"> <i class="fa fa-search"></i>
					</button>
				</input>
			</div>
		</form>
				
		<table class="table table-striped">
			<thead>
				<th scope="col">Event</th>
				<th scope="col">Date</th>
				<th scope="col">Location</th>
				<th scope="col">Price</th>
				<th scope="col">Actions</th>
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
							$<c:out value="${event.price}"></c:out>
						</td>
						<td>
							<c:if test="${event.user.id==user.id}">
								<a href="/event/${event.id}/editEvent">Edit</a>
							</c:if>
							<!-- <c:if test="${event.user.id!=user.id}">
								<a href="/events/attend/${event.id}">Attend Event</a>
							</c:if> -->
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>