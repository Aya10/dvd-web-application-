<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="models.Dvd"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Aya's DVD Homepage</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<!-- <link href="css/site.css" rel="stylesheet"/> -->
</head>
<body>
	<div class="container">

		<div class="row" class="row justify-content-md-center">
			<div class="col-md-12">


				<h4 class = "text-centre" >
					all DVDs <i class="fa fa-film" aria-hidden="true"></i>
				</h4>
				<%
					@SuppressWarnings("unchecked")
					ArrayList<Dvd> dvdArray = (ArrayList<Dvd>) request.getAttribute("dvds");
				%>

				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Genre</th>
							<th>Year</th>
						<tr>
					</thead>
					<tbody>
						<%
							for (Dvd dvd : dvdArray) {
								String buttons = "<td> <a class=\"btn btn-danger\" href=\"./DeleteDvdServlet?id=" + dvd.getId()
										+ "\"><i class=\"fa fa-trash\"></i><a/> <a class=\"btn btn-warning\" href=\"./UpdateDVDServlet?id="
										+ dvd.getId() + "\"><i class=\"fa fa-pencil-square-o\"></i><a/></td>";
								out.print("<tr><td>" + dvd.getTitle() + "</td><td>" + dvd.getGenre() + "</td><td>" + dvd.getYear()
										+ " </td> <td> <td> </td></tr>");
								if ((boolean) session.getAttribute("loggedin")) {
									out.print(buttons);
								}
								out.print("</td></tr>");
							}
						%>
					</tbody>

				</table>
			</div>
		</div>
		<a class="btn btn-primary" href="insert.html"><i
			class="fa fa-plus" aria-hidden="true"></i> new dvd</a>


	</div>
</body>
</html>