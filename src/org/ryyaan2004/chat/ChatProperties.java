package org.ryyaan2004.chat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.logging.Logger;

public class ChatProperties {
	private static final Logger log = Logger.getLogger(ChatProperties.class);
	private static final String ICPCM = "In ChatProperties constructor method: ";
	private static ChatProperties instance = null;
	private static Properties properties = null;
	private static Properties oauthProperties = null;
	
	private ChatProperties() throws FileNotFoundException, IOException{
		properties = new Properties();	
		InputStream in = getClass().getClassLoader().getResourceAsStream(Constants.INI_FILE); //new FileInputStream(Constants.INI_FILE);
		properties.load(in);
		
		oauthProperties = new Properties();
		in = getClass().getClassLoader().getResourceAsStream(Constants.OAUTH_FILE); //new FileInputStream(Constants.OAUTH_FILE);
		oauthProperties.load(in);
	}
	
	public static synchronized ChatProperties getInstance(){
		if ( instance == null ){
			try{
				instance = new ChatProperties();
			}
			catch ( FileNotFoundException e ){
				log.error( ICPCM + "Could not find the properties file " + 
							Constants.INI_FILE + ". Null is returned. " + e.getMessage() );
			}
			catch ( IOException e ){
				log.error( ICPCM + "An error occurred. Null is returned. " + e.getMessage() );
			}
			catch ( Exception e ){
				log.error( ICPCM + "A generic error occurred. " + e.getMessage() );
			}
		}
		return instance;
	}
	public String getValue( String propKey ){
		return this.properties.getProperty(propKey);
	}
	
	public String getOauthValue(String propKey){
		return this.oauthProperties.getProperty(propKey);
	}
}
