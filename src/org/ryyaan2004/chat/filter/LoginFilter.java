package org.ryyaan2004.chat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ryyaan2004.chat.service.CookieService;
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
        CookieService cs = new CookieService();

        if ( cs.cookieExistsInRequest( request ) || cs.cookieExistsInSession( session ) )
        {
            if ( !cs.cookieExistsInSession( session ) )
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
}
