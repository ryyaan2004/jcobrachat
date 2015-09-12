package org.ryyaan2004.chat.model.oauth;

import java.util.HashMap;
import java.util.Map;

public class OauthUserFactory
{

    public static final String GOOGLE = "google";
    public static final String TWITTER = "twitter";

    private static final Map<String, Class<?>> providerMap = new HashMap<>();

    static
    {
        providerMap.put( GOOGLE, GoogleOauthUser.class );
        providerMap.put( TWITTER, GoogleOauthUser.class );
    }

    public static OauthUser getOauthUser( final String provider )
    {
        if ( provider.toLowerCase().contains( GOOGLE ) )
        {
            return new GoogleOauthUser();
        }
        else
            return new GoogleOauthUser();
    }

    public static Class<?> getClassForProvider( final String provider )
    {
        if ( provider == null || provider.length() < 1 )
        {
            throw new IllegalArgumentException( "The argument must not be null and must not be an empty string" );
        }
        return providerMap.get( provider );
    }
}
