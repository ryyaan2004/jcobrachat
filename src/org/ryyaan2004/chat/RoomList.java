package org.ryyaan2004.chat;

import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class RoomList {
	private Map roomList;

	public RoomList()
	{
		roomList = new HashMap();
	}
	
	/**
	* Adder method for putting a Room object
	* into the roomList HashMap instance variable
	* @param room - Room object to add
	*/
	public synchronized void addRoom(Room room)
	{
		roomList.put(room.getName(), room);
	}
	
	/**
	* Method to remove a specific Room object from the
	* roomList HashMap instance variable
	* @param name - String name representing the Room object to be removed
	* room to be removed from the list of rooms.
	*/
	public synchronized void removeRoom(String name)
	{
		roomList.remove(name);
	}
	
	/** 
	* Returns a specific Room object from the
	* roomList HashMap instance variable
	* @param name is the name of the ChatRoom object to be returned.
	* @return ChatRoom object.
	*/
	public Room getRoom(String name)
	{
		return (Room)roomList.get(name);
	}
	
	/** 
	* Finds the specific Room where a specific Participant
	* is located
	* @param pName - The name of the Participant to search for
	* @return Room
	*/
	public Room getRoomOfParticipant(String pName)
	{
		Room[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			boolean pIsInRoom = rooms[i].isParticipantInRoom(pName);
			if (pIsInRoom)
			{
				return rooms[i];
			}
		}
		return null;
	}
	/** 
	* Returns the entire Set of Rooms
	* @return Set
	*/
	
	public Set getRoomList()
	{
		return roomList.entrySet();
	}
	
	/** 
	* This method returns a simple Array of Room
	* objects
	* @return Room[]
	*/
	public Room[] getRoomListArray()
	{
		Room[] roomArray = new Room[roomList.size()];
		Set roomlist = getRoomList();
		Iterator iter = roomlist.iterator();
		int i = 0;
		while(iter.hasNext())
		{
			Map.Entry entry = (Map.Entry)iter.next();
			String key = (String) entry.getKey();
			roomArray[i] = (Room)entry.getValue();
			i++;
		}
		return roomArray;
	}
	
	/**
	* Method to search Rooms for a specific Participant
	* @param pName - The name of the Participant being searched for
	* @return boolean
	*/
	public boolean participantExists(String pName)
	{
		boolean participantExists = false;
		Room[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			participantExists = rooms[i].isParticipantInRoom(pName);
			if (participantExists)
			{
				break;
			}
		}
		return participantExists;
	}
}
