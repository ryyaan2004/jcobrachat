package org.ryyaan2004.chat;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class SessionListener implements HttpSessionAttributeListener{
	public void attributeAdded(HttpSessionBindingEvent hsbe)
	{
	}

	public void attributeReplaced(HttpSessionBindingEvent hsbe)
	{
	}

	/** 
	* This method deletes a user from the list upon session expiration
	*/
	public void attributeRemoved(HttpSessionBindingEvent hsbe)
	{
		String name = hsbe.getName();
		HttpSession session = hsbe.getSession();
		if ("nickname".equalsIgnoreCase(name))
		{
			String nickname = (String)hsbe.getValue();
			if (nickname != null)
			{
				ServletContext application = session.getServletContext();
				if (application != null)
				{
					Object o = application.getAttribute("roomlist");		
					if (o != null)
					{
						RoomList roomList = (RoomList)o;
						Room room = roomList.getRoomOfParticipant(nickname);
						if (room != null)
						{
							Object participant = room.removeParticipant(nickname);
							if (participant != null)
							{
								String n = ((Participant)participant).getName();
							}
							
						}
					}
				}
				else
				{
					System.out.println("ServletContext is null");
				}					
			}
		}		
	}
}
