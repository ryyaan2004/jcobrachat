package org.ryyaan2004.chat.model;

import org.ryyaan2004.chat.model.oauth.OauthUser;

/**
 * The user class represents the cobrachat system user. There are two use cases
 * <ol>
 * <li>It is used in an active java session to represent the user. During this
 * use case it is constructed with the
 * {@link org.ryyaan2004.chat.model.oauth.OauthUser OauthUser} parameter.</li>
 * <li>It is used to represent a user in a rest call. During this use case it is
 * constructed with the zero argument constructor and most likely loaded via
 * jdbc</li>
 * </ol>
 * 
 * @author ryyaan2004 (ryyaan_2004@yahoo.com)
 *
 */
public class User
{

    private OauthUser oauthUser;
    private String email;
    private String alias;
    private String firstname;
    private String lastname;

    public User()
    {
    }

    public User( OauthUser ou )
    {
        this.oauthUser = ou;
        setEmail( ou.getEmail() );
        setFirstname( ou.getFirstName() );
        setLastname( ou.getLastName() );
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias( String alias )
    {
        this.alias = alias;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname( String lastname )
    {
        this.lastname = lastname;
    }

    public String getName()
    {
        return oauthUser.getName();
    }
}
