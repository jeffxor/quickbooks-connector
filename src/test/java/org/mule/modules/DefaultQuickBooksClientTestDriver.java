/*
 * Copyright (c) 2011 Zauber S.A.  -- All rights reserved
 */

package org.mule.modules;

import org.junit.Test;
import org.mule.modules.quickbooks.api.DefaultQuickBooksClient;
import org.mule.modules.quickbooks.api.QuickBooksClient;
import org.mule.tck.FunctionalTestCase;
/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 1, 2011
 */

public class DefaultQuickBooksClientTestDriver extends FunctionalTestCase
{    
    @Test
    public final void newClientWithWrongAccessKey()
    {
        //this.realmId = "212879793";
        //# App Token "94f212d5b56f0b413bba40fb476b4e76cf96"
        //# OAuth Consumer Key "qyprdjjAVqbMjp3iQHH2SutYSm2min"
        //# OAuth Consumer Secret "6I5mTDGjthIeIWJm6iKx7Q4jAihKFd1yvFvkMi3B"
        QuickBooksClient client = new DefaultQuickBooksClient(System.getenv("realmId"),
            System.getenv("consumerKey"), System.getenv("consumerSecret"));
        
        
    }

    /** @see org.mule.tck.FunctionalTestCase#getConfigResources() */
    @Override
    protected String getConfigResources()
    {
        return "";
    }
}
