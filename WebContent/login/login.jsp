<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="X-UA-Compatible" content="IE=edge">
		<title>Login</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<style rel="stylesheet" media="all">
			body { background-color: #dddddd; }

			.formX {
				max-width: 600px;
				border: 1px solid #cccccc;
				padding: 20px;
				border-radius: 6px;
				margin: 20px auto;
				background-color: #fff;
			}
			
			.formX form {
				margin-bottom: 10px;
			}
		</style>
	</head>
	<body>

		<div class="container">
			<h1 class="text-center">Login Form</h1>
			<div class="row formX">
				<form method="post">
					<div class="form-group">
						<label for="username">Username</label>
						<input type="text" id="username" name="username" class="form-control" placeholder="Inserisci user">
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" id="password" name="password" class="form-control" placeholder="Inserisci pass">
					</div>

					<input type="button" id="submit_btn" class="btn btn-primary" name="submit" value="submit">
				</form>
				<div id="log" class="text-center"></div>
			</div>
			<div class="small text-center">Sir Xiradorn Form</div>
		</div>

		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.4.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				loginFunct();
			});

			function loginFunct() {
				$('#submit_btn').on("click", function(e){
					e.preventDefault();
					
					var username = $('#username').val();
					var password = $('#password').val();

					$.ajax({
						url: "loginRegisterUser.action",
						type: "post",
						data: "username=" + username + "&password=" + password,
						dataType: "json",
						success: function(result){
							if(result.response == "success" && result.response != null) {
								$('#log').html("<div class='alert alert-success'><span class='glyphicon glyphicon-ok-sign' aria-hidden='true'></span> Ok Il log è avvenuto con successo</div>");
							} else {
								$('#log').html("<div class='alert alert-danger'><span class='glyphicon glyphicon-remove-sign' aria-hidden='true'></span> Attenzione !!! Campi non corretti</div>");
							}
				    	},
					});
				});
			};
		</script>
	</body>
</html>
