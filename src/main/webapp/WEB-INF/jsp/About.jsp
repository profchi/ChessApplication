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
	
	<div >
		<div  name = "text" id = "about">
		<p> <strong> About </strong> </p>
				
		This is an online chess game. <br>
		
		<p> </p>
		<p> <strong> How to Play</strong></p>
		You have to be registered to setup a game. Enter the login page to register
		and once you have registered, You can log in and then setup a game.
		
		You have the option of playing online with a random player also logged in or playing with a specific
		user whose username is known. You also have the option of playing locally as both black and white. <br>
		
		When playing against an opponent you would be prompted to chose what color you would like to play as,
		
		Selecting <strong> Okay </strong> means you play as white. Selecting <strong> Cancel</strong> means playing 
		as black so ensure your opponent selects the opposite color when playing with a specific user.
		
		To play on same computer with two users. Different browsers have to be used.
		
		You have to log out and back after a game if you want tot play again
		
		</div> 
	</div>
	
	
	<h2 class = "left homeoptions" id = "contact" onclick = "gotoContactUs()">Contact Us</h2>
	<img src="resources/Images/facebook-icon.png" class = "right homeoptions" id = "facebook" >
	<img src="resources/Images/instagram-icon.png" class = "right homeoptions" id = "instagram">
	 <script src="resources/script.js">
      </script>
</body>