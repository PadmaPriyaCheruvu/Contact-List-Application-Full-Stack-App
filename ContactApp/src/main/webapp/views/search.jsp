<html>
<head>Search Contacts

 <style>
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
		Search Page
		<a href="/ContactApp">Home</a>
		
		<form class="form-inline" method="post" action="/ContactApp/search/${search_txt}">
		<input type="text" id="search_txt" class="form-control" placeholder="Enter text">
		<button type="submit" name="save" class="btn btn-primary">Search</button>
		</form>
		
		<c:forEach  items="${contacts}" var="contact" >

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
					
                </c:forEach>  
		
		</body>
</html>