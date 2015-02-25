var socket = new WebSocket("ws://" + document.location.host + document.location.pathname + "ws_chat");
socket.onmessage = function (message) {
    document.getElementById("chatLog").textContent += message.data + "\n";
};
window.onload = function () {
    document.getElementById("send").onclick = function () {
        var messageElement = document.getElementById("message");
        socket.send(messageElement.value);
        messageElement.value = "";
    };
    document.getElementById("disconnect").onclick = function () {
        socket.close();
    };
};