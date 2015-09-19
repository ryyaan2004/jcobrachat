package org.ryyaan2004.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class CookieDao
{

    private static Logger log = Logger.getLogger( CookieDao.class );

    public void persistCookie( String session_id,
                               int user_id,
                               long created_time,
                               long max_age )
    {
        String sql = "INSERT INTO jcc_session(session_id, user_id, created_time, max_age)\n" +
                     "VALUES( ?, ?, ?, ? );";
        /**
         * 1) Determine session_id, user_id, created_time, max_age from sessions
         * user object (may need UserService support)
         * 2) Validate all from 1 and
         * 3) If valid attempt to persist, if invalid throw exception
         */
        try ( Connection conn = JCobraConnection.getConnection(); PreparedStatement ps = conn.prepareStatement( sql ) )
        {
            ps.setString( 1, session_id );
            ps.setInt( 2, user_id );
            ps.setLong( 3, created_time );
            ps.setLong( 4, max_age );

            int result = ps.executeUpdate();

            if ( result < 1 )
            {
                // TODO: throw system exception
            }
            else if ( result > 1 )
            {
                // TODO: throw a different system exception
            }
        }
        catch (SQLException e)
        {
            log.error( "In CookieDao#persistCookie: An SQLException was caught as a result of the insert statement. Message follows:\n\t" +
                       e.getMessage(), e );
        }
    }
}
