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
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.quickbooks.AccountDetail;
import org.mule.modules.quickbooks.EntityType;
import org.mule.modules.quickbooks.QuickBooksModule;
import org.mule.modules.quickbooks.api.MapBuilder;
import org.mule.modules.quickbooks.schema.Customer;
import org.mule.modules.quickbooks.schema.PhysicalAddress;
import org.mule.modules.quickbooks.schema.SalesTerm;

import ar.com.zauber.commons.mom.CXFStyle;
import ar.com.zauber.commons.mom.MapObjectMapper;


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
    
    @SuppressWarnings("unchecked")
    @Test
    public void createCustomerAnswersNonNullCustomerWithId() throws Exception
    {
        List<Map<String, Object>> auxList = new ArrayList<Map<String, Object>>();
        
        Map<String, Object> auxMap = new HashMap<String, Object>();
        
        auxMap.put("Line1", null);
        auxMap.put("Line2", null);
        auxMap.put("City", null);
        auxMap.put("CountrySubDivisionCode", null);
        auxMap.put("PostalCode", null);
        auxMap.put("Tag", "Billing");
        
        auxList.add(auxMap);

        Customer c = module.createCustomer(accessToken, 
            accessTokenSecret, 
            "Susana", 
            "Susana", 
            "Melina", 
            "Perez",
            null, null, null, 
            new ArrayList<Map<String, Object>>(), 
            null, null, new ArrayList<String>(),
            new ArrayList<Map<String, Object>>(),
            new ArrayList<Map<String, Object>>()
//            auxList
//            Arrays.<Map<String, Object>>asList(new HashMap<String, Object>()
//                {
//                    {
//                        put("Line1", null);
//                        put("Line2", null);
//                        put("City", null);
//                        put("CountrySubDivisionCode", null);
//                        put("PostalCode", null);
//                        put("Tag", "Billing");
//                    } 
//               }
//            )
        );
        
        assertEquals("Susana", c.getName());
        assertNotNull(c.getId());

        Map<String, Object> idType = new HashMap<String, Object>();
        idType.put("value", c.getId().getValue());
        //idType.put("idDomain", c.getId().getIdDomain());
        module.deleteObject(accessToken, accessTokenSecret, EntityType.CUSTOMER, idType, c.getSyncToken());
    }
    
    @Test
    @Ignore
    public void testname() throws Exception
    {
        
     List<Map<String, Object>> auxList = new ArrayList<Map<String, Object>>();
        
        Map<String, Object> auxMap = new HashMap<String, Object>();
        
        auxMap.put("Line1", null);
        auxMap.put("Line2", null);
        auxMap.put("City", null);
        auxMap.put("CountrySubDivisionCode", null);
        auxMap.put("PostalCode", null);
        auxMap.put("Tag", "Billing");
        
        auxList.add(auxMap);

        MapObjectMapper mom = new MapObjectMapper("org.mule.modules.quickbooks.schema");
        mom.setPropertyStyle(CXFStyle.STYLE);
        Customer c = mom.fromMap(Customer.class,
                new MapBuilder()
                .with("name", "Susana")
                .with("givenName", "Susana")
                .with("middleName", "Melina")
                .with("familyName", "Perez")
                .with("suffix", null)
                .with("DBAName", null)
                .with("showAs", null)
                .with("webSite", new ArrayList<Map<String, Object>>())
                .with("salesTermId", null)
                .with("salesTaxCodeId", null)
                .with("email", new ArrayList<String>())
                .with("phone", new ArrayList<Map<String, Object>>())
                .with("address", auxList)
                .build()
                );
        
        PhysicalAddress ph = c.getAddress().get(0);
        
        assertEquals(ph.getTag(), "Billing");
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
    @Ignore
    public void modifyCustomer()
    {
        Customer c1 = module.createCustomer(accessToken, 
            accessTokenSecret,
            "Paul", 
            "Paul", 
            null, 
            "Jhonson",
            null, null, null, 
            new ArrayList<Map<String, Object>>(), 
            null, null, new ArrayList<String>(),
            new ArrayList<Map<String, Object>>(),
            new ArrayList<Map<String, Object>>()
        );
        
        assertEquals("Jhonson", c1.getFamilyName());
        
        Map<String, Object> idType = new HashMap<String, Object>();
        idType.put("value", c1.getId().getValue());
        
        Customer c2 = module.updateCustomer(accessToken, 
            accessTokenSecret, 
            idType, 
            c1.getSyncToken(), 
            c1.getName(), 
            c1.getGivenName(), 
            c1.getMiddleName(), 
            "Smith", 
            null, null, null, 
            new ArrayList<Map<String, Object>>(), 
            null, null, new ArrayList<String>(),
            new ArrayList<Map<String, Object>>(),
            new ArrayList<Map<String, Object>>()
        );
        
        Customer c3 = (Customer) module.getObject(accessToken, accessTokenSecret, EntityType.CUSTOMER, idType);
        
        assertEquals("Smith", c3.getFamilyName());
        
        module.deleteObject(accessToken, accessTokenSecret, EntityType.CUSTOMER, idType, null);
    }
    
    @Test
    @Ignore
    public void getAllCustomersAnswersNonNullListWithCustomers() throws Exception
    {
        Iterable it = module.findObjects(accessToken, accessTokenSecret, EntityType.CUSTOMER, null, null);
        
        for (Object c : it)
        {
            System.out.println(((Customer) c).getName());
        }
    }
    
    @Test
    public void createSalesTermAnswersNonNullSalesTermWithId()
    {
        SalesTerm salesTerm = module.createSalesTerm(accessToken,
            accessTokenSecret, 
            "SalesTerm1",
            3,
            null,
            null,
            2,
            null,
            null,
            null);
        
        assertEquals("SalesTerm1", salesTerm.getName());
        Map<String, Object> idType = new HashMap<String, Object>();
        idType.put("value", salesTerm.getId().getValue());
        
        module.deleteObject(accessToken, accessTokenSecret, EntityType.SALESTERM, idType, null);
    }
    
}
