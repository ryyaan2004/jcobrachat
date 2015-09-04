jQuery(function ($) {
	var userName = window.userName;
	var socket = '';
	socket = openSocket(socket);
//		socket.send("userName",{userName:userName});
	var $messageSend = $('#messageSend');
	var	$messageInput = $('#messageInput');
	var $chatMessages = $('#chatMessage');
    var $displayName = $('#displayName');

	$messageSend.click(function (e){
		sendMessage();
	});

	$messageInput.keypress(function (e){
		if (e.keyCode == 13 && !e.shiftKey){
			e.preventDefault();
			sendMessage();
		}
	});

	function getImage(img){
		"use strict";
		return '<img src="' + img + '"/>';
	}

	function createMsgSpan( msgTxt, msgTs, uid ){
		"use strict";
		msgTxt = JSON.parse('"' + msgTxt + '"');
		return '<span class="message" id="' + uid + msgTs + '">' + msgTxt + '</span>';
	}

	$('#image_for_message').on('change', function(e){
		var file = e.originalEvent.target.files[0];
		var reader = new FileReader();
		reader.onload = function(event){
			window.user_file = event.target.result;
		}
		reader.readAsDataURL(file);
	});
	
	socket.onmessage = function(event) {
		var eventData = JSON.parse(event.data);
		// Parse the various event types and call 
		// the appropriate handler function
		
		if ( eventData.msgType == 'new-message' ) {
			onNewMessage(eventData)
		}
		else if ( eventData.msgType == 'user-list' ) {
			onUserList(eventData)
		}
	};

	function onNewMessage(data) {
		"use strict";
		//console.log("data:=" + data);
		var messageTS = new Date(data.timestamp);
		var seconds = messageTS.getTime()
		var messageTXT = data.message;
		var displayName = data.displayName;
		var includeBreak = data.image == 'false' ? '' : '</br>';
		var imageMsg = data.image == 'false' ? '' : getImage(data.image);
        
		messageTS = (messageTS.getMonth() + 1) + '/' +
		messageTS.getDate() + ' ' +
		messageTS.toLocaleTimeString();
		
		$chatMessages.append(
				'<div class="timestamp">' + messageTS + ' &lt; ' + displayName + ' &gt; ' +
				imageMsg + includeBreak + createMsgSpan(messageTXT, seconds, displayName) + '</div>');
		$('.message').linkify();
		var chatMsgs = document.getElementById('chatMessage');
		chatMsgs.scrollTop = chatMsgs.scrollHeight;
		$.titleAlert("Message from " + displayName, {requireBlur:true, stopOnFocus:true, duration:60000, interval:500});

	};

	function onUserList(data) {
		"use strict";
		var c = 0;
		var theList = data.userList;
		var userArea = $('#left-nav-content');
		$('.userList').remove();
		for ( c = 0; c < theList.length; c++ ){
			userArea.append('<div id="' + theList[c].userName + '" class="userList">' + theList[c].userName + '</div>');
		}
	};
	
	function sendMessage(message){
		if ((message || $messageInput.val()) === '') {
			//do nothing
		}else {
            var sendMe = {};
			var userImg = $('#image_for_message');
			userImg = userImg[0];
            /*message = $displayName.val() + '-' + ( message || $messageInput.val() );*/
            sendMe.message = ( message || $messageInput.val() );
            sendMe.displayName = $displayName.val();
			sendMe.image = userImg.files.length > 0 ? window.user_file : "false";
			sendMe.msgType = 'new-message';
			socket.send(JSON.stringify(sendMe));
			$messageInput.val('');		
		}
	}
	
//	function openSocket(){
//        // Ensures only one connection is open at a time
//        if(socket !== undefined && socket.readyState !== WebSocket.CLOSED){
//           writeResponse("WebSocket is already opened.");
//            return;
//        }
//        // Create a new instance of the websocket
//        return socket = new WebSocket("ws://localhost:8080/chat/nest");
//	}
	
	function closeSocket(){
        socket.close();
    }

    function writeResponse(text){
        messages.innerHTML += "<br/>" + text;
    }
});