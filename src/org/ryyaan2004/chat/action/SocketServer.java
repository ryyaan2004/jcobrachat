package org.ryyaan2004.chat.action;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.ryyaan2004.chat.util.Constants;
import org.ryyaan2004.chat.model.ChatMessage;

import java.util.List;

@ServerEndpoint(value = Constants.WEBSOCKET_SERVER_ENDPOINT, decoders = {SocketServerDecoder.class}, encoders = {SocketServerEncoder.class})
public class SocketServer {
	private static final Logger log = Logger.getLogger(SocketServer.class);
	
	private static final String ISS = "In SocketServer#";
	private static final String ISSOOM = ISS + "onOpen method: ";
	private static final String ISSOMM = ISS + "onMessage method: ";
	private static final String ISSOCM = ISS + "onClose method: ";
	
	private static final String CONNECTION_MESSAGE = "Connection Established with server";
	
	private static final String NEW_MESSAGE_TYPE = "new-message";
	
	@OnOpen
	public void onOpen(Session session) {
		log.info(ISSOOM + "connection opened by '" + session.getId() + "'");
		try {
			session.getBasicRemote().sendText(CONNECTION_MESSAGE);
		}
		catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		}
	}
	
	@OnMessage
	public void onMessage(List<ChatMessage> messages, Session session) {
		for(ChatMessage message : messages)
		{
			log.info(ISSOMM + "message received from '" + session.getId() + "'. Message follows:\n\t" + message);
			try {
				if( message.getValue("msgType").equalsIgnoreCase(NEW_MESSAGE_TYPE) )
				{
					//session.getBasicRemote().sendText( message.getValue("message") );
					session.getBasicRemote().sendObject(message);
				}
			}
			catch (IOException | EncodeException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		log.info(ISSOCM + "session '" + session.getId() + "' has closed");
	}
}
