package org.ryyaan2004.chat.model;

import org.apache.log4j.Logger;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.ryyaan2004.chat.util.Constants;

public class ChatMessage {
	
	private static final Logger log = Logger.getLogger(ChatMessage.class);
	private static final String ICMGV = "In ChatMessage#getValue: ";
	
	/**
	 * The date and time when the message was received by the server
	 */
	private Date receivedAt;
	
	/**
	 * The JSON object which holds the message data in key-value pairs
	 */
	private JsonObject messageObj;
	
	public ChatMessage(){
	}
	
	public ChatMessage(Date receivedAt, JsonObject chatMessageObj){
		this.receivedAt = receivedAt;
		this.messageObj = jsonObjectToBuilder(chatMessageObj).add("timestamp", this.receivedAt.toString()).build();
	}
	
	/**
	 * Retrieves the value associated with key "message". Returns an
	 * empty string if no value is associated with the provided key.
	 * @return String
	 */
	public String getValue(String key){
		if( key == null || key.equals(Constants.EMPTY_STRING) )
		{
			log.error(ICMGV + "A null or empty string was passed as a paramater");
			String errMsg = "The key passed in to the ChatMessage#getValue method must not be a null or empty string";
			throw new InvalidParameterException(errMsg);
		}
		
		if( getMessageObj().getString(key) == null || getMessageObj().getString(key).equals(Constants.EMPTY_STRING) ){
			log.debug(ICMGV + "The call to ChatMessage#getMessageObj with parameter '" + key + "' resulted in a null or empty string");
			return Constants.EMPTY_STRING;
		}
		return getMessageObj().getString(key);
	}
	
	public Date getReceivedAt(){
		return receivedAt;
	}
	
	public void setReceivedAt(Date receivedAt){
		this.receivedAt = receivedAt;
	}
	
	public JsonObject getMessageObj(){
		return messageObj;
	}
	
	public void setMessageObj(JsonObject messageObj){
		this.messageObj = messageObj;
	}
	
	@Override
	public String toString(){
		return getMessageObj() + " received at " + receivedAt.toString();
	}
	
	private JsonObjectBuilder jsonObjectToBuilder(JsonObject jsonObj){
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		for (Entry<String, JsonValue> entry : jsonObj.entrySet() ) {
			job.add( entry.getKey(), entry.getValue() );
		}
		
		return job;
	}
}
