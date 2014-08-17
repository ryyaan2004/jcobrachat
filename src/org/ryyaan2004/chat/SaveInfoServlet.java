package org.ryyaan2004.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

public class SaveInfoServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(SaveInfoServlet.class);
	String nickname = null;
	String email = null;
	String comment = null;
	HttpSession session = null;
	String contextPath = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		nickname = request.getParameter("nickname");
		contextPath = request.getContextPath();
		email = request.getParameter("email");
		comment = request.getParameter("comment");
		session = request.getSession(true);
		try
		{
			RoomList roomList = (RoomList) getServletContext().getAttribute("chatroomlist");
			Room room = roomList.getRoomOfParticipant(nickname);

			
			if (room != null)
			{
				Participant participant = room.getParticipant(nickname);
				participant.setEmail(email);
				participant.setComment(comment);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.write("<html>\n<head>\n<title>Information Saved</title>\n</head>\n<body>\n");
				out.write("<b>Information Saved</b>\n<div align=\"center\">\n<form name=\"closing\">\n");
				out.write("<input type=\"button\" onClick=\"window.close()\" value=\"Close\">\n");
				out.write("</form>\n</div>\n</body>\n</html>");
				out.close();
			}
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
			response.sendRedirect(contextPath + "/error.jsp");
		}
	
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		doGet(request, response);
	}
}
