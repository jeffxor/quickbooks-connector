/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.mule.modules.quickbooks.EntityType;
import org.mule.modules.quickbooks.api.Exception.QuickBooksException;
import org.mule.modules.quickbooks.schema.CdmBase;
import org.mule.modules.quickbooks.schema.FaultInfo;
import org.mule.modules.quickbooks.schema.IdType;
import org.mule.modules.quickbooks.schema.QboUser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
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
    
    /** @throws QuickBooksException 
     * @see org.mule.modules.quickbooks.api.QuickBooksClient#create(java.lang.Object) */
    @Override
    public <T> T create(T obj) throws QuickBooksException
    {
        try
        {
            T response = this.gateway.path("/resource/" + obj.getClass().getSimpleName().toLowerCase() + "/v2/" + realmId)
            .type(MediaType.APPLICATION_XML)
            .header("Content-Type", "application/xml")
            .post(new GenericType<T>(obj.getClass()), obj);
            
            return response;
            
        }
        catch (final UniformInterfaceException e)
        {
            final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
            throw new QuickBooksException(fault);
        }

    }

    /** @throws QuickBooksException 
     * @see org.mule.modules.quickbooks.api.QuickBooksClient#getObject() */
    @Override
    public <T extends CdmBase> T getObject(EntityType type, IdType id) throws QuickBooksException
    {   
        try
        {
            T response = this.gateway.path("/resource/" + type.getResouceName() + "/v2/" + realmId + "/" + id.getValue())
                .type(MediaType.APPLICATION_XML)
                .header("Content-Type", "application/xml")
                .get(type.<T>getType());
            
            return response;
        }
        catch (final UniformInterfaceException e)
        {
            final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
            throw new QuickBooksException(fault);
        }
    }

    /** @throws QuickBooksException 
     * @see org.mule.modules.quickbooks.api.QuickBooksClient#update(java.lang.String) */
    @Override
    public <T> T update(T obj) throws QuickBooksException
    {
        try
        {
            T response = this.gateway.path("/resource/" + " " + "/v2/" + realmId)
                .type(MediaType.APPLICATION_XML)
                .header("Content-Type", "application/xml")
                .post(new GenericType<T>(obj.getClass()), obj);
            
            return response;
        }
        catch (final UniformInterfaceException e)
        {
            final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
            throw new QuickBooksException(fault);
        }

    }

    /** @throws QuickBooksException 
     * @see org.mule.modules.quickbooks.api.QuickBooksClient#deleteObject(java.lang.Object) */
    @Override
    public <T extends CdmBase> void deleteObject(EntityType type, IdType id, String syncToken) throws QuickBooksException
    {   
        if (syncToken == null)
        {
            syncToken = ((CdmBase) getObject(type, id)).getSyncToken();
        }
        try
        {
            T obj = type.newInstance();
            obj.setSyncToken(syncToken);
            obj.setId(id);
            T response = this.gateway.path("/resource/" + type.getResouceName() + "/v2/" + realmId + "/" + id.getValue() + "?methodx=delete")
                .type(MediaType.APPLICATION_XML)
                .header("Content-Type", "application/xml")
                .post(type.<T>getType(), obj);
        }
        catch (final UniformInterfaceException e)
        {
            final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
            throw new QuickBooksException(fault);
        }

    }

    /** @see org.mule.modules.quickbooks.api.QuickBooksClient#findObjects() */
    @Override
    public <T> List<T> findObjects()
    {

        // TODO: Auto-generated method stub
        return null;

    }
    
    private String getBaseURI() throws QuickBooksException
    {
        WebResource aux = Client.create().resource("https://qbo.intuit.com/qbo30/rest/user/v2/" + this.realmId);
        aux.addFilter(oauthFilter);
        try
        {
            QboUser response = aux.type(MediaType.APPLICATION_XML)
                .header("Content-Type", "application/xml")
                .get(QboUser.class);
            
            return response.getCurrentCompany().getBaseURI();
        }
        catch (final UniformInterfaceException e)
        {
            final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
            throw new QuickBooksException(fault);
        }
    }

}
