<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.Dvd"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		Dvd dvd = (Dvd) request.getAttribute("dvd");
	%>
	<form action="./UpdateDvdServlet" method="POST">
		<h5>Update Dvd</h5>
		<input type="hidden" name="id" value="<%=dvd.getId()%>"> 
		<input type="text" name="title" placeholder="Title"value="<%=dvd.getTitle()%>">
		<input type="text" name="genre"placeholder="Genre" value="<%=dvd.getGenre()%>"> 
		<input type="number" name="year" placeholder="Year"value="<%=dvd.getYear()%>">
		<button type="submit">Update</button>

	</form>

</body>
</html>