<html>
<head>
<style>
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
<hi>Record deleted!!</hi><br>
<a href="/ContactApp">Home</a>

<body>
	<%
        String redirectURL = "/ContactApp";
        response.sendRedirect(redirectURL);
    %>
</body>
</html>