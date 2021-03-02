var oddclick = false;
var cellid;
var oldcolor;

var isturn;

var from;
var image;
var to;

var oddclickid;

var board;
var gameset;

var gameid;
var game;
var whitesturn = true;
var data;

var cellfrom;
var cellto;

var ready = true;

var turn;

var gameended = false;

function makemove(move){
	
	from = cellfrom;
	to = cellto;
	
	var request = $.ajax({
		  url: "PlayOpponentMakeMove",
		  type: "post",
		  data: {
			  move : move
			  }
		});
	
	request.done(function(msg) {
		
		var json = JSON.parse(msg);
		
		console.log(json["valid"]);
		if (json["valid"]){
			ready = false;
			getBoardValues();
		}
		console.log((json["gameended"]));
		if(json["gameended"]){
			stopReload = json["gameended"];
			console.log((json["declareWinner"]));
			switch (json["declareWinner"]){
				case "white":
					alert ("White Wins");
					saveGame();
					location = "menu";
					break;
				case "draw":
					alert ("Draw");
					saveGame();
					location = "menu";
					break;
				case "black":
					alert ("Black Wins");
					saveGame();
					location = "menu";
					break;
				default:
					break;
				
			} 
		}
		
	}); 
	
}

function getmove(){
	
	var request = $.ajax({
		  url: "PlayOpponentGetMove",
		  type: "post"
		});
	
request.done(function(msg) {
		
		var json = JSON.parse(msg);
		
		console.log(msg);
		
		var moves = json["move"].split("-");
		
		from = document.getElementById(moves[0]);
		to = document.getElementById(moves[1]);
		
		ready = true;
		
		getBoardValues();
				
		if(json["gameended"]){
			switch (json["declareWinner"]){
				case "white":
					alert ("White Wins");
					saveGame();
					location = "menu";
					break;
				case "draw":
					alert ("Draw");
					saveGame();
					location = "menu";
					break;
				case "black":
					alert ("Black Wins");
					saveGame();
					location = "menu";
					break;
				case "abandoned":
					alert("Game abandoned, You win");
					saveGame();
					location = "menu";
					break;
				default:
					break;
				
			} 
		}
		
	}); 
	
}


function move(e){
	console.log(ready);
	
	if(!ready)
		return;
	
	e = e || window.event;
	e = e.target || e.srcElement;
	
	
	
	if (!oddclick){
		cellid = e.id;
		cellfrom = document.getElementById(cellid);
		oldcolor = cellfrom.style.background;
		cellfrom.style.backgroundColor = "red";
		oddclickid = cellid;
		console.log(ready);
	}
	else{
		cellfrom = document.getElementById(cellid);
		cellto = document.getElementById(e.id);
		
		cellfrom.style.backgroundColor = oldcolor;
		let move = "" + oddclickid + e.id;
		
		makemove(move);
	}
	oddclick = !oddclick;
}
