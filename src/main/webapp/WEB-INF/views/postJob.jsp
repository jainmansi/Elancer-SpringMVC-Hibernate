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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="resources/js/html5shiv.js"></script>
	<script src="resources/js/respond.min.js"></script>
	
	<![endif]-->
</head>

<script>
	function added() {
		$.ajax({
			type : "GET",
			url : "addCategory",
			success : function(data) {
				$('#submitted').html(data);
			},

		});
		return false;
	}
</script>

<body>

	<form:form action="addJob.htm" commandName="job" modelAttribute="job"
		method="post">
		<div class="top-margin">
			<label>Title:<span class="text-danger">*</span></label>
			<form:input path="jobTitle" pattern="^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$" title="Enter a valid title" required="true" class="form-control" />
			<form:errors path="jobTitle" />
		</div>
		<div class="top-margin">
			<label>Description:<span class="text-danger">*</span></label>
			<form:textarea path="jobDescription" pattern="^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$" title="Enter valid input"  required="true"  class="form-control" />
			<form:errors path="jobDescription" />
		</div>
		<div class="top-margin">
			<label>Salary:<span class="text-danger">*</span></label>
			<form:input path="pay" pattern="[0-9]*" title="Salary cannot be null"  required="true"  class="form-control" />
			<form:errors path="pay" />
		</div>
		<div class="top-margin">
			<label>Select A Category:<span class="text-danger">*</span></label> <select
				name="catId" class="form-control">
				<c:forEach var="category" items="${list}" varStatus="status">
					<option value="${category.categoryId}">${category.categoryName}</option>
				</c:forEach>
			</select>

		</div>

		<hr>

		<div class="row">
			<div class="col-lg-8"></div>
			<div class="col-lg-4 text-right">
				<button class="btn btn-action" type="submit">Register</button>
			</div>
		</div>
	</form:form>

</body>
</html>