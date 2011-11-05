/**
 * Mule QuickBooks Connector
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
    /**Creation and Retrieve: this sample will be focused on showing basic CRUD features of
QuickBooks. It will integrate Mongo connector - entities created in QuickBooks will be imported
from a Mongo Collection, and with Scripting component - retrieved entities will be shown to the
user formatting it with a Groovy script.
*/

    public void testCreateAccount() throws Exception
    {
        System.out.println(lookupFlowConstruct("CreateCustomers").process(getTestEvent("")).getMessage().getPayload());
    }

    private Flow lookupFlowConstruct(final String name)
    {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }

}
