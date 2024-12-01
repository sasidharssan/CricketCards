const hostAddress = location.host;
const url = "ws://"+ hostAddress +"/spring-boot-cardgame";
const preUrl = "/user/";
const signalUrl = "/topic/signal";
const appSignal = "/app/signal";
var loggedIn = 0;


const client = new StompJs.Client({
    brokerURL: url,
	forceBinaryWSFrames: true,
	appendMissingNULLonIncoming: true
});

class User {
	userId;
	username;
	room;
	loggedIn;
	cards;
	
	constructor(userId, username, room) {
		this.userId = userId;
		this.username = username;
		this.room = room;
		this.cards = null;
		this.loggedIn = 1;
	}
};

class Message {
	user;
	signal;
	
	constructor(user, signal) {
		this.user = user;
		this.signal= signal;
	}
};

var user;
var message;

document.addEventListener("DOMContentLoaded", function() {
	username = document.getElementById('username');
	roomId = document.getElementById('roomId');
	uid = document.getElementById('userId');
	connectButton = document.getElementById('connect');
	beforeConnect = document.getElementById('beforeConnect');
	afterConnect = document.getElementById('afterConnect');
	
	if(document.getElementById("firstUser").textContent == "n")
		document.getElementById("waitMsg").style.display = "none";
	
});

window.addEventListener("beforeunload" , () => {
	disconnect();
});

client.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    user = new User(uid.value, username.value, roomId.value);
    
    client.subscribe(preUrl + user.userId + signalUrl, (message) => {
    	showResult(JSON.parse(message.body));
    });

	if(loggedIn == 0) {
		if(document.getElementById("firstUser").textContent == "n")
			sendConnectMsg();
	}
}

async function connect() {
	client.activate();
	console.log('connected');
}

function disconnect() {
	if(loggedIn == 1) {
		sendDisconnectMsg();
	}
	client.deactivate();
	console.log('disconnected');	
}

function showResult(message) {
	if(message.signal == 'JOINED') {
		document.getElementById('startGame').disabled = false;
		document.getElementById('waitMsg').textContent = message.user.username 
			+ ' has joined';
	}
	if(message.signal == 'SUBMITTED') {
		document.getElementById('resultBtn').disabled = false;
		document.getElementById('placeBtn').disabled = 'true';
	}
	if(message.signal == 'PLACED') {
		document.getElementById('submitBtn').disabled = false;
	}
	if(message.signal == 'DISCONNECTED') {
		alert(message.user.username + ' is disconnected!')
	}
}

function awaitConnect(retries) {
	client.activate();
	if(client.connected) {
		console.log('connected');
	} else {
		if(retries > 0)
			setTimeout(awaitConnect(retries-1), 2000);
		else
			console.log('Connection failed');
	}
}

function checkAndConnect() {
	if(!client.connected) {
		awaitConnect(5);
	}
}

function sendConnectMsg() {
	message = new Message(user, 'JOINED');
		client.publish({
			destination: appSignal,
			body: JSON.stringify(message)
		})
}

function sendSubmitMsg() {
	message = new Message(user, 'SUBMITTED');
		client.publish({
			destination: appSignal,
			body: JSON.stringify(message)
		})
}

function sendPlacedMsg() {
	message = new Message(user, 'PLACED');
		client.publish({
			destination: appSignal,
			body: JSON.stringify(message)
		})
}

function sendDisconnectMsg() {
	message = new Message(user, 'DISCONNECTED');
		client.publish({
			destination: appSignal,
			body: JSON.stringify(message)
		})
}
