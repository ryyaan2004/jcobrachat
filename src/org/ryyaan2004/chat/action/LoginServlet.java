package org.ryyaan2004.chat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.ryyaan2004.chat.OAuthHelper;
import org.ryyaan2004.chat.model.User;
import org.ryyaan2004.chat.model.oauth.OauthUser;
import org.ryyaan2004.chat.model.oauth.OauthUserFactory;
import org.ryyaan2004.chat.util.ChatProperties;
import org.ryyaan2004.chat.util.Constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * LoginServlet handles logging a user into our system by being a single point
 * of contact for the oauth providers and ensuring that our login related
 * session variables/cookies exist.
 * 
 * LoginServlet handles sending oauth requests to the providers and receiving
 * their responses. It should handle placing session variables related to login
 * info as well as cookies.
 * 
 * TODO: The cookie stuff should probably be in a CookieManager class, right?
 * 
 * @author ryyaan2004 (ryyaan_2004@yahoo.com)
 *
 */
public class LoginServlet extends HttpServlet
{

    private static final long serialVersionUID = -100L;
    private static final Logger log = Logger.getLogger( LoginServlet.class );
    private String contextPath = "";
    private static final String TWITTER_POST_REQUEST_TOKEN_URL = "https://api.twitter.com/oauth/request_token";
    private static final String charsetEncoding = "UTF-8";
    private HttpSession session;

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
            session.setAttribute( provider, OauthUserFactory.TWITTER );
            Twitter twitter = TwitterFactory.getSingleton();
            twitter.setOAuthConsumer( props.getOauthValue( Constants.TWITTER_CONSUMER_KEY ),
                                      props.getOauthValue( Constants.TWITTER_CONSUMER_SECRET ) );
            RequestToken requestToken = null;

            try
            {
                requestToken = twitter.getOAuthRequestToken( Constants.OAUTH_CALLBACK_URI );
            }
            catch (TwitterException e)
            {
                log.error( "In LoginServlet#doGet an error occurred during the attempt to retrieve a Twitter RequestToken",
                           e );
            }

            session.setAttribute( "twitterRequestToken", requestToken );
            response.sendRedirect( requestToken.getAuthenticationURL() );

        }
        else if ( requestUrl.contains( props.getOauthValue( Constants.OAUTH_CALLBACK_URI ) ) )
        {
            String oauthProvider = (String) session.getAttribute( provider );
            log.debug( "An oauth response has been received from: '" + oauthProvider + "'" );

            // if ( OauthUserFactory.GOOGLE.equals( oauthProvider ) )
            OAuthHelper helper = new OAuthHelper();
            String json = helper.getUserInfoJson( request.getParameter( Constants.CODE ) );

            ObjectMapper mapper = new ObjectMapper();
            OauthUser ou = (OauthUser) mapper.readValue( json, OauthUserFactory.getClassForProvider( oauthProvider ) );
            User user = new User( ou );
            session.setAttribute( Constants.USER, user );

            /*
             * We sendRedirect to clear the /oauthcallback from the browsers url
             * bar
             */
            response.sendRedirect( "/chat/chat" );
        }
        else
        {
            log.debug( "The requestUrl:=" + requestUrl );
            response.sendRedirect( contextPath + "/index.jsp" );
        }
    }
}
