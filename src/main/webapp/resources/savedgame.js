var index = 0;

function getBoardState(){
	var request = $.ajax({	
		  url: "LoadMoves",
		  type: "post",
		  data: {
			  index : index
		  }
		});
	
	request.done(function(msg) {
			loadBoard(msg);
		});
	
}

function loadBoard(jsonString){
	var cell;
	var json = JSON.parse(jsonString);
	
	 
	
	for (var entry in json){
		 if(json.hasOwnProperty(entry)){
				
			
			 cell = document.getElementById(entry);
			 
			 switch(json[entry]){
			 	case "WB":
			 		cell.style.backgroundImage = "url('resources/Chess/white-bishop.png')";
			 		break;
			 	case "WR":
			 		cell.style.backgroundImage = "url('resources/Chess/white-rook.png')";
			 		break;
			 	case "WN":
			 		cell.style.backgroundImage = "url('resources/Chess/white-knight.png')";
			 		break;
			 	case "WK":
			 		cell.style.backgroundImage = "url('resources/Chess/white-king.png')";
			 		break;
			 	case "WQ":
			 		cell.style.backgroundImage = "url('resources/Chess/white-queen.png')";
			 		break;
			 	case "WP":
			 		cell.style.backgroundImage = "url('resources/Chess/white-pawn.png')";
			 		break;
			 		
			 		
			 	case "BB":
			 		cell.style.backgroundImage = "url('resources/Chess/black-bishop.png')";
			 		break;
			 	case "BR":
			 		cell.style.backgroundImage = "url('resources/Chess/black-rook.png')";
			 		break;
			 	case "BN":
			 		cell.style.backgroundImage = "url('resources/Chess/black-knight.png')";
			 		break;
			 	case "BK":
			 		cell.style.backgroundImage = "url('resources/Chess/black-king.png')";
			 		break;
			 	case "BQ":
			 		cell.style.backgroundImage = "url('resources/Chess/black-queen.png')";
			 		break;
			 	case "BP":
			 		cell.style.backgroundImage = "url('resources/Chess/black-pawn.png')";
			 		break;	
			 	case true:
			 	case false:
			 		break;
			 	default:
			 		cell.style.backgroundImage = "none";
			 		break;
			 }
		 }
	}
	
		
}

function next(){
	
	++index;
	getBoardState();
}

function goBack(){
	location = "LoadGame";
}