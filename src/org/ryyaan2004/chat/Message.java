package org.ryyaan2004.chat;

public class Message {
	private String senderName = null;
	private String message = null;
	private long timeStamp;
	/**
	* 
	* @param name Name of the person sending the message
	* @param message Body of the message
	* @param timeStamp The time the message was sent
	*/
	public Message(String name, String message, long timeStamp)
	{
		setSenderName(name);
		setMessage(message);
		setTimeStamp(timeStamp);
	}
	
	/**
	* Returns the name of the message sender
	* @return String
	*/
	public String getSenderName()
	{
		return senderName;
	}
	
	/**
	 * Setter for senderName
	 * @param name The name to be set to the private member senderName 
	 */
	private void setSenderName(String name){
		if ( name != null){
			this.senderName = name;
		}
		else{
			this.senderName = Constants.EMPTY_STRING;
		}
	}
	
	/**
	* Returns the message body
	* @return String
	*/
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * Setter for message
	 * @param message The body of the message that was sent
	 */
	private void setMessage(String message){
		if ( message != null){
			this.message = message;
		}
		else{
			this.message = Constants.EMPTY_STRING;
		}
	}
	/**
	* Returns the time the message was sent
	* @return long
	*/
	public long getTimeStamp()
	{
		return timeStamp;
	}
	
	/**
	 * Setter for timeStamp
	 * @param ts Long - The time the message was sent
	 */
	private void setTimeStamp(Long ts){
		if ( ts != null){
			this.timeStamp = ts;
		}
		else{
			this.timeStamp = 0;
		}
	}
}
