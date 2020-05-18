<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact List Application Home</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <style>
        #leftbox { 
                float:left;  
                width:33%; 
                height:200px; 
            } 
            #middlebox{ 
                float:left;  
                width:33%; 
                height:200px; 
            } 
            #rightbox{ 
                float:right; 
                width:33%; 
                height:200px; 
            } 
        </style>
    </head>
    <body>
            <h1>Contact List</h1>
            
            <div class="container">
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    Add New Contact
  </button>

  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">New Contact</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
</div>
            
            
             	
            <!-- <h3><a href="/newContact">New Contact</a></h3> -->

				<c:forEach  items="${listContact}" var="contact" >

					<div  id="leftbox">
					<h2> Name :  ${contact.fname} ${contact.mname} ${contact.lname}</h3>
					Phones:<br>
					<c:forEach  items="${contact.phones}" var="ph" >
					    ${ph.phType }	phone : ${ph.areaCode}-${ph.number}<br>
					 </c:forEach>
					 Dates: <br>
					 <c:forEach  items="${contact.dates}" var="dt" >
					    ${dt.dtType } : ${dateVal}<br>
					 </c:forEach>
					</div>
					
					<div  id="middlebox">
					<h2> Address</h3>
					<c:forEach  items="${contact.addresses}" var="add" >
					    ${add.addrType}	address : ${add.addr} , ${add.city} , ${add.state} , ${add.zip} <br>
					 </c:forEach>
					</div>
					
					<div  id="rightbox">
									 
					<a  href="/editContact?id=${contact.id}">Edit</a>
<%-- 					<form:form method="DELETE" action="/deleteContact/${contact.id}"><input type="submit" value="delete"></form:form>
 --%>					
					<form action="/ContactApp/deleteContact/${contact.id}" method = "post"> 
					<input type="submit" value="Submit">
					</form>
					
<%--  					<a method="DELETE" href="deleteContact/${contact.id}">Delete</a>
 --%>					</div>  <br><br>
                </c:forEach>             
    </body>
</html>