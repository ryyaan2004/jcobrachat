package org.ryyaan2004.chat;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.ryyaan2004.chat.model.oauth.GoogleOauthUser;
import org.ryyaan2004.chat.util.Constants;

public class Room {

	private String name = null;
	private String description = null;
	private Map participants = new HashMap();
	private List messages = new LinkedList();
	private int max_messages = 25;
	
	/**
	* Public constructor for a Room object
	* @param name - The Room's name
	* @param description - The Room's description
	*/
	public Room(String name, String description)
	{
		setName(name);
		setDescription(description);
	}
	
	/**
	* Getter method for the Room object
	* @return String - The Room name
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	 * Private setter method for the Room object
	 * @param name - The name of the Room
	 */
	private void setName(String name){
		if ( name != null ){
			this.name = name;
		}
		else{
			this.name = Constants.EMPTY_STRING;
		}
	}
	
	/**
	* Returns the Room's description
	* @return String - A description of the Room
	*/
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Private setter method for our Room's description
	 * @param description
	 */
	private void setDescription(String description){
		if ( description != null ){
			this.description = description;
		}
		else{
			this.description = Constants.EMPTY_STRING;
		}
	}
	
	/**
	* Public adder for our list of Participants
	* @param p - Participant object to add to the list
	*/
	public synchronized void addParticipant(GoogleOauthUser p)
	{
		if ( p != null && 
				p.getName() != null 
				&& !p.getName().equals(Constants.EMPTY_STRING) ){
			participants.put(p.getName(), p);
		}
		
	}
	
	/**
	* Public remover method for our list of Participants
	* @param participantName - The name of the participant to be removed
	*/
	public synchronized Object removeParticipant(String participantName)
	{
		//if ( participants.containsKey(participantName) ){
		return participants.remove(participantName);
	}
	
	/**
	* Finds a Participant by name from our list of
	* participants, then returns that Participant
	* @param participantName - The name of the Participant to find
	* @return Participant
	*/
	public GoogleOauthUser getParticipant(String participantName)
	{
		return (GoogleOauthUser)participants.get(participantName);
	}
	
	/**
	* A public method to check for the presence of a
	* particular Participant
	* @param participantName - The name of the Participant to find
	* @return boolean
	*/
	
	public boolean isParticipantInRoom(String participantName)
	{
		return participants.containsKey(participantName);
	}
	
	/**
	* Calculates the number or Participants currently
	* in the Room
	* @return int - The calculated count of Participants
	*/
	public int countParticipants()
	{
		return participants.size();
	}
	
	/**
	* Getter method for the Set of all Participants
	* @return Set - The complete set of current Participants
	*/
	public Set getParticipants()
	{
		return participants.entrySet();
	}
	
	/** 
	* Getter method for a simple Array of Participants
	* @return Participant[]
	*/
	public GoogleOauthUser[] getParticipantArray()
	{
		GoogleOauthUser[] participantsArray = new GoogleOauthUser[this.participants.size()];
		Set participantSet = getParticipants();
		Iterator iter_p = participantSet.iterator();
		int i = 0;
		while( iter_p.hasNext() )
		{
			Map.Entry entry = (Map.Entry)iter_p.next();
			String key = (String) entry.getKey();
			participantsArray[i] = (GoogleOauthUser)entry.getValue();
			i++;
		}
		return participantsArray;
	}
	
	/** 
	* Adder method for the list of messages
	* @param msg - A Message object
	*/
	public synchronized void addMessage(Message msg)
	{
		if(messages.size()==max_messages)
		{
			((LinkedList)messages).removeFirst();
		}
		messages.add(msg);
	}
	
	/**
	* A method to return a list iterator for
	* the list of messages
	* @return ListIterator
	*/	
	public ListIterator getMessages()
	{
		return messages.listIterator();
	}

	/**
	* Find messages that occurred after a certain time
	* and return them in a simple Array
	* @param ts Time in milliseconds
	* @return Message[]
	*/	
	public Message[] getMessages(long ts)
	{
		ListIterator li = messages.listIterator();
		List tmp = new ArrayList();
		Message m;
		while (li.hasNext())
		{
			m = (Message)li.next();
			if (m.getTimeStamp() >= ts)
			{
				tmp.add(m);
			}
		}
		Object o[] = tmp.toArray();
		Message[] arr = new Message[o.length];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (Message)o[i];
		}
		return arr;
	}

	/**
	* The total number of messages
	* @return int
	*/
	public int getTotalMessageCount()
	{
		return messages.size();
	}
	
	/**
	* Setter method for the maximum number of messages to
	* contain in our list of messages
	* @param max - Our maximum list size value
	*/
	public void setMaximumMessageCount(int max)
	{
		max_messages = max;
	}
	
	/**
	* Getter method for the maximum number of messages that
	* will be contained in our list of messages
	* @return int
	*/
	public int getMaximumMessageCount()
	{
		return max_messages;
	}
}
