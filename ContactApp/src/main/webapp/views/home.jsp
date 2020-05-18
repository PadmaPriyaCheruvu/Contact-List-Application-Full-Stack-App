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
  			<script>
				function myCreateFunction() {
				  var table = document.getElementById("myTable");
				  var row = table.insertRow(1);
				  var cell1 = row.insertCell(0);
				  var cell2 = row.insertCell(1);
				  var cell3 = row.insertCell(2);
				  var cell4 = row.insertCell(3);
				  var cell5 = row.insertCell(4);
				  cell1.innerHTML = "<input type='text'/>";
				  cell2.innerHTML = "<input type='text'/>";
				  cell3.innerHTML = "<input type='text'/>";
				  cell4.innerHTML = "<input type='text'/>";
				  cell5.innerHTML = "<input type='text'/>";
				}
				
				function myDeleteFunction() {
				  document.getElementById("myTable").deleteRow(1);
				}
			</script>        <style>
        #leftbox { 
                float:left;  
                width:33%; 
                height:200px;
                align:center; 
            } 
            #middlebox{ 
                float:left;  
                width:33%; 
                height:200px; 
                align:center; 
            } 
            #rightbox{ 
                float:right; 
                width:33%; 
                height:200px;
                align:center;  
            } 
				a:link, a:visited {
				  background-color: #f44336;
				  color: white;
				  padding: 14px 25px;
				  text-align: center;
				  text-decoration: none;
				  display: inline-block;
				}
				
				a:hover, a:active {
				  background-color: red;
				}
        </style>

    </head>
    <body>
            <h1 align="center">Contact List</h1>
            
            <div class="container" align="center">
			  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" >
			    Add New Contact
			  </button>
			  <br><br>
    
	   <form action="/ContactApp/searchPage" method = 'get' align="center">
 	         <button type="submit" class="btn btn-primary">Search Contacts</button>
	      </form> 

  <div class="modal" id="myModal">
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">New Contact</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <form:form method = "POST"  modelAttribute="userForm" action = "/ContactApp/insertContact">
         <table>
            <tr>
               <td><form:label path = "fname">First Name</form:label></td>
               <td><form:input path = "fname" /></td>
            </tr>
            <tr>
               <td><form:label path = "mname">Middle Name</form:label></td>
               <td><form:input path = "mname" /></td>
            </tr>
            <tr>
               <td><form:label path = "lname">Last Name</form:label></td>
               <td><form:input path = "lname" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>
         </table>
         
         <!-- <form:form class="form-horizontal" method="post" modelAttribute="userForm" action="/ContactApp/insertContact">
   	</form:form> --> 
         <table id="myTable">
			  <tr>
			    <th>Address Type</th>
			    <th>Address</th>
			    <th>City</th>
			    <th>State</th>
			    <th>Zip</th>
			  </tr>
			  
					</table>
		         <button onclick="myCreateFunction()">Add address</button>          
		      </form:form>
		        </div>
		        
		        <!-- Modal footer -->
		        <div class="modal-footer">
		          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		        </div>
		        
		      </div>
		    </div>
		  </div>
		  
		</div>
            
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
									 
					<form action="/ContactApp/editPage/${contact.id}" method = "post"> 
					<input type="submit" value="Edit Page">
					</form>	
					
					<br><br>
					
					<form action="/ContactApp/deleteContact/${contact.id}" method = "post"> 
					<input type="submit" value="Delete">
					</form>
					</div>  <br><br>
                </c:forEach>  
                
   
   
   
    </body>
</html>