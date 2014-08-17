package org.ryyaan2004.chat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.client.json.jackson2.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -100L;
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	private String contextPath = "";
	private static final String ILSDGM = "In LoginServlet doGet method: ";
	//private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	//private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	HttpSession session;
	
	private static Oauth2 oauth2;
	private ChatProperties props;
	
	/** This method just redirects user to a login page.*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		contextPath = request.getContextPath();
		//response.sendRedirect(contextPath + "/login.jsp");
		String requestUrl = ((HttpServletRequest)request).getRequestURL().toString();
		props = ChatProperties.getInstance();
		
		if ( requestUrl.contains(props.getOauthValue(Constants.GOOGLE_AUTH_URL)) ){
			log.error("A request for the GOOGLE auth url has been received");
			OAuthHelper helper = new OAuthHelper();
			String url = helper.buildLoginUrl();
			session = request.getSession();
			session.setAttribute( Constants.STATE, helper.getStateToken() );
			response.sendRedirect(url);
		}
		else if ( requestUrl.contains(props.getOauthValue(Constants.TWITTER_AUTH_URL)) ){
			log.error("A request for the TWITTER auth url has been received");
		}
		else if (requestUrl.contains(props.getOauthValue(Constants.CALLBACK_URI)) ){
			log.error("A response from GOOGLE has been received");
			OAuthHelper helper = new OAuthHelper();
			//log.error(helper.getUserInfoJson(request.getParameter(Constants.CODE)));
			String json = helper.getUserInfoJson(request.getParameter(Constants.CODE));
			ObjectMapper mapper = new ObjectMapper();
			Participant p = mapper.readValue(json, Participant.class);
			session = request.getSession();
			session.setAttribute("name", p.getName());
			session.setAttribute("email", p.getEmail());
			session.setAttribute("title", "jCobra Chat Rooms List");
			
			response.sendRedirect("chat.jsp");
		}
		else{
			log.error("The requestUrl:=" + requestUrl);
			response.sendRedirect(contextPath + "/index.jsp");
		}
		
	}
	/** Performs some validation and if everything is ok sends user to a page which displays a list of
	* rooms available.
	*/
	/*public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		contextPath = request.getContextPath();		
		String nickname = request.getParameter("nickname");
		nickname = nickname.trim().toLowerCase();

		if ( (nickname != null && nickname.length() > 3 && nickname.indexOf(" ") == -1) )
		{
			try
			{
				RoomList roomlist = (RoomList)getServletContext().getAttribute("chatroomlist");
				boolean participantExists = roomlist.participantExists(nickname);
				if (participantExists)
				{
					response.sendRedirect(contextPath + "/login.jsp?d=t&n="+nickname);
				}
				else
				{
					HttpSession session = request.getSession(true);
					int timeout = 1800; // 30 minutes
					String t = getServletContext().getInitParameter("sessionTimeout"); // gets Minutes
					if (t != null)
					{
						try
						{
							timeout = Integer.parseInt(t);
							timeout = timeout * 60;
						}
						catch (NumberFormatException e)
						{							
							log.error( ILSDGM + "Unable to set the session timeout to the value of parameter" + e.getMessage() );
						}
					}
					session.setMaxInactiveInterval(timeout);
					session.setAttribute("nickname", nickname);
					// Because Participant objects are stored in room.
					// So before user selects any room he is added to a temporary room "StartUp"
					Room room = roomlist.getRoom("StartUp"); 
					nickname = nickname.toLowerCase();
					Participant participant = null;
					participant = new Participant( nickname, new java.util.Date().getTime() );
					room.addParticipant(participant);
					response.sendRedirect(contextPath + "/listrooms.jsp");

				}
			}
			catch(Exception e)
			{
				log.error( ILSDGM + "There was an exception thrown, redirecting to error.jsp " + e.getMessage() );
				response.sendRedirect(contextPath + "/error.jsp");
			}
		}
		else
		{
			response.sendRedirect(contextPath + "/login.jsp?ic=t");
		}
	}*/
}
