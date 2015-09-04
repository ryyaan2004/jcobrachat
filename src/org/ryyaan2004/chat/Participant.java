package org.ryyaan2004.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.ryyaan2004.chat.util.Constants;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {

	private String name = null;
	private String first_name = null;
	private String last_name = null;
	private String comment = null;
	private String email = null;
	private long loginTime = -1;
	private long roomEntryTime = -1;
	private boolean isAuthenticated = false;
	
	/**
	* Participant constructor
	* 
	* @param name This Participants name
	* @param loginTime Timestamp of when the Participant logged into the system
	*/
	public Participant(String name, long loginTime)
	{
		setName(name);
		setLoginTime(loginTime);
	}	
	
	public Participant(){}
	
	/**
	* Returns the Participants name
	* @return java.lang.String
	*/
	public String getName()
	{
		return name;
	}
	
	private void setName(String name){
		if ( name != null ){
			this.name = name;
		}
		else{
			this.name = Constants.EMPTY_STRING;
		}
	}
	
	/** 
	* Assigns a value to the instance variable comment
	* @param comment - The string to set to the comment instance variable
	*/
	public void setComment(String comment)
	{
		if ( comment != null ){
			this.comment = comment;
		}
		else{
			this.comment = Constants.EMPTY_STRING;
		}
	}
	
	/** 
	* Returns the value of our comment instance variable
	* @return String - The comment made by the Participant
	*/	
	public String getComment()
	{
		return this.comment;
	}
	
	/** 
	* Sets this Participants email address
	* @param email - String value of the email address to be set
	*/
	public void setEmail(String email)
	{
		if ( email != null ){
			this.email = email;
		}
		else{
			this.email = Constants.EMPTY_STRING;
		}
	}
	
	/**
	* Returns the email address of the Participant
	* @return String - The Participants email address
	*/
	public String getEmail()
	{
		return this.email;
	}
	/**
	* Returns the login timestamp for the Participant
	* @return long - The loginTime timestamp for the Participant
	*/
	public long getLoginTime()
	{
		return this.loginTime;
	}
	
	/**
	 * Private setter for loginTime
	 * @param ts - Long value of the time the Participant logged in
	 */
	private void setLoginTime(long ts){
		if ( ts > 0 ){
			this.loginTime = ts;
		}
		else{
			this.loginTime = -1;
		}
	}

	/**
	* Public setter for time in milliseconds when a Participant
	* entered a Room
	* @param long milliseconds got using Date.getTime()
	*/

	public void setRoomEntryTime( long entryTime)
	{
		if ( entryTime > 0 ){
			this.roomEntryTime = entryTime;
		}
		else{
			this.roomEntryTime = -1;
		}
	}
	/**
	* Returns time in milliseconds when a Participant entered a Room.
	* @return long Time in milliseconds that the Participant entered the room
	*/
	public long getRoomEntryTime()
	{
		return roomEntryTime;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public void setGiven_name(String given_name){
		this.first_name = given_name;
	}
	
	public void setFamily_name(String family_name){
		this.last_name = family_name;
	}
	
	public String getGiven_name(){
		return this.first_name;
	}
	
	public String getFamily_name(){
		return this.last_name;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}
