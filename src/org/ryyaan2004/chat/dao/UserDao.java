package org.ryyaan2004.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ryyaan2004.chat.model.User;

public class UserDao {
  private static Logger log = Logger.getLogger(UserDao.class);
  
  public static List< User > getAllUsers() {
    List< User > users = new ArrayList< User >();
    String selectUsers = "Select email, alias, firstname, lastname From users";

    try ( Connection conn = JCobraConnection.getConnection(); PreparedStatement statement = conn.prepareStatement( selectUsers ); ResultSet result = statement.executeQuery(); ) {

      while ( result.next() ) {
        User user = new User();
        user.setEmail( result.getString( "email" ) );
        user.setAlias( result.getString( "alias" ) );
        user.setFirstname( result.getString( "firstname" ) );
        user.setLastname( result.getString( "lastname" ) );

        users.add( user );
      }

    }
    catch ( SQLException e ) {
      log.error("In UserDao#getAllUsers: A SQLException occurred", e);;
    }
    return users;
  }
}
