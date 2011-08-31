/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules.quickbooks.api;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.NotImplementedException;
import org.mule.modules.quickbooks.EntityType;
import org.mule.modules.quickbooks.api.Exception.QuickBooksException;
import org.mule.modules.quickbooks.schema.CdmBase;
import org.mule.modules.quickbooks.schema.FaultInfo;
import org.mule.modules.quickbooks.schema.IdType;
import org.mule.modules.quickbooks.schema.QboUser;
import org.mule.modules.quickbooks.schema.SearchResults;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
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
    private final String realmId;
    private final OAuthClientFilter oauthFilter;
    private final WebResource gateway;
    private Integer resultsPerPage = 100;
    
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
    public <T extends CdmBase> T create(T obj)
    {
        try
        {
            String str = String.format("/resource/%s/v2/%s",
                obj.getClass().getSimpleName().toLowerCase(), realmId);
            T response = this.gateway.path(str)
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
    public <T extends CdmBase> T getObject(EntityType type, IdType id)
    {   
        try
        {
            String str = String.format("/resource/%s/v2/%s/%s",
                type.getResouceName(), realmId, id.getValue());
            T response = this.gateway.path(str)
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
    public <T extends CdmBase> T update(T obj)
    {
        try
        {
            String str = String.format("/resource/%s/v2/%s",
                obj.getClass().getSimpleName().toLowerCase(), realmId);
            T response = this.gateway.path(str)
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
    
    
//    /**
//     * 
//     */
//    private void foo()
//    {
//        SearchResults results;
//        Iterator<Collection<X>> result = f.get(xxxx);
//        
//        Iterable<X> 
//
//    }

    /** @throws QuickBooksException 
     * @see org.mule.modules.quickbooks.api.QuickBooksClient#deleteObject(java.lang.Object) */
    @Override
    public <T extends CdmBase> void deleteObject(EntityType type, IdType id, String syncToken)
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
            
            String str = String.format("/resource/%s/v2/%s/%s?methodx=delete",
                type.getResouceName(), realmId, id.getValue());
            T response = this.gateway.path(str)
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

    /** @param query 
     * @param type 
     * @see org.mule.modules.quickbooks.api.QuickBooksClient#findObjects() */
    @Override
    public <T extends CdmBase> Iterable<T> findObjects(final EntityType type, final String queryFilter, final String querySort)
    {
        return new PaginatedIterable<T, SearchResults>()
            {
                @Override
                protected SearchResults firstPage()
                {
                    try
                    {
                        //TODO verificar si quick acepta filter y sort vacio
                        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
                        formData.add("filter", queryFilter);
                        formData.add("sort", querySort);
                        formData.add("ResultsPerPage", resultsPerPage.toString());
                        formData.add("PageNum", "1");
                        
                        String str = String.format("/resource/%ss/v2/%s", type.getResouceName(), realmId); 
                        SearchResults response = gateway.path(str)
                            .type(MediaType.APPLICATION_FORM_URLENCODED)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .post(SearchResults.class, formData);
                        
                        return response;
                    }
                    catch (final UniformInterfaceException e)
                    {
                        final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
                        throw new QuickBooksException(fault);
                    }
                }

                @Override
                protected SearchResults nextPage(SearchResults currentPage)
                {
                    try
                    {
                        Integer pageNum = currentPage.getCurrentPage() + 1;
                        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
                        formData.add("Filter", queryFilter);
                        formData.add("Sort", querySort);
                        formData.add("ResultsPerPage", resultsPerPage.toString());
                        formData.add("PageNum", pageNum.toString());
                        
                        String str = String.format("/resource/%ss/v2/%s", type.getResouceName(), realmId); 
                        SearchResults response = gateway.path(str)
                            .type(MediaType.APPLICATION_FORM_URLENCODED)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .post(SearchResults.class, formData);
                        
                        return response;
                    }
                    catch (final UniformInterfaceException e)
                    {
                        final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
                        throw new QuickBooksException(fault);
                    }
                }
                
                @Override
                protected boolean hasNextPage(SearchResults page)
                {
                    return page.getCount() > page.getCurrentPage();
                }

                @Override
                @SuppressWarnings("unchecked")
                protected Iterator<T> pageIterator(SearchResults page)
                {
                    try
                    {
                        return ((List<T>) PropertyUtils.getProperty(page.getCdmCollections(), type.getSimpleName())).iterator();
                    }
                    catch (IllegalAccessException e)
                    {
                            throw new NotImplementedException(e);
                    }
                    catch (InvocationTargetException e)
                    {
                            throw new NotImplementedException(e);
                    }
                    catch (NoSuchMethodException e)
                    {
                            throw new NotImplementedException(e);
                    }
                }
            };
    }
    
    private String getBaseURI()
    {
        String str = String.format("https://qbo.intuit.com/qbo30/rest/user/v2/%s", realmId);
        WebResource aux = Client.create().resource(str);
        aux.addFilter(oauthFilter);
        try
        {
            QboUser response = aux.header("Content-Type", "application/xml")
                .get(QboUser.class);
            
            return response.getCurrentCompany().getBaseURI();
        }
        catch (final UniformInterfaceException e)
        {
            final FaultInfo fault = e.getResponse().getEntity(FaultInfo.class);
            throw new QuickBooksException(fault);
        }
    }
    
    public void setResultsPerPage(Integer resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

}
