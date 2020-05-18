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
				  var table = document.getElementById("addressTable");
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
				
				function phCreateFunction() {
					  var table = document.getElementById("phTable");
					  var row = table.insertRow(1);
					  var cell1 = row.insertCell(0);
					  var cell2 = row.insertCell(1);
					  var cell3 = row.insertCell(2);

					  cell1.innerHTML = "<input type='text'/>";
					  cell2.innerHTML = "<input type='text'/>";
					  cell3.innerHTML = "<input type='text'/>";

					}
				
				function dtCreateFunction() {
					  var table = document.getElementById("dtTable");
					  var row = table.insertRow(1);
					  var cell1 = row.insertCell(0);
					  var cell2 = row.insertCell(1);

					  cell1.innerHTML = "<input type='text'/>";
					  cell2.innerHTML = "<input type='text'/>";

					}
				function myDeleteFunction() {
				  document.getElementById("myTable").deleteRow(1);
				}
			</script>
    </head>
    <body>
            <h1 align="center">Edit Contact</h1>
            <div class="container" align="center">

          <form:form action="/ContactApp/edit" method = "POST" modelAttribute="userForm">
               First Name : <input type="text" name = "fname" value="${contact.fname}"/>
               Middle Name : <input type="text" name = "mname" value="${contact.mname}"/>
               Last Name : <input type="text" name = "lname" value="${contact.lname}"/>

         <table id="addressTable">
			<tr>
			    <th>Address Type</th>
			    <th>Address</th>
			    <th>City</th>
			    <th>State</th>
			    <th>Zip</th>
			  </tr>
			  <c:forEach  items="${contact.addresses}" var="add" >

               
               <td><input type="text" name = "addrType" value="${add.addrType}"/></td>
               <td><input type="text" name = "addr" value="${add.addr}"/></td>
               <td><input type="text" name = "city" value="${add.city}"/></td>
               <td><input type="text" name = "state" value="${add.state}"/></td>
               <td><input type="text" name = "zip" value="${add.zip}"/></td>
			  </c:forEach>
			  
			</table>
			<button onclick="myCreateFunction()">Add address</button>
			
			 <table id="phoneTable">
			<tr>
			    <th>Phone Type</th>
			    <th>Area Code</th>
			    <th>Number</th>
			  </tr>
			  <c:forEach  items="${contact.phones}" var="ph" >

               <td><input type="text" name = "phType" value="${ph.phType}"/></td>
               <td><input type="text" name = "areaCode" value="${ph.areaCode}"/></td>
                <td><input type="text" name = "number" value="${ph.number}"/></td>

			  </c:forEach>
			  
			</table>
			<button onclick="phCreateFunction()">Add Phone</button>
			
			 <table id="dtTable">
			<tr>
			    <th>Date Type</th>
			    <th>Date</th>

			  </tr>
			  <c:forEach  items="${contact.dates}" var="dt" >

               <td><input type="text" name = "addrType" value="${dt.dtType}"/></td>
               <td><input type="text" name = "addr" value="${dt.dateVal}"/></td>

			  </c:forEach>
			  
			</table>
			<button onclick="dtCreateFunction()">Add Date</button>
			<input type="submit" value = "Update">
		  </form:form>
		        
	</div>					
     				<br><br>
					<form action="/ContactApp" method = "post" align = "center"> 
					<input type="submit" value="Home">
					</form>
     </body>
</html>