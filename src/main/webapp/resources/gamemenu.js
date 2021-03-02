var xhr;

function playgame(){
	location = "PlayGame";
}

function playRandomUser(){
	
	//sessionStorage.setItem("gametype",1);
	
	
	let playercolor = confirm("Select Okay to play as white and cancel to play as black");
	
	var turn;
	
	if (playercolor){
		turn = "white";
		var request = $.ajax({
			  url: "PlayOpponentGameSetup",
			  type: "post",
			  data: {
				  turn : turn,
				  color : playercolor
				  }
			});
	}
	else{
		turn = "black";
		var request = $.ajax({
			  url: "PlayOpponentAcceptGame",
			  type: "post",
			  data: {
				  turn : turn,
				  color : playercolor
				  }
			});
	}
	playgame();
}


function playSpecificUser(){
	
	
	
	let playercolor = confirm("Select Okay to play as white and cancel to play as black");
	
	var name = prompt ("Enter username of opponent");
	
	sessionStorage.setItem("turn", playercolor);
	
	if (playercolor){
		turn = "white";
		var request = $.ajax({
			  url: "PlayOpponentGameSetup",
			  type: "post",
			  data: {
				  turn : turn,
				  opponent : name
				  }
			});
	}
	else{
		turn = "black";
		var request = $.ajax({
			  url: "PlayOpponentAcceptGame",
			  type: "post",
			  data: {
				  turn : turn,
				  opponent : name
				  }
			});
	}
	
	
	playgame();
}

function playSelf(){
	
	var request = $.ajax({
		  url: "PlaySelfGameSetup",
		  type: "post"
		});
	 
	location = "PlayGame"
}



function menu(){
	location = "menu";
}