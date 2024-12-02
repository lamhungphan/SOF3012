var username = null;
var websocket = null;

function init() {
    while (username === null) {
        username = prompt("Enter username");
    }
    websocket = new
    WebSocket(`ws://localhost:8080/websocket/json/chat/${username}`);
    websocket.onopen = function (resp) {
        console.log("onopen", resp);
    }
    websocket.onmessage = function (resp) {
    }
    websocket.onerror = function (resp) {
        alert('An error occured, closing application');
        console.log("onerror", resp);
    }
    websocket.onclose = function (resp) {
        alert(resp.reason || 'Goodbye');
        console.log("onerror", resp);
    }
}

function send() {
    var input = document.getElementById("message");
    var msg = {sender: username, text: input.value, type: 2}
    websocket.send(JSON.stringify(msg));
    input.value = '';
}

// Viết mã cho hàm xử lý sự kiện WebSocket.onmessage
var msg = JSON.parse(resp.data);
var output = document.getElementById('messages');
output.innerHTML = `${output.innerHTML}<p><b>${msg.sender}</b>:
${msg.text}</p>`;
if (msg.type != 2) {
    document.getElementById('client-count').innerHTML = `Chatters:
${msg.count}`;
}