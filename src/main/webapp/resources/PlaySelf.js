var oddclick = false;
var cellid;
var oldcolor;

var isturn;

var oddclickid;

var board;
var gameset;

var gameid;
var game;
var whitesturn = true;
var data;

var from;
var image;
var to;

var cellfrom;
var cellto;

var ready = true;

var gameended = false;


function makeMove(move){
	
	ready = false;
	
	from = cellfrom;
	to = cellto;
	
	var request = $.ajax({
		  url: "PlaySelfMakeMove",
		  type: "post",
		  data: {
			  move : move
			  }
		});
	
	request.done(function(msg) {
		ready = true;
		var json = JSON.parse(msg);
		
		console.log(json["valid"]);
		if (json["valid"]){
			//if (json["castled"])
				getBoardValues();
			/*else{
				image = from.style.backgroundImage;
				from.style.backgroundImage = "none";
				to.style.backgroundImage = image;
			}*/
		}
		console.log((json["gameended"]));
		if(json["gameended"]){
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




function move(e){
	
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
		makeMove(move);
	}
	oddclick = !oddclick;
}

