package org.ryyaan2004.chat.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JCobraConnection {

  public static Connection getConnection() throws SQLException {

    final String USER_KEY = "user";
    final String PW_KEY = "password";
    final String CONNURL_KEY = "connUrl";

    Properties props = new Properties();
    String connUrl = getConnectionProps().getProperty( CONNURL_KEY );
    props.setProperty( USER_KEY, getConnectionProps().getProperty( USER_KEY ) );
    props.setProperty( PW_KEY, getConnectionProps().getProperty( PW_KEY ) );

    Connection conn = DriverManager.getConnection( connUrl, props );

    return conn;
  }

  private static Properties getConnectionProps() {
    Properties props = new Properties();
    String propFileName = "/dataSource.properties";

    try ( InputStream inputStream = JCobraConnection.class.getResourceAsStream( propFileName ); ) {

      if ( inputStream != null ) {
        props.load( inputStream );
      }
      else {
        throw new FileNotFoundException( "property file '" + propFileName + "' not found in the classpath" );
      }
    }
    catch ( Exception ex ) {
      ex.printStackTrace();
    }
    return props;
  }
}
