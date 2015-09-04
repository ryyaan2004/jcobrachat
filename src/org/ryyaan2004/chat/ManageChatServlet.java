package org.ryyaan2004.chat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.util.Properties;
import java.util.Enumeration;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import org.jboss.logging.Logger;
import org.ryyaan2004.chat.util.Constants;

public class ManageChatServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(ManageChatServlet.class);
	RoomList rooms = new RoomList();
	Properties props = null;
	private static String IMCSI = "In ManageChatServlet init method: ";
	private static String IMCSANR = "In ManageChatServlet addNewRoom method: ";
	public void init() throws ServletException{
		try
		{
			String path = "";
			path = "/WEB-INF/" + getServletContext().getInitParameter(Constants.INI_FILE);
			String fullPath;
			fullPath = getServletContext().getRealPath(path);
			
			if (fullPath != null)
			{
				InputStream fis = new FileInputStream(fullPath);

				props = new Properties();
				props.load(fis);
				Enumeration keys = props.keys();
				while (keys.hasMoreElements())
				{
					String roomName = (String)keys.nextElement();
					String roomDescr = (String)props.getProperty(roomName);
					addNewRoom(rooms, roomName, roomDescr);
				}
				fis.close();
				getServletContext().setAttribute("roomlist", rooms);
				//System.err.println("Room List Created");
				log.info("Room list created");
			}
			else
			{
				//System.out.println("Unable to get realpath of chatproperty file " + path + ".\nCheck that application war file is expanded and file can be read.\nChat application won't work.");
				log.error( IMCSI + "Not able to obtain the path of " + Constants.INI_FILE + " file at " + path + 
						"\nCheck that application war file is expanded and file can be read.\nChat application won't work.");
			}
		}
		catch(FileNotFoundException e)
		{
			log.error( IMCSI + "Could not find the properties file: " + e.getMessage() );
		}
		catch(IOException e)
		{
			log.error( IMCSI + "The properties file could not be loaded: " + e.getMessage() );
		}		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Room List Created");
		out.close();
	}
	
	/**	
	* Allows users to add new rooms after performing minimum validation.
	* Also saves information to the properties file if required by initialization parameter <code>saveRooms</code>.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String roomName = request.getParameter("rn");
		String roomDescr = request.getParameter("rd");
		if (roomName == null || (roomName = roomName.trim()).length() == 0 || roomDescr == null || (roomDescr = roomDescr.trim()).length() == 0)
		{
			request.setAttribute("error", "Please specify the room name and room description");
			getServletContext().getRequestDispatcher("/addRoom.jsp").forward(request, response);
			return;

		}
		else if (roomName != null && roomName.indexOf(" ") != -1)
		{
			request.setAttribute("error", "Room Name cannot contain spaces");
			getServletContext().getRequestDispatcher("/addRoom.jsp").forward(request, response);
			return;
		}
		try
		{
			if (rooms != null)
			{
				addNewRoom(rooms, roomName, roomDescr);
			}
			
			
			String s = getServletContext().getInitParameter("saveRooms");
			boolean save = false;
			if (s != null && "true".equals(s) )
			{
				Boolean b = Boolean.valueOf(s);
				save = b.booleanValue();
			}
			if (save)
			{
				if (props != null)
				{
					props.put(roomName, roomDescr);
					String path = "/WEB-INF/"+getServletContext().getInitParameter( Constants.INI_FILE );
					String fullPath = getServletContext().getRealPath(path);
					OutputStream fos = new FileOutputStream(fullPath);
					props.store(fos, "List of Rooms");
					fos.close();	
				}
				else
				{
					log.error("Properties are null");
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("Properties are null");
				}
			}
			response.sendRedirect(request.getContextPath() + "/listrooms.jsp");
		}
		catch (Exception e)
		{
				log.error( IMCSANR + "There was an exception " + e.getMessage());
		}		
	}
	/**
	* Adds a new Room to RoomList object and saves it to the properties file if required.
	*/
	public void addNewRoom(RoomList list, String roomName, String roomDescr)
	{
		String s = getServletContext().getInitParameter("max_messages");
		int maxMessages = 25;
		if (s != null)
		{
			try
			{
				maxMessages = Integer.parseInt(s);
			}
			catch (NumberFormatException e)
			{
				log.error( IMCSANR + "Could not set the max_messages to the parameter value " + e.getMessage() );
			}
		}
		Room room = new Room(roomName, roomDescr);
		room.setMaximumMessageCount(maxMessages);
		rooms.addRoom(room);		
	}

	/** Called when servlet is being destroyed */

	public void destroy()
	{
		log.error("Destroying all rooms");
	}
}
