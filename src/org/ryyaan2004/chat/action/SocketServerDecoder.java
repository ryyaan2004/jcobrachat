package org.ryyaan2004.chat.action;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.apache.log4j.Logger;
import org.ryyaan2004.chat.model.ChatMessage;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import java.io.StringReader;

public class SocketServerDecoder implements Decoder.Text<List<ChatMessage>>{
	
	private static final Logger log = Logger.getLogger(SocketServerDecoder.class);

	/**
	 * Lifecycle method not overridden
	 */
	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Lifecycle method not overridden
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Intercepts the incoming data to the websocket and processes
	 * it, returning the result to the websocket
	 */
	@Override
	public List<ChatMessage> decode(String s) throws DecodeException {
		log.debug("In SocketServerDecoder#decode: Received the following string: \n\t" + s);
		JsonReader reader = Json.createReader(new StringReader(s));
		//JsonArray jsonArray = reader.readArray();
		JsonObject jsonObj = (JsonObject) reader.read();
		
		List<ChatMessage> chatMessages = new ArrayList<>();
		
//		for(int i = 0; i < jsonArray.size(); i++){
//			JsonObject jsonObject = jsonArray.getJsonObject(i);
//			ChatMessage msg = new ChatMessage(new Date(), jsonObject);
//			chatMessages.add(msg);
//		}
		ChatMessage msg = new ChatMessage(new Date(), jsonObj);
		chatMessages.add(msg);
		return chatMessages;
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

}
