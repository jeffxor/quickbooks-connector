/**
 * Mule FWS Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


	/*
	 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
	 */

package org.mule.modules;

import org.junit.Test;
import org.mule.api.MuleContext;
import org.mule.api.processor.MessageProcessor;
import org.mule.modules.quickbooks.config.QuickBooksModuleOAuth1Adapter;
import org.mule.tck.AbstractMuleTestCase;
import org.mule.tck.FunctionalTestCase;
import org.mule.transport.http.HttpConnector;


/**
 *   
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

        QuickBooksModuleOAuth1Adapter moduleObject = muleContext.getRegistry().lookupObject(QuickBooksModuleOAuth1Adapter.class);
        moduleObject.setAccessToken("lvprdUOzPD8jlLdCSgKGYubbNAwFh03PUHM34gWvXPYoPdgJ");
        moduleObject.setAccessTokenSecret("B5zGyujNpe3dTwL4hHY5Cr0x1CRXqgukiAex9Aab");
        moduleObject.setOauthVerifier("");

//        profileFields = new HashSet<ProfileField>(2);
//        profileFields.add(ProfileField.LAST_NAME);
//        profileFields.add(ProfileField.HONORS);
//
//        networkUpdateTypes = new HashSet<NetworkUpdateType>(2);
//        networkUpdateTypes.add(NetworkUpdateType.PROFILE_UPDATE);1
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
        QuickBooksModuleOAuth1Adapter moduleObject = muleContext.getRegistry().lookupObject(QuickBooksModuleOAuth1Adapter.class);
    }

    /**
     * Retrieve a flow by name from the registry
     *
     * @param name Name of the flow to retrieve
     */
    protected MessageProcessor lookupFlowConstruct(String name)
    {
        return (MessageProcessor) AbstractMuleTestCase.muleContext.getRegistry().lookupFlowConstruct(name);
    }
}
