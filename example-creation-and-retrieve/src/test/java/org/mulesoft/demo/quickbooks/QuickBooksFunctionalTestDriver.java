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

import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
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
passed by the user, evaluated, and text notification will be sent for returned entities that match
some condition, using Twilio Connector.
*/

    public void testCreateAccount() throws Exception
    {
        MuleEvent testEvent = getTestEvent("");
        MuleMessage message = testEvent.getMessage();
        System.out.println(lookupFlowConstruct("CreateAccount").process(testEvent).getMessage().getPayload());
    }

    private Flow lookupFlowConstruct(final String name)
    {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }

}
