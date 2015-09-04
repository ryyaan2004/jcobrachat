package org.ryyaan2004.chat.action;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.ryyaan2004.chat.model.ChatMessage;

public class SocketServerEncoder implements Encoder.Text<ChatMessage>{

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ChatMessage object) throws EncodeException {
		// TODO Auto-generated method stub
		return object.getMessageObj().toString();
	}

}
