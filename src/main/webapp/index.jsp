<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-lancer</title>
	<link rel="shortcut icon" href="resources/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="resources/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="resources/css/main.css">
  <script src="resources/js/modernizr-2.7.1.js"></script>
</head>
<body class="home">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="index.html"><img src="resources/images/logo.png" alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a href="#">Home</a></li>
					<li><a class="btn" href="signin.htm">SIGN IN / SIGN UP</a></li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
				<h1 class="lead">WELCOME TO ELANCRR</h1>
				<p class="tagline">A place where people get their work done.</p>
				<p><a href="adduser.htm?type=client" class="btn btn-default btn-lg" role="button">I want to Hire!</a> 
					<a href="adduser.htm?type=freelancer" class="btn btn-action btn-lg" role="button">I want to Work!</a></p>
			</div>
		</div>
	</header>
	<!-- /Header -->

	<!-- Intro -->
	<div class="container text-center">
		<br> <br>
		<h2 class="thin">
			Opportunity is missed by most people because it is dressed in overalls and looks 
			like work. <br/></h2><br/>
			<b> - Thomas A. Edison</b>
		</p>
	</div>
	<!-- /Intro-->
	
	<!-- Social links. @TODO: replace by link/instructions in template -->
	<section id="social">
		<div class="container">
			<div class="wrapper clearfix">
				<!-- AddThis Button BEGIN -->
				<div class="addthis_toolbox addthis_default_style">
				<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
				<a class="addthis_button_tweet"></a>
				<a class="addthis_button_linkedin_counter"></a>
				<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
				</div>
				<!-- AddThis Button END -->
			</div>
		</div>
	</section>
	<!-- /social links -->


	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>+857 36 MANSI<br>
								<a href="mailto:#">elancrr@gmail.com</a><br>
								<br>
								Northeastern University.
							</p>	
						</div>
					</div>

					<div class="col-md-3 widget">
						
					</div>

					<div class="col-md-6 widget">
							<h3 class="widget-title">Follow me</h3>
							<div class="widget-body">
							<p class="follow-me-icons">
								<a href="https://twitter.com/MitigatedMyths"><i class="fa fa-twitter fa-2"></i></a>
								<a href="https://github.com/mansijain01"><i class="fa fa-github fa-2"></i></a>
								<a href="http://www.facebook.com/"><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div></div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2014, Mansi Jain. Designed by <a href="http://gettemplate.com/" rel="designer">gettemplate</a> 
							</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>

	</footer>	

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="resources/js/headroom.min.js"></script>
	<script src="resources/js/jQuery.headroom.min.js"></script>
	<script src="resources/js/template.js"></script>
</body>
</html>