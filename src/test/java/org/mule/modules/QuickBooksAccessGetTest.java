
	/*
	 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
	 */

package org.mule.modules;

import org.junit.Test;
import org.mule.api.MuleContext;
import org.mule.construct.SimpleFlowConstruct;
import org.mule.modules.quickbooks.config.QuickBooksModuleOAuthAdapter;
import org.mule.tck.AbstractMuleTestCase;
import org.mule.tck.FunctionalTestCase;
import org.mule.transport.http.HttpConnector;


/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 6, 2011
 */

public class QuickBooksAccessGetTest extends FunctionalTestCase
{
    @Override
    protected MuleContext createMuleContext() throws Exception
    {
         MuleContext muleContext = super.createMuleContext();
         muleContext.getRegistry().registerObject("connector.http.mule.default", new HttpConnector(muleContext));
        return muleContext;
    }
    
    @Override
    protected void doSetUp() throws Exception 
    {
//        MockitoAnnotations.initMocks(this);
//        LinkedInClientFactory.setDefaultClient(mockClient);

        QuickBooksModuleOAuthAdapter moduleObject = muleContext.getRegistry().lookupObject(QuickBooksModuleOAuthAdapter.class);
//        moduleObject.fetchAccessToken();
        System.out.println(moduleObject.getAuthorizationUrl());
        System.out.println(moduleObject.getRedirectUrl());
        System.out.println(moduleObject.getOauthVerifier());
        System.out.println(moduleObject.getRealmId());
        System.out.println(moduleObject.getConsumerKey());
        System.out.println(moduleObject.getConsumerSecret());
        System.out.println(moduleObject.getAccessToken());
        System.out.println(moduleObject.getAccessTokenSecret());
//        moduleObject.setAccessToken(null);
//        moduleObject.setAccessTokenSecret(null);
//        moduleObject.setOauthVerifier(null);

//        profileFields = new HashSet<ProfileField>(2);
//        profileFields.add(ProfileField.LAST_NAME);
//        profileFields.add(ProfileField.HONORS);
//
//        networkUpdateTypes = new HashSet<NetworkUpdateType>(2);
//        networkUpdateTypes.add(NetworkUpdateType.PROFILE_UPDATE);
//        networkUpdateTypes.add(NetworkUpdateType.RECOMMENDATION_UPDATE);
//
//        searchParameters = new HashMap<SearchParameter, String>(2);
//        searchParameters.put(SearchParameter.CURRENT_COMPANY, "MuleSoft");
//        searchParameters.put(SearchParameter.TITLE, "Engineer");
//
//        facets = new ArrayList<Parameter<FacetType, String>>(2);
//        facets.add(new Parameter<FacetType, String>(FacetType.INDUSTRY, "Software"));
//        facets.add(new Parameter<FacetType, String>(FacetType.PAST_COMPANY, "MuleSource"));
//
//        facetFields = new HashSet<FacetField>(2);
//        facetFields.add(FacetField.BUCKET_NAME);
//        facetFields.add(FacetField.BUCKET_CODE);
    }
    
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }

    @Test
    public void testFlow() throws Exception
    {
        lookupFlowConstruct("TestForTheAccessPower").process(getTestEvent(""));
        QuickBooksModuleOAuthAdapter moduleObject = muleContext.getRegistry().lookupObject(QuickBooksModuleOAuthAdapter.class);
System.out.println("-----------");
System.out.println(moduleObject.getAuthorizationUrl());
      System.out.println(moduleObject.getRedirectUrl());
      System.out.println(moduleObject.getOauthVerifier());
      System.out.println(moduleObject.getRealmId());
      System.out.println(moduleObject.getConsumerKey());
      System.out.println(moduleObject.getConsumerSecret());
      System.out.println(moduleObject.getAccessToken());
      System.out.println(moduleObject.getAccessTokenSecret());
    }

    /**
     * Retrieve a flow by name from the registry
     *
     * @param name Name of the flow to retrieve
     */
    protected SimpleFlowConstruct lookupFlowConstruct(String name)
    {
        return (SimpleFlowConstruct) AbstractMuleTestCase.muleContext.getRegistry().lookupFlowConstruct(name);
    }
}
