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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.quickbooks.AccountDetail;
import org.mule.modules.quickbooks.EntityType;
import org.mule.modules.quickbooks.QuickBooksModule;
import org.mule.modules.quickbooks.schema.Customer;


/**
 * TODO: Description of the class, Comments in english by default
 * 
 * @author Gaston Ponti
 * @since Sep 9, 2011
 */

public class QuickBooksModuleTestDriver
{

    private QuickBooksModule module;
    private String accessToken, accessTokenSecret;

    /**
     * 
     */
    @Before
    public void setup()
    {
        module = new QuickBooksModule();
        module.setConsumerKey("qyprdjjAVqbMjp3iQHH2SutYSm2min");
        module.setConsumerSecret("6I5mTDGjthIeIWJm6iKx7Q4jAihKFd1yvFvkMi3B");
        module.setRealmId("212879793");
        module.init();
        
        accessToken = "lvprdUOzPD8jlLdCSgKGYubbNAwFh03PUHM34gWvXPYoPdgJ";
        accessTokenSecret = "B5zGyujNpe3dTwL4hHY5Cr0x1CRXqgukiAex9Aab";

    }

    @Test
    @Ignore
    public void createAccountAnswersNonNullAccountWithId() throws Exception
    {
        module.createAccount(accessToken, accessTokenSecret, "TestAccount9876", null, AccountDetail.SAVINGS, null, null,
            null, null);
    }
    
    @SuppressWarnings("serial")
    @Test
    @Ignore
    public void createCustomerAnswersNonNullCustomerWithId() throws Exception
    {
        Customer c = module.createCustomer(accessToken, 
            accessTokenSecret, 
            "Maria5", 
            "Susana", 
            "Melina", 
            "Perez",
            null, null, null, 
            new ArrayList<Map<String, Object>>(), 
            null, null, new ArrayList<String>(),
            new ArrayList<Map<String, Object>>(),
            /*Arrays.<Map<String, Object>>asList(new HashMap<String, Object>()
                {
                    {
                        put("Line1", null);
                        put("Line2", null);
                        put("City", null);
                        put("CountrySubDivisionCode", null);
                        put("PostalCode", null);
                        put("Tag", "Billing");
                    } 
               }
            )*/new ArrayList<Map<String, Object>>()
        );
        
        assertEquals("Maria5", c.getName());
        assertNotNull(c.getId());

        Map<String, Object> idType = new HashMap<String, Object>();
        idType.put("value", c.getId().getValue());
        //idType.put("idDomain", c.getId().getIdDomain());
        module.deleteObject(accessToken, accessTokenSecret, EntityType.CUSTOMER, idType, c.getSyncToken());
    }
    
    @Test
    @Ignore
    public void getCustomerAnswersNonNullCustomerWithId() throws Exception
    {
        Map<String, Object> idType = new HashMap<String, Object>();
        idType.put("value", "1");
        //idType.put("idDomain", c.getId().getIdDomain());
        Customer c = (Customer) module.getObject(accessToken, accessTokenSecret, EntityType.CUSTOMER, idType);
        
        assertEquals("Ricardo", c.getName());
        assertNotNull(c.getId());
    }
    
    @Test
    public void getAllCustomersAnswersNonNullListWithCustomers() throws Exception
    {
        Iterable it = module.findObjects(accessToken, accessTokenSecret, EntityType.CUSTOMER, null, null);
        
        for (Object c : it)
        {
            System.out.println(((Customer) c).getName());
        }
    }
    
    
}
