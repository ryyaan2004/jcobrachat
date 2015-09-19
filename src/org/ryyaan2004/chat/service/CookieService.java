package org.ryyaan2004.chat.service;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ryyaan2004.chat.model.User;
import org.ryyaan2004.chat.util.Constants;

/**
 * CookieService contains the 'business' logic for managing cookies. It
 * interacts with the data layer to persist and retrieve cookies.
 * 
 * @author ryyaan2004 (ryyaan_2004@yahoo.com)
 *
 */
public class CookieService
{

    /**
     * Delete the cookie immediately
     */
    public static int DELETE_COOKIE = 0;

    /**
     * The cookie will expire in one week
     */
    public static int ONE_WEEK = 604800;

    /**
     * The cookie will expire in two weeks
     */
    public static int TWO_WEEKS = 1209600;

    /**
     * The cookie will expire in one month
     */
    public static int ONE_MONTH = 2592000;

    /**
     * The cookie will last until the end of the session
     */
    public static int SESSION_ONLY = -1;

    private static int DEFAULT_AGE = ONE_WEEK;

    public CookieService()
    {
    };

    /**
     * Checks the session for the cookie that indicates a login
     * 
     * @param session HttpSession
     * @return true if cookie exists
     */
    public boolean cookieExistsInSession( HttpSession session )
    {
        boolean truthiness = false;

        if ( session != null && session.getAttribute( Constants.COOKIE ) != null )
        {
            truthiness = true;
        }
        return truthiness;
    }

    /**
     * Checks parameter request for the cookie that indicates a login
     * 
     * @param req HttpServletRequest
     * @return true if cookie exists
     */
    public boolean cookieExistsInRequest( HttpServletRequest req )
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

    /**
     * Causes a cookie to be created with the default max age, via the
     * {@link #createCookie} method then
     * adds it to both parameters
     * 
     * @param res javax.servlet.http.HttpServletResponse
     * @param session javax.servlet.http.HttpSession
     */
    public void addCookie( HttpServletResponse res,
                           HttpSession session )
    {
        addCookie( res, session, DEFAULT_AGE );
    }

    /**
     * Causes a cookie to be created with the specified max age, via the
     * {@link #createCookie} method then
     * adds it to both parameters
     * 
     * @param res javax.servlet.http.HttpServletResponse
     * @param session javax.servlet.http.HttpSession
     * @param maxAge org.ryyaan2004.chat.service.CookieService.CookieAge
     */
    public void addCookie( HttpServletResponse res,
                           HttpSession session,
                           int maxAge )
    {
        Cookie cookie = createCookie( maxAge );
        res.addCookie( cookie );
        addCookieToSession( cookie, session );
    }

    /**
     * Adds the provided cookie to the HttpSession
     * 
     * @param cookie javax.servlet.http.Cookie
     * @param session javax.servlet.http.HttpSession
     */
    public void addCookieToSession( Cookie cookie,
                                    HttpSession session )
    {
        session.setAttribute( Constants.COOKIE, cookie );
    }

    /**
     * Creates a cookie with the specified maxAge:
     * <ol>
     * <li>param1={@link org.ryyaan2004.chat.util.Constants#COOKIE}</li>
     * <li>param2={@link org.ryyaan2004.chat.util.Constants#COOKIE_PREFIX} + a
     * random integer</li>
     * </ol>
     * Should only be called once per session. If a cookie already exists
     * 
     * @return the newly created cookie
     */
    public Cookie createCookie( int maxAge )
    {
        Random rand = new Random();
        int randInt = rand.nextInt();
        Cookie cookie = new Cookie( Constants.COOKIE, Constants.COOKIE_PREFIX + randInt );
        setMaxAge( cookie, maxAge );
        return cookie;
    }

    /**
     * Creates a cookie with the default maxAge:
     * <ol>
     * <li>param1={@link org.ryyaan2004.chat.util.Constants#COOKIE}</li>
     * <li>param2={@link org.ryyaan2004.chat.util.Constants#COOKIE_PREFIX} + a
     * random integer</li>
     * </ol>
     * Should only be called once per session. If a cookie already exists
     * 
     * @return the newly created cookie
     */
    public Cookie createCookie()
    {
        return createCookie( DEFAULT_AGE );
    }

    /**
     * Delete the provided cookie
     * 
     * @param cookie Cookie
     */
    public void deleteCookie( Cookie cookie )
    {
        cookie.setMaxAge( DELETE_COOKIE );
    }

    /**
     * <p>
     * Set the max age of the cookie to the value provided in the
     * second parameter
     * </p>
     * 
     * <p>
     * <b>Note</b>, to delete cookie immediately use
     * {@link #deleteCookie(Cookie)}
     * </p>
     * 
     * @param cookie javax.servlet.http.Cookie
     * @param age org.ryyaan2004.chat.service.CookieService.CookieAge
     * @return cookie with max age set as specified
     */
    public Cookie setMaxAge( Cookie cookie,
                             int age )
    {
        cookie.setMaxAge( age );
        return cookie;
    }

    /**
     * 1) Determine session_id, user_id, created_time, max_age from sessions
     * user object (may need UserService support)
     * 2) Validate all from 1 and
     * 3) If valid attempt to persist, if invalid throw excep
     * 
     * @param session
     */
    public void storeCookie( HttpSession session )
    {
        User user = (User) session.getAttribute( Constants.USER );

    }
}
