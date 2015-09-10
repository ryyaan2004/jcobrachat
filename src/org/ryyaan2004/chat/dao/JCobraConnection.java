package org.ryyaan2004.chat.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JCobraConnection {
  private static Logger log = Logger.getLogger(JCobraConnection.class);
  private static DataSource ds = null;
  private static String jCobraChatJndi = "java:/jcobrachatDs";
  
  static
  {
	  try 
	  {
		ds = (DataSource) InitialContext.doLookup(jCobraChatJndi);
	  } 
	  catch (NamingException ne) 
	  {
		log.error("In JCobraConnection static initialization block: The datasource lookup failed",ne);
	  }
  }
  
  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
