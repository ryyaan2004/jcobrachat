package org.ryyaan2004.chat.model.oauth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.ryyaan2004.chat.util.Constants;

/**
 * The <code>GoogleOauthUser</code> class is used for mapping the json response
 * from Google to the {@link org.ryyaan2004.chat.model.oauth.OauthUser OauthUser} class
 * that serves as the generic model of an oauth user
 * 
 * @author ryyaan2004 (ryyaan_2004@yahoo.com)
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleOauthUser extends OauthUser{
	
	public GoogleOauthUser(){}
	
	/** 
	* Sets this Participants email address
	* @param email - String value of the email address to be set
	*/
	public void setEmail(String email)
	{
		if ( email != null ){
			this.email = email;
		}
		else{
			this.email = Constants.EMPTY_STRING;
		}
	}
	
	/**
	 * The Google json response contains the field 'given_name' and therefore
	 * the naming of this method is necessary to map that response to the 
	 * appropriate member of our superclass
	 * @param given_name
	 */
	public void setGiven_name(String given_name){
		this.firstName = given_name;
	}
	
	/**
	 * The Google json response contains the field 'family_name' and therefore
     * the naming of this method is necessary to map that response to the 
     * appropriate member of our superclass
	 * @param family_name
	 */
	public void setFamily_name(String family_name){
		this.lastName = family_name;
	}
}
