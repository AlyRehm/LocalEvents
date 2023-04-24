<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

     <div class="bg-img">
         <div class="content">
            <header>Login Form</header>
            <form:form action="/register" method="POST" modelAttribute="newUser">
            	<div class="errors">
            	 	<p class="errors"><form:errors path="username"/></p>
	                <p class="errors"><form:errors path="email"/></p>
	                <p class="errors"><form:errors path="password"/></p>
	            </div>
	            
	            <div class="field">
                  <span class="fa fa-user"></span>
                  <form:input path="username" type="text" placeholder="Username" id="form3Example3" class="form-control" />
                  <form:label path="username" class="form-label" for="form3Example3"></form:label>
               </div>
               <div class="field">
                  <span class="fa fa-user"></span>
                  <form:input path="email" type="email" placeholder="Email" id="form3Example3" class="form-control" />
                  <form:label path="email" class="form-label" for="form3Example3"></form:label>
               </div>
               <div class="field space">
                  <span class="fa fa-lock"></span>
                 <form:input path="password" type="password" placeholder="Password" id="form3Example4" class="form-control" />
                 <form:label path="password" class="form-label" for="form3Example4"></form:label>
                  <span class="show">SHOW</span>
               </div>
               <div class="field space">
                  <span class="fa fa-lock"></span>
                 <form:input path="confirmPassword" type="password" placeholder="Confirm Password" id="form3Example4" class="form-control" />
                 <form:label path="confirmPassword" class="form-label" for="form3Example4"></form:label>
               </div>
               
               <div class="field">
                  <input type="submit" value="REGISTER">
               </div>
            </form:form>
            
            <div class="signup">
               Already have an account?
               <a href="/login">Login</a>
            </div>
         </div>
      </div>
</body>
</html>