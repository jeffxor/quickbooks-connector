/**
 * Mule QuickBooks Connector
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.quickbooks.api.MapBuilder;
import org.mule.modules.quickbooks.schema.Customer;
import org.mule.modules.quickbooks.schema.ObjectFactory;
import org.mule.modules.quickbooks.utils.ObjectFactories;
import org.mule.modules.utils.mom.CxfMapObjectMappers;

import com.zauberlabs.commons.mom.MapObjectMapper;


/**
 *   
 * 
 * 
 * @author Gaston Ponti
 * @since Sep 9, 2011
 */

public class ObjectFactoriesTest
{
    private ObjectFactory objectFactory;
    private MapObjectMapper mom;
    /**
     * 
     */
    @Before
    public void setup()
    {
        objectFactory = new ObjectFactory();
        mom = CxfMapObjectMappers.defaultWithPackage("org.mule.modules.quickbooks.schema").build();
    }

    @Test
    public void createTestJaxbElement() throws Exception
    {
        List<Map<String, Object>> webSite = new ArrayList<Map<String, Object>>();
        List<String> email =  new ArrayList<String>();
        List<Map<String, Object>> phone = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> address = Arrays.<Map<String, Object>>asList(
            new HashMap<String, Object>()
                {
                    {
                        put("line1", null);
                        put("line2", null);
                        put("city", null);
                        put("countrySubDivisionCode", null);
                        put("postalCode", null);
                    } 
               }
            );
        
        Customer customer = (Customer) mom.unmap(
            new MapBuilder()
            .with("name", "Ricardo")
            .with("givenName", "Enrique")
            .with("middleName", "Gerardo")
            .with("familyName", "Perez")
            .with("suffix", null)
            .with("DBAName", null)
            .with("showAs", null)
            .with("webSite", webSite)
            .with("salesTermId", null)
            .with("salesTaxCodeId", null)
            .with("email", email)
            .with("phone", phone)
            .with("address", address)
            .build(), Customer.class
        );
        
        JAXBElement<Customer> jaxbCustomer = ObjectFactories.createJaxbElement(customer, objectFactory);
        
        assertEquals("Ricardo", jaxbCustomer.getValue().getName());
    }
}

