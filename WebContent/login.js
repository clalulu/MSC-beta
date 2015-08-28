$(document).ready(function () {
	loginFunct();
});


function loginFunct() {
	$('#submit_btn').click(function(){
		var username = $('#username').value;
		var password = $('#password').value;
			 
		/*var data = "username = "+username+"&password = "+password;
		var json = $.getJSON('./Login.action',data, function(username){
				 if(user.Out =="success"){
					 redirect
				 } 
				 else{
					 $('#errorLogin').append("username o password errati, riprovare");
				 }
		 });*/
		$('#errorLogin').html("username: " + username + ", password: " + password);
	});
}



