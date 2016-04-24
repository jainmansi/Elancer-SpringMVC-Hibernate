<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>Elancrr - where work is get done!</title>

	<link rel="shortcut icon" href="resources/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="resources/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="resources/css/main.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="resources/js/html5shiv.js"></script>
	<script src="resources/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	
						<table class="table table-striped">
						<thead>
							<tr>
								<th>Job Title</th>
								<th>Job Description</th>
								<th>Salary</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="application" items="${applications}" varStatus="status">
							<tr>
           						<td> <c:out value=" ${application.job.jobTitle}"/><br></td>
           						<td> <c:out value=" ${application.job.jobDescription}"/><br></td>
           						<td> <c:out value=" ${application.job.pay}"/><br></td>  
           						<td> <c:out value=" ${application.status}"/><br></td>       						
           					</tr>
                        </c:forEach> 
                        </tbody>
						</table>

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="resources/js/headroom.min.js"></script>
	<script src="resources/js/jQuery.headroom.min.js"></script>
	<script src="resources/js/template.js"></script>
</body>
</html>