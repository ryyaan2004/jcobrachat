package org.ryyaan2004.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.ryyaan2004.chat.model.User;
import org.ryyaan2004.chat.model.oauth.OauthUser;
import org.ryyaan2004.chat.model.oauth.OauthUserFactory;
import org.ryyaan2004.chat.util.ChatProperties;
import org.ryyaan2004.chat.util.Constants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.oauth2.Oauth2;

public class LoginServlet extends HttpServlet
{

    private static final long serialVersionUID = -100L;
    private static final Logger log = Logger.getLogger( LoginServlet.class );
    private String contextPath = "";
    private static final String ILSDGM = "In LoginServlet doGet method: ";
    private HttpSession session;

    private static Oauth2 oauth2;
    private ChatProperties props;

    @Override
    public void doGet( HttpServletRequest request,
                       HttpServletResponse response ) throws ServletException, IOException
    {
        String provider = "provider";
        contextPath = request.getContextPath();
        String requestUrl = request.getRequestURL().toString();
        props = ChatProperties.getInstance();
        session = request.getSession();

        if ( requestUrl.contains( props.getOauthValue( Constants.GOOGLE_AUTH_URL ) ) )
        {
            log.debug( "A request for the GOOGLE auth url has been received" );
            OAuthHelper helper = new OAuthHelper();
            String url = helper.buildLoginUrl();
            session.setAttribute( Constants.STATE, helper.getStateToken() );
            session.setAttribute( provider, OauthUserFactory.GOOGLE );
            response.sendRedirect( url );
        }
        else if ( requestUrl.contains( props.getOauthValue( Constants.TWITTER_AUTH_URL ) ) )
        {
            log.debug( "A request for the TWITTER auth url has been received" );
        }
        else if ( requestUrl.contains( props.getOauthValue( Constants.OAUTH_CALLBACK_URI ) ) )
        {
            String oauthProvider = (String) session.getAttribute( provider );
            log.debug( "An oauth response has been received from: '" + oauthProvider + "'" );
            OAuthHelper helper = new OAuthHelper();
            String json = helper.getUserInfoJson( request.getParameter( Constants.CODE ) );
            ObjectMapper mapper = new ObjectMapper();

            OauthUser ou = (OauthUser) mapper.readValue( json, OauthUserFactory.getClassForProvider( oauthProvider ) );
            User user = new User( ou );
            session = request.getSession();
            session.setAttribute( Constants.USER, user );
            session.setAttribute( "title", "jCobra Chat Rooms List" );

            response.sendRedirect( "chat.jsp" );
        }
        else
        {
            log.debug( "The requestUrl:=" + requestUrl );
            response.sendRedirect( contextPath + "/index.jsp" );
        }
    }
}
