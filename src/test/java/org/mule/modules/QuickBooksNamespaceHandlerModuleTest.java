/**
 * Mule QuickBooks Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.modules;

import org.junit.Test;
import org.mule.api.processor.MessageProcessor;
import org.mule.tck.AbstractMuleTestCase;
import org.mule.tck.FunctionalTestCase;

public class QuickBooksNamespaceHandlerModuleTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }

    @Test
    public void testFlow() throws Exception
    {
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
