<!DOCTYPE html>

<head>
	<link rel = "stylesheet" href = "resources/style.css">	
</head>
<body onload = "getBoardValues()">
	<video autoplay muted loop id="myVideo">
	  <source src="resources/Images/test.mp4" type="video/mp4" id = "myVideo">
	</video>

	
	
	<div id = "topbar">

		<img src="resources/Images/chess-icon.png" class = "left icon" >
		<h2 class = "left homeoptions" >Online Chess</h2>
		
		
		<div id = "nav2">
			<h2 class = "right homeoptions" onclick = "quitGame()">Quit</h2>
		</div>
	</div>
	
	
	<div id = "outerboard">
		<div class = "innerboard" onclick = "move(this.id)">
		<p class = "playernames"></p>
			<div class = "square odd" id = "00"  >
			</div >
			<div class = "square even" id = "01">
			</div >
			<div class = "square odd" id = "02" >
			</div >
			<div class = "square even" id = "03">
			</div >
			<div class = "square odd"  id = "04" >
			</div >
			<div class = "square even" id = "05" >
			</div >
			<div class = "square odd" id = "06" >
			</div >
			<div class = "square even" id = "07">
			</div >
			

			<div class = "square even" id = "10">
			</div >
			<div class = "square odd"  id = "11">
			</div >
			<div class = "square even" id = "12">
			</div >
			<div class = "square odd"  id = "13">
			</div >
			<div class = "square even" id = "14">
			</div >
			<div class = "square odd"  id = "15">
			</div >
			<div class = "square even" id = "16">
			</div >
			<div class = "square odd"  id = "17">
			</div >
			
			
			<div class = "square odd" id = "20">
			</div >
			<div class = "square even" id = "21">
			</div >
			<div class = "square odd" id = "22" >
			</div >
			<div class = "square even" id = "23">
			</div >
			<div class = "square odd"  id = "24" >
			</div >
			<div class = "square even" id = "25" >
			</div >
			<div class = "square odd" id = "26" >
			</div >
			<div class = "square even" id = "27">
			</div >
			

			<div class = "square even" id = "30">
			</div >
			<div class = "square odd"  id = "31">
			</div >
			<div class = "square even" id = "32">
			</div >
			<div class = "square odd"  id = "33">
			</div >
			<div class = "square even" id = "34">
			</div >
			<div class = "square odd"  id = "35">
			</div >
			<div class = "square even" id = "36">
			</div >
			<div class = "square odd"  id = "37">
			</div >
			
			
			<div class = "square odd" id = "40">
			</div >
			<div class = "square even" id = "41">
			</div >
			<div class = "square odd" id = "42" >
			</div >
			<div class = "square even" id = "43">
			</div >
			<div class = "square odd"  id = "44" >
			</div >
			<div class = "square even" id = "45" >
			</div >
			<div class = "square odd" id = "46" >
			</div >
			<div class = "square even" id = "47">
			</div >
			

			<div class = "square even" id = "50">
			</div >
			<div class = "square odd"  id = "51">
			</div >
			<div class = "square even" id = "52">
			</div >
			<div class = "square odd"  id = "53">
			</div >
			<div class = "square even" id = "54">
			</div >
			<div class = "square odd"  id = "55">
			</div >
			<div class = "square even" id = "56">
			</div >
			<div class = "square odd"  id = "57">
			</div>
			
			<div class = "square odd" id = "60" >
			</div >
			<div class = "square even" id = "61">
			</div >
			<div class = "square odd" id = "62" >
			</div >
			<div class = "square even" id = "63">
			</div >
			<div class = "square odd"  id = "64" >
			</div >
			<div class = "square even" id = "65" >
			</div >
			<div class = "square odd" id = "66" >
			</div >
			<div class = "square even" id = "67">
			</div >
			

			<div class = "square even" id = "70">
			</div >
			<div class = "square odd"  id = "71">
			</div >
			<div class = "square even" id = "72">
			</div >
			<div class = "square odd"  id = "73">
			</div >
			<div class = "square even" id = "74">
			</div >
			<div class = "square odd"  id = "75">
			</div >
			<div class = "square even" id = "76">
			</div >
			<div class = "square odd"  id = "77">
			</div>
		</div>
		
	</div>
	<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
	<% if (session.getAttribute("gametype").equals("PlaySelf")){ %>
		 <script src="resources/PlaySelf.js" type="text/javascript">
      </script>
	<%}
	else{
	%>
	 <script src="resources/PlayOpponent.js" type="text/javascript">
      </script>
      <%} %>
      
	 <script src="resources/game.js" type="text/javascript">
      </script>
</body>