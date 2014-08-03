<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome to Cobra Chat</title>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
	</head>
	<body>
		<div class="container">
		
			<div class="jumbotron text-center">
				<h1><span class="fa fa-lock"></span> Cobra Chat Authentication</h1>
		
				<p>Login or Register with:</p>
		
				<a href="/auth/facebook" class="btn btn-primary"><span class="fa fa-facebook"></span> Facebook</a>
				<a href="/auth/twitter" class="btn btn-info"><span class="fa fa-twitter"></span> Twitter</a>
				<a href="/auth/google" class="btn btn-danger"><span class="fa fa-google-plus"></span> Google+</a>
			</div>
		</div>
	</body>
</html>