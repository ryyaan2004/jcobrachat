package org.ryyaan2004.chat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ryyaan2004.chat.util.Constants;

public class LoginFilter implements Filter
{

    private static final Logger log = Logger.getLogger( LoginFilter.class );

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter( ServletRequest req,
                          ServletResponse res,
                          FilterChain chain )
                                          throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession( false );

        String requestUri = request.getRequestURI();

        if ( cookieExistsInRequest( request ) || cookieExistsInSession( session ) )
        {
            if ( !cookieExistsInSession( session ) )
            {
                /*
                 * 1) Get the user from persistent storage
                 * 2) Add user to session
                 * 3) Add cookie to session
                 */
            }

            chain.doFilter( req, res );
        }
        else if ( ( requestUri.equals( "/chat/" ) || requestUri.equals( "/chat/index.jsp" ) ||
                    requestUri.contains( "auth/" ) || requestUri.contains( "/oauthcallback" ) ) ||
                  ( session != null && session.getAttribute( Constants.USER ) != null ) )
        {
            chain.doFilter( req, res );
        }
        else
        {
            response.sendRedirect( request.getContextPath() + "/index.jsp" );
        }
    }

    @Override
    public void init( FilterConfig arg0 ) throws ServletException
    {
        // TODO Auto-generated method stub

    }

    /**
     * Checks the session for the cookie that indicates a login
     * 
     * @param session
     * @return true if cookie exists
     */
    private boolean cookieExistsInSession( HttpSession session )
    {
        boolean truthiness = false;

        if ( session != null && session.getAttribute( Constants.COOKIE ) != null )
        {
            truthiness = true;
        }
        return truthiness;
    }

    private boolean cookieExistsInRequest( HttpServletRequest req )
    {
        boolean truthiness = false;
        Cookie[] cookies;

        if ( req != null && req.getCookies() != null )
        {
            cookies = req.getCookies();

            for ( Cookie cookie : cookies )
            {
                if ( Constants.COOKIE.equals( cookie.getName() ) )
                {
                    truthiness = true;
                    break;
                }
            }
        }
        return truthiness;
    }
}
