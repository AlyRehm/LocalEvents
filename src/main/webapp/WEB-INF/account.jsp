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
<title>Account</title>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <h1>View Events</h1>
            <a href="/event/new">New Event</a>
            <a href="/dashboard">Dashboard</a>
            <a href="/logout">Logout</a>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Area</th>
                    <th>Event</th>
                    <th>Date</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="event" items="${userEvents}">
                    <tr>
                        <td><c:out value="${event.location}"></c:out></td>
                        <td><c:out value="${event.eventName}"></c:out></td>
                        <td><c:out value="${event.eventDate}"></c:out></td>
                        <td><c:out value="${event.price}"></c:out></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <!--add logic for session user events-->
        <table class="table">
            <thead>
                <tr>
                    <th>Area</th>
                    <th>Event</th>
                    <th>Date</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
            <!-- where the attended events would go -->
           <%--  <c:forEach var="event" items="${attendedEvents}">
                    <tr>
                        <td><c:out value="${event.location}"></c:out></td>
                        <td><c:out value="${event.eventName}"></c:out></td>
                        <td><c:out value="${event.eventDate}"></c:out></td>
                        <td><c:out value="${event.price}"></c:out></td>
                    </tr>
            </c:forEach> --%>
            </tbody>
        </table>
    </div>
</body>
</html>