<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Elancrr - where work is get done!</title>

<link rel="shortcut icon" href="resources/images/gt_favicon.png">

<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">

<!-- Custom styles for our template -->
<link rel="stylesheet" href="resources/css/bootstrap-theme.css"
	media="screen">
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
				<th>Job Category</th>
				<th>Salary</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="job" items="${myJobList}" varStatus="status">
				<tr>
					<td><c:out value=" ${job.jobTitle}" /><br></td>
					<td><c:out value=" ${job.jobDescription}" /><br></td>
					<td><c:out value=" ${job.jobCategory.categoryName}" /><br></td>
					<td><c:out value=" ${job.pay}" /><br></td>
					<td><a style="text-decoration: none"
						href="jobStatus.htm?id=${job.jobId}"><h4>
								<span class="label label-warning">View Status</span>
							</h4></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>