/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.List;

import javax.ws.rs.core.MediaType;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.apache.commons.lang.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

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
    private OAuthConsumer consumer;
    private WebResource gateway;
    
    public DefaultQuickBooksClient(String realmId, String consumerKey, String consumerSecret, String accessKey, String accessSecret)
    {
        this.realmId = realmId;
        this.consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
        this.gateway = Client.create().resource(getBaseURI());
        
    }
    
    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#create(java.lang.Object) */
    @Override
    public void create(Object obj)
    {

        // TODO: Auto-generated method stub

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#getObject() */
    @Override
    public Object getObject(String type, String objectId)
    {

        /*OAuthProvider p; new DefaultOAuthProvider(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
        OAuthProvider provider = new DefaultOAuthProvider(
            "https://oauth.intuit.com/oauth/v1/get_request_token",
            "https://oauth.intuit.com/oauth/v1/get_access_token",
            "https://workplace.intuit.com/app/Account/DataSharing/Authorize");*/
//        try
//        {
//            URL context = new URL("x-www-form-urlencoded");
//            URL url = new URL(getBaseURI() + "/resource/" + type + "/v2/" + realmId + "/" + objectId);
//            HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
//            
//            consumer.sign(request);
//            
//            request.connect();
//            //TODO seguir es GET
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
        
        //TODO ver la parte de injections
        String response = this.gateway.path("/resource/" + type + "/v2/" + realmId + "/" + objectId).type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .get(String.class);
        
        return null;

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#update(java.lang.String) */
    @Override
    public void update(String username)
    {

        // TODO: Auto-generated method stub

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#deleteObject(java.lang.Object) */
    @Override
    public void deleteObject(String type, String objectId)
    {
        /*OAuthProvider p; new DefaultOAuthProvider(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
        OAuthProvider provider = new DefaultOAuthProvider(
            "https://oauth.intuit.com/oauth/v1/get_request_token",
            "https://oauth.intuit.com/oauth/v1/get_access_token",
            "https://workplace.intuit.com/app/Account/DataSharing/Authorize");*/
//        try
//        {
//            URL context = new URL("application/xml");
//            URL url = new URL(context, getBaseURI() + "/resource/" + type + "/v2/" + realmId + "/" + objectId + "?methodx=delete");
//            HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
//            
//            consumer.sign(request);
//            
//            request.connect();
//            //TODO seguir es POST
//        }
//        catch (Exception e)
//        {
//            return;
//        }
        
        //TODO ver la parte de injections
        String response = this.gateway.path("/resource/" + type + "/v2/" + realmId + "/" + objectId  + "?methodx=delete")
            .type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .post(String.class);

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#findObjects() */
    @Override
    public List<Object> findObjects()
    {

        // TODO: Auto-generated method stub
        return null;

    }
    
    private String getBaseURI()
    {
        WebResource aux = Client.create().resource("https://qbo.intuit.com/qbo30/rest/user/v2/" + this.realmId);
        String response = aux.type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .get(String.class);
        
        return StringUtils.substringBetween(response, "<BaseUri>", "</BaseUri>");

    }

}
