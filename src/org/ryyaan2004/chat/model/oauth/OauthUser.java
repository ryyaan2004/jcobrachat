package org.ryyaan2004.chat.model.oauth;


public abstract class OauthUser
{
    protected String firstName = null;
    protected String lastName = null;
    protected String email = null;
    protected boolean isAuthenticated = false;
    
    public String getName()
    {
        return this.firstName + " " + this.lastName;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public boolean isAuthenticated()
    {
        return this.isAuthenticated;
    }
    
    public void setAuthenticated( boolean isAuthenticated )
    {
        this.isAuthenticated = isAuthenticated;
    }
}
