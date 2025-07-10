const WS = new WebSocket("wss://" + window.location.hostname + ":8000/ws");

WS.onopen = function() {
   console.log('"On OPEN" triggered');
};

WS.onclose = function(closeEvent) {
    console.log(closeEvent);
};

WS.onerror = function(errorEvent) {
    console.log(errorEvent);
};

export default WS;