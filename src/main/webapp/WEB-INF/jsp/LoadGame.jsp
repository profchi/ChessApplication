<%@page import = "com.example.demo.model.SavedGame" %>
<%@page import = "java.util.List" %>

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
					<h2 class = "homeoptionshidden" onclick = "menu()">Game</h2>
					<h2 class = "homeoptionshidden" onclick = "gotoHome()">Logout</h2>
				</div>
		</div>
		
		<div id = "nav">
			<h2 class = "right homeoptions" onclick = "gotoHome()">Logout</h2>
			<h2 class = "right homeoptions" onclick = "menu()">Game</h2>
		</div>
		
		
		
	</div>
	
	<div onclick = "load(this.id)">
	<p></p>
	<%List<SavedGame> savedGame = (List<SavedGame>)session.getAttribute("savedgames"); %>
	
	<%if (savedGame.isEmpty()){ %>
		<div  id = "0" class = "menubutton" >No Game Stored</div>
	<%}else { %>
		<%int i = 1; %>
		
		
		<%for (SavedGame game : savedGame) {%>
		
			
			<button id = <%=game.getId() + "" %>  class = "menubutton" > 
			<%=game.getPlayer1().getUsername() %> VS <%=game.getPlayer2().getUsername() %>       playedAs: <%=game.getPlayedAs() %>
			 </button>
			
			<%++i; %>
		<%} %>
	<%} %>
	</div>>
		<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
	 <script src="resources/script.js">
      </script>
</body>