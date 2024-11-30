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
	
	firstPlayerDisplay();
	
	drawCardBtn.disabled = 'true';
	submitBtn.disabled = true;
	oppPlayer.style.display = "none";
	
	drawCardBtn.addEventListener("click", (e) => {
		e.preventDefault();
		drawCard();
	});
	
	placeBtn.addEventListener("click", (e) => {
		e.preventDefault();
		placeCard();
	});

	submitBtn.addEventListener("click", (e) => {
		e.preventDefault();
		submitCard();
	});
	
	resultBtn.addEventListener("click", (e) => {
		e.preventDefault();
		seeResult();
	});	
	
	if(username.value == 'SDA') {
		document.getElementById('usernamePrint').style.color = 'red';
		document.getElementById('usernamePrint').textContent += "👑";
	}
	
});
	

function drawCard() {
	var draw = {
		"roomId" : room.value,
		"uid" : uid.value
	}
	
	$.ajax({
		type : 'POST',
		datatype: 'json',
		url: "/drawCard",
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
		url: "/submit",
		data: input,
		cache: false,
		timeout: 600000,
		success: function(data) {
			player = data.oppPlayer;
			winner = data.winnerUser;
			cardCount = data.cardCount;
			drawCardBtn.disabled = false;
			oppPlayer.style.display = "inline";
			fillPlayers(player, '2');
			count.textContent = "count: " + cardCount;
			firstPlayer.textContent = winner;
			sendSubmitMsg();
			resultBtn.disabled = 'true';
			submitBtn.disabled = 'true';
			printResult(winner, cardCount);
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
		url: "/result",
		data: input,
		cache: false,
		timeout: 600000,
		success: function(data) {
			player = data.oppPlayer;
			winner = data.winnerUser;
			cardCount = data.cardCount;
			drawCardBtn.disabled = false;
			oppPlayer.style.display = "inline";
			fillPlayers(player, '2');
			count.textContent = "count: " + cardCount;
			firstPlayer.textContent = winner;
			placeBtn.disabled = 'true';
			resultBtn.disabled = 'true';
			submitBtn.disabled = 'true';
			printResult(winner, cardCount);
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

function printResult(winner, cardCount) {
	if(winner == uid.value) {
		displayResult.textContent = 'New Card Added!';
	} else {
		displayResult.textContent = 'You lost a card!';
	}
	if(cardCount == 0) {
		final.textContent = 'YOU LOST THE GAME!';
	} else if(cardCount == 8) {
		final.textContent = 'YOU WON THE GAME!';
	}	
}