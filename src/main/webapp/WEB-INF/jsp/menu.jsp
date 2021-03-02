<!DOCTYPE html>

<head>
	<link rel = "stylesheet" href = "resources/style.css">	
</head>
<body onload = "checkForLogin()">
	<video autoplay muted loop id="myVideo">
	  <source src="resources/Images/test.mp4" type="video/mp4" id = "myVideo">
	</video>

	
	
	<div id = "topbar">

		<img src="resources/Images/chess-icon.png" class = "left icon" >
		<h2 class = "left homeoptions" >Online Chess</h2>
		
		<div id = "dropdownmenu">
			<img src="resources/Images/menu.png" class = "right icon" id = "dropbtn" onclick = "dropdown()">
				<div id = "nav-menu">
					<h2 class = "homeoptionshidden" onclick = "menu()">Game</h2>
					<h2 class = "homeoptionshidden" onclick = "gotoHome()">Logout</h2>
				</div>
		</div>
		
		<div id = "nav">
			<h2 class = "right homeoptions" onclick = "gotoHome()">Logout</h2>
			<h2 class = "right homeoptions" onclick = "menu()">Game</h2>
		</div>
		
		
		
	</div>
	
	<p></p>
	
	<div>
		<button class = "menubutton" onclick = "play()"> Play </button>
		<button class = "menubutton" onclick = "loadSavedGame()"> Load Game </button>
		<button class = "menubutton" onclick = "gotoHome()"> Log out </button>

	</div>
	
	

	 <script src="resources/script.js">
      </script>
</body>