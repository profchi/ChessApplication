console.log(sessionStorage.getItem("username"));

function gotoHome(){
	
	location = "index";
}

function gotoLogin(){
	location = "login";
}

function gotoAbout(){
	location = "About";
}

function gotoContactUs(){
	location = "ContactUs";
}

function menu(){
	location = "menu";
}

function play(){
	location = "gamemenu";
}

function playgame(){
	location = "PlayGame";
}


function dropdown(){
	document.getElementById("nav-menu").display = block;
}

function checkform(){
	var form = document.getElementById("createform");
	
	var match = document.getElementById("mismatch");
	
	if(form.password.value != form.confirm.value ){
		match.innerHTML = "Passwords do not match";
		event.preventDefault();
	}
}

function checkformlogin(){
	var form = document.getElementById("loginform");
	
	sessionStorage.setItem("username",form.username.value);
}

function checkForLogin(){

}

function loadSavedGame(){
	location = "LoadGame";
}

function load(e){
	
	e = e || window.event;
	e = e.target || e.srcElement;
	
	var threshold = 1;
	
	var value = parseInt(e.id);

	if (value >= threshold){
		$.ajax({
			  url: "LoadSpecificGame",
			  type: "post",
			  data: {
				  gameid : e.id
				  }
			});
		location = "savedgame";
	}
}

