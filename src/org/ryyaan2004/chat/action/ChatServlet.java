package org.ryyaan2004.chat.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet( HttpServletRequest request,
                       HttpServletResponse response ) throws ServletException, IOException
    {
        HttpSession session = request.getSession( false );
        session.setAttribute( "title", "jCobra Chat Rooms List" );
        RequestDispatcher rd = request.getRequestDispatcher( "/chat.jsp" );
        rd.forward( request, response );
    }
}
