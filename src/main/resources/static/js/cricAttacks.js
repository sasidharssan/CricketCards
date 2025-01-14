const exitPage = (event) => {
	event.preventDefault();
	event.returnValue = true;
};
document.addEventListener('DOMContentLoaded', function() {
	username = document.getElementById('username');
	room = document.getElementById('roomId');
	uid = document.getElementById('userId'); 
	firstPlayer = document.getElementById("firstPlayer");
	drawCardBtn = document.getElementById("drawCardBtn");
	placeBtn = document.getElementById("placeBtn");
	resultBtn = document.getElementById("resultBtn");
	submitBtn = document.getElementById("submitBtn");
	drawCardBlock = document.getElementById("drawCardBlock");
	placeBlock = document.getElementById("placeBlock");
	resultBlock = document.getElementById("resultBlock");
	submitBlock = document.getElementById("submitBlock");
	oppPlayer = document.getElementById("oppPlayer");
	option = document.getElementById("option");
	count = document.getElementById("count");
	displayResult = document.getElementById("displayResult");
	final = document.getElementById("final");
	chatForm = document.getElementById("chatForm");
	chatBtn = document.getElementById("chatBtn");
	chatPop = document.getElementById("chat-popup");
	closeChatBtn = document.getElementById("closeChatBtn");
	clickSound = document.getElementById("clickSound");
	btnBadge = document.getElementById("btnBadge");
	
	firstPlayerDisplay();
	
	drawCardBtn.disabled = 'true';
	submitBtn.disabled = true;
	oppPlayer.style.display = "none";
	
	drawCardBtn.addEventListener("click", (e) => {
		e.preventDefault();
		clickSound.play();
		drawCard();
	});
	
	placeBtn.addEventListener("click", (e) => {
		e.preventDefault();
		clickSound.play();
		placeCard();
	});

	submitBtn.addEventListener("click", (e) => {
		e.preventDefault();
		clickSound.play();
		submitCard();
	});
	chatPop
	resultBtn.addEventListener("click", (e) => {
		e.preventDefault();
		clickSound.play();
		seeResult();
	});	
	
	if(username.value == 'SDA') {
		document.getElementById('usernamePrint').style.color = 'red';
		document.getElementById('usernamePrint').textContent += "👑";
	}
	
	chatPop.style.display ="none";

	chatBtn.addEventListener("click", (e) => {
		if(chatPop.style.display == "none") {
			chatPop.style.display = "block";
			btnBadge.style.display = "none";
			btnBadge.textContent = '0';
		} else {
			chatPop.style.display = "none";
		}
		clickSound.play();
	});	
	
	closeChatBtn.addEventListener("click", (e) => {
		chatPop.style.display = "none";
		clickSound.play();
	});
	
	chatForm.addEventListener("submit", (e) => {
		e.preventDefault();
		document.getElementById("chats").innerHTML += "<p><span style='color:purple;'>"
				+ username.value + "</span>: " + chatForm.elements["0"].value + "<br>";
		sendChatMsg();
	});

});
	

function drawCard() {
	var draw = {
		"roomId" : room.value,
		"uid" : uid.value
	}
	
	$.ajax({
		type : 'POST',
		datatype: 'json',
		url: "/cricAttacks/drawCard",
		data: draw,
		cache: false,
		timeout: 600000,
		success: function(data) {
			player1 = data;
			drawCardBtn.disabled = true;
			oppPlayer.style.display = "none";
			fillPlayers(player1, '1');
			displayResult.textContent = '';
			placeBtn.disabled = false;
			firstPlayerDisplay();
		}
	})
}

function placeCard() {
	sendPlacedMsg();
}

function submitCard() {
	var input = {
		"roomId" : room.value,
		"uid" : uid.value,
		"option" : option.value
	}
	
	$.ajax({
		type : 'POST',
		datatype: 'json',
		url: "/cricAttacks/submit",
		data: input,
		cache: false,
		timeout: 600000,
		success: function(data) {
			player = data.oppPlayer;
			winner = data.winnerUser;
			cardCount = data.cardCount;
			finalResult = data.finalResult;
			drawCardBtn.disabled = false;
			oppPlayer.style.display = "inline";
			gameOption = option.value;
			fillPlayers(player, '2');
			count.textContent = "count: " + cardCount;
			firstPlayer.textContent = winner;
			sendSubmitMsg();
			resultBtn.disabled = 'true';
			submitBtn.disabled = 'true';
			printResult(winner, finalResult, gameOption);
		}
	})
}

function seeResult() {
	var input = {
		"roomId" : room.value,
		"uid" : uid.value
	}
	
	$.ajax({
		type : 'POST',
		datatype: 'json',
		url: "/cricAttacks/result",
		data: input,
		cache: false,
		timeout: 600000,
		success: function(data) {
			player = data.oppPlayer;
			winner = data.winnerUser;
			cardCount = data.cardCount;
			finalResult = data.finalResult;
			gameOption = data.option;
			drawCardBtn.disabled = false;
			oppPlayer.style.display = "inline";
			fillPlayers(player, '2');
			count.textContent = "count: " + cardCount;
			firstPlayer.textContent = winner;
			placeBtn.disabled = 'true';
			resultBtn.disabled = 'true';
			submitBtn.disabled = 'true';
			printResult(winner, finalResult, gameOption);
		}
	})
}

function fillPlayers(player, number) {
	document.getElementById("playerName" + number).textContent = player.name;
	document.getElementById("designation" + number).textContent = player.designation;
	document.getElementById("matches" + number).textContent = player.matches;
	document.getElementById("runs" + number).textContent = player.runs;
	document.getElementById("strikeRate" + number).textContent = player.strikeRate;
	document.getElementById("ballsFaced" + number).textContent = player.ballsFaced;
	document.getElementById("highest" + number).textContent = player.highest;
	document.getElementById("batAverage" + number).textContent = player.batAverage;
	document.getElementById("centuries" + number).textContent = player.centuries;
	document.getElementById("wickets" + number).textContent = player.wickets;
	document.getElementById("best" + number).textContent = player.best;
	document.getElementById("ballsBowled" + number).textContent = player.ballsBowled;
	document.getElementById("economy" + number).textContent = player.economy;
	document.getElementById("bowlAverage" + number).textContent = player.bowlAverage;
	document.getElementById("fifers" + number).textContent = player.fifers;
}

function firstPlayerDisplay() {
	if(firstPlayer.textContent == uid.value) {
		submitBlock.style.display = "block";
		placeBlock.style.display = "none";
		resultBlock.style.display = "none";
	} else {
		submitBlock.style.display = "none";
		placeBlock.style.display = "block";
		resultBlock.style.display = "block";
		resultBtn.disabled = true;
	}
}

function printResult(winner, finalResult, gameOption) {
	if(winner == uid.value) {
		displayResult.textContent = 'New Card Added! ' + gameOption + ' is chosen';
	} else {
		displayResult.textContent = 'You lost a card! ' + gameOption + ' is chosen';
	}
	if(finalResult == "LOST") {
		final.textContent = 'YOU LOST THE GAME! ' + gameOption + ' is chosen';
	} else if(finalResult == "WON") {
		final.textContent = 'YOU WON THE GAME! ' + gameOption + ' is chosen';
	}	
}