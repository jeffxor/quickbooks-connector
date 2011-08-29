/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;

/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Aug 19, 2011
 */

public class DefaultQuickBooksClient implements QuickBooksClient
{
    private String realmId;
    private OAuthClientFilter oauthFilter;
    private WebResource gateway;
    
    public DefaultQuickBooksClient(String realmId, String consumerKey, String consumerSecret, String accessKey, String accessSecret)
    {
        this.realmId = realmId;
        Client client = Client.create();
        
        OAuthParameters params = new OAuthParameters().signatureMethod("HMAC-SHA1").consumerKey(consumerKey).token(accessKey).version();
        OAuthSecrets secrets = new OAuthSecrets().consumerSecret(consumerSecret).tokenSecret(accessSecret);
        
        this.oauthFilter = new OAuthClientFilter(client.getProviders(), params, secrets);
        
        this.gateway = Client.create().resource(getBaseURI());
        this.gateway.addFilter(this.oauthFilter);
        
        //String networkResult = res.header("X-Tradeshift-TenantId", "634a8633-6368-433a-bf33-7904f5080db0")
        //    .header("User-Agent", "TradeshiftJerseyTest/0.1")
        //    .get(String.class); 
    }
    
    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#create(java.lang.Object) */
    @Override
    public <T> T create(T obj)
    {
        T response = this.gateway.path("/resource/" + " " + "/v2/" + realmId)
        .type(MediaType.APPLICATION_XML)
        .header("Content-Type", "application/xml")
        .post(new GenericType<T>(obj.getClass()));

        return response;
    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#getObject() */
    @Override
    public <T> T getObject(String type, String objectId)
    {   
        //TODO ver la parte de injections
        T response = this.gateway.path("/resource/" + type.toLowerCase() + "/v2/" + realmId + "/" + objectId)
            .type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .get(new GenericType<T>(obj.getClass()));
        
        
        return null;

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#update(java.lang.String) */
    @Override
    public <T> T update(T obj)
    {

        // TODO: Auto-generated method stub
        return null;

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#deleteObject(java.lang.Object) */
    @Override
    public void deleteObject(String type, String objectId)
    {   
        //TODO ver la parte de injections
        String response = this.gateway.path("/resource/" + type + "/v2/" + realmId + "/" + objectId  + "?methodx=delete")
            .type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .post(String.class);

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#findObjects() */
    @Override
    public <T> List<T> findObjects()
    {

        // TODO: Auto-generated method stub
        return null;

    }
    
    private String getBaseURI()
    {
        WebResource aux = Client.create().resource("https://qbo.intuit.com/qbo30/rest/user/v2/" + this.realmId);
        aux.addFilter(oauthFilter);
        String response = aux.type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .get(String.class);
        
        return StringUtils.substringBetween(response, "<BaseUri>", "</BaseUri>");

    }

}
//this.consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);

/*OAuthProvider p; new DefaultOAuthProvider(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
OAuthProvider provider = new DefaultOAuthProvider(
    "https://oauth.intuit.com/oauth/v1/get_request_token",
    "https://oauth.intuit.com/oauth/v1/get_access_token",
    "https://workplace.intuit.com/app/Account/DataSharing/Authorize");*/
//try
//{
//    URL context = new URL("x-www-form-urlencoded");
//    URL url = new URL(getBaseURI() + "/resource/" + type + "/v2/" + realmId + "/" + objectId);
//    HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
//    
//    consumer.sign(request);
//    
//    request.connect();
//    //TODO seguir es GET
//}
//catch (Exception e)
//{
//    return null;
//}
