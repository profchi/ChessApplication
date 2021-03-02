<!DOCTYPE html>

<head>
	<link rel = "stylesheet" href = "resources/style.css">	
</head>
<body>
	<video autoplay muted loop id="myVideo">
	  <source src="resources/Images/test.mp4" type="video/mp4" id = "myVideo">
	</video>

	
	
	<div id = "topbar">

		<img src="resources/Images/chess-icon.png" class = "left icon" >
		<h2 class = "left homeoptions" onclick = "gotoHome()">Online Chess</h2>
		
		<div id = "dropdownmenu">
			<img src="resources/Images/menu.png" class = "right icon" id = "dropbtn" onclick = "dropdown()">
				<div id = "nav-menu">
					<h2 class = "homeoptionshidden" onclick = "gotoHome()">Home</h2>
					<h2 class = "homeoptionshidden" onclick = "gotoAbout()">About</h2>
					<h2 class = "homeoptionshidden" onclick = "gotoLogin()">Login</h2>
				</div>
		</div>
		
		<div id = "nav">
			<h2 class = "right homeoptions" onclick = "gotoLogin()">Login</h2>
			<h2 class = "right homeoptions" onclick = "gotoAbout()">About</h2>
			<h2 class = "right homeoptions" onclick = "gotoHome()">Home</h2>
		</div>
		
		
		
	</div>
	
	<p></p>
	
	<div id = "form">
		<form  action="./processLogin" method = "post" id = "loginform" onSubmit = "return checkformlogin()">
			<p>Username:</p> <input type = "text" name = "username" class = "inputfield" required> <br>
			<p>Password:</p> <input type = "password"  name = "password" class = "inputfield" required> <br>
			<p></p>
			<input type="submit"  value = "login" class = "button">
			<p class="error" > ${name} </p>
			
		</form>
		
		<form action="createAccount"  id = "createform" method = "post" onSubmit = "checkform()" >
			<hr>
			<p>Username:</p> <input type = "text" name = "username" class = "inputfield" required> <br>
			<p>Password:</p> <input type = "password"  name = "password" class = "inputfield" required> <br>
			<p>Confirm Password:</p> <input type = "password" name = "confirm" class = "inputfield" required> <br>
			<p>Email:</p> <input type = "text"  name = "email" class = "inputfield" required> <br>
			<p></p>
			<input type="submit" value="SignUp" class = "button">
			<p class="error" id = "mismatch" >  </p>
			<p class="error" > ${existinguser} </p>
		</form>
	</div>
		
	
	<h2 class = "left homeoptions" id = "contact" onclick = "gotoContactUs()">Contact Us</h2>
	<img src="resources/Images/facebook-icon.png" class = "right homeoptions" id = "facebook" >
	<img src="resources/Images/instagram-icon.png" class = "right homeoptions" id = "instagram">
	 <script src="resources/script.js">
      </script>
</body>