/**
 * Mule FWS Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mulesoft.demo.quickbooks;

import org.mule.construct.Flow;
import org.mule.tck.FunctionalTestCase;

public class QuickBooksFunctionalTestDriver extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }
    /**Search: this example will show searching capabilities of QuickBooks. Arbitrary queries will be
     *passed by the user, evaluated, and text notification will be sent for returned entities that match
     *some condition, using Twilio Connector.
     */

    public void testSearchCustomerById() throws Exception
    {
        System.out.println(lookupFlowConstruct("GetProfileByUrl").process(getTestEvent("")).getMessage().getPayload());
    }

    public void testSearchListOfCustomers() throws Exception
    {
//      filter-> "CreateTime :BEFORE: 2011-08-07T14:30:00PST"
//      sort-> "FamilyName AToZ"
        System.out.println(lookupFlowConstruct("SearchListOfCustomers").process(getTestEvent("")).getMessage().getPayload());
    }
    
    public void testGetAllCustomers() throws Exception
    {
        System.out.println(lookupFlowConstruct("GetAllCustomers").process(getTestEvent("")).getMessage().getPayload());
    }
    private Flow lookupFlowConstruct(final String name)
    {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }

}
